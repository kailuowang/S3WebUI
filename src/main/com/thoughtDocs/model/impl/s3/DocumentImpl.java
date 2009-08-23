package com.thoughtDocs.model.impl.s3;

import com.thoughtDocs.model.Document;
import com.thoughtDocs.model.Folder;
import com.thoughtDocs.model.Repository;
import com.thoughtDocs.util.ThoughtDocsConfig;
import com.thoughtDocs.util.URLEncoder;

import java.io.IOException;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Kailuo "Kai" Wang
 * Date: Jul 10, 2009
 * Time: 6:29:24 PM
 */
public class DocumentImpl extends AbstractItem implements Document, Serializable {

    private DocumentImpl(S3Object obj, Repository repo) {
        super(obj, repo);
    }

    private static Document createTransientDocument(Repository repo, String key) {
        S3Object obj = S3Object.createNewTransient(((RepositoryImpl) repo).getBucket(), key);
        return new DocumentImpl(obj, repo);
    }

    public static Document createTransientDocument(Folder parentFolder, String name) {
        String key = createKey(parentFolder, name);
        return createTransientDocument(parentFolder.getRepository(), key);
    }


    public String getKey() {
        return s3Object.getKey();
    }

    /**
     * get the document by name from server
     *
     * @param repo
     * @param key
     * @return null if no such file found on server
     * @throws IOException
     */
    public static Document findFromRepository(Repository repo, String key) throws IOException {

        S3Object obj = ((RepositoryImpl) repo).getBucket().find(key);
        if(obj == null)
            return null;
        return loadedFromS3Object(repo, obj);
    }

    public static Document loadedFromS3Object(Repository repo, S3Object obj) throws IOException {
        return new DocumentImpl(obj, repo);
    }


    public String getSignedURL() {
        return s3Object.getSignedURL();
    }

    public byte[] getData() throws IOException {
        if (s3Object.getData() == null && !isTransient())
            refresh();
        return s3Object.getData();
    }

    public void setData(byte[] data) {
        s3Object.setData(data);
    }

    public String getContentType() {
        return s3Object.getContentType();
    }

    public Date getLastModified(){
        return s3Object.getLastModified();
    }

    public long getSize(){
        return s3Object.getSize();
    }

    public void setContentType(String contentType) {
        s3Object.setContentType(contentType);
    }

    public void delete() throws IOException {
        s3Object.delete();
    }


    /**
     * @return public url for external user to download (password needed)
     */
    public String getPublicUrl() {
        return ThoughtDocsConfig.getPublicDownloadSite() + "/" + getKey().replace(getName(), URLEncoder.encode(getName()));
    }

}
