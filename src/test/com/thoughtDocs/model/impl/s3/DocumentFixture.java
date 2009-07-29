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
    public void testNormalDocumentPassword() throws IOException {
        RepositoryFactory rf = new RepositoryFactory(createBucket());

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

    public void testBlankDocumentPassword() throws IOException {
        RepositoryFactory rf = new RepositoryFactory(createBucket());

        Document doc = DocumentImpl.createTransientDocument(rf.getDefaultRepository(), randomString());
        doc.setData(TEST_DATA.getBytes());
        String pass = "";
        doc.setPassword(pass);
        doc.save();
        doc = DocumentImpl.loadedFromRepository(rf.getDefaultRepository(), doc.getName());
        Assert.assertEquals(doc.getPassword(), pass);
        Assert.assertEquals(new String(doc.getData()), TEST_DATA);
        doc.delete();
    }

    public void testDefaultDocumentPassword() throws IOException {
        RepositoryFactory rf = new RepositoryFactory(createBucket());

        Document doc = DocumentImpl.createTransientDocument(rf.getDefaultRepository(), randomString());
        doc.setData(TEST_DATA.getBytes());
        doc.save();
        doc = DocumentImpl.loadedFromRepository(rf.getDefaultRepository(), doc.getName());
        Assert.assertEquals(doc.getPassword(), "@twthoughts");
        Assert.assertEquals(new String(doc.getData()), TEST_DATA);
        doc.delete();
    }

    protected S3Bucket createBucket() {
        return new MemoryBucketImpl("test");  //To change body of implemented methods use File | Settings | File Templates.
    }
}

