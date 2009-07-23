package com.thoughtDocs.model.impl.s3;

import com.amazon.s3.*;
import com.thoughtDocs.model.Account;
import com.thoughtDocs.model.Document;
import com.thoughtDocs.model.Repository;

import javax.persistence.Id;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kailuo "Kai" Wang
 * Date: Jul 10, 2009
 * Time: 3:58:15 PM
 */
public class RepositoryImpl implements Repository, Serializable {


    private AccountImpl account;
    Bucket bucket;

    private RepositoryImpl() {
    }

    public RepositoryImpl(Account account, Bucket bucket) {
        this.account = (AccountImpl) account;
        this.bucket = bucket;
    }

    @Id
    public String getName() {
        return bucket.name;
    }

    public List<Document> getDocuments() throws IOException {
        List<Document> retVal = new ArrayList<Document>();
        ListBucketResponse response = getAWSAuthConnection().listBucket(getName(), null, null, null, null);
        for (Object be : response.getEntries()) {
            ListEntry le = (ListEntry) be;
            retVal.add(new DocumentImpl(this, le));
        }
        return retVal;
    }

    public Account getAccount() {
        return account;
    }

    public void delete() throws IOException {
        getAWSAuthConnection().deleteBucket(getName(), null);
    }

    public void addDocument(Document doc) throws IOException {
        S3Object object = new S3Object(doc.getData(), null);
        Response response = getAWSAuthConnection().put(this.getName(), doc.getName(), object, null);
        ((DocumentImpl)doc).setRepository(this);
        response.assertSuccess();((DocumentImpl)doc).setRepository(this);
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RepositoryImpl that = (RepositoryImpl) o;
        if (account != null ? !account.equals(that.account) : that.account != null) return false;
        if (bucket != null ? !bucket.equals(that.bucket) : that.bucket != null) return false;

        return true;
    }

    public int hashCode() {
        int result;
        result = (account != null ? account.hashCode() : 0);
        result = 31 * result + (bucket != null ? bucket.hashCode() : 0);
        return result;
    }

    private AWSAuthConnection getAWSAuthConnection() {
        return account.getAwsAuthConnection();
    }

}
