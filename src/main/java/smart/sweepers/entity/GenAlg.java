package smart.sweepers.entity;

import java.util.ArrayList;
import java.util.Comparator;
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
	
	@SuppressWarnings("unused")
	public List<Genome> epoch(List<Genome> oldPop){
		listPopulation = oldPop;
		reset();
		listPopulation.sort(null);
		calculateBestWorstAvTot();
		
		List<Genome> newPop = new ArrayList<>();
		//Now to add a little elitism we shall add in some copies of the
		//fittest genomes. Make sure we add an EVEN number or the roulette
		//wheel sampling will crash
		if (Contents.iNumCopiesElite * Contents.iNumElite % 2 != 0) {
			grabNBest(Contents.iNumElite, Contents.iNumCopiesElite, newPop);
		}
		
		while(newPop.size() < popSize){
			Genome mum = getChromoRoulette();
			Genome dad = getChromoRoulette();
			
			List<Double> baby1 = new ArrayList<>();
			List<Double> baby2 = new ArrayList<>();
			crossover(mum.getListWeight(), dad.getListWeight(), baby1, baby2);
			mutate(baby1);
			mutate(baby2);
			newPop.add(new Genome(baby1, 0));
			newPop.add(new Genome(baby2, 0));
		}
		listPopulation = newPop;
		return listPopulation;
	}
	
	public double averageFitness(){
		return this.totalFitness / this.popSize;
	}
	
	private void crossover(List<Double> mum, List<Double> dad, List<Double> baby1, List<Double> baby2){
		if ((Util.randFloat() > crossoverRate) || (mum == dad)){
			baby1 = mum;
			baby2 = dad;
			return;
		}
		int cp = Util.randomInt(0, chromoLength - 1);
		
		for (int i = 0; i < cp; i++){
			baby1.add(mum.get(i));
			baby2.add(dad.get(i));
		}
		
		for (int i = cp; i <mum.size(); i++){
			baby1.add(dad.get(i));
			baby2.add(mum.get(i));
		}
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
		for (int i = nBest; i > 0; i--){
			for (int j = 0; j < numCopyies; j++){
				listPopulation.add(this.listPopulation.get(popSize - 1 - i));
			}
		}
	}
	
	private void calculateBestWorstAvTot(){
		totalFitness = 0;
		double highestSoFar = 0;
		double lowestSoFar = 9999999;
		
		for (int i = 0; i < popSize; i++){
			if (listPopulation.get(i).getFitness() > highestSoFar){
				highestSoFar = listPopulation.get(i).getFitness();
				fittnestGenome = i;
				bestFitness = highestSoFar;
			}
			
			if (listPopulation.get(i).getFitness() < lowestSoFar){
				lowestSoFar = listPopulation.get(i).getFitness();
				worstFitness = lowestSoFar;
			}
			totalFitness += listPopulation.get(i).getFitness();
		}
		averageFitness = totalFitness / popSize;
	}
	
	private void reset(){
		totalFitness = 0;
		bestFitness = 0;
		worstFitness = 9999999;
		averageFitness = 0;
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
