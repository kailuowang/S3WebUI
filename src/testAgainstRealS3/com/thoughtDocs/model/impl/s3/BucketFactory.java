package com.thoughtDocs.model.impl.s3;

import com.thoughtDocs.util.CredentialsConfig;
import com.thoughtDocs.TestConfig;

/**
 * Created by IntelliJ IDEA.
 * User: ThoughtWorks
 * Date: Jul 29, 2009
 * Time: 9:38:55 AM
 * To change this template use File | Settings | File Templates.
 */
public class BucketFactory {
    public static S3Bucket getTestS3Bucket() {

        return TestConfig.getTestAgainstRealS3Service() ?
                new S3BucketImpl(CredentialsConfig.getAWSAccessKey(),
                        CredentialsConfig.getAWSSecretKey(),
                        CredentialsConfig.getAWSBucketName()):
                new MemoryBucketImpl("test");

    }
}
