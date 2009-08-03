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
    private static final PropertiesConfig cfg = new PropertiesConfig("credentials.properties");



    /**
     * return the value of the given property key
     *
     * @param key key
     * @return
     */
    public static String getProperty(final String key) {
        return cfg.getProperty(key);
    }



    public static String getAWSAccessKey() {
        return getProperty("awsAccessKeyId");
    }

    public static String getAWSSecretKey() {

        return getProperty("awsSecretAccessKey");
    }

    public static String getAWSBucketName() {
        return getProperty("awsBucketName");
    }

    public static String getAdminPassword() {
        return getProperty("adminPassword");
    }

    public static String getDefaultPassword() {
        return getProperty("defaultPassword");
    }
}
