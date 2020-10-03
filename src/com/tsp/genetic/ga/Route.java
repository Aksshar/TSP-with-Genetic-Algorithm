package com.tsp.genetic.ga;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Route {
	private boolean isFitnessChanged = true;
	private double fitness = 0;
	
	private ArrayList<City> cities = new ArrayList<City>();
	private City depot = new City("depot",6.8741,79.8505);
	
	public Route(GeneticAlgorithm geneticAlgorithm) {
		geneticAlgorithm.getInitialRoute().forEach(x -> cities.add(null));
	}
	
	public Route(ArrayList<City> cities) {
		this.cities.addAll(cities);
		//this.cities.add(this.depot);
//		int in = 0;
		//
		Collections.shuffle(this.cities.subList(1,this.cities.size() - 1));
//		for(int i=0;i<this.cities.size();i++) {
//			if(this.cities.get(i).getLatitude() == this.depot.getLatitude() && this.cities.get(i).getLongitude() == this.depot.getLongitude())
//				in = i;
//		}
//		int indexOfdepot = this.cities.indexOf(this.depot);
		
//		this.cities.set(in, this.cities.get(0));
//		this.cities.set(0, this.depot);
	}
	public ArrayList<City> getCities(){
		isFitnessChanged = true;
		return cities;
	}
	public double getFitness() {
		if(isFitnessChanged == true) {
			fitness  = (1/calculateTotalDistance())*10000;
			isFitnessChanged = false;
		}
		return fitness;
	}
	
	public double calculateTotalDistance() {
		int citiesSize = this.cities.size();
		return (int) (this.cities.stream().mapToDouble(x -> {
			int cityIndex = this.cities.indexOf(x);
			double returnValue = 0;
			if(cityIndex < citiesSize - 1) returnValue = x.measureDistance(this.cities.get(cityIndex + 1));
			return returnValue;
		}).sum() + this.cities.get(0).measureDistance(this.cities.get(citiesSize - 1)));
	}
	
	public String toString() { return Arrays.toString(cities.toArray());}
}
