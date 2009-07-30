package com.thoughtDocs.model.impl.s3;

import com.amazon.s3.ListBucketResponse;
import com.amazon.s3.ListEntry;
import com.thoughtDocs.model.Document;
import com.thoughtDocs.model.Repository;
import com.thoughtDocs.exception.NotImplementedException;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kailuo "Kai" Wang
 * Date: Jul 10, 2009
 * Time: 3:58:15 PM
 */
public class RepositoryImpl implements Repository, Serializable {


    S3Bucket bucket;

    private RepositoryImpl() {
    }

    public RepositoryImpl(S3Bucket bucket) {
        this.bucket = bucket;
    }

    public String getName() {
        return bucket.getName();
    }

    public List<Document> getDocuments() throws IOException {
        List<Document> retVal = new ArrayList<Document>();
        List<S3Object> objects = bucket.getObjects();
        for (S3Object obj : objects) {
			//skip folders
			if(obj.getKey().endsWith("$folder$")) continue;
			
            retVal.add(DocumentImpl.loadedFromRepository(this, obj.getKey()));
        }
        return retVal;
    }


    public void delete() throws IOException {
        throw new NotImplementedException();
    }


    public S3Bucket getBucket() {
        return bucket;
    }
}
