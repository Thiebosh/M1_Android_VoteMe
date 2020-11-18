package com.lesbougs.androidprojectm1.model;

import java.util.Date;
import java.util.List;

public class User {
    public String _id;
    public String username;
    public String password;
    public Date creationDate;
    public List<Form> forms;

    public String getId() { return _id; }

    public String getUserName() {
        return username;
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

