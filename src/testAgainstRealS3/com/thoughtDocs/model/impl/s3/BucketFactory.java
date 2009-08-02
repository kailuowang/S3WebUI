package com.thoughtDocs.model.impl.s3;

import com.thoughtDocs.util.CredentialsConfig;

/**
 * Created by IntelliJ IDEA.
 * User: ThoughtWorks
 * Date: Jul 29, 2009
 * Time: 9:38:55 AM
 * To change this template use File | Settings | File Templates.
 */
public class BucketFactory {
    public static S3Bucket getTestS3Bucket() {

        final boolean temporarilyOffline = false;
        return temporarilyOffline ?
                new MemoryBucketImpl("test") :
                new S3BucketImpl(CredentialsConfig.getAWSAccessKey(),
                        CredentialsConfig.getAWSSecretKey(),
                        CredentialsConfig.getAWSBucketName());
    }
}
