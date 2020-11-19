package com.lesbougs.androidprojectm1.model;

import java.util.ArrayList;

public class FormAnswer {
    private ArrayList<WidgetAnswer> widgetAnswers;

    public FormAnswer(ArrayList<WidgetAnswer> widgetAnswers) {
        this.widgetAnswers = widgetAnswers;
    }

    public ArrayList<WidgetAnswer> getWidgetAnswers() {
        return widgetAnswers;
    }

    public void setWidgetAnswers(ArrayList<WidgetAnswer> widgetAnswers) {
        this.widgetAnswers = widgetAnswers;
    }
}
