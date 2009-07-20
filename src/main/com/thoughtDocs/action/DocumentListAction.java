package com.thoughtDocs.action;

import org.jboss.seam.annotations.*;
import org.jboss.seam.annotations.datamodel.DataModel;
import org.jboss.seam.annotations.datamodel.DataModelSelection;
import org.jboss.seam.log.Log;
import org.jboss.seam.international.StatusMessages;
import org.jboss.seam.ScopeType;
import org.jboss.seam.faces.FacesManager;
import com.thoughtDocs.model.Account;
import com.thoughtDocs.model.Repository;
import com.thoughtDocs.model.Document;

import java.util.List;
import java.io.IOException;
import java.io.Serializable;

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
    StatusMessages statusMessages;
    @In
    Account account;

    @DataModel
    List<Document> documents;
    @DataModelSelection
    private Document doc;


    @Factory
    public void getDocuments() throws IOException {
       documents = account.getRepositories().get(0).getDocuments();
    }

    public void download(Document doc){
        FacesManager.instance().redirectToExternalURL((doc.getSignedURL()));
    }
    
}
