package com.lesbougs.androidprojectm1.model;

import java.time.LocalDateTime;
import java.util.List;
import java.util.ArrayList;


public class Form {

    public String _id;
    public String smallId;
    public String title;
    public boolean isClosed;
    public int __v;
    public ArrayList<Widget> content;


    public Form(String title, boolean isClosed, String smallId, ArrayList<Widget> content , String _id) {
        this.title = title;
        this._id = _id;
        this.isClosed = isClosed;
        this.smallId = smallId;
        this.content = content;
    }

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

    public int get__v() {
        return __v;
    }

    public void set__v(int __v) {
        this.__v = __v;
    }

    public ArrayList<Widget> getContent() {
        return content;
    }

    public void setContent(ArrayList<Widget> content) {
        this.content = content;
    }
}
