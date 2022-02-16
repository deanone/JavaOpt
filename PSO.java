import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.lang.Math;
import java.io.IOException;

public class PSO 
{
    PSOPropertiesParser psopp;
    FunctionToOptimize f;
    ArrayList<Particle> particles;

    /**
     * The number of particles.
     */
    int numParticles;

    /**
     * The dimension of particles.
     */
    int d;

    /**
     * The tolerance for terminating the PSO method.
     */
    double tol;

    /**
     * The maximum number of iterations for the PSO method to terminate.
     */
    int maxNumOfIterations;

    /**
     * 
     */
    double lowerBound;

    /**
     * 
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
    double[] g;

    /**
     * Constructor.
     */
    public PSO()
    {
        try
        {
            psopp = new PSOPropertiesParser();
            psopp.readPropValues();
            this.f = new FunctionToOptimize(psopp.getPropertyAsInteger("fType"));
            this.particles = new ArrayList<Particle>();
            this.numParticles = psopp.getPropertyAsInteger("numParticles");
            this.d = psopp.getPropertyAsInteger("d");
            this.tol = psopp.getPropertyAsDouble("tol");
            this.maxNumOfIterations = psopp.getPropertyAsInteger("maxNumOfIterations");
            this.lowerBound = psopp.getPropertyAsDouble("lowerBound");
            this.upperBound = psopp.getPropertyAsDouble("upperBound");
            this.w = psopp.getPropertyAsDouble("w");
            this.phiP = psopp.getPropertyAsDouble("phiP");
            this.phiG = psopp.getPropertyAsDouble("phiG");

            // construct initial particles
            for (int i = 0; i < this.numParticles; ++i)
            {
                addParticle(new Particle(this.d, this.lowerBound, this.upperBound));
            }

            g = new double[d];

            Random r = new Random();
            for (int i = 0; i < g.length; ++i)
            {
                g[i] = lowerBound + (r.nextDouble() * (upperBound - lowerBound));
            }
        
        }
        catch (Exception e)
        {
            System.out.println("Exception: " + e);
        }
    }

    /**
     * Adds a new particle to the list of particles.
     * @param p the new particle.
     */
    public void addParticle(Particle p)
    {
        particles.add(p);
    }

    /**
     * Returns a particle from the list of particles.
     * @param i the index of the particle to return.
     * @return the particle at the input index.
     */
    public Particle getParticle(int i)
    {
        return particles.get(i);
    }
    
    /**
     * Returns the number of particles.
     * @return the number of particles.
     */
    public int getNumParticles()
    {
        return numParticles;
    }
    
    /**
     * Returns the dimension of the particles.
     * @return the dimension of the particles.
     */
    public int getParticlesDimension()
    {
        return d;
    }
    
    /**
     * Returns the tolerance for terminating the algorithm.
     * @return the tolerance for terminating the algorithm.
     */
    public double getTol()
    {
        return tol;
    }
    
    /**
     * Returns the maximum number of iterations for the PSO algorithm to terminate.
     * @return the maximum number of iterations for the PSO algorithm to terminate.
     */
    public int getMaxNumOfIterations()
    {
        return maxNumOfIterations;
    }

    /**
     * Returns the inertia weight.
     * @return the inertia weight.
     */
    public double getW()
    {
        return w;
    }
    
    /**
     * Returns the phiP parameter.
     * @return the phiP parameter.
     */
    public double getPhiP()
    {
        return phiP;
    }
    
    /**
     * Returns the phiG parameter.
     * @return the phiG parameter.
     */
    public double getPhiG()
    {
        return phiG;
    }

    /**
     * Returns the solution.
     * @return the solution.
     */
    public double[] getSolution()
    {
        return g;
    }

    /**
     * Prints the solution.
     */
    public void printSolution()
    {
        System.out.print("Solution: g=[");
        System.out.print(g[0]);
        for (int i = 1; i < g.length; ++i)
        {
            System.out.print("," + g[i]);
        }
        System.out.println("]");
    }
    
    /**
     * Runs the iterative PSO procedure.
     */
    public void run()
    {
        // initialization
        for (int i = 0; i < numParticles; ++i)
        {
            Particle particle = particles.get(i);
            particle.initialize();
            double[] p = particle.p();
            double fp = f.f(p);
            double fg = f.f(g);         
            if (fp < fg)
            {
                g = Arrays.copyOf(p, p.length);
            }
        }
        
        // main iteration loop
        Random r = new Random();
        int iter = 0;
        double[] gOld = new double[g.length];
        gOld = Arrays.copyOf(g, g.length);
        while (iter < maxNumOfIterations)
        {
            System.out.println("Iteration " + (iter + 1));
            for (int i = 0; i < numParticles; ++i)
            {
                Particle particle = particles.get(i);
                
                double[] v = particle.v();
                double[] x = particle.x();
                double[] p = particle.p();
                                
                for (int j = 0; j < d; ++j)
                {
                    double rp = r.nextDouble();
                    double rg = r.nextDouble();
                    v[j] = w * v[j] + phiP * rp * (p[j] - x[j]) + phiG * rg * (g[j] - x[j]);
                    x[j] = x[j] + v[j];
                }

                
                if (f.f(x) < f.f(p))
                {
                    // Update the particle's best known position
                    particle.p(x);
                    p = particle.p();
                    if (f.f(p) < f.f(g))
                    {
                        // Update the swarm's best known position
                        g = Arrays.copyOf(p, d);
                    }
                }
            }

            DistanceCalculator dc = new DistanceCalculator(gOld, g);
            double dist = dc.euclideanDistance();

            if (dist < tol)
            {
                break;
            }
            else
            {
                gOld = Arrays.copyOf(g, g.length);
                iter++;
            }
        }
    }
    
    /**
     * Starting point of the application.
     * TODO: To check and (possibly) refactor the following classes:
     * 1. PSO
     */
    public static void main(String[] args)
    {
        PSO pso = new PSO();
        pso.run();
        pso.printSolution();
        System.out.println("Optimum objective function value for solution g: " + pso.f.f(pso.getSolution()));
        System.exit(0);
    }
}
