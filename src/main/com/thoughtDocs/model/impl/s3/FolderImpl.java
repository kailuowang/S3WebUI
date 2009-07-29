package com.thoughtDocs.model.impl.s3;

import com.thoughtDocs.model.Folder;
import com.thoughtDocs.model.Repository;

/**
 * Created by IntelliJ IDEA.
 * User: ThoughtWorks
 * Date: Jul 29, 2009
 * Time: 11:25:23 AM
 * To change this template use File | Settings | File Templates.
 */
public class FolderImpl implements Folder {
    private static final String FOLDER_SUFFIX ="_$folder$";
    private S3Object s3Object;

    public FolderImpl(S3Object obj) {
        this.s3Object = obj;
    }


    public String getKey(){
        String key = s3Object.getKey();
        return key.replace(FOLDER_SUFFIX, "");
    }

    public static Folder createTransientFolder(Repository repo, String key) {
       S3Object obj = S3Object.createNewTransient(((RepositoryImpl) repo).getBucket(), key + FOLDER_SUFFIX);
       return new FolderImpl(obj);
    }
}
