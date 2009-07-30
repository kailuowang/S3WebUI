package com.thoughtDocs.model.impl.s3;

import com.thoughtDocs.model.Path;
import com.thoughtDocs.model.Item;
import com.thoughtDocs.model.Document;
import com.thoughtDocs.model.Repository;

import java.io.IOException;

/**
 * Created by Kailuo "Kai" Wang
 * Date: Jul 29, 2009
 * Time: 7:34:49 PM
 */
abstract class AbstractItem {
    protected S3Object s3Object;

    public AbstractItem(S3Object obj) {
        s3Object = obj;
    }

    public String getName() {
        return getPath().getItemName();
    }
    
    public String getFolderPath(){
        return getPath().getFolderPath();
    }

    private Path getPath() {
        return new Path(getKey());
    }

    public abstract String getKey();


    public void save() throws IOException {
        s3Object.save();
    }

    public void update() throws IOException {
        s3Object.update();
    }

    public boolean isTransient() {
        return s3Object.isTransient();
    }

    public static Item loadedFromRepository(Repository repo, String key) throws IOException {
            S3Object obj = S3Object.loadedFromServer(((RepositoryImpl) repo).getBucket(), key);
            obj.updateMeta();
            if (obj.isTransient())
                return null;
            else{
                boolean isFolder = key.indexOf(FolderImpl.FOLDER_SUFFIX) >= 0; //todo: better implementation needed here.
                if(isFolder)
                    return  FolderImpl.loadedFromS3Object(repo, obj);
                else
                    return DocumentImpl.loadedFromS3Object(obj);
            }
    }

}
