package com.thoughtDocs.viewModel.itemList;

import com.thoughtDocs.model.Folder;
import com.thoughtDocs.viewModel.ItemOpener;

import java.io.IOException;
import java.io.Serializable;

/**
 * Created by Kailuo "Kai" Wang
 * Date: Jul 30, 2009
 * Time: 10:17:38 PM
 */
public class FolderDisplayItem extends AbstractDisplayItem implements Serializable {
    protected FolderDisplayItem(Folder item) {
        super(item);
    }

    public Folder getFolder() {
        return (Folder) item;
    }

    public String getPublicUrl() {
        return "";
    }

  

    public void open(ItemOpener opener) throws IOException {
        opener.open(this.getFolder());
    }

    public String getIconFile() {
        return "/img/folder.png";
    }
}
