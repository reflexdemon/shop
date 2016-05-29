package org.shop.utils;

import java.security.MessageDigest;
import java.util.Base64;
import java.util.Date;
import java.util.Random;

/**
 * The type Crypto utils.
 * Created by vprasanna on 5/23/2016.
 */
public class CryptoUtils {

    private static Random rand = new Random((new Date()).getTime());

    /**
     * Encode string.
     *
     * @param input the input
     * @return the string
     */
    public static String encode(String input) {
        if (null == input) {
            return null;
        }

        Base64.Encoder encoder = Base64.getEncoder();
        byte[] salt = new byte[8];
        rand.nextBytes(salt);
        return new String(encoder.encode(salt)) + new String(encoder.encode(input.getBytes()));
    }

    /**
     * Decode string.
     *
     * @param input the input
     * @return the string
     */
    public static String decode(String input) {
        if (null == input) {
            return null;
        }
        Base64.Decoder encoder = Base64.getDecoder();
        if (input.length() > 12) {
            String cipher = input.substring(12);
            return new String(encoder.decode(cipher));
        }
        return input;
    }

    /**
     * Sha 256 string.
     *
     * @param base the base
     * @return the string
     */
    public static String sha256(String base) {
        try{
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(base.getBytes("UTF-8"));
            StringBuffer hexString = new StringBuffer();

            for (int i = 0; i < hash.length; i++) {
                String hex = Integer.toHexString(0xff & hash[i]);
                if(hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }

            return hexString.toString();
        } catch(Exception ex){
            throw new RuntimeException(ex);
        }
    }
    public static String sha256(CharSequence base) {
        if (null == base) {
            throw new IllegalArgumentException("Base for SHA256 encoding is null");
        }
        return sha256(base.toString());
    }
}
