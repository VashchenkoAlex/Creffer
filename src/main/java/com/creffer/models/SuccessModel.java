package com.creffer.models;

import com.creffer.models.users.RoleModel;

import java.util.List;

public class SuccessModel {
    private String email;
    private int status;
    private String token;
    private List<RoleModel> roles;
    private boolean correctPassword;

    public SuccessModel() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public List<RoleModel> getRoles() {
        return roles;
    }

    public void setRoles(List<RoleModel> roles) {
        this.roles = roles;
    }

    public boolean isCorrectPassword() {
        return correctPassword;
    }

    public void setCorrectPassword(boolean correctPassword) {
        this.correctPassword = correctPassword;
    }
}
