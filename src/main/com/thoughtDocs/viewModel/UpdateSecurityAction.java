package com.thoughtDocs.viewModel;

import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.ScopeType;
import com.thoughtDocs.model.Item;
import com.thoughtDocs.model.SecurityMode;

import java.io.IOException;
import java.io.Serializable;

/**
 * Created by Kailuo "Kai" Wang
 * Date: Aug 10, 2009
 * Time: 1:04:32 PM
 */
@Scope(ScopeType.CONVERSATION)
@Name("updateSecurityAction")
public class UpdateSecurityAction implements Serializable{
    private Item item;

    public void init(Item i){
        this.item = i;
    }


    public String getKey(){
        return item != null ? item.getKey() : "";
    }

    public void setSecurityMode(SecurityMode mode) throws IOException {
            item.setSecurityMode(mode);
    }

    public SecurityMode getSecurityMode() throws IOException {
        return item != null ? item.getSecurityMode() : null;
    }

    public String getPassword() throws IOException {
        return item != null ? item.getUsingPassword() : "";
    }

    public void setPassword(String newpass) throws IOException {
            item.setPassword(newpass);
    }

    public void update() throws IOException {
            item.save();
    }

    public boolean getPasswordReadonly() throws IOException {
        if(item != null)
            return !item.getSecurityMode().getCanSetPassword();
        return true;
    }


}
