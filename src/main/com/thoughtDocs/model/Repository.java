package com.thoughtDocs.model;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

/**
 * Created by Kailuo "Kai" Wang
 * Date: Jul 9, 2009
 * Time: 10:51:21 PM
 */
public interface Repository  extends Serializable {
    String getName();

    void delete() throws IOException;

    List<Item> findItmes(String folderPath) throws IOException;

    Folder getRootFolder();
}
