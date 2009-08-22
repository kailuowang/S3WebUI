package com.thoughtDocs.action;

import com.thoughtDocs.util.CredentialsConfig;
import com.thoughtDocs.model.impl.s3.UserS3;
import com.thoughtDocs.model.impl.s3.UserS3Store;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.log.Log;
import org.jboss.seam.security.Credentials;
import org.jboss.seam.security.Identity;
import org.jboss.seam.faces.FacesMessages;

@Name("authenticator")
public class Authenticator {
    @Logger
    private Log log;

    @In
    Identity identity;
    @In
    Credentials credentials;

    @In(create = true)
    UserS3Store userStore;


    public boolean authenticate() {
        log.info("authenticating {0}", credentials.getUsername());
        //write your authentication logic here,
        //return true if the authentication was
        //successful, false otherwise
        UserS3 user = userStore.find(credentials.getUsername());
        if ( user != null && 
                user.getPassword().equals(credentials.getPassword())) {
            identity.addRole("admin");
            return true;
        }
        return false;
    }

}
