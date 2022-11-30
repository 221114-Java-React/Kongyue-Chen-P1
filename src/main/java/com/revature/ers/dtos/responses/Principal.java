package com.revature.ers.dtos.responses;

public class Principal {
    private String id;
    private String username;
    private String roleId;
    private String authorization;

    public Principal() {
        super();
    }

    public Principal(String id, String username, String roleId, String authorization) {
        this.id = id;
        this.username = username;
        this.roleId = roleId;
        this.authorization = authorization;
    }

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

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getAuthorization() {
        return authorization;
    }

    public void setAuthorization(String authorization) {
        this.authorization = authorization;
    }
}
