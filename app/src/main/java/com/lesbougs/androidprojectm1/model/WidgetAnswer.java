package com.lesbougs.androidprojectm1.model;


public class WidgetAnswer {
    private String _id;
    private String result;

    public WidgetAnswer(final String widgetId, final String widgetAnswer) {
        this._id = widgetId;
        this.result = widgetAnswer;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
