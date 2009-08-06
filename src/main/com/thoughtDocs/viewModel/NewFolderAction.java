package com.thoughtDocs.viewModel;

import com.thoughtDocs.model.Folder;
import com.thoughtDocs.model.SecurityMode;
import com.thoughtDocs.model.impl.s3.FolderImpl;
import com.thoughtDocs.viewModel.itemList.DocumentListAction;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.RaiseEvent;

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
    private SecurityMode securityMode;
    private String password;


    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @In
    private Folder currentFolder;


    @RaiseEvent("NewFolderCreated")
    public void create() throws IOException {
        Folder newFolder = FolderImpl.createTransientFolder(currentFolder, name);
        newFolder.setSecurityMode(securityMode);
        newFolder.setPassword(password);
        newFolder.save();


    }


    public void setSecurityMode(SecurityMode securityMode) {
        this.securityMode = securityMode;
    }

    public SecurityMode getSecurityMode() {
        return securityMode;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }
}
