package com.lesbougs.androidprojectm1.holders;

import android.text.Editable;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.lesbougs.androidprojectm1.R;
import com.lesbougs.androidprojectm1.model.Widget;


public class AdminWidgetNumberHolder extends RecyclerView.ViewHolder {

    private final TextInputEditText mQuestion;
    private final TextInputEditText mMaxPoint;
    private final TextInputEditText mMinPoint;

    public AdminWidgetNumberHolder(View itemView) {
        super(itemView);

        mQuestion = itemView.findViewById(R.id.editTextQuestion);
        mMaxPoint = itemView.findViewById(R.id.editTextNumberMaxGrade);
        mMinPoint = itemView.findViewById(R.id.editTextNumberMinGrade);
    }

    public void setDetails(Widget widget) {
        mQuestion.setOnFocusChangeListener((v, focus) -> {
            if (!focus) {
                final Editable answer = mQuestion.getText();
                widget.setQuestion(answer.toString());
            }
        });

        mMinPoint.setOnFocusChangeListener((v, focus) -> {
            if (!focus) {
                final Editable answer = mMinPoint.getText();
                try {
                    widget.setMinPoint(Integer.parseInt(answer.toString()));
                }
                catch (Exception ignore) {

                }
            }
        });

        mMaxPoint.setOnFocusChangeListener((v, focus) -> {
            if (!focus) {
                final Editable answer = mMaxPoint.getText();
                try {
                    widget.setMaxPoint(Integer.parseInt(answer.toString()));
                }
                catch (Exception ignore) {

                }
            }
        });
    }
}
