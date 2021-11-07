import java.io.InputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.HashMap;
import java.nio.file.Paths;

public class PSOPropertiesParser
{
	HashMap<String, String> propMap;
	
	/**
	 * Reads the values of the properties from the properties file.
	 */
	public void readPropValues()
	{
		try
		{
			propMap = new HashMap<String, String>();
			Properties prop = new Properties();
			String cwd = Paths.get(".").toAbsolutePath().normalize().toString();
			String propFileName = cwd + "/PSO.properties";
			InputStream inputStream =  new FileInputStream(propFileName);

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

			}
			// TODO: Implement handler of the else case.
			inputStream.close();
		}
		catch (Exception e)
		{
			System.out.println("Exception: " + e);
		}
	}

	/**
	 * Returns the value of a property as integer.
	 * @param propertyName the name of the property.
	 * @return the value of the property (as integer).
	 */
	int getPropertyAsInteger(String propertyName)
	{
		return Integer.parseInt(propMap.get(propertyName));
	}

	/**
	 * Returns the value of a property as double.
	 * @param propertyName the name of the property.
	 * @return the value of the property (as double).
	 */
	double getPropertyAsDouble(String propertyName)
	{
		return Double.parseDouble(propMap.get(propertyName));
	}
}