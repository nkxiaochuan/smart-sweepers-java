package smart.sweepers.entity;

import java.util.ArrayList;
import java.util.List;

import smart.sweepers.Contents;
import smart.sweepers.util.Util;

public class GenAlg {

	//this holds the entire population of chromosomes
	private List<Genome> listPopulation;
	private int popSize;
	//amount of weights per chromo
	private int chromoLength;
	//total fitness of population
	private double totalFitness;
	
	private double bestFitness;
	
	private double averageFitness;
	
	private double worstFitness;
	
	private int fittnestGenome;
	//probability that a chromosones bits will mutate.
	//Try figures around 0.05 to 0.3 ish
	private double mutationRate;
	
	private double crossoverRate;
	
	private int generation;
	
	public GenAlg(int popSize, double muteRate, double crossRate, int numWeights){
		this.popSize = popSize;
		this.mutationRate = muteRate;
		this.crossoverRate = crossRate;
		this.chromoLength = numWeights;
		this.totalFitness = 0;
		this.generation = 0;
		this.fittnestGenome = 0;
		this.bestFitness = 0;
		this.worstFitness = 99999999;
		this.averageFitness = 0;
		listPopulation = new ArrayList<>();
		for (int i = 0; i < popSize;i++){
			listPopulation.add(new Genome());
			for (int j = 0; j < chromoLength; j++){
				listPopulation.get(i).getListWeight().add(Util.randomClamped());
			}
		}
	}
	
	public List<Genome> epoch(List<Genome> oldPop){
		return null;
	}
	
	public double averageFitness(){
		return this.totalFitness / this.popSize;
	}
	
	private void crossover(List<Double> mum, List<Double> dad, List<Double> baby1, List<Double> baby2){
		
	}
	
	private void mutate(List<Double> chromo){
		for (int i = 0; i < chromo.size(); i++){
			if (Util.randFloat() < mutationRate){
				chromo.set(i, Util.randomClamped() * Contents.dMaxPerturbation);
			}
		}
	}
	
	private Genome getChromoRoulette(){
		//generate a random number between 0 & total fitness count
		double Slice = (double)(Util.randFloat() * totalFitness);
		Genome theChosenOne = new Genome();
		double fitnessSoFar = 0;
		for (int i = 0; i < popSize; i++){
			fitnessSoFar += listPopulation.get(i).getFitness();
			//if the fitness so far > random number return the chromo at 
			//this point
			if (fitnessSoFar >= Slice){
				theChosenOne = listPopulation.get(i);
				break;
			}
		}
		return theChosenOne;
	}
	
	private void grabNBest(int nBest, int numCopyies, List<Genome> listPopulation){
		
	}
	
	private void calculateBestWorstAvTot(){
		
	}
	
	private void reset(){
		
	}
	
	

	public List<Genome> getListPopulation() {
		return listPopulation;
	}

	public void setListPopulation(List<Genome> listPopulation) {
		this.listPopulation = listPopulation;
	}

	public int getPopSize() {
		return popSize;
	}

	public void setPopSize(int popSize) {
		this.popSize = popSize;
	}

	public int getChromoLength() {
		return chromoLength;
	}

	public void setChromoLength(int chromoLength) {
		this.chromoLength = chromoLength;
	}

	public double getTotalFitness() {
		return totalFitness;
	}

	public void setTotalFitness(double totalFitness) {
		this.totalFitness = totalFitness;
	}

	public double getBestFitness() {
		return bestFitness;
	}

	public void setBestFitness(double bestFitness) {
		this.bestFitness = bestFitness;
	}

	public double getAverageFitness() {
		return averageFitness;
	}

	public void setAverageFitness(double averageFitness) {
		this.averageFitness = averageFitness;
	}

	public double getWorstFitness() {
		return worstFitness;
	}

	public void setWorstFitness(double worstFitness) {
		this.worstFitness = worstFitness;
	}

	public int getFittnestGenome() {
		return fittnestGenome;
	}

	public void setFittnestGenome(int fittnestGenome) {
		this.fittnestGenome = fittnestGenome;
	}

	public double getMutationRate() {
		return mutationRate;
	}

	public void setMutationRate(double mutationRate) {
		this.mutationRate = mutationRate;
	}

	public double getCrossoverRate() {
		return crossoverRate;
	}

	public void setCrossoverRate(double crossoverRate) {
		this.crossoverRate = crossoverRate;
	}

	public int getGeneration() {
		return generation;
	}

	public void setGeneration(int generation) {
		this.generation = generation;
	}
}
