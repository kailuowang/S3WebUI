package com.thoughtDocs.model.impl.s3;

import com.thoughtDocs.util.S3Config;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.*;

/**
 * Created by IntelliJ IDEA.
 * User: ThoughtWorks
 * Date: Jul 27, 2009
 * Time: 11:32:23 PM
 * These tests relies on valid s3 service account to run
 */
public class S3ServicesFixture {
    private static final String TEST_DATA = "testData";

    private S3Bucket createBucket() {
        return new S3BucketImpl(S3Config.getAwsAccessKey(), S3Config.getAwsSecretKey(), "thoughtdocstest");
    }

    private String randomString() {
        SecureRandom random = null;
        try {
            random = SecureRandom.getInstance("SHA1PRNG");
        } catch (NoSuchAlgorithmException e) {
            throw new UnknownError(e.getMessage());
        }
        random.setSeed(new Date().getTime());
        String randomString = String.valueOf(random.nextLong());
        return randomString;
    }

    @Test
    public void getObjects() throws IOException {
        S3Bucket bucket = createBucket();
        List<S3Object> objects = bucket.getObjects();
        assert objects != null;
    }


    @Test
    public void testAddObjectTest() throws IOException {
        S3Bucket bucket = createBucket();
        int oldSize = bucket.getObjects().size();
        S3Object obj = createRandomTestObj(bucket);
        Assert.assertEquals(bucket.getObjects().size(), oldSize + 1);
        obj.update();
        Assert.assertEquals(new String(obj.getData()), TEST_DATA);
        deleteAfterTest(obj);
    }

    private S3Object createRandomTestObj(S3Bucket bucket) throws IOException {
        String randomString = randomString();
        S3Object obj = S3Object.createNewTransient(bucket, randomString);
        obj.setData(TEST_DATA.getBytes());
        obj.save();
        return obj;
    }

    private void deleteAfterTest(S3Object obj) {
        try {
            obj.delete();
        } catch (Exception e) {
            //delete is not tested here.
        }
    }


    @Test
    public void testDeleteS3Object() throws IOException {
        S3Bucket bucket = createBucket();
        int oldSize = bucket.getObjects().size();
        S3Object obj = createRandomTestObj(bucket);
        Assert.assertEquals(bucket.getObjects().size(), oldSize + 1);
        obj.update();
        Assert.assertEquals(new String(obj.getData()), TEST_DATA);
        obj.delete();
        Assert.assertEquals(bucket.getObjects().size(), oldSize );
    }

    @Test
    public void testObjectMeta() throws IOException {

        S3Bucket bucket = createBucket();
        S3Object obj = S3Object.createNewTransient(bucket, randomString());
         Map<String, List<String>> meta = new TreeMap<String, List<String>>();
        meta.put("test", Arrays.asList("value"));
        obj.setMeta(meta);
        obj.setData(TEST_DATA.getBytes());
        obj.save();


        obj.update();
        meta = obj.getMeta();

        List<String> list = meta.get("test");
        Assert.assertEquals(list.size(), 1);
        assert list.contains("value");
        obj.delete();
        deleteAfterTest(obj);
    }

}
