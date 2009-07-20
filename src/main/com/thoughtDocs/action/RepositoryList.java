package com.thoughtDocs.action;

import org.jboss.seam.annotations.*;
import org.jboss.seam.annotations.datamodel.DataModel;
import org.jboss.seam.annotations.datamodel.DataModelSelection;
import org.jboss.seam.log.Log;
import org.jboss.seam.international.StatusMessages;
import org.jboss.seam.ScopeType;
import com.thoughtDocs.model.Account;
import com.thoughtDocs.model.Repository;

import java.util.List;
import java.io.IOException;

@Scope(ScopeType.SESSION)
@Name("repositoryList")
public class RepositoryList {
    @Logger
    private Log log;

    @In
    StatusMessages statusMessages;
    @In
    Account account;

    @DataModel
    List<Repository> repositories;
    @DataModelSelection
    private Repository repo;


    public void repositoryList() throws IOException {
        // implement your business logic here
        log.info("repositoryList.repositoryList() action called");
       
        statusMessages.add(account.toString());


    }


    @Factory
    public void getRepositories() throws IOException {
       repositories = account.getRepositories();
    }
    // add additional action methods

}
