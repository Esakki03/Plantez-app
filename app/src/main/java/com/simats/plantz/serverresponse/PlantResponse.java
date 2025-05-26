package com.simats.plantz.serverresponse;

import com.simats.plantz.PlantList;

import java.util.List;

public class PlantResponse {
    private boolean status;
    private String message;
    private List<PlantList> plantList;

    // Constructors
    public PlantResponse() {}

    public PlantResponse(boolean status, String message, List<PlantList> plantList) {
        this.status = status;
        this.message = message;
        this.plantList = plantList;
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

    public List<PlantList> getPlantList() {
        return plantList;
    }

    public void setPlantList(List<PlantList> plantList) {
        this.plantList = plantList;
    }

}

