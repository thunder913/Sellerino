package com.project.sap.models;

import com.sun.istack.NotNull;

import javax.persistence.*;


@Entity
public class User extends UserDto{
    @Transient
    private String repeatPassword;

    public String getRepeatPassword() {
        return repeatPassword;
    }

    public void setRepeatPassword(String repeatPassword) {
        this.repeatPassword = repeatPassword;
    }
}
