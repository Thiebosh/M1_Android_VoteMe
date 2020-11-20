package com.lesbougs.androidprojectm1.holders;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textfield.TextInputEditText;
import com.lesbougs.androidprojectm1.R;
import com.lesbougs.androidprojectm1.model.Widget;


public class VisitorWidgetNumberHolder extends RecyclerView.ViewHolder {

    private final TextView mQuestionTitle;
    private final TextView mLimitNumber;
    public TextInputEditText mAnswerInput;

    public VisitorWidgetNumberHolder(View itemView) {
        super(itemView);

        mQuestionTitle = itemView.findViewById(R.id.item_visitor_widget_number_title);
        mLimitNumber = itemView.findViewById(R.id.item_visitor_widget_number_limit);
        mAnswerInput = itemView.findViewById(R.id.item_visitor_widget_edit_number);

        itemView.setOnClickListener(view -> {
            //handle click event
            //Log.d("RecyclerView",mAnswerInput.getText().toString());
        });
    }

    public void setDetails(Widget widget) {
        String str = "Entre "+widget.getMinPoint()+" et "+widget.getMaxPoint();
        mLimitNumber.setText(str);
        mQuestionTitle.setText(widget.getQuestion());
    }
}
