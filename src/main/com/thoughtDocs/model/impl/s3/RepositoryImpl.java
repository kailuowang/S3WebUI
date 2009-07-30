package com.thoughtDocs.model.impl.s3;

import com.thoughtDocs.exception.NotImplementedException;
import com.thoughtDocs.model.Document;
import com.thoughtDocs.model.Repository;
import com.thoughtDocs.model.Item;
import com.thoughtDocs.model.Path;

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
            retVal.add(DocumentImpl.loadedFromRepository(this, obj.getKey()));
        }
        return retVal;
    }


    public void delete() throws IOException {
        throw new NotImplementedException();
    }

    public List<Item> findItmes(String folderPath) throws IOException {
        List<S3Object> objects = bucket.getObjects();
         List<Item> retVal = new ArrayList<Item>();
        for (S3Object obj : objects) {
            String key = obj.getKey();
            Path p = new Path(key);  //todo: better implemetation needed here
            if(p.getFolderPath().equals(folderPath))
                retVal.add(AbstractItem.loadedFromRepository (this, obj.getKey()));
        }
        return retVal;
    }


    public S3Bucket getBucket() {
        return bucket;
    }
}
