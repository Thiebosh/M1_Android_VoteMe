package com.lesbougs.androidprojectm1.holders;

import android.text.Editable;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.lesbougs.androidprojectm1.R;
import com.lesbougs.androidprojectm1.adapters.AdminWidgetAdapter;
import com.lesbougs.androidprojectm1.model.Widget;

import java.util.ArrayList;


public class AdminWidgetNumberHolder extends RecyclerView.ViewHolder {

    private final TextInputEditText mQuestion;
    private final TextInputEditText mMaxPoint;
    private final TextInputEditText mMinPoint;
    private final MaterialButton mDelete;

    public AdminWidgetNumberHolder(View itemView) {
        super(itemView);

        mQuestion = itemView.findViewById(R.id.editTextQuestion);
        mMaxPoint = itemView.findViewById(R.id.editTextNumberMaxGrade);
        mMinPoint = itemView.findViewById(R.id.editTextNumberMinGrade);
        mDelete = itemView.findViewById(R.id.button_delete);
    }

    public void setDetails(Widget widget, AdminWidgetAdapter adapter, ArrayList<Widget> widgets, int position) {
        mQuestion.setOnFocusChangeListener((v, focus) -> {
            if (!focus) {
                final Editable answer = mQuestion.getText();
                assert answer != null;
                widget.setQuestion(answer.toString());
            }
        });

        mMinPoint.setOnFocusChangeListener((v, focus) -> {
            if (!focus) {
                final Editable answer = mMinPoint.getText();
                try {
                    assert answer != null;
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
                    assert answer != null;
                    widget.setMaxPoint(Integer.parseInt(answer.toString()));
                }
                catch (Exception ignore) {

                }
            }
        });

        mDelete.setOnClickListener(view -> {
            widgets.remove(position);
            adapter.notifyDataSetChanged();
        });
    }
}
