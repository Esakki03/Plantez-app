package com.simats.plantz.serverresponse;

import com.simats.plantz.ArticleList;

import java.util.List;

public class ArticleResponse {
    private boolean status;
    private String message;
    private List<ArticleList> articleList;

    // Constructors
    public ArticleResponse() {}

    public ArticleResponse(boolean status, String message, List<ArticleList> articleList) {
        this.status = status;
        this.message = message;
        this.articleList = articleList;
    }

    // Getters and Setters
    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<ArticleList> getSpeciesList() {
        return articleList;
    }

    public void setPlantList(List<ArticleList> articleList) {
        this.articleList = articleList;
    }
}
