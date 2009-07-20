package com.thoughtDocs.model.impl.s3;

import com.amazon.s3.AWSAuthConnection;
import com.amazon.s3.Bucket;
import com.amazon.s3.QueryStringAuthGenerator;
import com.thoughtDocs.model.Account;
import com.thoughtDocs.model.Repository;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import static org.jboss.seam.ScopeType.SESSION;

/**
 * Created by Kailuo "Kai" Wang
 * Date: Jul 9, 2009
 * Time: 11:10:55 PM
 */
@Scope(SESSION)
@Name("account")
public class AccountImpl implements Account, Serializable {

    private AWSAuthConnection awsAuthConnection;
    SignedURLGenerator generator;


    private AccountImpl() {}

    public AccountImpl(AWSAuthConnection awsAuthConnection, SignedURLGenerator generator) {
        this.awsAuthConnection = awsAuthConnection;
        this.generator = generator;
    }

    public List<Repository> getRepositories() throws IOException {
        List<Repository> retVal = new ArrayList<Repository>();
        for (Object o : awsAuthConnection.listAllMyBuckets(null).getEntries()) {
            Repository repo = new RepositoryImpl(this, (Bucket) o);
            retVal.add(repo);
        }
        return retVal;
    }

    public SignedURLGenerator getSignedURLGenerator() {
        return generator;
    }

    public AWSAuthConnection getAwsAuthConnection() {
        return awsAuthConnection;

    }
}
