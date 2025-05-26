package com.simats.plantz.serverresponse;

import com.simats.plantz.PlantList;
import com.simats.plantz.SpeciesList;

import java.util.List;

public class SpeciesResponse {
    private boolean status;
    private String message;
    private List<SpeciesList> speciesList;

    // Constructors
    public SpeciesResponse() {}

    public SpeciesResponse(boolean status, String message, List<SpeciesList> speciesList) {
        this.status = status;
        this.message = message;
        this.speciesList = speciesList;
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

    public List<SpeciesList> getSpeciesList() {
        return speciesList;
    }

    public void setPlantList(List<SpeciesList> speciesList) {
        this.speciesList = speciesList;
    }
}
