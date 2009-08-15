package com.thoughtDocs.model.impl.s3;

import org.testng.annotations.Test;
import org.testng.Assert;
import com.thoughtDocs.model.Document;
import com.thoughtDocs.model.SearchFolder;
import com.thoughtDocs.model.Item;
import com.thoughtDocs.model.Folder;

import java.io.IOException;
import java.util.List;

/**
 * Created by Kailuo "Kai" Wang
 * Date: Aug 13, 2009
 * Time: 10:58:38 PM
 */
public class SearchFixture extends FixtureBase{


    @Test
    public void testSearchRootDocument() throws IOException {
         RepositoryImpl repo = new RepositoryImpl(testBucket());
         String keyword = randomString();
         Document doc = DocumentImpl.createTransientDocument(repo.getRootFolder(), randomString() + keyword);
         doc.save();
         Document doc2 = DocumentImpl.createTransientDocument(repo.getRootFolder(), randomString());
         doc2.save();

         SearchFolder searchFolder = new SearchFolderImpl(repo, keyword);
        List<Item> items = searchFolder.getItems();
        Assert.assertEquals(items.size(), 1);
        assert items.contains(doc);

     }

    @Test
    public void testSearchCaseinsensitive() throws IOException {
         RepositoryImpl repo = new RepositoryImpl(testBucket());
         String keyword = randomString() + "ABCD";
         Folder f = FolderImpl.createTransientFolder(repo.getRootFolder(), randomString());
         f.save();
         Document doc = DocumentImpl.createTransientDocument(f, randomString() + " " +  keyword.toLowerCase());
         doc.save();
         Document doc2 = DocumentImpl.createTransientDocument(repo.getRootFolder(), randomString());
         doc2.save();

         SearchFolder searchFolder = new SearchFolderImpl(repo, keyword);
        List<Item> items = searchFolder.getItems();
        Assert.assertEquals(items.size(), 1);
        assert items.contains(doc);

     }

    @Test
    public void testTermsWithMultipleKeywords() throws IOException {
         RepositoryImpl repo = new RepositoryImpl(testBucket());
         String keyword1 = randomString();
         String keyword2 = randomString();
         Folder f = FolderImpl.createTransientFolder(repo.getRootFolder(), randomString());
         f.save();
         Document doc = DocumentImpl.createTransientDocument(f, keyword2 + " " + randomString() + " " +  keyword1  );
         doc.save();
         Document doc2 = DocumentImpl.createTransientDocument(repo.getRootFolder(), randomString() + keyword1);
         doc2.save();

         SearchFolder searchFolder = new SearchFolderImpl(repo, keyword1 + " " + keyword2);
        List<Item> items = searchFolder.getItems();
        Assert.assertEquals(items.size(), 1);
        assert items.contains(doc);

     }
}
