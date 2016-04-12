/**
 * 
 */
package smart.sweepers.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Andy
 *
 */
public class Genome {

	private List<Double> listWeight = new ArrayList<Double>();
	private double fitness;
	
	public Genome(){
		fitness = 0;
	}
	public Genome(List<Double> w, double f){
		this.listWeight = w;
		this.fitness = f;
	}
	public List<Double> getListWeight() {
		return listWeight;
	}
	public void setListWeight(List<Double> listWeight) {
		this.listWeight = listWeight;
	}
	public double getFitness() {
		return fitness;
	}
	public void setFitness(double fitness) {
		this.fitness = fitness;
	}
}
