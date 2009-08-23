package com.thoughtDocs.model.impl.s3;

import com.thoughtDocs.model.Document;
import com.thoughtDocs.model.Folder;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import static com.thoughtDocs.util.Finder.*;

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
        return createTestRepo().getRootFolder();
    }

    private RepositoryImpl createTestRepo() {
        return new RepositoryImpl(testBucket());
    }

    @Test
    public void testDocumentKeyNameWithoutPath() {
        String name = randomString();
        String key = name;
        Document doc = DocumentImpl.createTransientDocument(getTestRootFolder(), key);
        Assert.assertEquals(doc.getName(), name);
    }

    @Test
    public void shouldGetSize() throws IOException {
        RepositoryImpl repo = createTestRepo();
        String name = randomString();
        String key = name;
        Document doc = DocumentImpl.createTransientDocument(repo.getRootFolder(), key);
        doc.setData(TEST_DATA.getBytes());
        doc.save();
        doc = (Document) find(repo.getRootFolder().getItems(), key);
        Assert.assertEquals((int)doc.getSize(), TEST_DATA.length());
    }


}

