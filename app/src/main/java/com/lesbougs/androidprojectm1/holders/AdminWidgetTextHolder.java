package com.lesbougs.androidprojectm1.holders;

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

    public TextInputEditText getmQuestion() {
        return mQuestion;
    }

    public void setmQuestion(TextInputEditText mQuestion) {
        this.mQuestion = mQuestion;
    }

}
