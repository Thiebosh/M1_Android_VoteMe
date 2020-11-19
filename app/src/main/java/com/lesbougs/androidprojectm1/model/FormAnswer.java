package com.lesbougs.androidprojectm1.model;

import java.util.ArrayList;

public class FormAnswer {
    private ArrayList<WidgetAnswer> result;

    public FormAnswer(ArrayList<WidgetAnswer> widgetAnswers) {
        this.result = widgetAnswers;
    }

    public ArrayList<WidgetAnswer> getWidgetAnswers() {
        return result;
    }

    public void setResult(ArrayList<WidgetAnswer> widgetAnswers) {
        this.result = widgetAnswers;
    }
}
