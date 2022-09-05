import java.io.InputStream;
import java.io.FileInputStream;
import java.util.Properties;
import java.util.HashMap;
import java.nio.file.Paths;

/**
 * @author A. Salamanis
 * @version 0.1
 * @since 2020-04-25
 *
 * PSOPropertiesParser class: The class representing a properties file (.properties) parse.
 */
public class PSOPropertiesParser {
    HashMap<String, String> propertiesMap;
    
    /**
     * Reads the values of the properties from the properties file.
     */
    public void readPropertiesValues() {
        try
        {
            propertiesMap = new HashMap<String, String>();
            Properties prop = new Properties();
            String cwd = Paths.get(".").toAbsolutePath().normalize().toString();
            String propFilename = cwd + "/PSO.properties";
            InputStream inputStream =  new FileInputStream(propFilename);

            if (inputStream != null) {
                prop.load(inputStream);

                // set the properties' values
                propertiesMap.put("fType", prop.getProperty("fType"));
                propertiesMap.put("numParticles", prop.getProperty("numParticles"));
                propertiesMap.put("d", prop.getProperty("d"));
                propertiesMap.put("tol", prop.getProperty("tol"));
                propertiesMap.put("maxNumOfIterations", prop.getProperty("maxNumOfIterations"));
                propertiesMap.put("lowerBound", prop.getProperty("lowerBound"));
                propertiesMap.put("upperBound", prop.getProperty("upperBound"));
                propertiesMap.put("w", prop.getProperty("w"));
                propertiesMap.put("phiP", prop.getProperty("phiP"));
                propertiesMap.put("phiG", prop.getProperty("phiG"));
            }
            // TODO: Implement handler of the else case.
            inputStream.close();
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }
    }

    /**
     * Returns the value of a property as integer.
     * @param propertyName the name of the property.
     * @return the value of the property (as integer).
     */
    int getPropertyAsInteger(String propertyName) {
        return Integer.parseInt(propertiesMap.get(propertyName));
    }

    /**
     * Returns the value of a property as double.
     * @param propertyName the name of the property.
     * @return the value of the property (as double).
     */
    double getPropertyAsDouble(String propertyName) {
        return Double.parseDouble(propertiesMap.get(propertyName));
    }
}