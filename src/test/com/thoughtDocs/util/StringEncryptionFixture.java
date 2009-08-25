package com.thoughtDocs.util;

import org.testng.annotations.Test;
import static org.testng.Assert.*;
import org.keyczar.Crypter;
import org.keyczar.KeyczarTool;
import org.keyczar.exceptions.KeyczarException;
import com.thoughtDocs.model.impl.s3.FixtureBase;

/**
 *
 */
public class StringEncryptionFixture extends FixtureBase {
    private static final String LOCATION = "/Users/ThoughtWorks/IdeaProjects/thoughtdocs/thoughtDocs/keyset";
    private static final String LOCATION_COMMAND = "--location=" + LOCATION;

    //@Test
    public void shouldEncryptDecrypt() throws KeyczarException {
          String testString = randomString();

        Crypter se = new Crypter(LOCATION);

        String encrypted = se.encrypt(testString);
        assertFalse(encrypted.equals(testString));
        assertEquals(se.decrypt(encrypted), testString);
    }

    //@Test
    public void shouldCreateKey(){
        String[] args = {"create",
                LOCATION_COMMAND,
                        "--purpose=crypt",
                        "--name=Test"
                        };
           KeyczarTool.main(args);

          String[]   args2 = {"addkey",
                  LOCATION_COMMAND,
                        "--status=primary"
                        };
           KeyczarTool.main(args2);

        
    }
}
