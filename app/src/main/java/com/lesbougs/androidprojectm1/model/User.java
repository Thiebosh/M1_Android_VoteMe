package com.lesbougs.androidprojectm1.model;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

public class User {
    public String _id;
    public String username;
    public LocalDateTime creationDate;
    public List<String> forms;

    public User(String _id, String username, String creationDate, String forms) {
        this._id = _id.substring(1, _id.length() - 1);
        this.username = username.substring(1, username.length() - 1);
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            this.creationDate = LocalDateTime.parse(creationDate.substring(1, creationDate.length() - 2));
        }
        this.forms = Arrays.asList(forms.substring(1, forms.length() - 1).split("\\s*,\\s*"));//retire espaces et virgules
    }

    public String getId() { return _id; }

    public String getUserName() {
        return username;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public List<String> getForms() {
        return forms;
    }
}

