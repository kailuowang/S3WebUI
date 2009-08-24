package com.thoughtDocs.model.impl.s3;

import com.thoughtDocs.model.Document;
import com.thoughtDocs.model.Folder;
import com.thoughtDocs.model.Repository;
import com.thoughtDocs.model.SecurityMode;
import com.thoughtDocs.util.CredentialsConfig;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNull;
import org.testng.annotations.Test;

import java.io.IOException;

/**
 * Created by Kailuo "Kai" Wang
 * Date: Aug 2, 2009
 * Time: 11:48:34 AM
 */
public class SecurityFixture extends FixtureBase {
    private static final String TEST_DATA = "testData";

    @Test
    public void testDocumentSpecificPassword() throws IOException {
        Repository repository = createTestRepo();
        Document doc = DocumentImpl.createTransientDocument(repository.getRootFolder(), randomString());
        doc.setData(TEST_DATA.getBytes());
        String pass = "pass";
        doc.setPassword(pass);
        doc.save();
        doc = DocumentImpl.findFromRepository(repository, doc.getKey());
        assertEquals(doc.getPassword(), pass);
        assertEquals(new String(doc.getData()), TEST_DATA);
        doc.delete();
    }

    @Test
    public void shouldNotLoseDataWhenUpdateSecurity() throws IOException {
        Repository repository = createTestRepo();
        Document doc = DocumentImpl.createTransientDocument(repository.getRootFolder(), randomString());
        doc.setData(TEST_DATA.getBytes());

        doc.save();
        doc = DocumentImpl.findFromRepository(repository, doc.getKey());
        String pass = randomString();
        doc.setPassword(pass);
        doc.update();

        doc = DocumentImpl.findFromRepository(repository, doc.getKey());
        assertEquals(doc.getPassword(), pass);
        assertEquals(new String(doc.getData()), TEST_DATA);
        doc.delete();
    }


    @Test
    public void testDocumentSecurityMode() throws IOException {
        Repository repository = createTestRepo();
        Document doc = DocumentImpl.createTransientDocument(repository.getRootFolder(), randomString());
        doc.setSecurityMode(SecurityMode.INHERITED);
        doc.setData(TEST_DATA.getBytes());
        doc.save();
        doc = DocumentImpl.findFromRepository(repository, doc.getKey());
        assertEquals(doc.getSecurityMode(), SecurityMode.INHERITED);

        doc.delete();
    }


    @Test
    public void testFolderSecurityMode() throws IOException {
        Repository repository = createTestRepo();
        Folder folder = FolderImpl.createTransientFolder(repository.getRootFolder(), randomString());
        folder.setSecurityMode(SecurityMode.UNPROTECTED);
        folder.save();
        folder = FolderImpl.findFromRepository(repository, folder.getKey());
        assertEquals(folder.getSecurityMode(), SecurityMode.UNPROTECTED);
        folder.delete();
    }

    private Repository createTestRepo() {
        Repository repository = new RepositoryImpl(testBucket());
        return repository;
    }

    @Test
    public void testDefaultUsingPassword() throws IOException {
        Repository repository = createTestRepo();
        Document doc = DocumentImpl.createTransientDocument(repository.getRootFolder(), randomString());
        doc.setPassword("lalalal"); //this password should be used by default        
        doc.save();

        assertEquals(doc.getUsingPassword(), CredentialsConfig.getDefaultPassword());
    }

    @Test
    public void testSpecificPassword() throws IOException {
        Repository repository = createTestRepo();
        Document doc = DocumentImpl.createTransientDocument(repository.getRootFolder(), randomString());
        String testPassword = randomString();
        doc.setPassword(testPassword);
        doc.setSecurityMode(SecurityMode.SPECIFIED_PASSWORD);
        doc.save();

        assertEquals(doc.getUsingPassword(), testPassword);
    }

    @Test
    public void testUnrotected() throws IOException {
        Repository repository = createTestRepo();
        Document doc = DocumentImpl.createTransientDocument(repository.getRootFolder(), randomString());
        String testPassword = randomString();
        doc.setPassword(testPassword);
        doc.setSecurityMode(SecurityMode.UNPROTECTED);
        doc.save();
        assertNull(doc.getUsingPassword());
    }

    @Test
    public void testInherited() throws IOException {
        Repository repository = createTestRepo();
        Folder folder = FolderImpl.createTransientFolder(repository.getRootFolder(), randomString());
        folder.setSecurityMode(SecurityMode.SPECIFIED_PASSWORD);
        String testPassword = randomString();
        folder.setPassword(testPassword);
        folder.save();

        Document doc = DocumentImpl.createTransientDocument(folder, randomString());
        doc.setSecurityMode(SecurityMode.INHERITED);
        doc.save();
        assertEquals(doc.getUsingPassword(), testPassword);

    }
}
