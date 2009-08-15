package com.thoughtDocs.model.impl.s3;

import org.testng.annotations.Test;
import org.testng.Assert;
import com.thoughtDocs.model.Document;
import com.thoughtDocs.model.SearchFolder;
import com.thoughtDocs.model.Item;

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
}
