package com.thoughtDocs.model.impl.s3;

import com.thoughtDocs.model.Document;
import com.thoughtDocs.model.Repository;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: ThoughtWorks
 * Date: Jul 29, 2009
 * Time: 9:18:34 AM
 * To change this template use File | Settings | File Templates.
 */
public class RepositoryFixture extends FixtureBase {

    private static final String TEST_DATA = "testData";

    @Test
    public void testGetDocuments() throws IOException {
        Repository repo = new RepositoryImpl(new MemoryBucketImpl("test"));
        Document doc = DocumentImpl.createTransientDocument(repo.getRootFolder(), randomString());
        doc.setData(TEST_DATA.getBytes());
        String pass = "pass";
        doc.setPassword(pass);
        doc.save();
        Document docloaded = findDocByKey(doc.getKey(), repo.getDocuments());
        Assert.assertNotNull(docloaded);
        Assert.assertEquals(docloaded.getPassword(), pass);
        doc.delete();

    }

    private Document findDocByKey(String name, List<Document> documents) {
        for (Document doc : documents) {
            if (doc.getKey().equals(name))
                return doc;
        }
        return null;

    }


}
