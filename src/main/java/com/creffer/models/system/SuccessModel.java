package com.creffer.models.system;

import com.creffer.models.system.RoleModel;

import java.util.List;

public class SuccessModel {
    private List<RoleModel> roles;
    private boolean accessed;

    public SuccessModel() {
    }

    public List<RoleModel> getRoles() {
        return roles;
    }

    public void setRoles(List<RoleModel> roles) {
        this.roles = roles;
    }

    public boolean isAccessed() {
        return accessed;
    }

    public void setAccessed(boolean accessed) {
        this.accessed = accessed;
    }
}
