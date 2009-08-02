package com.thoughtDocs.model.impl.s3;

import com.thoughtDocs.model.*;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Created by Kailuo "Kai" Wang
 * Date: Jul 29, 2009
 * Time: 7:34:49 PM
 */
abstract class AbstractItem implements Item {
    protected S3Object s3Object;
    private Path path;
    protected static Repository repository;
    private boolean metaUpdated = false;

    public AbstractItem(S3Object obj, Repository repository) {
        s3Object = obj;
        this.repository = repository;
    }

    protected Map<String, List<String>> getMeta() throws IOException {
        if (!isTransient() && !metaUpdated) {
            updateMeta();
        }
        return s3Object.getMeta();
    }

    protected void updateMeta() throws IOException {
        s3Object.updateMeta();
        if (!isTransient())
            metaUpdated = true;
    }

    public String getName() {
        return getPath().getItemName();
    }

    public String getFolderPath() {
        return getPath().getFolderPath();
    }

    private Path getPath() {
        if (path == null)
            path = new Path(getKey());
        return path;
    }

    public Folder getParent() throws IOException {
        String folderPath = getPath().getFolderPath();
        if (folderPath.length() == 0)
            return new RootFolder(repository);
        else {
            String folderKey = folderPath.substring(0, folderPath.length() - 1);
            return FolderImpl.loadedFromRepository(repository, folderKey);
        }
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

        boolean isFolder = FolderImpl.isFolder(key);
        if (isFolder)
            return FolderImpl.loadedFromS3Object(repo, obj);
        else
            return DocumentImpl.loadedFromS3Object(repo, obj);

    }

    protected static String createKey(Folder parentFolder, String name) {
        if (parentFolder.isTransient())
            throw new UnsupportedOperationException("Cannot create sub folder under transient folder");
        String folderPath = parentFolder.getKey().length() > 0 ? parentFolder.getKey() + "/" : "";
        String key = folderPath + name;
        return key;
    }


    public SecurityMode getSecurityMode() {
        s3Object.getMeta();
        return null;
    }

    public void setSecurityMode() {

    }

}
