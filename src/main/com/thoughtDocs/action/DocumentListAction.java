package com.thoughtDocs.action;

import com.thoughtDocs.model.Account;
import com.thoughtDocs.model.Document;
import com.thoughtDocs.model.Repository;
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

/**
 * Created by IntelliJ IDEA.
 * User: ThoughtWorks
 * Date: Jul 20, 2009
 * Time: 3:19:13 PM
 * To change this template use File | Settings | File Templates.
 */
@Scope(ScopeType.SESSION)
@Name("documentListAction")
public class DocumentListAction implements Serializable {

    @Logger
    private Log log;

    @In
    private StatusMessages statusMessages;

    @In
    private Account account;

    @In
    private Repository defaultRepository;

    @DataModel
    private List<Document> documents;

    @DataModelSelection
    private Document doc;

    @Factory
    public void getDocuments() throws IOException {
        documents = defaultRepository.getDocuments();
    }

    public void download(Document doc) {
        FacesManager.instance().redirectToExternalURL((doc.getSignedURL()));
    }

    public void delete(Document doc) throws IOException {
       doc.delete();
        getDocuments();
    }

}
