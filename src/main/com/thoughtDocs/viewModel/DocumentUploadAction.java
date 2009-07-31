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


    public void upload(Document doc) throws IOException {
        doc.save();
        documentListAction.getDocuments();
    }

    @Factory(value = "uploadingDocument", autoCreate = true)
    public Document createUploadingDocument() {
        //the "newfile" key set here will be immidietly replaced by real file name of the uploaded filename
        Document doc = DocumentImpl.createTransientDocument(defaultRepository.getRootFolder(), "newfile");
        return doc;
    }


}

