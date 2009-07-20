package com.thoughtdocs.test;

import com.amazon.s3.Bucket;
import com.amazon.s3.AWSAuthConnection;
import com.amazon.s3.ListAllMyBucketsResponse;
import com.thoughtdocs.model.impl.s3.AccountImpl;
import com.thoughtdocs.model.Account;
import org.mockito.Mockito;

import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;
import java.net.MalformedURLException;
import java.io.IOException;


/**
 * Created by Kailuo "Kai" Wang
 * Date: Jul 10, 2009
 * Time: 4:57:18 PM
 */
public class DataProvider {

    public Account createMockAccount(Bucket... buckets) throws IOException {
        AWSAuthConnection connection = Mockito.mock(AWSAuthConnection.class);
        ListAllMyBucketsResponse response = Mockito.mock(ListAllMyBucketsResponse.class);
        Mockito.when(connection.listAllMyBuckets(null)).thenReturn(response);
        Mockito.when(response.getEntries()).thenReturn(Arrays.asList(buckets));
        Account account = new AccountImpl(connection);
        return account;
    }
}
