package com.lesbougs.androidprojectm1.model;

import java.util.ArrayList;


public class Widget {
    public String _id;
    public int order;
    public int type;
    public String question;
    public ArrayList<String> textFieldResult;
    public int maxPoint;
    public int minPoint;
    public ArrayList<Integer> resultPoint;

    @Override
    public String toString() {
        return "Widget{" +
                ", _id='" + _id + '\'' +
                ", order=" + order +
                ", type=" + type +
                ", textField='" + question + '\'' +
                ", textFieldResult=" + textFieldResult +
                ", maxPoint=" + maxPoint +
                ", minPoint=" + minPoint +
                ", resultPoint=" + resultPoint +
                '}';
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

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public ArrayList<String> getTextFieldResult() {
        return textFieldResult;
    }

    public void setTextFieldResult(ArrayList<String> textFieldResult) {
        this.textFieldResult = textFieldResult;
    }

    public int getMaxPoint() {

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

}
