package com.thoughtDocs.viewModel;

import com.thoughtDocs.model.Document;
import com.thoughtDocs.model.Repository;
import com.thoughtDocs.model.impl.s3.DocumentImpl;
import com.thoughtDocs.viewModel.itemList.DocumentListAction;
import com.thoughtDocs.exception.DocumentNotFoundException;
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.*;
import org.jboss.seam.annotations.web.RequestParameter;
import org.jboss.seam.international.StatusMessages;
import org.jboss.seam.log.Log;

import java.io.IOException;
import java.io.Serializable;

/**
 * Created by IntelliJ IDEA.
 * User: ThoughtWorks
 * Date: Jul 23, 2009
 * Time: 7:06:41 PM
 * To change this template use File | Settings | File Templates.
 */
@Scope(ScopeType.CONVERSATION)
@Name("documentPublicDownloadAction")
public class DocumentPublicDownloadAction implements Serializable {

    @RequestParameter
    private String key;
    @Logger
    private Log log;

    @In
    private StatusMessages statusMessages;

    private Document doc;

    @In
    private Repository defaultRepository;

    private String password;

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public String getFilename() {
        return doc != null ? doc.getKey() : "";
    }

    public void checkFile() throws IOException {
        if(doc == null)
            doc = DocumentImpl.findFromRepository(defaultRepository, key);
        if(doc == null)
        {
            throw new DocumentNotFoundException(key);
        }else if(doc.getUsingPassword() == null)
            download();
    }

    public void testPassword() throws IOException {
        if(doc == null)
        {
            statusMessages.add("File not found");
            return;
        }

        if ( password == null || !password.equals(doc.getUsingPassword())) {
            statusMessages.add("File and password does not match, please try again" );
            return;
        }
       
        download();
        //test url http://thoughtfiles.com:8080/thoughtDocs/areadme.txt
    }

    @End
    private void download() throws IOException {
        new DocumentListAction().open(doc);
    }
}
