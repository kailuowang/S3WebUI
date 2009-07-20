package com.thoughtdocs.test;

import com.amazon.s3.*;
import org.testng.annotations.Test;

import java.io.IOException;

/**
 * Created by Kailuo "Kai" Wang
 * Date: Jul 10, 2009
 * Time: 1:57:03 PM
 */
public class S3LearningFixture {


    static final String awsAccessKeyId = "AKIAJLYA6OJKIINF2YFQ";
    static final String awsSecretAccessKey = "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";

    //@Test
    public void creationTest() throws IOException {

        AWSAuthConnection connection = new AWSAuthConnection(awsAccessKeyId, awsSecretAccessKey);
        QueryStringAuthGenerator generator =
                new QueryStringAuthGenerator(awsAccessKeyId, awsSecretAccessKey);
        ListAllMyBucketsResponse buckets = connection.listAllMyBuckets(null);

        for (Object entry : buckets.entries) {
            Bucket bucket = (Bucket) entry;
            System.out.println(bucket.name);

            ListBucketResponse response = connection.listBucket(bucket.name, null, null, null, null);
            for (Object be : response.entries) {
                ListEntry le = (ListEntry) be;
                System.out.println(le.key);

                String signedUrl = generator.get(bucket.name, le.key, null);
                System.out.println(signedUrl);
            }
        }
    }

}
