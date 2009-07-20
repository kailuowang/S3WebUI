package com.thoughtDocs.test;

import org.mockito.Mockito;
import org.testng.annotations.Test;
import org.testng.Assert;
import com.amazon.s3.Bucket;
import com.amazon.s3.AWSAuthConnection;
import com.amazon.s3.ListBucketResponse;
import com.amazon.s3.ListEntry;
import com.thoughtDocs.model.Account;
import com.thoughtDocs.model.Repository;
import com.thoughtDocs.model.Document;
import com.thoughtDocs.model.impl.s3.AccountImpl;
import com.thoughtDocs.model.impl.s3.RepositoryImpl;

import java.util.Date;
import java.util.Arrays;
import java.util.Collection;
import java.io.IOException;

/**
 * Created by IntelliJ IDEA.
 * User: ThoughtWorks
 * Date: Jul 20, 2009
 * Time: 4:16:47 PM
 * To change this template use File | Settings | File Templates.
 */
public class DocumentFixture {
    @Test
    public void documentTest() throws IOException {


    }
}
