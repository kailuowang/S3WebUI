package com.thoughtDocs.test.testsAgainstRealS3;

import org.mockito.Mockito;
import org.testng.annotations.Test;
import org.testng.Assert;
import com.amazon.s3.Bucket;
import com.amazon.s3.AWSAuthConnection;
import com.amazon.s3.ListBucketResponse;
import com.amazon.s3.ListEntry;
import com.thoughtDocs.model.Account;
import com.thoughtDocs.model.Repository;
import com.thoughtDocs.model.Document;
import com.thoughtDocs.model.impl.s3.*;

import java.util.Date;
import java.util.Arrays;
import java.util.Collection;
import java.util.Calendar;
import java.io.IOException;

/**
 * Created by IntelliJ IDEA.
 * User: ThoughtWorks
 * Date: Jul 20, 2009
 * Time: 4:16:47 PM
 * To change this template use File | Settings | File Templates.
 */
public class DocumentFixture extends FixtureBase {

    public DocumentFixture() throws IOException {
    }

    @Test
    public void testAddDocumentTest() throws IOException {
        int oldSize = defaultRepository.getDocuments().size();
        DocumentImpl doc = new DocumentImpl();
        doc.setName(Calendar.getInstance().toString());
        doc.setData("testData".getBytes());
        defaultRepository.addDocument(doc);
        Assert.assertEquals(defaultRepository.getDocuments().size(), oldSize + 1);
    }

    @Test
    public void testDeleteDocument() throws IOException {
        DocumentImpl doc = new DocumentImpl();
        doc.setName(Calendar.getInstance().toString());
        doc.setData("testData".getBytes());
        defaultRepository.addDocument(doc);
        int oldSize = defaultRepository.getDocuments().size();
        doc.delete();
        Assert.assertEquals(defaultRepository.getDocuments().size(), oldSize - 1);
    }

    @Test
    public void testDocumentPassword() throws IOException {
        DocumentImpl doc = new DocumentImpl();
        doc.setName(Calendar.getInstance().toString());
        doc.setData("testData".getBytes());
        defaultRepository.addDocument(doc);
        String pass = "pass";
        doc.setPassword(pass);
        Assert.assertEquals(doc.getPassword(), pass);

    }
}

