
public class PSO 
{
	public static void main(String[] args)
	{
		Particle p = new Particle(10);
		for (int i = 0; i < p.n(); i++)
		{
			System.out.println(p.x(i));
		}
	}
}
