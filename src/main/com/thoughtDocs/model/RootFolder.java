package com.thoughtDocs.model;

import java.io.IOException;
import java.util.List;

/**
 * Created by Kailuo "Kai" Wang
 * Date: Jul 30, 2009
 * Time: 7:48:21 PM
 */
public class RootFolder implements Folder {
    private Repository repository;
    private static final String SUBITEMS_FOLDER_PATH = "";

    public RootFolder(Repository repository) {
        this.repository = repository;
    }

    public void save() throws IOException {
        //no need to save root folder
    }

    public List<Item> getItems() throws IOException {
        return repository.findItmes(SUBITEMS_FOLDER_PATH);
    }

    public Repository getRepository() {
        return repository;
    }

    public String getName() {
        return SUBITEMS_FOLDER_PATH;
    }

    public String getKey() {
        return SUBITEMS_FOLDER_PATH;
    }

    public boolean isTransient() {
        return false;
    }

    public void update() throws IOException {
        //nothing needed
    }

    public void delete() throws IOException {
        throw new UnsupportedOperationException("Rootfolder cannot be deleted");

    }

    public Folder getParent() throws IOException {
        return null;
    }
}
