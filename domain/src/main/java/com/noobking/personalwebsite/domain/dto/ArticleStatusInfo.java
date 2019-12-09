package com.noobking.personalwebsite.domain.dto;

import java.io.Serializable;

public class ArticleStatusInfo implements Serializable {
    private Long id;
    private boolean appreciation;
    private boolean share;
    private boolean comment;
    private boolean recommendation;
    private boolean published;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isAppreciation() {
        return appreciation;
    }

    public void setAppreciation(boolean appreciation) {
        this.appreciation = appreciation;
    }

    public boolean isShare() {
        return share;
    }

    public void setShare(boolean share) {
        this.share = share;
    }

    public boolean isComment() {
        return comment;
    }

    public void setComment(boolean comment) {
        this.comment = comment;
    }

    public boolean isRecommendation() {
        return recommendation;
    }

    public void setRecommendation(boolean recommendation) {
        this.recommendation = recommendation;
    }

    public boolean isPublished() {
        return published;
    }

    public void setPublished(boolean published) {
        this.published = published;
    }
}
