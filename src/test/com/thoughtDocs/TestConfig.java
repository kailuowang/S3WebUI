package com.thoughtDocs;

import com.thoughtDocs.util.PropertiesConfig;

/**
 * Created by Kailuo "Kai" Wang
 * Date: Aug 2, 2009
 * Time: 12:52:22 PM
 */
public class TestConfig {
    /**
     * configuration file
     */
    private static final PropertiesConfig cfg = new PropertiesConfig("test.properties");

    public static Boolean getTestAgainstRealS3Service(){
        return cfg.getProperty("testAgainstRealS3Service", new Boolean(true));
    }
}
