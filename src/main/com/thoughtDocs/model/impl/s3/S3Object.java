package com.thoughtDocs.model.impl.s3;

import java.util.Map;
import java.util.List;
import java.io.IOException;

/**
 * Created by IntelliJ IDEA.
 * User: ThoughtWorks
 * Date: Jul 26, 2009
 * Time: 5:20:46 PM
 * To change this template use File | Settings | File Templates.
 */
class S3Object {

    private String key;
    private byte[] data;
    private Map<String, List<String>> meta;
    private S3Bucket bucket;
    private boolean isTransient;
    
    public S3Object(S3Bucket bucket, String key) {
        this.bucket = bucket;
        this.key = key;
    }

    public static S3Object createNewTransient(S3Bucket bucket, String key){
        S3Object retVal = new S3Object(bucket, key);
        retVal.isTransient = true;
        return retVal;
    }

    public static S3Object loadedFromServer(S3Bucket bucket,String key){
        S3Object retVal = new S3Object(bucket, key);
        retVal.isTransient = true;
        return retVal;
    }

    public S3Bucket getBucket() {
        return bucket;
    }

    public void setBucket(S3Bucket bucket) {
        this.bucket = bucket;
    }

    public S3Object() {
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public Map<String, List<String>> getMeta() {
        return meta;
    }

    public void setMeta(Map<String, List<String>> meta) {
        this.meta = meta;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public boolean isTransient() {
        return isTransient;
    }

    /**
     * save transient object to database
     * @throws IOException
     */
    public void save() throws IOException {
        bucket.addObject(this);
        this.isTransient = false;
    }
    
    /**
     * update content from the s3 server
     * @throws IOException
     */
    public void update() throws IOException {
        if(isTransient)
            throw new RuntimeException("trying to update an object that is transient");
        bucket.update(this);
    }

    public void delete() throws IOException {
        bucket.remove(this);
        isTransient = true;
    }

    
}
