package com.lesbougs.androidprojectm1.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.lesbougs.androidprojectm1.R;
import com.lesbougs.androidprojectm1.holders.AdminFormHolder;
import com.lesbougs.androidprojectm1.model.Form;


import java.util.ArrayList;

public class AdminFormAdapter extends RecyclerView.Adapter<AdminFormHolder> {

    private final Context mContext;
    private final Activity mActivity;
    private final ArrayList<Form> mForms;
    private final String mUserPayload;
    private final String mUserSignature;

    public AdminFormAdapter(Activity activity, Context mContext, ArrayList<Form> forms, String payload, String signature) {
        this.mActivity = activity;
        this.mContext = mContext;
        this.mForms = forms;
        this.mUserPayload = payload;
        this.mUserSignature = signature;
    }

    @NonNull
    @Override
    public AdminFormHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.recyler_view_all_form_created, parent, false);
        return new AdminFormHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdminFormHolder holder, int position) {
        holder.setDetails(mForms.get(position), mActivity, mContext, mUserPayload, mUserSignature);
    }

    @Override
    public int getItemCount() {
        return mForms.size();
    }

}