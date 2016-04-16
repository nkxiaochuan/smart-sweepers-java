/**
 * 
 */
package smart.sweepers.entity;

import java.util.ArrayList;
import java.util.List;

import javax.swing.text.Utilities;

import smart.sweepers.Contents;
import smart.sweepers.util.Util;

/**
 * @author Andy
 *
 */
public class MineSweeper {

	private NeuralNet itsBrian;
	private Point2D itsPosition;
	private Point2D lookAt;
	private double rotation;
	private double speed;
	private double lTrack;
	private double rTrack;
	private double fitness;
	private double scale;
	private int closestMine;
	
	public MineSweeper(){
		this.rotation = Util.randFloat() * Contents.TwoPi;
		this.lTrack = 0.16;
		this.rTrack = 0.16;
		this.fitness = 0;
		this.scale = Contents.iSweeperScale;
		this.closestMine = 0;
		
		this.itsPosition = new Point2D(Util.randFloat() * Contents.WindowWidth, Util.randFloat() * Contents.WindowHeight);
	}
	
	public boolean update(List<Point2D> mines){
		List<Double> inputs = new ArrayList<>();
		Point2D pClosestMine = getClosestMine(mines);
		pClosestMine.point2DNormalize();
		inputs.add(pClosestMine.getX());
		inputs.add(pClosestMine.getY());
		inputs.add(lookAt.getX());
		inputs.add(lookAt.getY());
		List<Double> output = itsBrian.update(inputs);
		if (output.size() < Contents.iNumInputs){
			return false;
		}
		
		lTrack = output.get(0);
		rTrack = output.get(1);
		double rotForce = lTrack - rTrack;
		rotForce = Util.clamp(rotForce, -Contents.dMaxTurnRate, Contents.dMaxTurnRate);
		rotation += rotForce;
		speed = lTrack + rTrack;
		lookAt.setX(-Math.sin(rotation));
		lookAt.setY(Math.cos(rotation));
		itsPosition.setX(itsPosition.getX() + lookAt.getX() * speed);
		itsPosition.setY(itsPosition.getY() + lookAt.getY() * speed);
		if(itsPosition.getX() > Contents.WindowWidth )itsPosition.setX(0);
		if (itsPosition.getX() < 0) itsPosition.setX(Contents.WindowWidth);
		if (itsPosition.getY() > Contents.WindowHeight) itsPosition.setY(0);
		if (itsPosition.getY() < 0) itsPosition.setY(Contents.WindowHeight);
		return true;
	}
	
	public void worldTransform(List<Point> sweeper){
		J2DMatrix matTransform = new J2DMatrix();
		matTransform.scale(scale, scale);
		matTransform.rotate(rotation);
		matTransform.translate(itsPosition.getX(), itsPosition.getY());
		matTransform.transformPoints(sweeper);
	}
	
	public Point2D getClosestMine(List<Point2D> mines){
		double closest_so_far = 9999999;
		Point2D closestOject = new Point2D(0,0);
		for (int i = 0; i < mines.size(); i++){
			double len_to_object = Math.sqrt((mines.get(i).getX() - itsPosition.getX()) * (mines.get(i).getX() - itsPosition.getX()) + (mines.get(i).getY() - itsPosition.getY()) * (mines.get(i).getY() - itsPosition.getY()));
			if (len_to_object < closest_so_far){
				closest_so_far = len_to_object;
				closestOject.setX(itsPosition.getX() - mines.get(i).getX());
				closestOject.setY(itsPosition.getY() - mines.get(i).getY());
				closestMine = i;
			}
		}
		return closestOject;
	}
	
	//checks to see if the minesweeper has 'collected' a mine
	public int checkForMine(List<Point2D> mines, double size){
		Point2D distToObject = new Point2D(itsPosition.getX() - mines.get(closestMine).getX(),
				itsPosition.getY() - mines.get(closestMine).getY());
		if (distToObject.getPoint2DLength() < (size + 5)){
			return closestMine;
		}
		return -1;
	}
	
	public void reset(){
		this.itsPosition = new Point2D(Util.randFloat() * Contents.WindowWidth, Util.randFloat() * Contents.WindowHeight);
		this.fitness = 0;
		this.rotation = Util.randFloat() * Contents.TwoPi;
	}
	
	public NeuralNet getItsBrian() {
		return itsBrian;
	}
	public void setItsBrian(NeuralNet itsBrian) {
		this.itsBrian = itsBrian;
	}
	public Point2D getItsPosition() {
		return itsPosition;
	}
	public void setItsPosition(Point2D itsPosition) {
		this.itsPosition = itsPosition;
	}
	public Point2D getLookAt() {
		return lookAt;
	}
	public void setLookAt(Point2D lookAt) {
		this.lookAt = lookAt;
	}
	public double getRotation() {
		return rotation;
	}
	public void setRotation(double rotation) {
		this.rotation = rotation;
	}
	public double getSpeed() {
		return speed;
	}
	public void setSpeed(double speed) {
		this.speed = speed;
	}
	public double getlTrack() {
		return lTrack;
	}
	public void setlTrack(double lTrack) {
		this.lTrack = lTrack;
	}
	public double getrTrack() {
		return rTrack;
	}
	public void setrTrack(double rTrack) {
		this.rTrack = rTrack;
	}
	public double getFitness() {
		return fitness;
	}
	public void setFitness(double fitness) {
		this.fitness = fitness;
	}
	public double getScale() {
		return scale;
	}
	public void setScale(double scale) {
		this.scale = scale;
	}
	public int getClosestMine() {
		return closestMine;
	}
	public void setClosestMine(int closestMine) {
		this.closestMine = closestMine;
	}
}
