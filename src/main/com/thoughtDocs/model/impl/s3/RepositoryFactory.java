package com.thoughtDocs.model.impl.s3;

import com.thoughtDocs.model.Account;
import com.thoughtDocs.model.Repository;
import org.jboss.seam.annotations.Factory;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;

import java.io.IOException;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: ThoughtWorks
 * Date: Jul 22, 2009
 * Time: 4:07:17 PM
 * To change this template use File | Settings | File Templates.
 */
@Name("repositoryFactory")
public class RepositoryFactory {

    @In
    private Account account;

    @Factory(autoCreate = true)
    public Repository getDefaultRepository() throws IOException {
        List<Repository> repos = account.getRepositories();
        for (Repository repo : repos) {
            if (repo.getName().equals("thoughtdocstest"))
                return repo;
        }
        return repos.get(0);

    }


    public RepositoryFactory(Account account) {
        this.account = account;
    }

    public RepositoryFactory() {
    }
}
