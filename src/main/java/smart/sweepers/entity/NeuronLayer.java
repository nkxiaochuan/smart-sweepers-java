/**
 * 
 */
package smart.sweepers.entity;

import java.util.List;

/**
 * @author Andy
 *
 */
public class NeuronLayer {

	private int numNeurons;
	private List<Neuron> listNeurons;
	
	public NeuronLayer(int numNeurons, int numInputsPerNeuron){
		this.numNeurons = numNeurons;
		for (int i = 0; i < numNeurons; i++){
			listNeurons.add(new Neuron(numInputsPerNeuron));
		}
	}

	public int getNumNeurons() {
		return numNeurons;
	}

	public void setNumNeurons(int numNeurons) {
		this.numNeurons = numNeurons;
	}

	public List<Neuron> getListNeurons() {
		return listNeurons;
	}

	public void setListNeurons(List<Neuron> listNeurons) {
		this.listNeurons = listNeurons;
	}
}
