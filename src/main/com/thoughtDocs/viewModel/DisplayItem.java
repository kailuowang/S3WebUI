package com.thoughtDocs.viewModel;

import com.thoughtDocs.model.Item;
import com.thoughtDocs.model.Folder;

import java.io.IOException;

/**
 * Created by Kailuo "Kai" Wang
 * Date: Jul 30, 2009
 * Time: 10:31:30 PM
 */
public interface DisplayItem {
    String getName();

    String getPublicUrl();

    String getPassword() throws IOException;

    Item getItem();

    void open(ItemOpener opener) throws IOException;
}
