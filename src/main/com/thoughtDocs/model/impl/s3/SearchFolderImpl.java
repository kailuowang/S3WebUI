package com.thoughtDocs.model.impl.s3;

import com.thoughtDocs.model.*;

import java.util.List;
import java.io.IOException;

/**
 * Created by Kailuo "Kai" Wang
 * Date: Aug 13, 2009
 * Time: 11:13:10 PM
 */
public class SearchFolderImpl implements SearchFolder {
    private String term;
    private Repository repository;

    public SearchFolderImpl(Repository repo, String term) {
        this.term = term;
        this.repository = repo;
    }

    public String getTerm() {
        return this.term;
    }

    public List<Item> getItems() throws IOException {
        return repository.searchItems(getTerm());
    }

    public Repository getRepository() {
        return this.repository;
    }

    public boolean getAllowNewItem() {
        return false;
    }

    public String getName() {
        return "Search results for \"" + getTerm() + "" ;
    }

    public String getKey() {
        return getName();
    }

    public boolean isTransient() {
        return true;
    }

    public void refresh() throws IOException {

    }

    public void delete() throws IOException {
       throwReadonlyException();
    }

    public Folder getParent() throws IOException {
        return getRepository().getRootFolder();
    }

    public SecurityMode getSecurityMode() throws IOException {
        return null;
    }

    public void setSecurityMode(SecurityMode mode) throws IOException {
        throwReadonlyException();
    }

    public String getPassword() throws IOException {
        return null;
    }

    public void setPassword(String password) throws IOException {
        throwReadonlyException();
    }

    public String getUsingPassword() throws IOException {
        return null;
    }

    public void save() throws IOException {
        throwReadonlyException();
    }

    private void throwReadonlyException(){
         throw new UnsupportedOperationException("Search Folder cannot be modified/deleted");
    }
}
