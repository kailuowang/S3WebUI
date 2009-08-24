package com.thoughtDocs.model;

import java.io.IOException;
import java.io.Serializable;

/**
 * Created by Kailuo "Kai" Wang
 * Date: Jul 29, 2009
 * Time: 7:34:38 PM
 */
public interface Item extends Serializable{
    String getName();

    String getKey();

    boolean isTransient();

    /**
     * refresh data from server
     * @throws IOException
     */
    void refresh() throws IOException;

    void delete() throws IOException;

    Folder getParent() throws IOException;

    SecurityMode getSecurityMode() throws IOException;

    void setSecurityMode(SecurityMode mode) throws IOException;

    /**
     * get the password specified for this particular item. it's not necessarily the password this item should be test against
     * @return
     * @throws IOException
     */

    String getPassword() throws IOException;

    /**
     * set the password specified for this particular item. it's not necessarily the password this item should be test against
     * @throws IOException
     */
    void setPassword(String password) throws IOException;

    /**
     *
     *
     * @return the password that should be test against when downloading this.
    * returns null  if there is no password needed.
    */
    String getUsingPassword() throws IOException;

    /**
     * save data onto server
     * @throws IOException
     */
    void save() throws IOException;

    /**
     * update settings to server
     * @throws IOException
     */
    void update() throws IOException;
}
