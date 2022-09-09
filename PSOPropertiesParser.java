import java.io.File;
import java.util.Scanner;

import java.util.HashMap;
import java.util.Map;
import java.nio.file.Paths;

/**
 * @author A. Salamanis
 * @version 0.2
 * @since 2020-04-25
 *
 * PSOPropertiesParser class: A class representing a properties file (.properties) parser.
 */
public class PSOPropertiesParser {
	String propertiesFilename;
    HashMap<String, String> propertiesMap;
    
    public PSOPropertiesParser(String propertiesFilename) {
    	String cwd = Paths.get(".").toAbsolutePath().normalize().toString();
    	this.propertiesFilename = cwd + propertiesFilename;
    }
    
    /**
     * Reads the values of the properties from the properties file.
     */
    public void readPropertiesValues() {
        try {
            propertiesMap = new HashMap<String, String>();
            File file =  new File(propertiesFilename);
            Scanner sc = new Scanner(file);
            boolean headerLine = true;
            while (sc.hasNextLine()) {
            	if (headerLine) {
            		headerLine = false;
            		sc.nextLine();
            		continue;
            	}
            	String line = sc.nextLine();
            	String[] items = line.split("=");
            	
            	propertiesMap.put(items[0], items[1]);
            }
            sc.close();
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }
    }
    
    public void printPropertiesMap() {
    	for (Map.Entry<String, String> set : propertiesMap.entrySet()) {
    		System.out.println(set.getKey() + '=' + set.getValue());
    	}
    }

    /**
     * Returns the value of a property as integer.
     * @param propertyName the name of the property.
     * @return the value of the property (as integer).
     */
    public int getPropertyAsInteger(String propertyName) {
        return Integer.parseInt(propertiesMap.get(propertyName));
    }

    /**
     * Returns the value of a property as double.
     * @param propertyName the name of the property.
     * @return the value of the property (as double).
     */
    public double getPropertyAsDouble(String propertyName) {
        return Double.parseDouble(propertiesMap.get(propertyName));
    }
}