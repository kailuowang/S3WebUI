package com.thoughtDocs.model.impl.s3;

import com.thoughtDocs.model.Document;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

/**
 * Created by IntelliJ IDEA.
 * User: ThoughtWorks
 * Date: Jul 20, 2009
 * Time: 4:16:47 PM
 * To change this template use File | Settings | File Templates.
 */
public class DocumentFixture extends FixtureBase {
    private static final String TEST_DATA = "testData";


    @Test
    public void testDocumentPassword() throws IOException {
        RepositoryFactory rf = new RepositoryFactory(testBucket());
        Document doc = DocumentImpl.createTransientDocument(rf.getDefaultRepository(), randomString());
        doc.setData(TEST_DATA.getBytes());
        String pass = "pass";
        doc.setPassword(pass);
        doc.save();
        doc = DocumentImpl.loadedFromRepository(rf.getDefaultRepository(), doc.getName());
        Assert.assertEquals(doc.getPassword(), pass);
        Assert.assertEquals(new String(doc.getData()), TEST_DATA);
        doc.delete();
    }


}

