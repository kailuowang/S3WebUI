package com.thoughtDocs.util;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

import com.thoughtDocs.TestConfig;

/**
 * Created by IntelliJ IDEA.
 * User: ThoughtWorks
 * Date: Jul 27, 2009
 * Time: 1:40:34 PM
 * To change this template use File | Settings | File Templates.
 */
public class ConfigFixture {
    @Test
    public void testReadS3Config() throws IOException {
        String awsKey = CredentialsConfig.getProperty("awsAccessKeyId");
        Assert.assertNotNull(awsKey);
        assert awsKey.length() > 0;
    }



}
