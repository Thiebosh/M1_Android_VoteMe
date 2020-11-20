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


public class AdminWidgetTextHolder extends RecyclerView.ViewHolder {

    private final TextInputEditText mQuestion;
    private final MaterialButton mDelete;

    public AdminWidgetTextHolder(View itemView) {
        super(itemView);

        mQuestion = itemView.findViewById(R.id.editTextQuestion);
        mDelete = itemView.findViewById(R.id.button_delete);

        itemView.setOnClickListener(view -> {
            //handle click event
            //Log.d("RecyclerView",mAnswerInput.getText().toString());
        });
    }

    public void setDetails(Widget widget, AdminWidgetAdapter adapter, ArrayList<Widget> widgets, int position) {
        mQuestion.setOnFocusChangeListener((v, focus) -> {
            if (!focus) {
                final Editable answer = mQuestion.getText();
                try {
                    assert answer != null;
                    widget.setQuestion(answer.toString());
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
