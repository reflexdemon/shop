package org.shop.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import static org.shop.utils.CryptoUtils.sha256;

/**
 * Created by vprasanna on 5/29/2016.
 */
@Component
public class ApplicationPasswordEncoder implements PasswordEncoder {
    @Override
    public String encode(CharSequence charSequence) {
        if (null == charSequence) {
            throw new IllegalArgumentException("Input for encoding is empty");
        }
        return sha256(charSequence.toString());
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encoded) {
        if (null == rawPassword || null == encoded) {
            throw new IllegalArgumentException("Input for decoding is empty");
        }
        return encoded.equals(sha256(rawPassword));
    }
}
