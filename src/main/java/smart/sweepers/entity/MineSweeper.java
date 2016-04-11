/**
 * 
 */
package smart.sweepers.entity;

import java.util.List;

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
		return true;
	}
	
	public void worldTransform(List<Point> sweeper){
		
	}
	
	public Point2D getClosestMine(List<Point2D> mines){
		return null;
	}
	
	//checks to see if the minesweeper has 'collected' a mine
	public int checkForMine(List<Point2D> mines, double size){
		return 0;
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
