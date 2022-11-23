package com.revature.ers.models;

public class User {
    private String id;
    private String username;
    private String email;
    private String pwd;
    private String givenName;
    private String surname;
    private Boolean isActive;
    private String roleId;

    public User() {
        super();
    }

    public User(String id, String username, String email, String pwd, String givenName, String surname, Boolean isActive, String roleId) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.pwd = pwd;
        this.givenName = givenName;
        this.surname = surname;
        this.isActive = isActive;
        this.roleId = roleId;
    }

    /* What does the constructor provide */
    public User(String id) {
        this.id = id;
    }


    /* Getters and setters */
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getGivenName() {
        return givenName;
    }

    public void setGivenName(String givenName) {
        this.givenName = givenName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }
}
