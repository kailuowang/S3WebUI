package com.thoughtDocs.viewModel;

import com.thoughtDocs.model.impl.s3.UserS3;
import com.thoughtDocs.model.impl.s3.UserS3Store;
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Factory;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.international.StatusMessages;

import java.io.IOException;
import java.util.List;

/**
 *
 */
@Scope(ScopeType.PAGE)
@Name("registerAction")
public class RegisterAction {

    UserS3 user = new UserS3();

    @In(create = true)
    UserS3Store userStore;

    private String passwordCheck;

    @In
    private StatusMessages statusMessages;
    private String invitationCode;
    private String awsSecretKey;

    @Factory(autoCreate = true)
    public UserS3 getRegisteringUser() {
        return user;
    }

    public void setPasswordCheck(String passwordCheck) {
        this.passwordCheck = passwordCheck;
    }

    public String getPasswordCheck() {
        return passwordCheck;
    }

    public String register() throws IOException {
        List<String> validationErrors = userStore.validateNewUser(user, invitationCode, passwordCheck);
        if (validationErrors == null || validationErrors.size() == 0) {
            user.persist(userStore);
            statusMessages.add("You have successfully registered. Please login now.");
            return "/login.xhtml";
        }

        for (String err : validationErrors)
            statusMessages.add(err);
        return null;
    }


    public void setInvitationCode(String invitationCode) {
        this.invitationCode = invitationCode;
    }

    public String getInvitationCode() {
        return invitationCode;
    }

    public void setAwsSecretKey(String awsSecretKey) {
        this.awsSecretKey = awsSecretKey;
    }

    public String getAwsSecretKey() {
        return awsSecretKey;
    }
}
