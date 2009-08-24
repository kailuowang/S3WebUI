package com.thoughtDocs.viewModel.itemList;

import com.thoughtDocs.model.Item;
import com.thoughtDocs.viewModel.ItemOpener;

import java.io.IOException;
import java.io.Serializable;

/**
 * data item to be displayed in the document list grid
 */
public interface DisplayItem extends Serializable , Comparable {
    String getName();

    String getPublicUrl();

    String getPassword() throws IOException;

    Item getItem();

    void open(ItemOpener opener) throws IOException;

    boolean getDeletable();

    String getIconFile();

    String getSize();

    String getLastModified();

    boolean getHasSecurity();
}
