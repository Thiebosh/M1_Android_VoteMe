package com.lesbougs.androidprojectm1.holders;

import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.lesbougs.androidprojectm1.R;
import com.lesbougs.androidprojectm1.model.Widget;


public class VisitorWidgetHolder extends RecyclerView.ViewHolder {

    private final TextView mTitle;
    private final TextInputLayout mQuestionTitle;
    public TextInputEditText mAnswerInput;

    public VisitorWidgetHolder(View itemView) {
        super(itemView);

        mTitle = itemView.findViewById(R.id.item_widget_title);
        mQuestionTitle = itemView.findViewById(R.id.item_widget_text_input);
        mAnswerInput = itemView.findViewById(R.id.item_widget_edit_text);

        itemView.setOnClickListener(view -> {
            //handle click event
            //Log.d("RecyclerView",mAnswerInput.getText().toString());
        });
    }

    public void setDetails(Widget widget) {
        mTitle.setText(widget.getTitle());
        mQuestionTitle.setHint(widget.getTextField());
    }
}
