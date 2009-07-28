package com.thoughtDocs.model.impl.s3;

import com.amazon.s3.AWSAuthConnection;
import com.amazon.s3.QueryStringAuthGenerator;
import com.thoughtDocs.model.Account;
import com.thoughtDocs.util.S3Config;
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Factory;
import org.jboss.seam.annotations.Name;

import java.io.Serializable;

/**
 * Created by IntelliJ IDEA.
 * User: ThoughtWorks
 * Date: Jul 14, 2009
 * Time: 5:48:09 PM
 * To change this template use File | Settings | File Templates.
 */
@Name("accountFactory")
public class AccountFactory  implements Serializable {


    @Factory(value = "account", scope = ScopeType.SESSION, autoCreate = true)
    public Account loadAccount() {
        AWSAuthConnection connection = new AWSAuthConnection(S3Config.getAwsAccessKey(), S3Config.getAwsSecretKey());
        QueryStringAuthGenerator generator =
                new QueryStringAuthGenerator(S3Config.getAwsAccessKey(), S3Config.getAwsSecretKey());
        SignedURLGenerator signedURLGenerator = new SignedURLGenerator(generator);
        return new AccountImpl(connection, signedURLGenerator);
    }
}
