package com.lesbougs.androidprojectm1.holders;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.lesbougs.androidprojectm1.R;
import com.lesbougs.androidprojectm1.model.Widget;


public class AdminWidgetNumberHolder extends RecyclerView.ViewHolder {

    private final TextInputLayout mQuestionTitle;
    private final TextView mLimitNumber;
    public TextInputEditText mAnswerInput;

    public AdminWidgetNumberHolder(View itemView) {
        super(itemView);

        mQuestionTitle = itemView.findViewById(R.id.item_admin_widget_number_input);
        mLimitNumber = itemView.findViewById(R.id.item_admin_widget_number_limit);
        mAnswerInput = itemView.findViewById(R.id.item_admin_widget_edit_number);

        itemView.setOnClickListener(view -> {
            //handle click event
            //Log.d("RecyclerView",mAnswerInput.getText().toString());
        });
    }

    public void setDetails(Widget widget) {
        String str = "Entre "+widget.getMinPoint()+" et "+widget.getMaxPoint();
        mLimitNumber.setText(str);
        mQuestionTitle.setHint(widget.getQuestion());
    }
}
