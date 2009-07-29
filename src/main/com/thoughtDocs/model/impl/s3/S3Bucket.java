package com.thoughtDocs.model.impl.s3;

import java.io.IOException;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: ThoughtWorks
 * Date: Jul 27, 2009
 * Time: 1:09:48 PM
 * To change this template use File | Settings | File Templates.
 */
public interface S3Bucket {
    String getName();

    void saveObject(S3Object obj) throws IOException;

    void removeObject(S3Object obj) throws IOException;

    List<S3Object> getObjects() throws IOException;

    void updateObject(S3Object object) throws IOException;

    String getSignedUrl(S3Object object);

    void updateObjectMeta(S3Object object) throws IOException;
}
