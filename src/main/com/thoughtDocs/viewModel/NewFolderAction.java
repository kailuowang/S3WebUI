package com.thoughtDocs.viewModel;

import com.thoughtDocs.model.Folder;
import com.thoughtDocs.model.impl.s3.FolderImpl;
import com.thoughtDocs.viewModel.itemList.DocumentListAction;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;

import java.io.IOException;
import java.io.Serializable;

/**
 * Created by Kailuo "Kai" Wang
 * Date: Aug 1, 2009
 * Time: 11:00:00 PM
 */
@Name("newFolderAction")
public class NewFolderAction implements Serializable {
    @In
    DocumentListAction documentListAction;
    private String name;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }


    public void create() throws IOException {
        Folder newFolder = FolderImpl.createTransientFolder(documentListAction.getCurrentFolder(), name);
        newFolder.save();
        documentListAction.open(newFolder);

    }
}
