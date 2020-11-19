package com.lesbougs.androidprojectm1.model;

import java.util.List;
import java.util.ArrayList;


public class Widget {
    public String question;
    public String _id;
    public int order;
    public int type;
    public ArrayList<String> textFieldResult;
    public int maxPoint;
    public int minPoint;
    public ArrayList<Integer> resultPoint;

    public Widget(String question, String _id, int order, int type, ArrayList<String> textFieldResult, int maxPoint, int minPoint, ArrayList<Integer> resultPoint) {
        this.question = question;
        this._id = _id;
        this.order = order;
        this.type = type;
        this.textFieldResult = textFieldResult;
        this.maxPoint = maxPoint;
        this.minPoint = minPoint;
        this.resultPoint = resultPoint;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public ArrayList<String> getTextFieldResult() {
        return textFieldResult;
    }

    public void setTextFieldResult(ArrayList<String> textFieldResult) {
        this.textFieldResult = textFieldResult;
    }

    public int getMaxPoint() {
        return maxPoint;
    }

    public void setMaxPoint(int maxPoint) {
        this.maxPoint = maxPoint;
    }

    public int getMinPoint() {
        return minPoint;
    }

    public void setMinPoint(int minPoint) {
        this.minPoint = minPoint;
    }

    public ArrayList<Integer> getResultPoint() {
        return resultPoint;
    }

    public void setResultPoint(ArrayList<Integer> resultPoint) {
        this.resultPoint = resultPoint;
    }

/*
//passe par gson, voir VisitorActivity
    public Widget(String title, int order, int type, String textField, ArrayList<String> textFieldResult, int maxPoint, int minPoint,  ArrayList<Integer> resultPoint, String _id ) {
        this.title = title;
        this._id = _id;
        this.order = order;
        this.type = type;
        this.textField = textField;
        this.textFieldResult = textFieldResult;
        this.maxPoint = maxPoint;
        this.minPoint = minPoint;
        this.resultPoint = resultPoint;
    }

 */


}
