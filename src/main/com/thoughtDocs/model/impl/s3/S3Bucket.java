package com.thoughtDocs.model.impl.s3;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;


public interface S3Bucket  extends Serializable {
    String getName();

    void saveObject(S3Object obj) throws IOException;

    void removeObject(S3Object obj) throws IOException;

    List<S3Object> getObjects() throws IOException;

    void updateObject(S3Object object) throws IOException;

    String getSignedUrl(S3Object object);

    void updateObjectMeta(S3Object object) throws IOException;
}
