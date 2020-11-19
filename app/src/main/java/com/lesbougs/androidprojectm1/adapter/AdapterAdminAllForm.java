package com.lesbougs.androidprojectm1.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;
import com.google.gson.JsonObject;
import com.lesbougs.androidprojectm1.R;
import com.lesbougs.androidprojectm1.api.FormApiService;
import com.lesbougs.androidprojectm1.fragments.FormListFragment;
import com.lesbougs.androidprojectm1.fragments.FormResultFragment;
import com.lesbougs.androidprojectm1.interfaces.FragmentSwitcher;
import com.lesbougs.androidprojectm1.model.Api;
import com.lesbougs.androidprojectm1.model.User;


import java.util.ArrayList;
import java.util.Objects;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdapterAdminAllForm extends RecyclerView.Adapter {
    User currentUser;

    Activity activity;
    Context context;

    public AdapterAdminAllForm(Context context, Activity activity, User currentUser) {
        this.context = context;
        this.activity = activity;
        this.currentUser = currentUser;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyler_view_all_form_created, parent, false);
        MyViewHolder vh = new MyViewHolder(v); // pass the view to View Holder
        return vh;
    }


    public void UpdateRecycler(RecyclerView.ViewHolder holder, boolean newValue) {
        if (newValue) {
            ((MyViewHolder) holder).buttonClosed.setIcon(context.getResources().getDrawable(R.drawable.ic_baseline_lock_12));
            ((MyViewHolder) holder).buttonClosed.setBackgroundColor(context.getResources().getColor(R.color.red));
            ((MyViewHolder) holder).buttonClosed.setText(context.getResources().getText(R.string.admin_button_close));

        } else {
            ((MyViewHolder) holder).buttonClosed.setIcon(context.getResources().getDrawable(R.drawable.ic_baseline_lock_open_12));
            ((MyViewHolder) holder).buttonClosed.setBackgroundColor(context.getResources().getColor(R.color.green));
            ((MyViewHolder) holder).buttonClosed.setText(context.getResources().getText(R.string.admin_button_open));

        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((MyViewHolder) holder).nameForm.setText(currentUser.getForms().get(position).getTitle());


        UpdateRecycler(holder, currentUser.getForms().get(position).isClosed());


        RecyclerView.ViewHolder finalHolder = holder;

        ((MyViewHolder) holder).showResult.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                activity.runOnUiThread(() -> {
                    ((FragmentSwitcher) Objects.requireNonNull(activity))
                            .loadFragment(new FormResultFragment((ArrayList) currentUser.getForms().get(position).getContent()), true);
                });
            }
        });


        ((MyViewHolder) holder).buttonClosed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Executor backgroundThread = Executors.newSingleThreadExecutor();
                backgroundThread.execute(() -> {
                    FormApiService apiInterface = Api.getClient().create(FormApiService.class);

                    Call<JsonObject> call = apiInterface.closeForm(currentUser.getHeaderPayload(), currentUser.getSignature(), currentUser.getForms().get(position).get_id(), !currentUser.getForms().get(position).isClosed());
                    call.enqueue(new Callback<JsonObject>() {
                        @Override
                        public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {

                            if (response.code() == 200) {

                                if (currentUser.getForms().get(position).isClosed()) {
                                    activity.runOnUiThread(() -> {
                                        Toast.makeText(context, "Form is open", Toast.LENGTH_SHORT).show();
                                    });
                                } else {
                                    activity.runOnUiThread(() -> {
                                        Toast.makeText(context, "Form is close", Toast.LENGTH_SHORT).show();
                                    });
                                }

                                UpdateRecycler(finalHolder, !currentUser.getForms().get(position).isClosed());
                                currentUser.getForms().get(position).setClosed(!currentUser.getForms().get(position).isClosed());
                            } else {
                                activity.runOnUiThread(() -> {
                                    Toast.makeText(context, response.body().get("message").getAsString(), Toast.LENGTH_SHORT).show();
                                });
                            }
                        }

                        @Override
                        public void onFailure(Call<JsonObject> call, Throwable t) {
                            call.cancel();
                        }
                    });
                });

            }
        });


    }


    @Override
    public int getItemCount() {
        return currentUser.getForms().size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView nameForm;// init the item view's
        MaterialButton buttonClosed;// init the item view's
        Button showResult;// init the item view's

        public MyViewHolder(View itemView) {
            super(itemView);
            // get the reference of item view's
            nameForm = (TextView) itemView.findViewById(R.id.textView);
            buttonClosed = (MaterialButton) itemView.findViewById(R.id.button_closed);
            showResult = (Button) itemView.findViewById(R.id.button_show_result);
        }
    }
}