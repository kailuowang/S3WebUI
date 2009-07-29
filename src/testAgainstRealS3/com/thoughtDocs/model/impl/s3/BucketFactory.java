package com.thoughtDocs.model.impl.s3;

import com.thoughtDocs.util.S3Config;

/**
 * Created by IntelliJ IDEA.
 * User: ThoughtWorks
 * Date: Jul 29, 2009
 * Time: 9:38:55 AM
 * To change this template use File | Settings | File Templates.
 */
public class BucketFactory {
    public static S3Bucket getTestS3Bucket() {
        return new S3BucketImpl(S3Config.getAwsAccessKey(), S3Config.getAwsSecretKey(), "thoughtdocstest");
    }
}
