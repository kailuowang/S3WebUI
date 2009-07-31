package com.thoughtDocs.model.impl.s3;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Date;


/**
 * Created by IntelliJ IDEA.
 * User: ThoughtWorks
 * Date: Jul 22, 2009
 * Time: 9:47:23 PM
 * To change this template use File | Settings | File Templates.
 */
public abstract class FixtureBase {
               SecureRandom random ;

    protected String randomString() {
        String randomString = String.valueOf(random.nextLong());
        return randomString;
    }

    protected FixtureBase() {
           random = null;
        try {
            random = SecureRandom.getInstance("SHA1PRNG");
        } catch (NoSuchAlgorithmException e) {
            throw new UnknownError(e.getMessage());
        }
        random.setSeed(new Date().getTime());
    }

    protected S3Bucket testBucket() {
        return new MemoryBucketImpl("test");  //To change body of implemented methods use File | Settings | File Templates.
    }
}