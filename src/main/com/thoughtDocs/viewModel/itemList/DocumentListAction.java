package com.thoughtDocs.viewModel.itemList;

import com.thoughtDocs.model.Document;
import com.thoughtDocs.model.Folder;
import com.thoughtDocs.model.Item;
import com.thoughtDocs.model.Repository;
import com.thoughtDocs.model.impl.s3.SearchFolderImpl;
import com.thoughtDocs.model.impl.s3.RepositoryFactory;
import com.thoughtDocs.viewModel.ItemOpener;
import com.thoughtDocs.viewModel.ViewEvents;
import com.thoughtDocs.exception.RepositoryUnavailableException;
import org.jboss.seam.ScopeType;
import org.jboss.seam.core.Events;
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
import java.util.Collections;


@Scope(ScopeType.CONVERSATION)
@Name("documentListAction")
public class DocumentListAction implements Serializable, ItemOpener {

    @Logger
    private Log log;

    @In
    private StatusMessages statusMessages;

    @In(create = true)
    private RepositoryFactory repositoryFactory;


    private Folder currentFolder;

    @DataModel
    private List<DisplayItem> displayItems;

   
    @DataModelSelection
    private DisplayItem item;
    private String searchTerm;

    public DocumentListAction() {
    }

    public Repository getDefaultRepository() {
        try {
            return repositoryFactory.getDefaultRepository();
        } catch (IOException e) {
            throw  new RepositoryUnavailableException(e);
        }
    }

    @Out
    public Folder getCurrentFolder() {
        if (currentFolder == null)
            currentFolder = getDefaultRepository().getRootFolder();
        return currentFolder;
    }

    @Factory
    @Observer({ ViewEvents.CurrentLocationChanged,
                ViewEvents.NewFolderCreated,
                ViewEvents.ItemDeleted,
                ViewEvents.DocumentUploaded,
                ViewEvents.BucketChanged })
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

    public void open(Document doc) throws IOException {
        FacesManager.instance().redirectToExternalURL((doc.getSignedURL()));
    }


    public void open(Folder folder) throws IOException {
        currentFolder = folder;
        Events.instance().raiseEvent(ViewEvents.CurrentLocationChanged);
    }

    public void open(BackToParentDisplayItem item) throws IOException {
        goUpLevel();
    }


    public void goUpLevel() throws IOException {
       open(getCurrentFolder().getParent());

    }


    public void openItem(DisplayItem item) throws IOException {
        item.open(this);
    }

    @RaiseEvent(ViewEvents.ItemDeleted)
    public void delete(DisplayItem item) throws IOException {
        item.getItem().delete();
       // currentFolder = item.getItem().getParent();  //TODO: reinspect if this sentence is stil needed.
    }

    public boolean getHasLevelAbove() throws IOException {
        return getCurrentFolder().getParent() != null;
    }

    @Out
    public String getCurrentPath() throws IOException {

        return "/" + getCurrentFolder().getKey();
    }

    public void setSearchTerm(String searchTerm) {
        this.searchTerm = searchTerm;
    }

    public String getSearchTerm() {
        return searchTerm;
    }


    public void search() throws IOException {
        if(searchTerm != null && searchTerm.length() > 0)
        {
            open( new SearchFolderImpl(getDefaultRepository(), searchTerm));
        }
    }
}
