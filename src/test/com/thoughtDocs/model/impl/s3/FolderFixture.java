package com.thoughtDocs.model.impl.s3;

import org.testng.Assert;
import org.testng.annotations.Test;
import com.thoughtDocs.model.Folder;
import com.thoughtDocs.model.Item;

import java.util.List;
import java.io.IOException;

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
        String folderKey = "ge/" + folderName;
        Folder folder = (FolderImpl) FolderImpl.createTransientFolder(new RepositoryImpl(testBucket()), folderKey);
        Assert.assertEquals(folder.getKey(), folderKey);
        Assert.assertEquals(folder.getName(), folderName);
    }

    @Test
    public void testFolderSave() throws IOException {
        String key = randomString();
        RepositoryImpl repo = new RepositoryImpl(testBucket());
        Folder folder = FolderImpl.createTransientFolder(repo, key);
        folder.save();
        Folder loaded = FolderImpl.loadedFromRepository(repo, key);
        Assert.assertEquals(loaded.getKey(), key);
    }

    @Test
    public void testFolderTree() throws IOException {
        RepositoryImpl repo = new RepositoryImpl(testBucket());
        Folder rootFolder = FolderImpl.createTransientFolder(repo, "root" + randomString());
        rootFolder.save();
        Folder subFolder = FolderImpl.createTransientFolder( rootFolder, randomString());
        subFolder.save();
        List<Item> items = rootFolder.getItems();
        Assert.assertNotNull(items);
        Assert.assertEquals(items.size(), 1);
        Folder f = (Folder) items.get(0);
        Assert.assertEquals(f.getKey(), subFolder.getKey());

        Folder subFolder2 = FolderImpl.createTransientFolder( rootFolder, randomString());
        subFolder2.save();
        items = rootFolder.getItems();
        Assert.assertEquals(items.size(), 2);


        Folder subFolder12 = FolderImpl.createTransientFolder(f, randomString());
        subFolder12.save();
        items = f.getItems();
        Assert.assertEquals(items.size(), 1);
        f = (Folder) items.get(0);
        Assert.assertEquals(f.getKey(), subFolder12.getKey());



    }

}
