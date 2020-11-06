package com.lesbougs.androidprojectm1.model;

import java.util.Date;
import java.util.List;

public class User {
    public String userName;
    public String password;
    public Date creationDate;
    public List<Form> forms;

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public List<Form> getForms() {
        return forms;
    }
}

