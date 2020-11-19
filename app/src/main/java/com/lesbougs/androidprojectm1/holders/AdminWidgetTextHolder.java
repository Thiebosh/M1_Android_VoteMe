package com.lesbougs.androidprojectm1.holders;

import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.lesbougs.androidprojectm1.R;
import com.lesbougs.androidprojectm1.model.Widget;


public class AdminWidgetTextHolder extends RecyclerView.ViewHolder {

    private final TextInputLayout mQuestionTitle;
    public TextInputEditText mAnswerInput;

    public AdminWidgetTextHolder(View itemView) {
        super(itemView);

        mQuestionTitle = itemView.findViewById(R.id.item_admin_widget_text_input);
        mAnswerInput = itemView.findViewById(R.id.item_admin_widget_edit_text);

        itemView.setOnClickListener(view -> {
            //handle click event
            //Log.d("RecyclerView",mAnswerInput.getText().toString());
        });
    }

    public void setDetails(Widget widget) {
        mQuestionTitle.setHint(widget.getQuestion());
    }
}
