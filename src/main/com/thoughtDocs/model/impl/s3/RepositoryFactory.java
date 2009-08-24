package com.thoughtDocs.model.impl.s3;

import com.thoughtDocs.model.Document;
import com.thoughtDocs.model.Folder;
import com.thoughtDocs.model.Repository;
import com.thoughtDocs.model.SecurityMode;
import com.thoughtDocs.util.ThoughtDocsConfig;
import com.thoughtDocs.viewModel.ViewEvents;
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.*;
import org.jboss.seam.security.Identity;

import java.io.IOException;

 
@Scope(ScopeType.SESSION)
@Name("repositoryFactory")
public class RepositoryFactory {

    Repository defaultRepository;

    @In
    Identity identity;

    @In(create = true)
    UserS3Store userStore;

    public RepositoryFactory() {}

    @Factory(autoCreate = true , scope = ScopeType.SESSION)
    public Repository getDefaultRepository() throws IOException {

        if (defaultRepository == null)
        {
            defaultRepository =  ThoughtDocsConfig.getRunOnMemoryBucket() ?
                                     createTestRepository()
                                    : createRepoFromUser();

        }
        return defaultRepository;
    }

    @Observer( ViewEvents.BucketChanged)
    public void reloadRepository(){
        defaultRepository = null;

    }

    private Repository createRepoFromUser() {
        UserS3 user = userStore.find(identity.getCredentials().getUsername());
        assert user != null;

        return new RepositoryImpl( user.bucket());
    }

    private Repository createTestRepository() throws IOException {
        Repository repo = new RepositoryImpl(new MemoryBucketImpl("Development Test Repository"));
        Document d1 = DocumentImpl.createTransientDocument(repo.getRootFolder(), "areadme.txt");
        d1.setData("this is root!".getBytes());
        d1.save();

        Folder f1 = FolderImpl.createTransientFolder(repo.getRootFolder(), "ThoughtWorks Documents");
        f1.setSecurityMode(SecurityMode.SPECIFIED_PASSWORD);
        f1.setPassword("1122");
        f1.save();
        Document d11 = DocumentImpl.createTransientDocument(f1, "InternalMemo1.txt");
        d11.setData("this is internal!".getBytes());
        d11.save();
        Document d12 = DocumentImpl.createTransientDocument(f1, "ConfidentialMemo1.txt");
        d12.setData("this is Confidential!".getBytes());
        d12.save();

        Folder f2 = FolderImpl.createTransientFolder(repo.getRootFolder(), "GE Documents");
        f2.save();
        Document d21 = DocumentImpl.createTransientDocument(f2, "DO PT #2.txt");
        d21.setData("GE assigment Sow!".getBytes());
        d21.save();
        Folder f3 = FolderImpl.createTransientFolder(repo.getRootFolder(), "Public Documents");
        f3.setSecurityMode(SecurityMode.UNPROTECTED);
        f3.save();
        Document d31 = DocumentImpl.createTransientDocument(f3, "thoughtworksAgenda.txt");
        d31.setData("Martin Fowler".getBytes());
        d31.save();
        Document d32 = DocumentImpl.createTransientDocument(f3, "Agile.txt");
        d32.setData("Agile is tdd".getBytes());
        d32.save();

        Folder f31 = FolderImpl.createTransientFolder(f3, "Presentations");
        f31.save();

        Document d311 = DocumentImpl.createTransientDocument(f31, "DistributedAgile.txt");
        d311.setData("a challenge".getBytes());
        d311.save();
        return repo;
    }


}
