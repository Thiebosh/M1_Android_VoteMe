package com.lesbougs.androidprojectm1.model;

import java.util.List;

public class Widget {
    public String title;
    public String _id;
    public int order;
    public int type;
    public String textField;
    public List<String> textFieldResult;
    public int maxPoint;
    public int minPoint;
    public List<Integer> resultPoint;

    public Widget(String title, int order, int type, String textField, List<String> textFieldResult, int maxPoint, int minPoint,  List<Integer> resultPoint, String _id ) {
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public String getTextField() {
        return textField;
    }

    public void setTextField(String textField) {
        this.textField = textField;
    }

    public List<String> getTextFieldResult() {
        return textFieldResult;
    }

    public void setTextFieldResult(List<String> textFieldResult) {
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

    public List<Integer> getResultPoint() {
        return resultPoint;
    }

    public void setResultPoint(List<Integer> resultPoint) {
        this.resultPoint = resultPoint;
    }

    @Override
    public String toString() {
        return "Widget{" +
                "title='" + title + '\'' +
                ", _id='" + _id + '\'' +
                ", order=" + order +
                ", type=" + type +
                ", textField='" + textField + '\'' +
                ", textFieldResult=" + textFieldResult +
                ", maxPoint=" + maxPoint +
                ", minPoint=" + minPoint +
                ", resultPoint=" + resultPoint +
                '}';
    }
}
