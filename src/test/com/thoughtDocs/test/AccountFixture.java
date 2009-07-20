package com.thoughtDocs.test;

import com.amazon.s3.AWSAuthConnection;
import com.amazon.s3.Bucket;
import com.amazon.s3.ListAllMyBucketsResponse;
import com.thoughtDocs.model.impl.s3.*;
import com.thoughtDocs.model.*;
import org.mockito.Mockito;
import org.testng.annotations.Test;
import org.testng.Assert;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Collection;

/**
 * Created by Kailuo "Kai" Wang
 * Date: Jul 10, 2009
 * Time: 1:52:11 PM
 */
public class AccountFixture {

    @Test
    public void getRespositories() throws Exception {

        final String repoName = "mockBucket";
        final String repo2Name = "mockBucket2";
        Bucket mockBucket = new Bucket(repoName, new Date());
        Bucket mockBucket2 = new Bucket(repo2Name, new Date());

        DataProvider dp = new DataProvider();
        
        Account account = dp.createMockAccount(mockBucket, mockBucket2);
        Collection<Repository> repos = account.getRepositories();
        Assert.assertEquals(repos.size(), 2);
        Repository repo = new RepositoryImpl(account, mockBucket);
        Repository repo2 = new RepositoryImpl(account, mockBucket2);
        Assert.assertTrue(repos.contains(repo));
        Assert.assertTrue(repos.contains(repo2));

    }

}
