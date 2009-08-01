package com.thoughtDocs.viewModel.itemList;

import com.thoughtDocs.model.*;
import com.thoughtDocs.viewModel.itemList.DisplayItem;
import com.thoughtDocs.viewModel.itemList.AbstractDisplayItem;
import com.thoughtDocs.viewModel.ItemOpener;
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
    private List<DisplayItem> displayItems;

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
    public void getDisplayItems() throws IOException {

        displayItems = new ArrayList<DisplayItem>();

        if(getCurrentFolder().getParent() != null)
          displayItems.add(new BackToParentDisplayItem());
        for(Item item : getCurrentFolder().getItems())
            displayItems.add(AbstractDisplayItem.create(item));

    }

    public void open(Document doc)  throws IOException {
        FacesManager.instance().redirectToExternalURL((doc.getSignedURL()));
    }

    public void open(Folder folder) throws IOException {
        currentFolder = folder;
        getDisplayItems();
    }

    public void open(BackToParentDisplayItem item) throws IOException {
        currentFolder = getCurrentFolder().getParent();
        getDisplayItems();
    }

    public void openItem(DisplayItem item) throws IOException {
        item.open(this);
        getDisplayItems();
    }

    

    public void delete(DisplayItem item) throws IOException {
        item.getItem().delete();
        getDisplayItems();
    }
    
}
