package com.thoughtDocs.model.impl.s3;

import com.thoughtDocs.model.Folder;
import com.thoughtDocs.model.Item;
import com.thoughtDocs.model.Repository;

import java.io.IOException;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: ThoughtWorks
 * Date: Jul 29, 2009
 * Time: 11:25:23 AM
 * To change this template use File | Settings | File Templates.
 */
public class FolderImpl extends AbstractItem implements Folder {
    public static final String FOLDER_SUFFIX = "_$folder$";

    private FolderImpl(S3Object obj, Repository repository) {
        super(obj, repository);
    }

    public String getKey() {
        String key = s3Object.getKey();
        return key.replace(FOLDER_SUFFIX, "");
    }

    public void delete() throws IOException {
        for (Item item : getItems())
            item.delete();
        s3Object.delete();
    }

    private static Folder createTransientFolder(Repository repo, String key) {
        S3Object obj = S3Object.createNewTransient(((RepositoryImpl) repo).getBucket(), createS3ObjectKey(key));
        return new FolderImpl(obj, repo);
    }

    private static String createS3ObjectKey(String key) {
        return key + FOLDER_SUFFIX;
    }


    public static Folder loadedFromRepository(Repository repo, String key) {
        S3Object obj = S3Object.loadedFromServer(((RepositoryImpl) repo).getBucket(), createS3ObjectKey(key));
        return loadedFromS3Object(repo, obj);
    }

    static Folder loadedFromS3Object(Repository repo, S3Object obj) {
        return new FolderImpl(obj, repo);
    }

    public static Folder createTransientFolder(Folder parentFolder, String name) {
        String key = createKey(parentFolder, name);
        return createTransientFolder(parentFolder.getRepository(), key);
    }


    public List<Item> getItems() throws IOException {
        return repository.findItmes(subItemsFolderPath());
    }

    private String subItemsFolderPath() {
        return getKey() + "/";
    }

    public Repository getRepository() {
        return repository;
    }

    public boolean getAllowNewItem() {
        return true;
    }

    static boolean isFolder(String key) {
        return key.indexOf(FOLDER_SUFFIX) >= 0;
    }
}
