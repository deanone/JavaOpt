package com.asal.javaopt;

/**
 * @author A. Salamanis
 * @version 0.2
 * @since 2020-04-25
 *
 * OptimizerRunner class: Dummy class for just launching an application that runs the optimizers.
 */
public class OptimizerRunner {
    /**
     * Starting point of the application.
     */
	public static void main(String[] args) {
		String propertiesFilename = "\\pso.properties";
		ParticleSwarmOptimization pso = new ParticleSwarmOptimization(propertiesFilename);
		pso.run();
		pso.printSolution();
		
		System.out.println();
		
    	propertiesFilename = "\\de.properties";
        DifferentialEvolution differentialEvolutionMethod = new DifferentialEvolution(propertiesFilename);
        differentialEvolutionMethod.run();
        differentialEvolutionMethod.printSolution();
		
		System.exit(0); //TODO: Check if I need this line
	}
}