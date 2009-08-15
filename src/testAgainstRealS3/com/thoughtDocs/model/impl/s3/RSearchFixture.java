package com.thoughtDocs.model.impl.s3;

/**
 * Created by Kailuo "Kai" Wang
 * Date: Aug 15, 2009
 * Time: 11:39:11 AM
 */
public class RSearchFixture extends SearchFixture{
     @Override
    protected S3Bucket testBucket() {
        return BucketFactory.getTestS3Bucket();
    }
}
