package com.thoughtDocs.model;

import java.util.List;
import java.io.IOException;

/**
 * Created by Kailuo "Kai" Wang
 * Date: Jul 9, 2009
 * Time: 10:52:54 PM
 */
public interface Account {
    public List<Repository> getRepositories() throws IOException;
}
