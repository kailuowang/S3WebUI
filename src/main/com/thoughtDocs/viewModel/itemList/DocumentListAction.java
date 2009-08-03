package com.thoughtDocs.viewModel.itemList;

import com.thoughtDocs.model.Document;
import com.thoughtDocs.model.Folder;
import com.thoughtDocs.model.Item;
import com.thoughtDocs.model.Repository;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Collection;
import java.util.Collections;


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

    public Folder getCurrentFolder() {
        if (currentFolder == null)
            currentFolder = defaultRepository.getRootFolder();
        return currentFolder;
    }

    @Factory
    public void getDisplayItems() throws IOException {
        List<DisplayItem> items = new ArrayList<DisplayItem>();

        for (Item item : getCurrentFolder().getItems())
            items.add(AbstractDisplayItem.create(item));
        Collections.sort(items);
        displayItems = new ArrayList<DisplayItem>( items.size() + 1 );

        if (getCurrentFolder().getParent() != null)
            displayItems.add(new BackToParentDisplayItem());
        displayItems.addAll(items);
    }

    @Begin(nested = true)
    public void open(Document doc) throws IOException {
        FacesManager.instance().redirectToExternalURL((doc.getSignedURL()));
    }

    @Begin(nested = true)
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
        currentFolder = item.getItem().getParent();
        getDisplayItems();
    }

}
