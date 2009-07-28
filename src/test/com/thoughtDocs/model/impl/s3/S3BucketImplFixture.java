package com.thoughtDocs.model.impl.s3;

import com.thoughtDocs.util.S3Config;

/**
 * Created by IntelliJ IDEA.
 * User: ThoughtWorks
 * Date: Jul 28, 2009
 * Time: 6:07:21 PM
 * To change this template use File | Settings | File Templates.
 */
public class S3BucketImplFixture extends BucketFixtureBase {

    protected S3Bucket createBucket() {
        return new S3BucketImpl(S3Config.getAwsAccessKey(), S3Config.getAwsSecretKey(), "thoughtdocstest");
    }
}
