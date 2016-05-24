package org.shop.utils;

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
}
