package com.lesbougs.androidprojectm1.holders;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.lesbougs.androidprojectm1.R;
import com.lesbougs.androidprojectm1.model.Widget;


public class VisitorWidgetNumberHolder extends RecyclerView.ViewHolder {

    private final TextView mTitle;
    private final TextInputLayout mQuestionTitle;
    public TextInputEditText mAnswerInput;

    public VisitorWidgetNumberHolder(View itemView) {
        super(itemView);

        mTitle = itemView.findViewById(R.id.item_visitor_widget_number_title);
        mQuestionTitle = itemView.findViewById(R.id.item_visitor_widget_number_input);
        mAnswerInput = itemView.findViewById(R.id.item_visitor_widget_edit_number);

        itemView.setOnClickListener(view -> {
            //handle click event
            //Log.d("RecyclerView",mAnswerInput.getText().toString());
        });
    }

    public void setDetails(Widget widget) {
        mTitle.setText(widget.getQuestion());
        mQuestionTitle.setHint("Entre 0 et "+widget.getMaxPoint());
    }
}
