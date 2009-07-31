package com.thoughtDocs.viewModel;

import com.thoughtDocs.model.*;
import com.thoughtDocs.viewModel.DisplayItem;
import com.thoughtDocs.viewModel.AbstractDisplayItem;
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.*;
import org.jboss.seam.annotations.datamodel.DataModel;
import org.jboss.seam.annotations.datamodel.DataModelSelection;
import org.jboss.seam.faces.FacesManager;
import org.jboss.seam.international.StatusMessages;
import org.jboss.seam.log.Log;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;


@Scope(ScopeType.CONVERSATION)
@Name("documentListAction")
public class DocumentListAction implements Serializable, ItemOpener {

    @Logger
    private Log log;

    @In
    private StatusMessages statusMessages;

    @In
    private Repository defaultRepository;

    private Folder currentFolder;

    @DataModel
    private List<DisplayItem> items;

    @DataModelSelection
    private DisplayItem item;

    public DocumentListAction() {

    }


    public Repository getDefaultRepository() {
        return defaultRepository;
    }

    public Folder getCurrentFolder(){
        if(currentFolder == null)
            currentFolder = defaultRepository.getRootFolder();
        return currentFolder;
    }

    @Factory
    public void getItems() throws IOException {

        items = new ArrayList<DisplayItem>();
        
        for(Item item : getCurrentFolder().getItems())
            items.add(AbstractDisplayItem.create(item));
    }

    public void open(Document doc)  throws IOException {
        FacesManager.instance().redirectToExternalURL((doc.getSignedURL()));
    }

    public void open(Folder folder) throws IOException {
        currentFolder = folder;
        getItems();
    }

    public void open(DisplayItem item) throws IOException {
        item.open(this);
        getItems();
    }

    

    public void delete(DisplayItem item) throws IOException {
        item.getItem().delete();
        getItems();
    }
}
