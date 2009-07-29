package com.thoughtDocs.model.impl.s3;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by IntelliJ IDEA.
 * User: ThoughtWorks
 * Date: Jul 29, 2009
 * Time: 11:28:23 AM
 * To change this template use File | Settings | File Templates.
 */
public class FolderFixture extends FixtureBase {

    @Test
    public void testFolderCreation() {
        String folderName = randomString();
        String folderKey = "/ge/" + folderName;
        FolderImpl folder = (FolderImpl) FolderImpl.createTransientFolder(new RepositoryImpl(testBucket()), folderKey);
        Assert.assertEquals(folder.getKey(), folderKey);
        Assert.assertEquals(folder.getName(), folderName);
    }


    

}
