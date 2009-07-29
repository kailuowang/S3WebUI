package com.thoughtDocs.action;

import com.thoughtDocs.exception.NotImplementedException;
import com.thoughtDocs.model.Repository;
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.*;
import org.jboss.seam.annotations.datamodel.DataModel;
import org.jboss.seam.annotations.datamodel.DataModelSelection;
import org.jboss.seam.international.StatusMessages;
import org.jboss.seam.log.Log;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

@Scope(ScopeType.SESSION)
@Name("repositoryListAction")
public class RepositoryListAction implements Serializable {
    @Logger
    private Log log;

    @In
    StatusMessages statusMessages;
    @In

    @DataModel
    List<Repository> repositories;
    @DataModelSelection
    private Repository repo;


    @Factory
    public void getRepositories() throws IOException {
        throw new NotImplementedException();
    }
    // add additional action methods

}
