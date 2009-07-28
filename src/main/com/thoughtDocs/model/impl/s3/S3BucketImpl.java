package com.thoughtDocs.model.impl.s3;

import com.amazon.s3.*;
import com.thoughtDocs.model.Document;

import java.net.MalformedURLException;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

/**
 * Created by IntelliJ IDEA.
 * User: ThoughtWorks
 * Date: Jul 26, 2009
 * Time: 5:14:17 PM
 * To change this template use File | Settings | File Templates.
 */
class S3BucketImpl implements S3Bucket {
    private AWSAuthConnection awsAuthConnection;
    private QueryStringAuthGenerator queryStringGenerator;
    private String name;

    public String getName() {
        return name;
    }

    public S3BucketImpl(String accessKeyId, String secretKey, String name) {
        this.name = name;
        awsAuthConnection = new AWSAuthConnection(accessKeyId, secretKey);
        queryStringGenerator = new QueryStringAuthGenerator(accessKeyId, secretKey);
    }


    public void addObject(S3Object obj) throws IOException {
        obj.setBucket(this);
        com.amazon.s3.S3Object object = new com.amazon.s3.S3Object(obj.getData(), obj.getMeta());
        Response response = awsAuthConnection.put(name, obj.getKey(), object, null);
        response.assertSuccess();
    }

    public void removeObject(S3Object obj) throws IOException{
        Response response = awsAuthConnection.delete(name, obj.getKey(), null);
        response.assertSuccess();
    }

    public List<S3Object> getObjects() throws IOException {
          List<S3Object> retVal = new ArrayList<S3Object>();
          ListBucketResponse response = awsAuthConnection.listBucket(name, null, null, null, null);
          for (Object be : response.getEntries()) {
              ListEntry le = (ListEntry) be;
              retVal.add( S3Object.loadedFromServer(this, le.key));
          }
          return retVal;
      }


    /**
     * update data for the object
     * @param object
     * @throws IOException
     */
    public void update(S3Object object) throws IOException {
        com.amazon.s3.S3Object obj = awsAuthConnection.get(name, object.getKey(), null).object;
        object.setData(obj.data);
        object.setMeta(obj.metadata);
    }


    

}
