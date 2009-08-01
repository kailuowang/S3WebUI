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
        Folder folder = FolderImpl.createTransientFolder(repo.getRootFolder(), key);

        Folder loaded = FolderImpl.loadedFromRepository(repo, key);
        loaded.update();
        Assert.assertTrue(loaded.isTransient());

        folder.save();
        loaded = FolderImpl.loadedFromRepository(repo, key);
        loaded.update();
        Assert.assertFalse(loaded.isTransient());
        Assert.assertEquals(loaded.getKey(), key);
        folder.delete();
    }

    @Test
    public void testFolderTree() throws IOException {
  
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

        Assert.assertEquals(subFolder2.getParent().getKey(), firstLevel.getKey());
        Assert.assertTrue(firstLevel.getParent() instanceof RootFolder);

        Folder subFolder12 = FolderImpl.createTransientFolder(f, randomString());
        subFolder12.save();
        items = f.getItems();
        Assert.assertEquals(items.size(), 1);
        f = (Folder) items.get(0);
        Assert.assertEquals(f.getKey(), subFolder12.getKey());
        firstLevel.delete();

    }

    @Test
    public void testDeleteFolder() throws IOException {
        RepositoryImpl repo = new RepositoryImpl(testBucket());
        Folder firstLevel = FolderImpl.createTransientFolder(repo.getRootFolder(), "firstLevel" + randomString());
        firstLevel.save();
        Folder subFolder = FolderImpl.createTransientFolder(firstLevel, randomString());
        subFolder.save();
        Folder subFolder2 = FolderImpl.createTransientFolder(firstLevel, randomString());
        subFolder2.save(); 
        Folder subFolder3 = FolderImpl.createTransientFolder(firstLevel, randomString());
        subFolder3.save();
        Assert.assertEquals( firstLevel.getItems().size(), 3);

        subFolder3.delete();

        assertDeleted(repo, subFolder3);

        Assert.assertEquals(firstLevel.getItems().size(), 2);

        firstLevel.delete();



        assertDeleted(repo, firstLevel);
        assertDeleted(repo, subFolder);
        assertDeleted(repo, subFolder2);


    }

    private void assertDeleted(RepositoryImpl repo, Item item) throws IOException {
        Folder loaded = FolderImpl.loadedFromRepository(repo,  item.getKey());
        loaded.update();
        Assert.assertTrue(loaded.isTransient());
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
        firstLevel.delete();
    }

    private Folder createRoot() {
        Folder root = new RootFolder(new RepositoryImpl(testBucket()));
        return root;
    }

}
