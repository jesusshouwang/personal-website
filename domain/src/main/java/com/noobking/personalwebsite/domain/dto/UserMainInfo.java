package com.noobking.personalwebsite.domain.dto;

import java.io.Serializable;

/**
 * 用户主要信息
 */
public class UserMainInfo implements Serializable {
    private Long id;
    private String username;
    private String email;
    private String rights;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRights() {
        return rights;
    }

    public void setRights(String rights) {
        this.rights = rights;
    }
}
