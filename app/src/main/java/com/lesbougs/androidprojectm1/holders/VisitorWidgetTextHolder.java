package com.lesbougs.androidprojectm1.holders;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textfield.TextInputEditText;
import com.lesbougs.androidprojectm1.R;
import com.lesbougs.androidprojectm1.model.Widget;


public class VisitorWidgetTextHolder extends RecyclerView.ViewHolder {

    private final TextView mQuestionTitle;
    public TextInputEditText mAnswerInput;

    public VisitorWidgetTextHolder(View itemView) {
        super(itemView);

        mQuestionTitle = itemView.findViewById(R.id.item_visitor_widget_text_title);
        mAnswerInput = itemView.findViewById(R.id.item_visitor_widget_edit_text);

        itemView.setOnClickListener(view -> {
            //handle click event
            //Log.d("RecyclerView",mAnswerInput.getText().toString());
        });
    }

    public void setDetails(Widget widget) {
        mQuestionTitle.setText(widget.getQuestion());
    }
}
