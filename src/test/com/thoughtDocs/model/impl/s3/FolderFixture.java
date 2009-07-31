package com.thoughtDocs.model.impl.s3;

import com.thoughtDocs.model.Folder;
import com.thoughtDocs.model.Item;
import com.thoughtDocs.model.RootFolder;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

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
        Folder folder = FolderImpl.createTransientFolder(createRoot(), folderKey);
        Assert.assertEquals(folder.getKey(), folderKey);
        Assert.assertEquals(folder.getName(), folderName);
    }

    @Test
    public void testFolderSave() throws IOException {
        String key = randomString();
        RepositoryImpl repo = new RepositoryImpl(testBucket());
        Folder folder = FolderImpl.createTransientFolder(createRoot(), key);
        folder.save();
        Folder loaded = FolderImpl.loadedFromRepository(repo, key);
        Assert.assertEquals(loaded.getKey(), key);
    }

    @Test
    public void testFolderTree() throws IOException {
        RepositoryImpl repo = new RepositoryImpl(testBucket());
        Folder firstLevel = FolderImpl.createTransientFolder(createRoot(), "firstLevel" + randomString());
        firstLevel.save();
        Folder subFolder = FolderImpl.createTransientFolder(firstLevel, randomString());
        subFolder.save();
        List<Item> items = firstLevel.getItems();
        Assert.assertNotNull(items);
        Assert.assertEquals(items.size(), 1);
        Folder f = (Folder) items.get(0);
        Assert.assertEquals(f.getKey(), subFolder.getKey());

        Folder subFolder2 = FolderImpl.createTransientFolder(firstLevel, randomString());
        Assert.assertEquals(firstLevel.getItems().size(), 1);
        subFolder2.save();
        items = firstLevel.getItems();
        Assert.assertEquals(items.size(), 2);


        Folder subFolder12 = FolderImpl.createTransientFolder(f, randomString());
        subFolder12.save();
        items = f.getItems();
        Assert.assertEquals(items.size(), 1);
        f = (Folder) items.get(0);
        Assert.assertEquals(f.getKey(), subFolder12.getKey());

    }
    
    @Test
    public void testRootFolder() throws IOException {
        Folder root = createRoot();
        Folder firstLevel = FolderImpl.createTransientFolder(root, "firstLevel" + randomString());
        firstLevel.save();
        List<Item> items = root.getItems();
        Folder found = null;
        for(Item item :items)
        {
            if(item.getKey().equals(firstLevel.getKey()))
            {
                found = (Folder) item;
                break;
            }
        }
        Assert.assertNotNull(found);


    }

    private Folder createRoot() {
        Folder root = new RootFolder(new RepositoryImpl(testBucket()));
        return root;
    }

}
