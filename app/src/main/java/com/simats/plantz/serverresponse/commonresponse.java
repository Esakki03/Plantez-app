package com.simats.plantz.serverresponse;

public class commonresponse {
    private boolean message;
    private String username;


    // Getter Methods

    public boolean getMessage() {
        return message;
    }

    public String getUsername() {
        return username;
    }

    // Setter Methods

    public void setMessage(boolean message) {
        this.message = message;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    //profile
    private String email;
    private String fname;
    private String lname;



    // Getter Methods

    public String getEmail() {
        return email;
    }

    public String getFname() {
        return fname;
    }

    public String getLname() {
        return lname;
    }



    // Setter Methods

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }



}
