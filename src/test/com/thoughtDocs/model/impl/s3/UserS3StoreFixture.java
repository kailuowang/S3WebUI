package com.thoughtDocs.model.impl.s3;

import org.testng.annotations.Test;
import static org.testng.Assert.*;

/**
 *
 */
public class UserS3StoreFixture extends FixtureBase{
    @Override
    protected S3Bucket testBucket() {
        return BucketFactory.getTestS3Bucket();
    }

    @Test
    public void shouldAddUser() throws Exception {
        UserS3 user = new UserS3(randomString(), "pass", null, null, null);
        UserS3Store store = new UserS3Store();
        int oldSize = store.size();
        store.persist(user);
        assertEquals(store.size() - oldSize , 1);
    }

    @Test
    public void shouldFindUser() throws Exception {
        UserS3 user = new UserS3(randomString(), randomString(), null, null, null);
        UserS3Store store = new UserS3Store();
        store.persist(user);
        assertEquals(store.find(user.getUsername()), user);
    }

    @Test
    public void shouldPersist() throws Exception{
        UserS3 user = new UserS3(randomString(), randomString(), null, null, null);
        UserS3Store store = new UserS3Store();
        int oldSize = store.size();
        store.persist(user);
        store = new UserS3Store();
        assertEquals(store.find(user.getUsername()), user);
        assertEquals(store.size() - oldSize , 1);
    }


    @Test
    public void shouldRemovePersist() throws Exception{
        UserS3 user = new UserS3(randomString(), randomString(), null, null, null);
        UserS3Store store = new UserS3Store();
        int oldSize = store.size();
        store.persist(user);
        store = new UserS3Store();
        assertEquals(store.find(user.getUsername()), user);
        assertEquals(store.size() - oldSize , 1);
        store.remove(user);
        store = new UserS3Store();
        assertNull(store.find(user.getUsername()));
        assertEquals(store.size() , oldSize );
    }
}
