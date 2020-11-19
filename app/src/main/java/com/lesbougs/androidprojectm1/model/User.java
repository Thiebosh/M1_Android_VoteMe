package com.lesbougs.androidprojectm1.model;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class User {
    public String _id;
    public String username;
    public LocalDateTime creationDate;
    public List<Form> forms;
    public String signature;
    public String headerPayload;

    public User(String _id, String username, String creationDate,  List<Form> forms,String signature , String headerPayload) {
        this._id = _id.substring(1, _id.length() - 1);
        this.signature = signature;
        this.headerPayload = headerPayload;
        this.username = username.substring(1, username.length() - 1);
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            this.creationDate = LocalDateTime.parse(creationDate.substring(1, creationDate.length() - 2));
        }
        this.forms = forms;
    }


    public String getSignature() { return signature; }

    public String getHeaderPayload() { return headerPayload; }

    public String getId() { return _id; }

    public String getCreationDate() {
        return creationDate;
    }

    public List<Form> getForms() {
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

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public void setForms(List<Form> forms) {
        this.forms = forms;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public void setHeaderPayload(String headerPayload) {
        this.headerPayload = headerPayload;
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
}

