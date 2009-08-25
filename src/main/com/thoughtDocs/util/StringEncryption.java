package com.thoughtDocs.util;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;

/**
 *
 */
public class StringEncryption {
    private Cipher cipher;
    SecretKeySpec key;

    public StringEncryption() {
        byte[] keyBytes = new byte[]{0x00, 0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07, 0x08, 0x09,
                0x0a, 0x0b, 0x0c, 0x0d, 0x0e, 0x0f, 0x10, 0x11, 0x12, 0x13, 0x14, 0x15, 0x16, 0x17};

        key = new SecretKeySpec(keyBytes, "AES");
        try {
            cipher = Cipher.getInstance("AES/ECB/NoPadding");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }

    public String encrypt(String toEncrypt) {
        byte[] input = new byte[0];
        try {
            input = toEncrypt.getBytes("UTF-8");
            try {
                cipher.init(Cipher.ENCRYPT_MODE, key);
            } catch (InvalidKeyException e) {
                throw new RuntimeException(e);
            }
            return new String(cipher.update(input), "UTF-8");
        }


        catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }


    public String decrypt(String encrypted) {
        try {
            cipher.init(Cipher.DECRYPT_MODE, key);

        return new String(cipher.update(encrypted.getBytes()), "UTF-8");
              } catch (InvalidKeyException e) {
            throw new RuntimeException(e);

        } catch (UnsupportedEncodingException e) {
           throw new RuntimeException(e); }
    }
}
