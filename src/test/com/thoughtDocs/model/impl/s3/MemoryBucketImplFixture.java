package com.thoughtDocs.model.impl.s3;

/**
 * Created by IntelliJ IDEA.
 * User: ThoughtWorks
 * Date: Jul 28, 2009
 * Time: 4:23:53 PM
 * To change this template use File | Settings | File Templates.
 */
public class MemoryBucketImplFixture extends BucketFixtureBase {
    @Override
    protected S3Bucket createBucket() {
        return new MemoryBucketImpl("testBucket");
    }
}
