package com.thoughtDocs.model.impl.s3;

import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Factory;
import org.jboss.seam.ScopeType;
import com.thoughtDocs.model.Account;
import com.amazon.s3.AWSAuthConnection;
import com.amazon.s3.QueryStringAuthGenerator;

/**
 * Created by IntelliJ IDEA.
 * User: ThoughtWorks
 * Date: Jul 14, 2009
 * Time: 5:48:09 PM
 * To change this template use File | Settings | File Templates.
 */
@Name("accountFactory")
public class AccountFactory {
    static final String awsAccessKeyId = "AKIAJLYA6OJKIINF2YFQ";
    static final String awsSecretAccessKey = "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";

    @Factory(value = "account", scope = ScopeType.SESSION, autoCreate = true)
    public Account loadAccount() {
        AWSAuthConnection connection = new AWSAuthConnection(awsAccessKeyId, awsSecretAccessKey);

        QueryStringAuthGenerator generator =
                new QueryStringAuthGenerator(awsAccessKeyId, awsSecretAccessKey);
        SignedURLGenerator signedURLGenerator = new SignedURLGenerator(generator);
        return new AccountImpl(connection, signedURLGenerator);
    }
}
