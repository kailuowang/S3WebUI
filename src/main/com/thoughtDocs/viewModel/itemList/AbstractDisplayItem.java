package com.thoughtDocs.viewModel.itemList;

import com.thoughtDocs.exception.NotImplementedException;
import com.thoughtDocs.model.Document;
import com.thoughtDocs.model.Folder;
import com.thoughtDocs.model.Item;
import com.thoughtDocs.viewModel.ItemOpener;

import java.io.IOException;
import java.io.Serializable;

/**
 * Created by Kailuo "Kai" Wang
 * Date: Jul 30, 2009
 * Time: 10:04:32 PM
 */
public abstract class AbstractDisplayItem implements DisplayItem, Serializable {
    Item item;


    protected AbstractDisplayItem(Item item) {
        this.item = item;
    }

    public String getName() {
        return item.getName();
    }

    public Item getItem() {
        return item;
    }

    public abstract void open(ItemOpener opener) throws IOException;

    public boolean getDeletable() {
        return true;
    }

    public abstract String getIconFile();

    public static DisplayItem create(Item item) {
        if (item instanceof Document)
            return new DocumentDisplayItem((Document) item);
        if (item instanceof Folder)
            return new FolderDisplayItem((Folder) item);
        throw new NotImplementedException(item.getClass() + " diplay item is not implemented yet");

    }
    public String getPassword() throws IOException {
        String retVal =  item.getUsingPassword();
        return retVal != null ? retVal : "None";
    }


    public int compareTo(Object anotherItem) throws ClassCastException {
       if (!(anotherItem instanceof DisplayItem))
         throw new ClassCastException("A DisplayItem object expected.");

       int typeCompare = - getClass().getName().compareTo(anotherItem.getClass().getName());
       if(typeCompare != 0)
            return typeCompare;
       String thatName = ((DisplayItem) anotherItem).getName();
       return this.getName().compareTo(thatName);
     }


}
