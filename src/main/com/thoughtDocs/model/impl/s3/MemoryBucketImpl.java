package com.thoughtDocs.model.impl.s3;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * a bucket implementation that uses memory to store objects basically for testing purpose
 */
public class MemoryBucketImpl implements S3Bucket {
    private List<S3Object> objects;

    public MemoryBucketImpl(String name) {
        objects = new ArrayList<S3Object>();
        this.name = name;
    }

    private String name;


    public String getName() {
        return name;
    }

    public void saveObject(S3Object obj) throws IOException {
        S3Object toRemove = findByKey(obj.getKey(), objects);
        if (toRemove != null)
            objects.remove(obj);
        objects.add(obj);
    }

    public void updateObjectMeta(S3Object obj) throws IOException {
        findByKey(obj.getKey(), objects).setMeta(obj.getMeta());
    }


    private S3Object findByKey(String key, List<S3Object> objects) {
        for (S3Object object : objects)
            if (object.getKey().equals(key))
                return object;
        return null;
    }

    public void removeObject(S3Object obj) throws IOException {
        objects.remove(findByKey(obj.getKey(), objects));
    }

    public List<S3Object> getObjects() throws IOException {

        List<S3Object> retVal = new ArrayList<S3Object>();
        for (S3Object obj : objects) {
            S3Object returnObj = S3Object.loadedFromServer(this, obj.getKey());
            returnObj.setSize(obj.getSize());
            returnObj.setLastModified(obj.getLastModified());
            retVal.add(returnObj);
        }
        return retVal;
    }

    public void refreshObject(S3Object object) throws IOException {
        for (S3Object obj : objects) {
            if (obj.getKey().equals(object.getKey())) {
                object.setMeta(obj.getMeta());
                object.setData(obj.getData());
                object.setTransient(false);
                return;
            }
        }
        object.setTransient(true);
    }

    public String getSignedUrl(S3Object object) {
        return "http://localhost:8080/thoughtDocs/memoryDL/" + object.getKey();
    }

    public void refreshObjectMeta(S3Object object) throws IOException {
        refreshObject(object);
    }

    public S3Object find(String key) throws IOException {
        return findByKey(key, getObjects());
    }


}
