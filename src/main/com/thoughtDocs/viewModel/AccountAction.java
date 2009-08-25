package com.thoughtDocs.viewModel;

import com.thoughtDocs.model.impl.s3.UserS3;
import com.thoughtDocs.model.impl.s3.UserS3Store;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.international.StatusMessages;
import org.jboss.seam.security.Identity;
import org.jboss.seam.core.Events;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 *
 */
@Name("accountAction")
public class AccountAction {
    @In
    Identity identity;

    @In
    UserS3 currentUser;

    @In
    UserS3Store userStore;

    @In
    private StatusMessages statusMessages;
    private String newPassword;
    private String oldPassword;
    private String newPasswordCheck;


    public String remove() throws IOException {

        userStore.remove(currentUser);
        identity.logout();
        return "login";
    }

    public void update() throws IOException {
        List<String> validationErrors = validate();
        if (validationErrors.size() > 0) {
            for (String err : validationErrors) {
                statusMessages.add(err);
            }
            return;
        }
        if (newPassword != null && newPassword.length() > 0)
            currentUser.setHashedPassword(newPassword);

        statusMessages.add("Account information updated.");
        currentUser.persist(userStore);
        Events.instance().raiseEvent(ViewEvents.BucketChanged);
    }

    private List<String> validate() {

        if (newPassword != null && newPassword.length() > 0) {
            if (!currentUser.getHashedPassword().equals(oldPassword)) {
                return Arrays.asList("old password isn't entered correctly");
            }
            if (!newPassword.equals(newPasswordCheck)) {
                return Arrays.asList("new password does not match");
            }

        }

        return currentUser.validate();
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setNewPasswordCheck(String newPasswordCheck) {
        this.newPasswordCheck = newPasswordCheck;
    }

    public String getNewPasswordCheck() {
        return newPasswordCheck;
    }
}
