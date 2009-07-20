package com.thoughtDocs.model.impl.s3;

import com.thoughtDocs.model.Document;
import com.thoughtDocs.model.Repository;
import com.amazon.s3.ListEntry;

/**
 * Created by Kailuo "Kai" Wang
 * Date: Jul 10, 2009
 * Time: 6:29:24 PM
 */
public class DocumentImpl implements Document {
    private Repository repository;
    private ListEntry listEntry;

    public DocumentImpl(Repository repository, ListEntry listEntry) {
        this.repository = repository;
        this.listEntry = listEntry;
    }

    public Repository getRepository() {
        return repository;
    }

    public String getName() {
        return listEntry.key;
    }
}
