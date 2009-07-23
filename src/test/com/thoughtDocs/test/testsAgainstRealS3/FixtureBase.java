package com.thoughtDocs.test.testsAgainstRealS3;

import com.thoughtDocs.model.Account;
import com.thoughtDocs.model.Repository;
import com.thoughtDocs.model.impl.s3.AccountFactory;
import com.thoughtDocs.model.impl.s3.RepositoryFactory;

import java.io.IOException;

/**
 * Created by IntelliJ IDEA.
 * User: ThoughtWorks
 * Date: Jul 22, 2009
 * Time: 9:47:23 PM
 * To change this template use File | Settings | File Templates.
 */
public class FixtureBase {
    Account account;
    Repository defaultRepository;

    public FixtureBase() throws IOException {
        account = new AccountFactory().loadAccount();
        RepositoryFactory rf = new RepositoryFactory(account);
        defaultRepository = rf.getDefaultRepository();


    }


}
