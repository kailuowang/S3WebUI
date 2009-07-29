package com.thoughtDocs.model.impl.s3;

/**
 * Created by IntelliJ IDEA.
 * User: ThoughtWorks
 * Date: Jul 29, 2009
 * Time: 9:46:38 AM
 * To change this template use File | Settings | File Templates.
 */
public class RRepositoryFixture extends RepositoryFixture {
    @Override
    protected S3Bucket testBucket() {
        return BucketFactory.getTestS3Bucket();
    }
}
