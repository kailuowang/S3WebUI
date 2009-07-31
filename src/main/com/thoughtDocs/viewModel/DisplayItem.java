package com.thoughtDocs.viewModel;

import com.thoughtDocs.model.Item;
import com.thoughtDocs.model.Folder;

/**
 * Created by Kailuo "Kai" Wang
 * Date: Jul 30, 2009
 * Time: 10:31:30 PM
 */
public interface DisplayItem {
    String getName();

    String getPublicUrl();

    String getPassword();

    Item getItem();

    Folder open();
}
