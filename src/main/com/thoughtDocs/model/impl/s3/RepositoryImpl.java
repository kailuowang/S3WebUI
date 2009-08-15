package com.thoughtDocs.model.impl.s3;

import com.thoughtDocs.exception.NotImplementedException;
import com.thoughtDocs.model.*;

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
    private Folder rootFolder;

    private RepositoryImpl() {
    }

    public RepositoryImpl(S3Bucket bucket) {
        this.bucket = bucket;
        rootFolder = new RootFolder(this);
    }

    public String getName() {
        return bucket.getName();
    }


    public void delete() throws IOException {
        throw new NotImplementedException();
    }

    /**
     * Find items under a path
     * @param folderPath
     * @return
     * @throws IOException
     */
    public List<Item> findItmes(String folderPath) throws IOException {
        List<Item> retVal = new ArrayList<Item>();
        for (S3Object obj : bucket.getObjects()) {
            String key = obj.getKey();
            Path p = new Path(key);
            if (p.getFolderPath().equals(folderPath))
                retVal.add(AbstractItem.loadedFromRepository(this, obj.getKey()));
        }
        return retVal;
    }

    /**
     * Search item using search term in file name
     * @param term
     * @return
     * @throws IOException
     */
    public List<Item> searchItmes(String term) throws IOException {
        String[] keywords = term.split(" ");
        List<Item> retVal = new ArrayList<Item>();
        for (S3Object obj : bucket.getObjects()) {
            String itemName = new Path(obj.getKey()).getItemName();
            boolean match = true;
            for(String keyword :keywords){
                if(itemName.indexOf(keyword)<0)
                  {
                      match = false;
                      break;
                  }
            }
            if(match)
                retVal.add(AbstractItem.loadedFromRepository(this, obj.getKey()));
        }
        return retVal;
    }

    public Folder getRootFolder() {
        return rootFolder;
    }


    public S3Bucket getBucket() {
        return bucket;
    }
}
