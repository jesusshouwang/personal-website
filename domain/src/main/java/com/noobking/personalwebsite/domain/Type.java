package com.noobking.personalwebsite.domain;

import java.io.Serializable;

/**
 * 类型实体
 */
public class Type implements Serializable {
    private Long id;
    private String name;
    private boolean status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
