package com.lesbougs.androidprojectm1.holders;

import android.text.Editable;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.lesbougs.androidprojectm1.R;
import com.lesbougs.androidprojectm1.model.Widget;


public class AdminWidgetTextHolder extends RecyclerView.ViewHolder {

    private  TextInputEditText mQuestion;

    public AdminWidgetTextHolder(View itemView) {
        super(itemView);

        mQuestion = itemView.findViewById(R.id.editTextQuestion);

        itemView.setOnClickListener(view -> {
            //handle click event
            //Log.d("RecyclerView",mAnswerInput.getText().toString());
        });
    }

    public void setDetails(Widget widget) {
        mQuestion.setOnFocusChangeListener((v, focus) -> {
            if (!focus) {
                final Editable answer = mQuestion.getText();
                try {
                    widget.setQuestion(answer.toString());
                }
                catch (Exception ignore) {

                }
            }
        });
    }
}
