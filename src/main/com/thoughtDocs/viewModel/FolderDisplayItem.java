package com.thoughtDocs.viewModel;

import com.thoughtDocs.model.Folder;

/**
 * Created by Kailuo "Kai" Wang
 * Date: Jul 30, 2009
 * Time: 10:17:38 PM
 */
public class FolderDisplayItem extends AbstractDisplayItem {
    protected FolderDisplayItem(Folder item) {
        super(item);
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

    public Folder open() {
        return getFolder();
    }
}
