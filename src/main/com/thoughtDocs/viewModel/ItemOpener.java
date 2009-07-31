package com.thoughtDocs.viewModel;

import com.thoughtDocs.model.Document;
import com.thoughtDocs.model.Folder;

import java.io.IOException;

/**
 * Created by Kailuo "Kai" Wang
 * Date: Jul 31, 2009
 * Time: 4:44:15 PM
 */
public interface ItemOpener {
    void open(Document doc)  throws IOException;

    void open(Folder folder) throws IOException;
}
