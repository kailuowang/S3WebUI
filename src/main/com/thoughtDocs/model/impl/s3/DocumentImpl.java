package com.thoughtDocs.model.impl.s3;

import com.thoughtDocs.model.Document;
import com.thoughtDocs.model.Repository;
import com.amazon.s3.*;

/**
 * Created by Kailuo "Kai" Wang
 * Date: Jul 10, 2009
 * Time: 6:29:24 PM
 */
public class DocumentImpl implements Document {
    private RepositoryImpl repository;
    private AccountImpl account;
    private AWSAuthConnection connection;
    private ListEntry listEntry;

    public DocumentImpl(Repository repository, ListEntry listEntry) {
        this.repository = (RepositoryImpl) repository;
        this.listEntry = listEntry;
        account = (AccountImpl) repository.getAccount();
    }

    public Repository getRepository() {
        return repository;
    }

    public String getName() {
        return listEntry.key;
    }

    public void delete() {
        throw new UnsupportedOperationException();
    }

    public String getSignedURL(){
        return account.getSignedURLGenerator().getSignedURL(this);
    }
}
