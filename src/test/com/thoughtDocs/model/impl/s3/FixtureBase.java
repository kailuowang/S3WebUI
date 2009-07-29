package com.thoughtDocs.model.impl.s3;

import com.thoughtDocs.model.Repository;
import com.thoughtDocs.model.impl.s3.RepositoryFactory;

import java.io.IOException;
import java.security.SecureRandom;
import java.security.NoSuchAlgorithmException;
import java.util.Date;


/**
 * Created by IntelliJ IDEA.
 * User: ThoughtWorks
 * Date: Jul 22, 2009
 * Time: 9:47:23 PM
 * To change this template use File | Settings | File Templates.
 */
public class FixtureBase {
 

     protected String randomString()  {
        SecureRandom random = null;
        try {
            random = SecureRandom.getInstance("SHA1PRNG");
        } catch (NoSuchAlgorithmException e) {
            throw new UnknownError(e.getMessage());
        }
        random.setSeed(new Date().getTime());
        String randomString = String.valueOf(random.nextLong());
        return randomString;
    }


}