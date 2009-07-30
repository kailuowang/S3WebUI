package com.thoughtDocs.model.impl.s3;

/**
 * Created by Kailuo "Kai" Wang
 * Date: Jul 30, 2009
 * Time: 6:08:32 PM
 */
public class RFolderFixture extends FolderFixture{
   @Override
    protected S3Bucket testBucket() {
        return BucketFactory.getTestS3Bucket();
    }
}
