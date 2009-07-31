package com.thoughtDocs.action;

import com.thoughtDocs.model.Document;
import com.thoughtDocs.model.Folder;
import com.thoughtDocs.model.Repository;
import com.thoughtDocs.model.Item;
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
public class DocumentListAction implements Serializable {

    @Logger
    private Log log;

    @In
    private StatusMessages statusMessages;

    @In
    private Repository defaultRepository;

    private Folder currentFolder;

    @DataModel
    private List<DisplayItem> documents;

    @DataModelSelection
    private DisplayItem doc;

    public DocumentListAction() {

    }

    public Folder getCurrentFolder(){
        if(currentFolder == null)
            currentFolder = defaultRepository.getRootFolder();
        return currentFolder;
    }

    @Factory
    public void getDocuments() throws IOException {

        documents = new ArrayList<DisplayItem>();
        for(Item item : getCurrentFolder().getItems())
            documents.add(AbstractDisplayItem.create(item));
    }

    private void download(Document doc) {
        FacesManager.instance().redirectToExternalURL((doc.getSignedURL()));
    }

    public void open(DisplayItem displayItem) throws IOException {
        Folder loadedFolder = displayItem.open();
        if(loadedFolder != null)
        {
            currentFolder = loadedFolder;
            getDocuments();
        }
    }

    public void delete(Document doc) throws IOException {
        doc.delete();
        getDocuments();
    }
}
