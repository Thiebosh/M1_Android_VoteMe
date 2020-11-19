package com.lesbougs.androidprojectm1.model;

import java.util.ArrayList;


public class Form {

    public String _id;
    public String smallId;
    public String title;
    public boolean isClosed;
    public ArrayList<Widget> content;

    @Override
    public String toString() {
        return "Form{" +
                "title='" + title + '\'' +
                ", isClosed=" + isClosed +
                ", smallId='" + smallId + '\'' +
                ", content=" + content +
                ", _id='" + _id + '\'' +
                '}';
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getSmallId() {
        return smallId;
    }

    public void setSmallId(String smallId) {
        this.smallId = smallId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isClosed() {
        return isClosed;
    }

    public void setClosed(boolean closed) {
        isClosed = closed;
    }

    public ArrayList<Widget> getContent() {
        return content;
    }

    public void setContent(ArrayList<Widget> content) {
        this.content = content;
    }
}
