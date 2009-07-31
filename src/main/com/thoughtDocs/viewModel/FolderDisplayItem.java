package com.thoughtDocs.viewModel;

import com.thoughtDocs.model.Folder;

import java.io.IOException;

/**
 * Created by Kailuo "Kai" Wang
 * Date: Jul 30, 2009
 * Time: 10:17:38 PM
 */
public class FolderDisplayItem extends AbstractDisplayItem {
    protected FolderDisplayItem(Folder item, ItemOpener opener) {
        super(item, opener);
    }

    public Folder getFolder() {
        return (Folder) item;
    }

    public String getPublicUrl() {
        return "";
    }

    public String getPassword() {
        return "";
    }

    public void open() throws IOException {
        opener.open(this.getFolder());
    }
}
