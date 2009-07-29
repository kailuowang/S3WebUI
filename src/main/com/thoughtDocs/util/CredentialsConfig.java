package com.thoughtDocs.util;

/**
 * Created by IntelliJ IDEA.
 * User: ThoughtWorks
 * Date: Jul 27, 2009
 * Time: 1:21:55 PM
 * To change this template use File | Settings | File Templates.
 */

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


/**
 * A temporary solution for reading s3 account
 */
public class CredentialsConfig {

    /**
     * configuration file
     */
    private static final String configurationFilePath = "credentials.properties";

    private static Properties props = null;

    /**
     * return the value of the given property key
     *
     * @param key key
     * @return
     */
    public static String getProperty(final String key)  {
        if (props == null) {
            try {
                props = loadFromResource(configurationFilePath);
            } catch (IOException e) {
                throw new Error("Couldn't find credentials.properties file in the resource folder, " +
                        "please add it with two keys awsAccessKeyId and " +
                        "awsSecretAccessKey");
            }
        }
        return props.getProperty(key);
    }


    /**
     * code taken from seam src
     *
     * @param resource resource
     * @return
     */
    private static Properties loadFromResource(final String resource) throws IOException {
        props = new Properties();
        final InputStream stream = Thread.currentThread().getContextClassLoader().getResourceAsStream(resource);
        try {
            props.load(stream);
        }
        finally {
            try {
                stream.close();
            }
            catch (final IOException ex) {
                props = null;
            }
        }

        return props;
    }


    public static String getAWSAccessKey(){
        return getProperty("awsAccessKeyId") ;
    }

     public static String getAWSSecretKey(){

         return getProperty("awsSecretAccessKey");
    }

    public static String getAWSBucketName() {
        return getProperty("awsBucketName");
    }

    public static String getAdminPassword() {
        return getProperty("adminPassword");
    }

	public static String getDefaultDocumentPassword() {
		return getProperty("defaultDocumentPassword");
	}
}
