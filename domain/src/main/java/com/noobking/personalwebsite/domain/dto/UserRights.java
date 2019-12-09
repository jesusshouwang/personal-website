package com.noobking.personalwebsite.domain.dto;

import java.io.Serializable;

/**
 * 主要携带用户权限
 */
public class UserRights implements Serializable {

    private Long id;

    private boolean rights;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isRights() {
        return rights;
    }

    public void setRights(boolean rights) {
        this.rights = rights;
    }
}
