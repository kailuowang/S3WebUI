package com.thoughtDocs.viewModel.itemList;

import com.thoughtDocs.model.Item;
import com.thoughtDocs.viewModel.ItemOpener;

import java.io.IOException;

/**
 * Created by Kailuo "Kai" Wang
 * Date: Aug 1, 2009
 * Time: 11:07:59 AM
 */
public class BackToParentDisplayItem implements DisplayItem {


    public String getName() {
        return "..";
    }

    public String getPublicUrl() {
        return "";
    }

    public String getPassword() throws IOException {
        return "";
    }

    public Item getItem() {
        return null;
    }

    public void open(ItemOpener opener) throws IOException {
        opener.open(this);
    }

    public boolean getDeletable() {
        return false;
    }
}
