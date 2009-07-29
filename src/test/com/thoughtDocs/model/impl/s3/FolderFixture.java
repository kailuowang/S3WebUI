package com.thoughtDocs.model.impl.s3;

import org.testng.annotations.Test;
import org.testng.Assert;
import com.thoughtDocs.model.Folder;

/**
 * Created by IntelliJ IDEA.
 * User: ThoughtWorks
 * Date: Jul 29, 2009
 * Time: 11:28:23 AM
 * To change this template use File | Settings | File Templates.
 */
public class FolderFixture extends FixtureBase{

    @Test
    public void testFolderCreation(){
        String folderKey = randomString();
        FolderImpl folder = (FolderImpl) FolderImpl.createTransientFolder(new RepositoryImpl(testBucket()),folderKey);
        Assert.assertEquals(folder.getKey(), folderKey );

    }

    
}
