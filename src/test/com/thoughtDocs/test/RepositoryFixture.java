package com.thoughtDocs.test;

import org.testng.annotations.Test;
import org.testng.Assert;
import org.mockito.Mockito;
import com.amazon.s3.Bucket;
import com.amazon.s3.AWSAuthConnection;
import com.amazon.s3.ListBucketResponse;
import com.amazon.s3.ListEntry;
import com.thoughtDocs.model.impl.s3.AccountImpl;
import com.thoughtDocs.model.*;
import java.util.Date;
import java.util.Collection;
import java.util.Arrays;
import java.io.IOException;

/**
 * Created by Kailuo "Kai" Wang
 * Date: Jul 10, 2009
 * Time: 4:47:10 PM
 */
public class RepositoryFixture {

    @Test
    public void testGetDocuments() throws IOException {
        DataProvider dp = new DataProvider();
        final String mockBucketName = "mockBucket";
        final String documentKey = "someMockDocument.txt";

        Bucket bucket = new Bucket(mockBucketName, new Date());
        Account account = dp.createMockAccount(bucket);
        AWSAuthConnection connection = ((AccountImpl) account).getAwsAuthConnection();

        ListBucketResponse response = Mockito.mock(ListBucketResponse.class);
        ListEntry le = new ListEntry();
        le.key =   documentKey;
        Mockito.when(response.getEntries()).thenReturn(Arrays.asList(le));
        Mockito.when(connection.listBucket(bucket.name, null,null,null,null)).thenReturn(response);

        Repository repo = account.getRepositories().iterator().next();

        Assert.assertEquals(repo.getName(), bucket.name);

        Collection<Document> documents = repo.getDocuments();
        Assert.assertEquals(1, documents.size());
    }
}
