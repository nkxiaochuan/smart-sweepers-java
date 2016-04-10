/**
 * 
 */
package smart.sweepers.entity;

import java.util.ArrayList;
import java.util.List;

import javax.swing.text.AbstractDocument.Content;

import smart.sweepers.Contents;

/**
 * @author Andy
 *
 */
public class NeuralNet {

	private int numInputs;
	private int numOutputs;
	private int numHiddenLayers;
	private int neuronsPerHiddenlyr;
	private List<NeuronLayer> listLayers;
	
	public NeuralNet(){
		this.numInputs = Contents.iNumInputs;
		this.numOutputs = Contents.iNumOutputs;
		this.numHiddenLayers = Contents.iNumHidden;
		this.neuronsPerHiddenlyr = Contents.inumPerHiddenlyr;
		createNet();
	}
	
	/**
	 * this method builds the ANN. The weights are all initially set to 
	 * random values -1 < w < 1
	 */
	public void createNet(){
		listLayers = new ArrayList<NeuronLayer>();
		if (numHiddenLayers > 0){
			//create first hidden layer
			listLayers.add(new NeuronLayer(neuronsPerHiddenlyr, numInputs));
			for (int i = 0; i < numHiddenLayers - 1; i++){
				listLayers.add(new NeuronLayer(neuronsPerHiddenlyr, neuronsPerHiddenlyr));
			}
		}
		//create output layer
		listLayers.add(new NeuronLayer(numOutputs, numOutputs));
	}
	
	public List<Double> getWeights(){
		List<Double> weights = new ArrayList<Double>();
		for (int i = 0; i < numHiddenLayers + 1; i++ ){
			for (int j = 0; j < listLayers.get(i).getNumNeurons(); j++){
				for (int k = 0; k < listLayers.get(i).getListNeurons().get(j).getNumInputs(); k++){
					weights.add(listLayers.get(i).getListNeurons().get(j).getListWeight().get(k));
				}
			}
		}
		return weights;
	}
	
	public int getNumberOfWeights(){
		int weight = 0;
		
		for (int i = 0; i < numHiddenLayers + 1; i++){
			for (int j = 0; j < listLayers.get(i).getNumNeurons(); j++){
				for (int k = 0; k < listLayers.get(i).getListNeurons().get(j).getNumInputs(); k++){
					weight++;
				}
			}
		}
		return weight;
	}
	
	public void setWeights(List<Double> weights){
		int weightCount = 0;
		for (int i = 0; i < numHiddenLayers + 1; i++){
			for (int j = 0; j < listLayers.get(i).getNumNeurons(); j++){
				for (int k = 0; k < listLayers.get(i).getListNeurons().get(j).getNumInputs(); k++){
					listLayers.get(i).getListNeurons().get(j).getListWeight().set(k, weights.get(weightCount));
				}
			}
		}
	}
	
	public List<Double> update(List<Double> inputs){
		List<Double> outPuts = new ArrayList<>();
		int weightCount = 0;
		if (inputs.size() != numInputs){
			return outPuts;
		}
		for (int i = 0; i < numHiddenLayers + 1; i++){
			if (i > 0){
				inputs = outPuts;		
			}
			outPuts.clear();
			weightCount = 0;
			
			for (int j = 0; j < listLayers.get(i).getNumNeurons(); j++){
				double netInput = 0;
				int numInputs = listLayers.get(i).getListNeurons().get(j).getNumInputs();
				for (int k = 0; k < numInputs; k++){
					netInput += listLayers.get(i).getListNeurons().get(j).getListWeight().get(k) * inputs.get(weightCount ++);
				}
				//add in the bias
				netInput += listLayers.get(i).getListNeurons().get(j).getListWeight().get(numInputs - 1) * Contents.dBias;
				
				outPuts.add(sigmoid(netInput, Contents.dActivationResponse));
				
				weightCount = 0;
			}
		}
		return outPuts;
	}
	
	public double sigmoid(double netinput, double response){
		
		return ( 1 / ( 1 + Math.exp(-netinput / response)));
	}

	public int getNumInputs() {
		return numInputs;
	}

	public void setNumInputs(int numInputs) {
		this.numInputs = numInputs;
	}

	public int getNumOutputs() {
		return numOutputs;
	}

	public void setNumOutputs(int numOutputs) {
		this.numOutputs = numOutputs;
	}

	public int getNumHiddenLayers() {
		return numHiddenLayers;
	}

	public void setNumHiddenLayers(int numHiddenLayers) {
		this.numHiddenLayers = numHiddenLayers;
	}

	public int getNeuronsPerHiddenlyr() {
		return neuronsPerHiddenlyr;
	}

	public void setNeuronsPerHiddenlyr(int neuronsPerHiddenlyr) {
		this.neuronsPerHiddenlyr = neuronsPerHiddenlyr;
	}

	public List<NeuronLayer> getListLayers() {
		return listLayers;
	}

	public void setListLayers(List<NeuronLayer> listLayers) {
		this.listLayers = listLayers;
	}
}
