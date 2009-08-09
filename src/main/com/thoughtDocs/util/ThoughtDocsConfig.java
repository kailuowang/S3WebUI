package com.thoughtDocs.util;

/**
 * Created by Kailuo "Kai" Wang
 * Date: Aug 3, 2009
 * Time: 9:39:53 AM
 */
public class ThoughtDocsConfig {
    /**
     * configuration file
     */
    private static final PropertiesConfig cfg = new PropertiesConfig("thoughtDocs.properties");

    public static Boolean getRunOnMemoryBucket(){
        return cfg.getProperty("runOnMemoryBucket", new Boolean(false));
    }


    public static String getPublicDownloadSite() {
        return cfg.getProperty("publicDownloadSite", "http://thoughtfiles.com");
    }
}
