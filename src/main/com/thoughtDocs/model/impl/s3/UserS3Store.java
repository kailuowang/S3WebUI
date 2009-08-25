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
import java.util.*;

/**
 *
 */
@Name("userStore")
@Scope(ScopeType.APPLICATION)
public class UserS3Store implements Serializable {

    File file;
    Set<UserS3> users;

    public UserS3Store() throws IOException {



        file = new File("users.yml");
        users = new HashSet<UserS3>();
        Collection loaded = loadUsers(file);
        if (loaded != null) {
            for (Object user : loaded) {
                users.add((UserS3) user);
            }
        }
        if(users.size() == 0)
            persist(loadDefaultUser());

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

        UserS3 user = new UserS3("admin",
                "",
                CredentialsConfig.getAWSAccessKey(),
                CredentialsConfig.getAWSSecretKey(),
                CredentialsConfig.getAWSBucketName());
        user.changePassword(CredentialsConfig.getAdminPassword());
        return user;
    }

    void persist(UserS3 user) throws IOException {
        if(!users.contains(user))
            users.add(user);
        persist();
    }

    public void persist() throws IOException {
        if (!file.exists())
            file.createNewFile();
        Yaml.dump(users, file);
    }

    public int size() {
        return users.size();
    }

    public UserS3 find(String username) {

        for (UserS3 user : users) {
            if (user.getUsername().equals(username))
                return user;
        }
        return null;
    }

    public void remove(UserS3 user) throws IOException {
        if(users.remove(user)) {
            persist();
        }
    }

    /**
     * validate
     *
     * @return validation errors, empty if there is no validation error
     */
    public List<String> validateNewUser(UserS3 user, String invitationCode) {
        if (!CredentialsConfig.getInvitationCode().equals(invitationCode)) {
            return Arrays.asList("incorrect invitation code");
        }

        
        
        if (find(user.getUsername()) != null) {
            return Arrays.asList("Username " + user.getUsername() + " is already taken.");
        }

        return user.validate();
    }
}
