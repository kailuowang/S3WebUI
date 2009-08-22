package com.thoughtDocs.model.impl.s3;

import com.thoughtDocs.exception.FailedToLoadUsersException;
import com.thoughtDocs.util.CredentialsConfig;
import org.ho.yaml.Yaml;
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 *
 */
@Name("userStore")
@Scope(ScopeType.APPLICATION)
public class UserS3Store implements Serializable {

    File file;
    Set<UserS3> users;
    UserS3 defaultUser;

    public UserS3Store() throws IOException {

        defaultUser = loadDefaultUser();

        file = new File("users.yml");
        users = new HashSet<UserS3>();
        Collection loaded = loadUsers(file);
        if (loaded != null) {
            for (Object user : loaded) {
                users.add((UserS3) user);
            }
        }
    }

    private static Collection loadUsers(File ymlFile) throws FileNotFoundException {

        Object loaded = null;
        if (ymlFile.exists()) {
            try {
                loaded = Yaml.load(ymlFile);
            } catch (org.ho.yaml.exception.YamlException e) {
                throw new FailedToLoadUsersException("error reading " + ymlFile.getAbsolutePath(), e);
            }
        }
        return (Collection) loaded;
    }

    private UserS3 loadDefaultUser() {

        return new UserS3("admin",
                CredentialsConfig.getAdminPassword(),
                CredentialsConfig.getAWSAccessKey(),
                CredentialsConfig.getAWSSecretKey(),
                CredentialsConfig.getAWSBucketName());
    }

    public void add(UserS3 user) throws IOException {
        users.add(user);
        persist();
    }

    private void persist() throws IOException {
        if (!file.exists())
            file.createNewFile();
        Yaml.dump(users, file);
    }

    public int size() {
        return users.size() + 1;
    }

    public UserS3 find(String username) {
        if (defaultUser.getUsername().equals(username))
            return defaultUser;
        for (UserS3 user : users) {
            if (user.getUsername().equals(username))
                return user;
        }
        return null;
    }
}
