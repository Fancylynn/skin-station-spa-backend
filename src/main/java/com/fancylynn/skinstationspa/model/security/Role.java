package com.fancylynn.skinstationspa.model.security;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Lynn on 2018/3/6.
 */

@Entity
public class Role {
    @Id
    private int roleId;
    private String username;

    @OneToMany(mappedBy = "role", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<UserRole> userRoles = new HashSet<>();

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Set<UserRole> getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(Set<UserRole> userRoles) {
        this.userRoles = userRoles;
    }
}
