package com.lesbougs.androidprojectm1.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.lesbougs.androidprojectm1.R;
import com.lesbougs.androidprojectm1.holders.AdminWidgetNumberHolder;
import com.lesbougs.androidprojectm1.holders.AdminWidgetTextHolder;
import com.lesbougs.androidprojectm1.model.Widget;

import java.util.ArrayList;

public class AdminWidgetAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final static int TYPE_TEXT = 1;
    private final static int TYPE_POINT = 2;

    private final Context mContext;
    private final ArrayList<Widget> mWidgets;

    public AdminWidgetAdapter(Context context, ArrayList<Widget> widgets) {
        this.mContext = context;
        this.mWidgets = widgets;
    }

    @Override
    public int getItemViewType(int position) {
        if (mWidgets.get(position).getType() == 0) {
            return TYPE_TEXT;
        }
        else {
            return TYPE_POINT;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == TYPE_TEXT) {
            View view = LayoutInflater.from(mContext).inflate(R.layout.item_admin_widget_text, parent, false);
            return new AdminWidgetTextHolder(view);
        }
        else {
            View view = LayoutInflater.from(mContext).inflate(R.layout.item_admin_widget_number, parent, false);
            return new AdminWidgetNumberHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == TYPE_TEXT) {
            ((AdminWidgetTextHolder) holder).setDetails(mWidgets.get(position), this, mWidgets, position);
        }
        else if (getItemViewType(position) == TYPE_POINT) {
            ((AdminWidgetNumberHolder) holder).setDetails(mWidgets.get(position), this, mWidgets, position);
        }
    }

    @Override
    public int getItemCount() {
        return mWidgets.size();
    }
}
