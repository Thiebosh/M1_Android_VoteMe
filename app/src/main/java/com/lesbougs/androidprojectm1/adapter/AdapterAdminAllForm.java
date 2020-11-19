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
import com.lesbougs.androidprojectm1.holders.AdapterAdminAllHolder;
import com.lesbougs.androidprojectm1.interfaces.FragmentSwitcher;
import com.lesbougs.androidprojectm1.model.Api;
import com.lesbougs.androidprojectm1.model.User;


import org.jetbrains.annotations.NotNull;

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

    @NotNull
    @Override
    public AdapterAdminAllHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyler_view_all_form_created, parent, false);
        AdapterAdminAllHolder vh = new AdapterAdminAllHolder(v); // pass the view to View Holder
        return vh;
    }


    public void UpdateRecycler(AdapterAdminAllHolder holder, boolean newValue) {
        if (newValue) {
           holder.getButtonClosed().setIcon(context.getResources().getDrawable(R.drawable.ic_baseline_lock_12));
            holder.getButtonClosed().setBackgroundColor(context.getResources().getColor(R.color.red));
            holder.getButtonClosed().setText(context.getResources().getText(R.string.admin_button_close));

        } else {
            holder.getButtonClosed().setIcon(context.getResources().getDrawable(R.drawable.ic_baseline_lock_open_12));
            holder.getButtonClosed().setBackgroundColor(context.getResources().getColor(R.color.green));
            holder.getButtonClosed().setText(context.getResources().getText(R.string.admin_button_open));

        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        AdapterAdminAllHolder finalHolder =(AdapterAdminAllHolder) holder;
        finalHolder.getNameForm().setText(currentUser.getForms().get(position).getTitle());


        UpdateRecycler(finalHolder, currentUser.getForms().get(position).isClosed());




        finalHolder.getShowResult().setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                activity.runOnUiThread(() -> {
                    ((FragmentSwitcher) Objects.requireNonNull(activity))
                            .loadFragment(new FormResultFragment((ArrayList) currentUser.getForms().get(position).getContent()), true);
                });
            }
        });


        finalHolder.getButtonClosed().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Executor backgroundThread = Executors.newSingleThreadExecutor();
                backgroundThread.execute(() -> {
                    FormApiService apiInterface = Api.getClient().create(FormApiService.class);

                    Call<JsonObject> call = apiInterface.closeForm(currentUser.getHeaderPayload(), currentUser.getSignature(), currentUser.getForms().get(position).get_id(), !currentUser.getForms().get(position).isClosed());
                    call.enqueue(new Callback<JsonObject>() {
                        @Override
                        public void onResponse(@NotNull Call<JsonObject> call, @NotNull Response<JsonObject> response) {

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
                                    assert response.body() != null;
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


}