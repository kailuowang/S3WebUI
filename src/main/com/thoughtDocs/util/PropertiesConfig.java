package com.thoughtDocs.util;

import com.thoughtDocs.exception.InvalidConfigurationException;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Config helper classes that uses property file
 * Created by Kailuo "Kai" Wang
 * Date: Aug 2, 2009
 * Time: 11:56:40 AM
 */
public class PropertiesConfig {

    /**
     * configuration file
     */
    private String configurationFilePath;


    /**
     * return the value of the given property key
     *
     * @param key key
     * @return
     */
    public String getProperty(final String key) {
        return getProperty(String.class, key );
    }

    public <T> T getProperty(Class<T> argType, String key) {
        return getProperty(argType, key, true);  
    }


    public <T> T getProperty(String key, T defaultVal) {
        if (defaultVal == null)
            throw new IllegalArgumentException("defaultVal cannot be null");
        Class<T> type = (Class<T>) defaultVal.getClass();
        T retVal = getProperty(type, key, false);
        if (retVal == null)
            return defaultVal;
        else return retVal;


    }

    private <T> T getProperty(Class<T> argType, String key, boolean throwExceptionIfNotFound) {
        Properties prop = getProperties();
        if (prop != null) {
            String retVal = (String) prop.get(key);
            if (retVal != null && ( argType == String.class || retVal.length() > 0 )) {
                return Convert.valOf(argType, retVal);
            }
        }

        if (throwExceptionIfNotFound)
            throw new InvalidConfigurationException("Cannot find the configuration file - '"
                    + configurationFilePath + "' or there is no value set for the key '" + key + "' ");

        return null;

    }


    public PropertiesConfig(String configurationFilePath) {
        this.configurationFilePath = configurationFilePath;
    }

    /**
     * code taken from seam src
     *
     * @return
     */
    private Properties getProperties() {
        final InputStream stream = Thread.currentThread().getContextClassLoader().getResourceAsStream(configurationFilePath);
        if (stream == null)
            return null;
        Properties properties = new Properties();
        try {
            properties.load(stream);
        }
        catch (IOException e) {
            return null;
        } finally {
            try {
                stream.close();
            } catch (IOException e) {
                return null;
            }
        }
        return properties;
    }
}
