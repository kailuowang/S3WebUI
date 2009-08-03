package com.thoughtDocs.model;

import java.io.IOException;

/**
 * Created by Kailuo "Kai" Wang
 * Date: Aug 1, 2009
 * Time: 8:20:04 PM
 */
public enum SecurityMode {
    SPECIFIED_PASSWORD{
        @Override
        public String usingPassword(Item item) throws IOException {
            return item.getPassword();
        }
    },
    INHERITED{
        @Override
        public String usingPassword(Item item) throws IOException {
            Folder parent = item.getParent();
            return parent.getSecurityMode().usingPassword(parent);
        }
    },
    UNPROTECTED{
        @Override
        public String usingPassword(Item item){
            return null;
        }
    };

    /**
     *
     * @param item
     * @return the password that should be test against when downloading the item.
     * returns null or empty string if there is no password needed. 
     */
     public abstract String usingPassword(Item item) throws IOException;


}
