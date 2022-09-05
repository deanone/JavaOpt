import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/**
 * @author A. Salamanis
 * @version 0.2
 * @since 2020-04-25
 *
 * PSO class: The class representing the PSO method.
 */
public class PSO {
    PSOPropertiesParser psoPropertiesParser;
    FunctionToOptimize funcToOptimize;
    ArrayList<Particle> swarm;

    /**
     * The number of particles.
     */
    int numParticles;

    /**
     * The dimension of particles.
     */
    int dimension;

    /**
     * The tolerance for terminating the PSO method.
     */
    double tol;

    /**
     * The maximum number of iterations for the PSO method to terminate.
     */
    int maxNumIterations;

    /**
     * The lower bound of the interval from which the initial random values of the particles are generated.
     */
    double lowerBound;

    /**
     * The upper bound of the interval from which the initial random values of the particles are generated.
     */
    double upperBound;

    /**
     * The inertia weight.
     */
    double w;

    /**
     * The cognitive coefficient.
     */
    double phiP;

    /**
     * The social coefficient.
     */
    double phiG;

    /**
     * The solution estimated by the PSO method.
     */
    double[] solution;
    
    Random randomNumberGenerator;

    /**
     * Constructor.
     */
    public PSO() {
        try {
        	// read properties of the algorithm from a properties file
            psoPropertiesParser = new PSOPropertiesParser();
            psoPropertiesParser.readPropertiesValues();
            this.funcToOptimize = new FunctionToOptimize(psoPropertiesParser.getPropertyAsInteger("fType"));
            this.swarm = new ArrayList<Particle>();
            this.numParticles = psoPropertiesParser.getPropertyAsInteger("numParticles");
            this.dimension = psoPropertiesParser.getPropertyAsInteger("dimension");
            this.tol = psoPropertiesParser.getPropertyAsDouble("tol");
            this.maxNumIterations = psoPropertiesParser.getPropertyAsInteger("maxNumIterations");
            this.lowerBound = psoPropertiesParser.getPropertyAsDouble("lowerBound");
            this.upperBound = psoPropertiesParser.getPropertyAsDouble("upperBound");
            this.w = psoPropertiesParser.getPropertyAsDouble("w");
            this.phiP = psoPropertiesParser.getPropertyAsDouble("phiP");
            this.phiG = psoPropertiesParser.getPropertyAsDouble("phiG");
            Random randomNumberGenerator = new Random();
            randomNumberGenerator.setSeed(42); // for reproducibility of the results

            // construct initial particles
            for (int particleIndex = 0; particleIndex < this.numParticles; ++particleIndex) {
                addParticle(new Particle(this.dimension, this.lowerBound, this.upperBound, randomNumberGenerator));
            }
            
            // initialize solution vector
            solution = new double[dimension];
            
            for (int i = 0; i < solution.length; ++i) {
            	solution[i] = lowerBound + (randomNumberGenerator.nextDouble() * (upperBound - lowerBound));
            }
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }
    }

    /**
     * Adds a new particle to the swarm.
     * @param particle the new particle.
     */
    public void addParticle(Particle particle) {
        swarm.add(particle);
    }

    /**
     * Returns a particle from the swarm.
     * @param particleIndex the index of the particle to return.
     * @return the particle at the input index.
     */
    public Particle getParticle(int particleIndex) {
        return swarm.get(particleIndex);
    }
    
    /**
     * Returns the number of particles.
     * @return the number of particles.
     */
    public int getNumParticles() {
        return numParticles;
    }
    
    /**
     * Returns the dimension of the particles.
     * @return the dimension of the particles.
     */
    public int getParticlesDimension() {
        return dimension;
    }
    
    /**
     * Returns the tolerance for terminating the algorithm.
     * @return the tolerance for terminating the algorithm.
     */
    public double getTol() {
        return tol;
    }
    
    /**
     * Returns the maximum number of iterations for the PSO algorithm to terminate.
     * @return the maximum number of iterations for the PSO algorithm to terminate.
     */
    public int getMaxNumIterations() {
        return maxNumIterations;
    }

    /**
     * Returns the inertia weight.
     * @return the inertia weight.
     */
    public double getW() {
        return w;
    }
    
    /**
     * Returns the phiP parameter.
     * @return the phiP parameter.
     */
    public double getPhiP() {
        return phiP;
    }
    
    /**
     * Returns the phiG parameter.
     * @return the phiG parameter.
     */
    public double getPhiG() {
        return phiG;
    }

    /**
     * Returns the solution.
     * @return the solution.
     */
    public double[] getSolution() {
        return solution;
    }

    /**
     * Prints the solution.
     */
    public void printSolution() {
        System.out.println();
        System.out.println("<------------------------ Solution ------------------------>");
        System.out.print("g = [");
        System.out.printf("%.4f", solution[0]);
        for (int solutionElementIndex = 1; solutionElementIndex < solution.length; ++solutionElementIndex) {
            System.out.printf(", %.4f", solution[solutionElementIndex]);
        }
        System.out.println("]");
        System.out.println("<---------------------------------------------------------->");
    }
    
    /**
     * Runs the iterative PSO procedure.
     */
    public void run() {
        // initialization
        for (int particleIndex = 0; particleIndex < numParticles; ++particleIndex) {
        	Particle particle = swarm.get(particleIndex);
            particle.initialize();
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
            System.out.println("Iteration: " + (iterationsIndex + 1));
            for (int particleIndex = 0; particleIndex < numParticles; ++particleIndex) {
                
            	Particle particle = swarm.get(particleIndex);
                
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
    
    /**
     * Starting point of the application.
     */
    public static void main(String[] args) {
        PSO pso = new PSO();
        pso.run();
        pso.printSolution();

        //System.out.printf("\nOptimum objective function value for solution g: %.4f\n", pso.f.f(pso.getSolution()));
        
        System.exit(0);
    }
}
