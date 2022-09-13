package com.asal.javaopt;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import java.util.HashMap;
import java.util.Map;
import java.nio.file.Paths;

/**
 * @author A. Salamanis
 * @version 0.2
 * @since 2020-04-25
 *
 * PSOPropertiesParser class: A class representing a parser of a properties file (i.e., .properties).
 */
public class PropertiesParser {
	String propertiesFilename;
    HashMap<String, String> propertiesMap;
    
    /**
     * Constructor.
     */
    public PropertiesParser(String propertiesFilename) {
    	String cwd = Paths.get(".").toAbsolutePath().normalize().toString();
    	System.out.println(cwd);
    	this.propertiesFilename = cwd + propertiesFilename;
    }
    
    /**
     * Reads the values of the properties from the properties file.
     */
    public void readPropertiesValues() {
        try {
            propertiesMap = new HashMap<String, String>();
            Scanner sc = new Scanner(new File(propertiesFilename));
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
        } catch (FileNotFoundException e) {
        	// TODO: Better handle the case when file is missing
            System.out.println("Exception: " + e);
        }
    }
    
    /**
     * Prints the properties parsed from the properties file. 
     */
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