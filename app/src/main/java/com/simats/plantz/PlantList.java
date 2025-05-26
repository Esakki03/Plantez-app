package com.simats.plantz;

public class PlantList {

    private int id;
    private String name;
    private String description;
    private String geographic_distribution;
    private String image;
    private String medicinal_use;
    private String season;

    // Constructors

    public PlantList(int id, String name, String description, String geographicDistribution, String image, String medicinalUse, String season) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.geographic_distribution = geographicDistribution;
        this.image = image;
        this.medicinal_use = medicinalUse;
        this.season = season;
    }

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getGeographicDistribution() {
        return geographic_distribution;
    }

    public void setGeographicDistribution(String geographicDistribution) {
        this.geographic_distribution = geographicDistribution;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getMedicinalUse() {
        return medicinal_use;
    }

    public void setMedicinalUse(String medicinalUse) {
        this.medicinal_use = medicinalUse;
    }

    public String getSeason() {
        return season;
    }

    public void setSeason(String season) {
        this.season = season;
    }


}
