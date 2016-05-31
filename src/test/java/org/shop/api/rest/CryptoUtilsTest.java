package org.shop.api.rest;

import org.junit.Test;
import org.shop.utils.CryptoUtils;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by vprasanna on 5/23/2016.
 */
public class CryptoUtilsTest {

    @Test
    public void testEncode1() throws Exception {
        String TEXT = "INPUT Text";
        assertTrue("Check for equality", TEXT.equalsIgnoreCase(CryptoUtils.decode(CryptoUtils.encode(TEXT))));
    }

    @Test
    public void testEncode2() throws Exception {
        String TEXT = null;
        assertNull("Check for Null", CryptoUtils.decode(CryptoUtils.encode(TEXT)));
    }

}