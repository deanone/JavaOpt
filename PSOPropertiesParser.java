import java.io.InputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.HashMap;
import java.nio.file.Paths;

public class PSOPropertiesParser
{
	HashMap<String, String> propMap;
	InputStream inputStream;
	
	public void readPropValues()
	{
		try
		{
			propMap = new HashMap<String, String>();
			Properties prop = new Properties();
			String cwd = Paths.get(".").toAbsolutePath().normalize().toString();
			String propFileName = cwd + "/pso.properties";

			// TODO: Change the following line in order for the inputStream to read the pso.properties file from the cwd.
			//inputStream = new InputStream(propFileName);

			if (inputStream != null) 
			{
				prop.load(inputStream);

				// get the property value
				propMap.put("fType", prop.getProperty("fType"));
				propMap.put("numParticles", prop.getProperty("numParticles"));
				propMap.put("d", prop.getProperty("d"));
				propMap.put("tol", prop.getProperty("tol"));
				propMap.put("maxNumOfIterations", prop.getProperty("maxNumOfIterations"));
				propMap.put("lowerBound", prop.getProperty("lowerBound"));
				propMap.put("upperBound", prop.getProperty("upperBound"));
				propMap.put("w", prop.getProperty("w"));
				propMap.put("phiP", prop.getProperty("phiP"));
				propMap.put("phiG", prop.getProperty("phiG"));

				System.out.println("OK1.2.8");
			}
			inputStream.close();
		}
		catch (Exception e)
		{
			System.out.println("Exception: " + e);
		}
	}

	int getPropertyAsInteger(String propertyName)
	{
		return Integer.parseInt(propMap.get(propertyName));
	}

	double getPropertyAsDouble(String propertyName)
	{
		return Double.parseDouble(propMap.get(propertyName));
	}
}