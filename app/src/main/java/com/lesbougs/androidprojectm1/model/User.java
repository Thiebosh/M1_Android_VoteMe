package com.lesbougs.androidprojectm1.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class User {
    public String _id;
    public String username;
    public String creationDate;
    public LocalDateTime date;
    public ArrayList<Form> forms;
    public int type;
    public int __v;

    public String signature;
    public String headerPayload;

    public User(String _id, String username, String creationDate,  ArrayList<Form> forms,String signature , String headerPayload) {
        this._id = _id.substring(1, _id.length() - 1);
        this.signature = signature;
        this.headerPayload = headerPayload;
        this.username = username.substring(1, username.length() - 1);
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            this.date = LocalDateTime.parse(creationDate.substring(1, creationDate.length() - 2));
        }
        this.forms = forms;
    }

    @Override
    public String toString() {
        return "User{" +
                "_id='" + _id + '\'' +
                ", username='" + username + '\'' +
                ", creationDate=" + creationDate +
                ", forms=" + forms +
                ", signature='" + signature + '\'' +
                ", headerPayload='" + headerPayload + '\'' +
                '}';
    }

    public String getSignature() { return signature; }

    public String getHeaderPayload() { return headerPayload; }

    public String getId() { return _id; }


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

    public ArrayList<Form> getForms() {
        return forms;
    }

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

    public void setForms(ArrayList<Form> forms) {
        this.forms = forms;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public void setHeaderPayload(String headerPayload) {
        this.headerPayload = headerPayload;
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
}

