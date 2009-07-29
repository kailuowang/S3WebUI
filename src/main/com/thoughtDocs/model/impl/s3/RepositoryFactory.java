package com.thoughtDocs.model.impl.s3;

import com.thoughtDocs.model.Repository;
import com.thoughtDocs.util.CredentialsConfig;
import org.jboss.seam.annotations.Factory;
import org.jboss.seam.annotations.Name;

import java.io.IOException;

/**
 * Created by IntelliJ IDEA.
 * User: ThoughtWorks
 * Date: Jul 22, 2009
 * Time: 4:07:17 PM
 * To change this template use File | Settings | File Templates.
 */
@Name("repositoryFactory")
public class RepositoryFactory {

    private S3Bucket defaultBucket;


    @Factory(autoCreate = true)
    public Repository getDefaultRepository() throws IOException {
        return new RepositoryImpl(defaultBucket);
    }

    public RepositoryFactory(S3Bucket defaultBucket) {
        this.defaultBucket = defaultBucket;
    }

    public RepositoryFactory() {
        this(new S3BucketImpl(CredentialsConfig.getAWSAccessKey(),
                CredentialsConfig.getAWSSecretKey(),
                CredentialsConfig.getAWSBucketName()));
    }
}
