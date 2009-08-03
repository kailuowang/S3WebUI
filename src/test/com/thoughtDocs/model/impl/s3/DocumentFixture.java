package com.thoughtDocs.model.impl.s3;

import com.thoughtDocs.model.Document;
import com.thoughtDocs.model.Folder;
import com.thoughtDocs.model.Repository;
import com.thoughtDocs.model.SecurityMode;
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
    public void testDocumentKeyNameWithPath() {
        String name = randomString();
        String key = "ge/" + name;
        Document doc = DocumentImpl.createTransientDocument(getTestRootFolder(), key);
        Assert.assertEquals(doc.getName(), name);
    }

    private Folder getTestRootFolder() {
        return new RepositoryImpl(testBucket()).getRootFolder();
    }

    @Test
    public void testDocumentKeyNameWithoutPath() {
        String name = randomString();
        String key = name;
        Document doc = DocumentImpl.createTransientDocument(getTestRootFolder(), key);
        Assert.assertEquals(doc.getName(), name);
    }



}

