package com.lesbougs.androidprojectm1.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.lesbougs.androidprojectm1.R;
import com.lesbougs.androidprojectm1.holders.WidgetHolder;
import com.lesbougs.androidprojectm1.model.Widget;

import java.util.ArrayList;

public class WidgetAdapter extends RecyclerView.Adapter<WidgetHolder> {

    private final Context mContext;
    private final ArrayList<Widget> mWidgets;

    public WidgetAdapter(Context context, ArrayList<Widget> widgets) {
        this.mContext = context;
        this.mWidgets = widgets;
    }

    @NonNull
    @Override
    public WidgetHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_widget, parent, false);
        return new WidgetHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WidgetHolder holder, int position) {
        holder.setDetails(mWidgets.get(position));
    }

    @Override
    public int getItemCount() {
        return mWidgets.size();
    }
}
