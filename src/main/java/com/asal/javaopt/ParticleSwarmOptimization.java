package com.asal.javaopt;

import java.util.Arrays;
import java.util.Random;

/**
 * @author A. Salamanis
 * @version 0.2
 * @since 2020-04-25
 *
 * PSO class: The class representing the Particle Swarm Optimization (PSO) method.
 */
class ParticleSwarmOptimization extends Optimizer {
    /**
     * The inertia weight.
     */
    protected double w;

    /**
     * The cognitive coefficient.
     */
    protected double phiP;

    /**
     * The social coefficient.
     */
    protected double phiG;
    
    Random randomNumberGenerator;

    /**
     * Constructor.
     */
    protected ParticleSwarmOptimization(String propertiesFilename) {
    	super(propertiesFilename);
    	try {
        	// read properties of the PSO algorithm from a .properties file
            this.w = psoPropertiesParser.getPropertyAsDouble("w");
            this.phiP = psoPropertiesParser.getPropertyAsDouble("phiP");
            this.phiG = psoPropertiesParser.getPropertyAsDouble("phiG");
            double lowerBound = psoPropertiesParser.getPropertyAsDouble("lowerBound");
            double upperBound = psoPropertiesParser.getPropertyAsDouble("upperBound");
            Random randomNumberGenerator = new Random();
            randomNumberGenerator.setSeed(42); // for reproducibility of the results

            // construct particles
            for (int particleIndex = 0; particleIndex < numAgents; ++particleIndex) {
            	Particle particle = new Particle(this.dimension);
            	particle.initialize(lowerBound, upperBound, randomNumberGenerator);
            	addAgent(particle);
            }
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }
    }

    /**
     * Returns the inertia weight.
     * @return the inertia weight.
     */
    protected double getW() {
        return w;
    }
    
    /**
     * Returns the phiP parameter.
     * @return the phiP parameter.
     */
    protected double getPhiP() {
        return phiP;
    }
    
    /**
     * Returns the phiG parameter.
     * @return the phiG parameter.
     */
    protected double getPhiG() {
        return phiG;
    }
    
    /**
     * Runs the iterative PSO procedure.
     */
    protected void run() {
        // initialization
        for (int particleIndex = 0; particleIndex < numAgents; ++particleIndex) {
        	Particle particle = (Particle) swarm.get(particleIndex);
            double[] particlePosition = particle.getPosition();
            double funcValueAtParticlePosition = funcToOptimize.evaluate(particlePosition);
            double funcValueAtSolution = funcToOptimize.evaluate(solution);         
            if (funcValueAtParticlePosition < funcValueAtSolution) {
                solution = Arrays.copyOf(particlePosition, particlePosition.length);
            }
        }
        
        // main iteration loop
        Random randomNumberGenerator = new Random();
        int iterationsIndex = 0;
        double[] solutionOld = new double[solution.length];
        solutionOld = Arrays.copyOf(solution, solution.length);
        while (iterationsIndex < maxNumIterations) {
        	String funcValueForSolution = String.format("%.03f", funcToOptimize.evaluate(solution));
            System.out.println("Iteration " + (iterationsIndex + 1) + ": f(x) -> " + funcValueForSolution);
            for (int particleIndex = 0; particleIndex < numAgents; ++particleIndex) {
                
            	Particle particle = (Particle) swarm.get(particleIndex);
                
            	double[] position = particle.getPosition();
            	double[] velocity = particle.getVelocity();
                double[] bestPosition = particle.getBestPosition();
                                
                for (int j = 0; j < dimension; ++j) {
                    double rp = randomNumberGenerator.nextDouble();
                    double rg = randomNumberGenerator.nextDouble();
                    velocity[j] = w * velocity[j] + phiP * rp * (bestPosition[j] - position[j]) + phiG * rg * (solution[j] - position[j]);
                    position[j] = position[j] + velocity[j];
                }

                
                if (funcToOptimize.evaluate(position) < funcToOptimize.evaluate(bestPosition)) {
                    // Update the particle's best known position
                    particle.setBestPosition(position);
                    bestPosition = particle.getBestPosition();
                    if (funcToOptimize.evaluate(bestPosition) < funcToOptimize.evaluate(solution)) {
                        // Update the swarm's best known position
                        solution = Arrays.copyOf(bestPosition, bestPosition.length);
                    }
                }
            }

            DistanceCalculator distanceCalculator = new DistanceCalculator(solutionOld, solution);
            double dist = distanceCalculator.euclideanDistance();
            if (dist < tol) {
                break;
            } else {
                solutionOld = Arrays.copyOf(solution, solution.length);
                iterationsIndex++;
            }
        }
    }
}
