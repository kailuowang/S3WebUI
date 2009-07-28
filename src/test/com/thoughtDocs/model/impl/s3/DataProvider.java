package com.thoughtDocs.model.impl.s3;

import com.amazon.s3.Bucket;
import com.amazon.s3.AWSAuthConnection;
import com.amazon.s3.ListAllMyBucketsResponse;
import com.thoughtDocs.model.impl.s3.AccountImpl;
import com.thoughtDocs.model.impl.s3.SignedURLGenerator;
import com.thoughtDocs.model.Account;
import org.mockito.Mockito;

import java.util.Arrays;
import java.io.IOException;


/**
 * Created by Kailuo "Kai" Wang
 * Date: Jul 10, 2009
 * Time: 4:57:18 PM
 */
public class DataProvider {
    AWSAuthConnection connection;
    SignedURLGenerator generator;


    public AWSAuthConnection getConnection() {
        return connection;
    }

    public SignedURLGenerator getGenerator() {
        return generator;
    }

    public Account createMockAccount(Bucket... buckets) throws IOException {
        connection = Mockito.mock(AWSAuthConnection.class);
        ListAllMyBucketsResponse response = Mockito.mock(ListAllMyBucketsResponse.class);
        generator = Mockito.mock(SignedURLGenerator.class);

        Mockito.when(connection.listAllMyBuckets(null)).thenReturn(response);
        Mockito.when(response.getEntries()).thenReturn(Arrays.asList(buckets));
        Account account = new AccountImpl(connection, generator);
        return account;
    }
}
