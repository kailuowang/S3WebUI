package com.thoughtDocs.viewModel;

import com.thoughtDocs.model.Document;
import com.thoughtDocs.model.Repository;
import com.thoughtDocs.model.impl.s3.DocumentImpl;
import org.jboss.seam.annotations.*;
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
    private Repository defaultRepository;
    private String password;
    private String name;
    private String contentType;
    private byte[] data;


    public void upload() throws IOException {
        Document doc = DocumentImpl.createTransientDocument(defaultRepository.getRootFolder(), name);
        doc.setPassword(password);
        doc.setContentType(contentType);
        doc.setData(data);
        doc.save();
        documentListAction.getItems();
    }




    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getContentType() {
        return contentType;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public byte[] getData() {
        return data;
    }
}

