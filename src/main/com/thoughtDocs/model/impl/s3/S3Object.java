package com.thoughtDocs.model.impl.s3;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: ThoughtWorks
 * Date: Jul 26, 2009
 * Time: 5:20:46 PM
 * To change this template use File | Settings | File Templates.
 */
class S3Object implements Serializable {

    private String key;
    private byte[] data;
    private Map<String, List<String>> meta = new TreeMap<String, List<String>>();
    private S3Bucket bucket;
    private boolean isTransient;
    private String contentType;
    private long size;
    private Date lastModified;


    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public S3Object(S3Bucket bucket, String key) {
        this.bucket = bucket;
        this.key = key;
    }

    public static S3Object createNewTransient(S3Bucket bucket, String key) {
        S3Object retVal = new S3Object(bucket, key);
        retVal.isTransient = true;
        return retVal;
    }

    public static S3Object loadedFromServer(S3Bucket bucket, String key) {
        S3Object retVal = new S3Object(bucket, key);
        retVal.isTransient = false;
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

    public long getSize() {
        if (size == 0 && data != null)
            size = data.length;
        return size;
    }

    public void setSize(long s) {
        size = s;
    }

    public void setData(byte[] data) {
        this.data = data;
        size = data != null ? data.length : 0;
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


    public boolean isTransient() {
        return isTransient;
    }

    public void setTransient(boolean val) {
        this.isTransient = val;
    }

    /**
     * save transient object to database
     *
     * @throws IOException
     */
    public void save() throws IOException {
        bucket.saveObject(this);
        this.isTransient = false;
    }

    /**
     * update content from the s3 server
     *
     * @throws IOException
     */
    public void update() throws IOException {
        bucket.updateObject(this);
    }

    public void delete() throws IOException {
        bucket.removeObject(this);
        isTransient = true;
    }

    public String getSignedURL() {
        return bucket.getSignedUrl(this);
    }


    public void updateMeta() throws IOException {

        bucket.updateObjectMeta(this);

    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        S3Object s3Object = (S3Object) o;

        if (!key.equals(s3Object.key)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return key.hashCode();
    }

    public Date getLastModified() {
        return lastModified;
    }

    public void setLastModified(Date lastModified) {
        this.lastModified = lastModified;
    }
}
