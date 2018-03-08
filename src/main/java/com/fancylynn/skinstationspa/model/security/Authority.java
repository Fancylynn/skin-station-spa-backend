package com.fancylynn.skinstationspa.model.security;

import org.springframework.security.core.GrantedAuthority;

/**
 * Created by Lynn on 2018/3/6.
 */
public class Authority implements GrantedAuthority{
    private final String authority;

    public Authority(String authority) {
        this.authority = authority;
    }

    @Override
    public String getAuthority() {
        return authority;
    }

}
