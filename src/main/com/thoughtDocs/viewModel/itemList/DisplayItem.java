package com.thoughtDocs.viewModel.itemList;

import com.thoughtDocs.model.Item;
import com.thoughtDocs.viewModel.ItemOpener;

import java.io.IOException;
import java.io.Serializable;

/**
 * Created by Kailuo "Kai" Wang
 * Date: Jul 30, 2009
 * Time: 10:31:30 PM
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
