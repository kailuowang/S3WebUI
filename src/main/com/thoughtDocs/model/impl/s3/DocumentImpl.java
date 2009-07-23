package com.thoughtDocs.model.impl.s3;

import com.amazon.s3.*;
import com.thoughtDocs.model.Document;
import com.thoughtDocs.model.Repository;
import org.jboss.seam.annotations.Name;
import org.drools.concurrent.UpdateObject;

import java.io.IOException;
import java.io.Serializable;
import java.util.Map;
import java.util.TreeMap;
import java.util.Arrays;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;

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

    public String getPassword(){
        return "";
    }

    public void setPassword(String password) throws IOException {
        Map headers = new TreeMap();
        headers.put("x-amz-public-password", Arrays.asList(new String[] { password }));

        GetResponse getResponse = connection.getACL(getRepository().getName(), getName(), null);
                byte[] acl = getResponse.object.data;

        Response response = connection.putACL(getRepository().getName(), getName(), new String(acl), headers);

        response.assertSuccess();

    }

    public void setSize(long size) {
        this.listEntry.size = size;
    }

    public long getSize() {
        return listEntry.size;
    }

}
