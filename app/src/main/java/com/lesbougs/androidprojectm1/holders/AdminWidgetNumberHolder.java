package com.lesbougs.androidprojectm1.holders;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.lesbougs.androidprojectm1.R;
import com.lesbougs.androidprojectm1.model.Widget;


public class AdminWidgetNumberHolder extends RecyclerView.ViewHolder {

    private  TextInputEditText mQuestion;
    private  TextInputEditText mMaxPoint;
    private TextInputEditText mMinPoint;

    public AdminWidgetNumberHolder(View itemView) {
        super(itemView);

        mQuestion = itemView.findViewById(R.id.editTextQuestion);
        mMaxPoint = itemView.findViewById(R.id.editTextNumberMaxGrade);
        mMinPoint = itemView.findViewById(R.id.editTextNumberMinGrade);

    }

    public TextInputEditText getmQuestion() {
        return mQuestion;
    }

    public void setmQuestion(TextInputEditText mQuestion) {
        this.mQuestion = mQuestion;
    }

    public TextInputEditText getmMaxPoint() {
        return mMaxPoint;
    }

    public void setmMaxPoint(TextInputEditText mMaxPoint) {
        this.mMaxPoint = mMaxPoint;
    }

    public TextInputEditText getmMinPoint() {
        return mMinPoint;
    }

    public void setmMinPoint(TextInputEditText mMinPoint) {
        this.mMinPoint = mMinPoint;
    }
}
