package com.lesbougs.androidprojectm1.model;

import java.util.Date;
import java.util.List;

public class User {
    public String _id;
    public String username;
    public Date creationDate;
    public List<String> forms;

    public User( String _id,String username,Date creationDate,List<String> forms){
        this._id = _id;
        this.username = username;
        this.creationDate = creationDate;
        this.forms = forms;
    }

    public String getId() { return _id; }

    public String getUserName() {
        return username;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public List<String> getForms() {
        return forms;
    }
}

