package com.thoughtDocs.viewModel;

import com.thoughtDocs.model.Item;
import com.thoughtDocs.model.Document;
import com.thoughtDocs.model.Folder;
import com.thoughtDocs.exception.NotImplementedException;

import java.io.IOException;

/**
 * Created by Kailuo "Kai" Wang
 * Date: Jul 30, 2009
 * Time: 10:04:32 PM
 */
public abstract class AbstractDisplayItem implements DisplayItem {
    Item item;
    protected ItemOpener opener;

    protected AbstractDisplayItem(Item item, ItemOpener opener) {
        this.opener = opener;
        this.item = item;
    }

    public String getName() {
        return item.getName();
    }

    public Item getItem() {
        return item;
    }

    public abstract void open() throws IOException;

    public static DisplayItem create(Item item, ItemOpener opener) {
        if(item instanceof Document)
            return new DocumentDisplayItem((Document) item,  opener);
        if(item instanceof Folder)
            return new FolderDisplayItem((Folder) item, opener);
        throw new NotImplementedException(item.getClass() + " diplay item is not implemented yet");

    }
}
