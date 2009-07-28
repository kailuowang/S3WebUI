package com.thoughtDocs.model.impl.s3;

import com.amazon.s3.AWSAuthConnection;
import com.amazon.s3.ListEntry;
import com.amazon.s3.Response;
import com.amazon.s3.S3Object;
import com.thoughtDocs.model.Document;
import com.thoughtDocs.model.Repository;
import org.jboss.seam.annotations.Name;

import java.io.IOException;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by Kailuo "Kai" Wang
 * Date: Jul 10, 2009
 * Time: 6:29:24 PM
 */
@Name("uploadingDocument")
public class DocumentImpl implements Document, Serializable {
    private RepositoryImpl repository;
    private AccountImpl account;
    private AWSAuthConnection connection;
    private ListEntry listEntry;
    private byte[] data;
    private String contentType;
    private final String AMAZON_HEADER_PREFIX = "x-amz-";
    private static final String PUBLIC_PASSWORD_META_KEY = "public-password";
    private String password;

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public void setRepository(Repository repository) {
        this.repository = (RepositoryImpl) repository;
        account = (AccountImpl) repository.getAccount();
        connection = account.getAwsAuthConnection();
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public Repository getRepository() {
        return repository;
    }

    public String getName() {
        return listEntry.key;
    }

    public void setName(String newName) {
        listEntry.key = newName;
    }

    public String getSignedURL() {
        return account.getSignedURLGenerator().getSignedURL(this);
    }

    public DocumentImpl(Repository repository, ListEntry listEntry) {
        this.listEntry = listEntry;
        setRepository(repository);
    }

    public DocumentImpl() {
        listEntry = new ListEntry();
    }


    public void delete() throws IOException {
        Response response = connection.delete(getRepository().getName(), getName(), null);
        response.assertSuccess();
    }

    public String getPassword() throws IOException {
        if (password == null && repository != null) {
            password = getPasswordFrom(connection.head(getRepository().getName(), getName(), null).object);

        }

        return password;
    }


    public void setPassword(String password) throws IOException {
        this.password = password;
    }

    public void upload(Repository repo) throws IOException {
        setRepository(repo);
        Map meta = null;
        if (password != null) {
            meta = new TreeMap();
            meta.put(PUBLIC_PASSWORD_META_KEY, Arrays.asList(new String[]{password}));
        }
        S3Object object = new S3Object(getData(), meta);
        Response response = connection.put(getRepository().getName(), getName(), object, null);
        response.assertSuccess();
    }

    public void refresh() throws IOException {
        S3Object object = connection.get(getRepository().getName(), getName(), null).object;
        data = object.data;
        password = getPasswordFrom(object);
    }


    public void setSize(long size) {
        this.listEntry.size = size;
    }

    public long getSize() {
        return listEntry.size;
    }

    private String getPasswordFrom(S3Object object) {
        Object passwords = object.metadata.get(PUBLIC_PASSWORD_META_KEY);
        if (passwords == null)
            return null;
        return (String) ((List) passwords).get(0);
    }

    /**
     *
     * @return public url for external user to download (password needed)
     */
    public String getPublicUrl(){
         return "http://thoughtfiles.com/" + getName() ;
    }

    public void setPublicUrl(String url){
        //this is readonly
    }

}
