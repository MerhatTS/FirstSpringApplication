package com.example.FirstSpringApplication.Security;

import org.springframework.security.core.GrantedAuthority;

public class GrantedAuthorityImpl implements GrantedAuthority {

    private String authority;
    private boolean isRole;

    public GrantedAuthorityImpl(String authority, boolean isRole){
        this.authority = authority;
        this.isRole = isRole;
    }

    @Override
    public String getAuthority() {
        return isRole ? ("ROLE" + authority) : authority;
    }
}
