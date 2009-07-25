package com.thoughtDocs.action;

import com.thoughtDocs.model.Account;
import com.thoughtDocs.model.Document;
import com.thoughtDocs.model.Repository;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.international.StatusMessages;
import org.jboss.seam.log.Log;

import java.io.IOException;
import java.io.Serializable;

@Name("documentUploadAction")
public class DocumentUploadAction implements Serializable {

    @Logger
    private Log log;

    @In
    private StatusMessages statusMessages;
    @In
    DocumentListAction documentListAction;

    @In
    private Account account;

    @In
    private Repository defaultRepository;

    private String password;


    public void upload(Document doc) throws IOException {
        doc.setPassword(password);
        doc.upload(defaultRepository);
        documentListAction.getDocuments();
    }


    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }
}

