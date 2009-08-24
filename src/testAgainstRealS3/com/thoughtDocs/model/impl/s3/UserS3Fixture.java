package com.thoughtDocs.model.impl.s3;

import org.testng.annotations.Test;
import static org.testng.Assert.*;

/**
 *
 */
public class UserS3Fixture  extends FixtureBase{
    @Override
    protected S3Bucket testBucket() {
        return BucketFactory.getTestS3BucketForReal();
    }

    private S3BucketImpl getBucket(){
        return (S3BucketImpl) testBucket();
    }

    @Test
    public void shouldPersist() throws Exception{
        UserS3 user = new UserS3(randomString(),
                                    randomString(),
                                    getBucket().getAccessKeyId(),
                                    getBucket().getSecretAccessKey(),
                                    getBucket().getName());
        UserS3Store store = new UserS3Store();
        int oldSize = store.size();
        user.persist(store);
        store = new UserS3Store();
        assertEquals(store.find(user.getUsername()), user);
        assertEquals(store.size() - oldSize , 1);
        store.remove(user);
    }

    @Test
    public void shouldAutoCreateBucket() throws Exception{
        UserS3 user = new UserS3(randomString(),
                                    randomString(),
                                    getBucket().getAccessKeyId(),
                                    getBucket().getSecretAccessKey(),
                                    null);
        UserS3Store store = new UserS3Store();
        assertNull(user.getBucketName());
        user.persist(store);
        assertTrue( user.getBucketName().contains(user.getUsername().toLowerCase()));
        store.remove(user);
    }

}
