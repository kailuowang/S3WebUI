package com.thoughtDocs.model;

import java.util.Collection;
import java.io.IOException;

/**
 * Created by Kailuo "Kai" Wang
 * Date: Jul 9, 2009
 * Time: 10:51:21 PM
 */
public interface Repository {
    String getName();

    Collection<Document> getDocuments() throws IOException;

    Account getAccount();

    void delete();
}
