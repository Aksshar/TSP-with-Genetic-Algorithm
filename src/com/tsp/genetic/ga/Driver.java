package com.tsp.genetic.ga;

import java.util.ArrayList;
import java.util.Arrays;

public class Driver {

	public ArrayList<City> initialRoute = new ArrayList<City>(Arrays.asList(
			new City("Wellawatte", 6.8741, 79.8505),
			new City("Bataramulla", 6.8980, 79.9223),
			new City("Rajagiriya", 6.9094, 79.8943),
			new City("Gampaha", 7.0840, 80.0098),
			new City("Nugegoda", 6.8649, 79.8997),
			new City("Thalawathugoda", 6.8759, 79.9392),
			new City("Kurunagal", 7.4818, 80.3609),
			new City("Panadura", 6.7106, 79.9074),
			new City("Negombo", 7.2008, 79.8737),
			new City("Piliyandala", 6.8018, 79.9227),
			new City("Wellawatte", 6.8741, 79.8505),
			new City("Bataramulla", 6.8980, 79.9223),
			new City("Rajagiriya", 6.9094, 79.8943),
			new City("Gampaha", 7.0840, 80.0098),
			new City("Nugegoda", 6.8649, 79.8997),
			new City("Thalawathugoda", 6.8759, 79.9392),
			new City("Kurunagal", 7.4818, 80.3609),
			new City("Panadura", 6.7106, 79.9074),
			new City("Negombo", 7.2008, 79.8737),
			new City("Piliyandala", 6.8018, 79.9227),
			new City("Wellawatte", 6.8741, 79.8505),
			new City("Bataramulla", 6.8980, 79.9223),
			new City("Rajagiriya", 6.9094, 79.8943),
			new City("Gampaha", 7.0840, 80.0098),
			new City("Nugegoda", 6.8649, 79.8997),
			new City("Thalawathugoda", 6.8759, 79.9392),
			new City("Kurunagal", 7.4818, 80.3609),
			new City("Panadura", 6.7106, 79.9074),
			new City("Negombo", 7.2008, 79.8737),
			new City("Piliyandala", 6.8018, 79.9227)
			
			));
	
	public static void main(String[] args) {
		
		Driver driver = new Driver();
		Population population = new Population(GeneticAlgorithm.POPULATION_SIZE,driver.initialRoute);
		population.sortRoutesByFitness();
		GeneticAlgorithm geneticAlgorithm = new GeneticAlgorithm(driver.initialRoute);
		int generationNumber = 0;
		driver.printHeading(generationNumber++);
		driver.printPopulation(population);
		while(generationNumber < GeneticAlgorithm.NUMB_OF_GENERATIONS) {
			driver.printHeading(generationNumber++);
			population = geneticAlgorithm.evolve(population);
			population.sortRoutesByFitness();
			driver.printPopulation(population);
		}
		System.out.println("");
		System.out.println("Best Route Found so far: " + population.getRoutes().get(0));
		System.out.println("w/ a distance of: "+String.format("%.2f",population.getRoutes().get(0).calculateTotalDistance())+ " miles");
	}
	
	public void printPopulation(Population population) {
		population.getRoutes().forEach(x -> {
			System.out.println(Arrays.toString(x.getCities().toArray()) + " | "+ String.format("%.4f", x.getFitness())+ " | "+String.format("%.2f",x.calculateTotalDistance()));
		});
	}
	
	public void printHeading(int generationNumber) {
		System.out.println("> Generation # "+generationNumber);
		String headingColumn1 = "Route";
		String remainingHeadingColumns = "Fitness  | Distance (in miles)";
		int cityNamesLength = 0;
		for(int x = 0; x< initialRoute.size();x++) cityNamesLength += initialRoute.get(x).getName().length();
		int arrayLength = cityNamesLength + initialRoute.size()*2;
		int partialLength = (arrayLength - headingColumn1.length()/2);
		for(int x = 0; x< partialLength;x++) System.out.print(" ");
		if((arrayLength % 2) == 0)System.out.print(" ");
		System.out.println(" | "+ remainingHeadingColumns);
		cityNamesLength += remainingHeadingColumns.length() + 3;
		for(int x=0; x < cityNamesLength +initialRoute.size()*2;x++)System.out.print("-");
		System.out.println("");
	}

}
