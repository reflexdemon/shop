package org.shop.model;

import org.springframework.security.core.GrantedAuthority;

/**
 * Created by vprasanna on 5/28/2016.
 */
public class Role implements GrantedAuthority {


    private String authority;


    public Role(String authority) {
        this.authority = authority;
    }

    @Override
    public String getAuthority() {
        return authority.toString();
    }
}
