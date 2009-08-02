package com.thoughtDocs.model;

import java.io.IOException;

/**
 * Created by Kailuo "Kai" Wang
 * Date: Jul 29, 2009
 * Time: 7:34:38 PM
 */
public interface Item {
    String getName();

    String getKey();

    boolean isTransient();

    void update() throws IOException;

    void delete() throws IOException;

    Folder getParent() throws IOException;
}
