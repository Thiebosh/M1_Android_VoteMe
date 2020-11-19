package com.lesbougs.androidprojectm1.model;

import java.time.LocalDateTime;
import java.util.List;

public class Form {
    public String title;
    public boolean isClosed;
    public String smallId;
    public List<Widget> content;
    public String _id;


    public Form(String title, boolean isClosed, String smallId, List<Widget> content , String _id) {
        this.title = title;
        this._id = _id;
        this.isClosed = isClosed;
        this.smallId = smallId;
        this.content = content;


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

    public String getSmallId() {
        return smallId;
    }

    public void setSmallId(String smallId) {
        this.smallId = smallId;
    }

    public List<Widget> getContent() {
        return content;
    }

    public void setContent(List<Widget> content) {
        this.content = content;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
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
}
