package com.lesbougs.androidprojectm1.model;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class User {
    public String _id;
    public String username;
    public String creationDate;
    public LocalDateTime date;
    public int type;
    public int __v;
    public ArrayList<Form> forms;


    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            this.date = LocalDateTime.parse(creationDate.substring(1, creationDate.length() - 2));
        }
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int get__v() {
        return __v;
    }

    public void set__v(int __v) {
        this.__v = __v;
    }

    public ArrayList<Form> getForms() {
        return forms;
    }

    public void setForms(ArrayList<Form> forms) {
        this.forms = forms;
    }
}

