package com.lesbougs.androidprojectm1.holders;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;
import com.google.gson.JsonObject;
import com.lesbougs.androidprojectm1.R;
import com.lesbougs.androidprojectm1.api.FormApiService;
import com.lesbougs.androidprojectm1.fragments.FormResultFragment;
import com.lesbougs.androidprojectm1.interfaces.FragmentSwitcher;
import com.lesbougs.androidprojectm1.model.Api;
import com.lesbougs.androidprojectm1.model.Form;

import java.util.Objects;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class AdminFormHolder extends RecyclerView.ViewHolder {

    private final TextView mNameForm;
    private final MaterialButton mButtonClosed;
    private final Button mShowResult;

    public AdminFormHolder(View itemView) {
        super(itemView);

        mNameForm = (TextView) itemView.findViewById(R.id.textView);
        mButtonClosed = (MaterialButton) itemView.findViewById(R.id.button_closed);
        mShowResult = (Button) itemView.findViewById(R.id.button_show_result);

        itemView.setOnClickListener(view -> {
            //handle click event
            //Log.d("RecyclerView",mAnswerInput.getText().toString());
        });
    }

    public void setDetails(Form form, Activity activity, Context context, String userPayload, String userSignature) {
        mNameForm.setText(form.getTitle());


        mShowResult.setOnClickListener(view -> activity.runOnUiThread(() ->
            ((FragmentSwitcher) Objects.requireNonNull(activity))
                .loadFragment(new FormResultFragment(form.getContent()), true)
        ));

        UpdateRecycler(context, form.isClosed());

        mButtonClosed.setOnClickListener(view -> {
            //call next fragment with data transfer
            (Executors.newSingleThreadExecutor()).execute(() -> {
                FormApiService apiInterface = Api.getClient().create(FormApiService.class);

                Call<JsonObject> call = apiInterface.closeForm(userPayload, userSignature, form.get_id(), form.isClosed());

                call.enqueue(new Callback<JsonObject>() {
                    @Override
                    public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                        if (response.code() == 200) {
                            form.setClosed(!form.isClosed());

                            if (form.isClosed()) {
                                activity.runOnUiThread(() ->
                                    Toast.makeText(context, "Form is close", Toast.LENGTH_SHORT).show());
                            }
                            else {
                                activity.runOnUiThread(() ->
                                    Toast.makeText(context, "Form is open", Toast.LENGTH_SHORT).show());
                            }

                            UpdateRecycler(context, form.isClosed());
                        }
                        else {
                            activity.runOnUiThread(() ->
                                Toast.makeText(context, Objects.requireNonNull(response.body()).get("message").getAsString(), Toast.LENGTH_SHORT).show());
                        }
                    }

                    @Override
                    public void onFailure(Call<JsonObject> call, Throwable t) {
                        call.cancel();
                    }
                });
            });
        });
    }

    public void UpdateRecycler(Context context, boolean newValue) {
        if (newValue) {
            mButtonClosed.setIcon(context.getDrawable(R.drawable.ic_baseline_lock_12));
            mButtonClosed.setBackgroundColor(context.getColor(R.color.red));
        }
        else {
            mButtonClosed.setIcon(context.getDrawable(R.drawable.ic_baseline_lock_open_12));
            mButtonClosed.setBackgroundColor(context.getColor(R.color.green));
        }
    }
}
