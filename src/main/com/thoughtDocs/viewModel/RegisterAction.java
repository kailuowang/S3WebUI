package com.thoughtDocs.viewModel;

import com.thoughtDocs.model.impl.s3.UserS3;
import com.thoughtDocs.model.impl.s3.UserS3Store;
import com.thoughtDocs.util.CredentialsConfig;
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Factory;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.international.StatusMessages;

import java.io.IOException;

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
        if (CredentialsConfig.getInvitationCode().equals(invitationCode)) {
            if (passwordCheck.equals(user.getPassword())) {
                if (userStore.find(user.getUsername()) == null) {
                    userStore.add(user);
                    return "login";
                } else
                    statusMessages.add("Your password does not match.");
            } else
                statusMessages.add("Your password does not match.");
        } else
            statusMessages.add("incorrect invitation code");
        return null;
    }


    public void setInvitationCode(String invitationCode) {
        this.invitationCode = invitationCode;
    }

    public String getInvitationCode() {
        return invitationCode;
    }
}
