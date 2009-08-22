package com.thoughtDocs.model.impl.s3;

import org.testng.annotations.Test;
import static org.testng.Assert.*;

/**
 *
 */
public class UserS3StoreFixture extends FixtureBase{
    @Test
    public void shouldAddUser() throws Exception {
        UserS3 user = new UserS3(randomString(), "pass", null, null, null);

        UserS3Store store = new UserS3Store();
        int oldSize = store.size();

        store.add(user);

        assertEquals(store.size() - oldSize , 1);
    }

    @Test
    public void shouldFindUser() throws Exception {
        UserS3 user = new UserS3(randomString(), randomString(), null, null, null);
        UserS3Store store = new UserS3Store();
        store.add(user);
        assertEquals(store.find(user.getUsername()), user);
    }

    @Test
    public void shouldPersist() throws Exception{
        UserS3 user = new UserS3(randomString(), randomString(), null, null, null);
        UserS3Store store = new UserS3Store();
        int oldSize = store.size();
        store.add(user);
        store = new UserS3Store();
        assertEquals(store.find(user.getUsername()), user);
        assertEquals(store.size() - oldSize , 1);
    }
}
