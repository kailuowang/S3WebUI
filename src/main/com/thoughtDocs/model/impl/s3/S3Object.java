package com.thoughtDocs.model.impl.s3;

import java.util.Map;
import java.util.List;

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

    public S3Object(S3Bucket bucket, String key) {
        this.bucket = bucket;
        this.key = key;
    }

    public S3Bucket getBucket() {
        return bucket;
    }

    public void setBucket(S3Bucket bucket) {
        this.bucket = bucket;
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

    
}
