package com.thoughtDocs.model.impl.s3;

/**
 * Created by Kailuo "Kai" Wang
 * Date: Aug 2, 2009
 * Time: 11:49:31 AM
 */
public class RSecurityFixture extends SecurityFixture{
    @Override
    protected S3Bucket testBucket() {
        return BucketFactory.getTestS3Bucket();
    }
}
