package com.thoughtDocs.model;

import java.util.Collection;
import java.util.List;
import java.io.IOException;
import java.net.MalformedURLException;

/**
 * Created by Kailuo "Kai" Wang
 * Date: Jul 9, 2009
 * Time: 10:51:21 PM
 */
public interface Repository {
    String getName();

    List<Document> getDocuments() throws IOException;

      
    Account getAccount();

    void delete() throws IOException;
}
