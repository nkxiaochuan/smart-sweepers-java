/**
 * 
 */
package smart.sweepers.entity;

import java.util.List;

import smart.sweepers.util.Util;

/**
 * @author Andy
 *
 */
public class Neuron {

	private int numInputs;
	private List<Double> listWeight;
	
	public Neuron (int numInputs){
		this.numInputs = numInputs + 1;
		//we need an additional weight for the bias hence the +1
		for (int i = 0; i < numInputs + 1; i++){
			listWeight.add(Util.randomClamped());
		}
	}

	public int getNumInputs() {
		return numInputs;
	}

	public void setNumInputs(int numInputs) {
		this.numInputs = numInputs;
	}

	public List<Double> getListWeight() {
		return listWeight;
	}

	public void setListWeight(List<Double> listWeight) {
		this.listWeight = listWeight;
	}
}
