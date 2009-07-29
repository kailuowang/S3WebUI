package com.thoughtDocs.model.impl.s3;

import com.thoughtDocs.model.impl.s3.DocumentImpl;
import com.thoughtDocs.model.impl.s3.FixtureBase;
import com.thoughtDocs.model.Document;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: ThoughtWorks
 * Date: Jul 20, 2009
 * Time: 4:16:47 PM
 * To change this template use File | Settings | File Templates.
 */
public class DocumentFixture extends FixtureBase {
    private static final String TEST_DATA = "testData";


    public DocumentFixture() throws IOException {
    }







    @Test
    public void testDocumentPassword() throws IOException {
        RepositoryFactory rf = new RepositoryFactory(new MemoryBucketImpl("test"));

        Document doc = DocumentImpl.createTransientDocument(rf.getDefaultRepository(), randomString());
        doc.setData(TEST_DATA.getBytes());
        String pass = "pass";
        doc.setPassword(pass);
        doc.save();
        doc =  DocumentImpl.createTransientDocument(rf.getDefaultRepository(), doc.getName());
        doc.update();
        Assert.assertEquals(doc.getPassword(), pass);
        Assert.assertEquals(new String(doc.getData()), TEST_DATA);
        doc.delete();
    }
}

