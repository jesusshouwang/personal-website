package com.noobking.personalwebsite.domain.dto;

import com.noobking.personalwebsite.domain.Type;
import com.noobking.personalwebsite.domain.dto.UserMainInfo;

import java.io.Serializable;

public class ArticleMainInfo implements Serializable {
    private Long id;
    private Type type;
    private String title;
    private boolean published;
    private UserMainInfo userMainInfo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isPublished() {
        return published;
    }

    public void setPublished(boolean published) {
        this.published = published;
    }

    public UserMainInfo getUserMainInfo() {
        return userMainInfo;
    }

    public void setUserMainInfo(UserMainInfo userMainInfo) {
        this.userMainInfo = userMainInfo;
    }
}
