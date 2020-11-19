package com.lesbougs.androidprojectm1.holders;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;
import com.lesbougs.androidprojectm1.R;

public class AdapterAdminAllHolder extends RecyclerView.ViewHolder {
    TextView nameForm;// init the item view's
    MaterialButton buttonClosed;// init the item view's
    Button showResult;// init the item view's

    public AdapterAdminAllHolder(View itemView) {
        super(itemView);
        // get the reference of item view's
        nameForm = (TextView) itemView.findViewById(R.id.textView);
        buttonClosed = (MaterialButton) itemView.findViewById(R.id.button_closed);
        showResult = (Button) itemView.findViewById(R.id.button_show_result);
    }

    public TextView getNameForm() {
        return nameForm;
    }

    public void setNameForm(TextView nameForm) {
        this.nameForm = nameForm;
    }

    public MaterialButton getButtonClosed() {
        return buttonClosed;
    }

    public void setButtonClosed(MaterialButton buttonClosed) {
        this.buttonClosed = buttonClosed;
    }

    public Button getShowResult() {
        return showResult;
    }

    public void setShowResult(Button showResult) {
        this.showResult = showResult;
    }
}