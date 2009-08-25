package com.thoughtDocs.viewModel;

import com.thoughtDocs.model.Document;
import com.thoughtDocs.model.Repository;
import com.thoughtDocs.model.impl.s3.DocumentImpl;
import com.thoughtDocs.model.impl.s3.RepositoryFactory;
import com.thoughtDocs.model.impl.s3.UserS3Store;
import com.thoughtDocs.model.impl.s3.UserS3;
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
    @RequestParameter
    private String username;

    @Logger
    private Log log;

    @In(create = true)
    UserS3Store userStore;

    @In
    private StatusMessages statusMessages;

    private Document doc;

    
     @In(create = true)
    private RepositoryFactory repositoryFactory;

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
        UserS3 user = userStore.find(username);

        if(doc == null && user != null) {
            doc = DocumentImpl.findFromRepository(repositoryFactory.createRepoFromUser(user), key);
        }
        if(doc == null || user == null )
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
