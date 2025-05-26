package com.simats.plantz;

import java.util.List;

public class SpeciesList {
    private boolean status;
    private String message;
    private List<GrowItem> growList;

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

    public List<GrowItem> getGrowList() {
        return growList;
    }

    public void setGrowList(List<GrowItem> growList) {
        this.growList = growList;
    }
}

class GrowItem {
    private int id;
    private String name;
    private String subject;
    private String content;
    private String image;

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
