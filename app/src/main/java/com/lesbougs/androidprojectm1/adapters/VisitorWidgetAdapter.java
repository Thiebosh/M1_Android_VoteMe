package com.lesbougs.androidprojectm1.adapters;

import android.content.Context;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.lesbougs.androidprojectm1.R;
import com.lesbougs.androidprojectm1.holders.VisitorWidgetNumberHolder;
import com.lesbougs.androidprojectm1.holders.VisitorWidgetTextHolder;
import com.lesbougs.androidprojectm1.model.Widget;

import java.util.ArrayList;
import java.util.Collections;

public class VisitorWidgetAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final static int TYPE_TEXT = 1;
    private final static int TYPE_POINT = 2;

    private final Context mContext;
    private final ArrayList<Widget> mWidgets;
    private final ArrayList<String> mAnswers;

    public VisitorWidgetAdapter(Context context, ArrayList<Widget> widgets) {
        this.mContext = context;
        this.mWidgets = widgets;
        this.mAnswers = new ArrayList<>(Collections.nCopies(widgets.size(), ""));
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
            View view = LayoutInflater.from(mContext).inflate(R.layout.item_visit_widget_text, parent, false);
            return new VisitorWidgetTextHolder(view);
        }
        else {
            View view = LayoutInflater.from(mContext).inflate(R.layout.item_visit_widget_number, parent, false);
            return new VisitorWidgetNumberHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == TYPE_TEXT) {
            ((VisitorWidgetTextHolder) holder).setDetails(mWidgets.get(position));

            ((VisitorWidgetTextHolder) holder).mAnswerInput.setOnFocusChangeListener((v, focus) -> {
                if (!focus) {
                    final Editable answer = ((VisitorWidgetTextHolder) holder).mAnswerInput.getText();
                    mAnswers.set(position, answer != null ? answer.toString() : "");
                }
            });
        }
        else {
            ((VisitorWidgetNumberHolder) holder).setDetails(mWidgets.get(position));

            ((VisitorWidgetNumberHolder) holder).mAnswerInput.setOnFocusChangeListener((v, focus) -> {
                if (!focus) {
                    final Editable answer = ((VisitorWidgetNumberHolder) holder).mAnswerInput.getText();
                    mAnswers.set(position, answer != null ? answer.toString() : "");
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mWidgets.size();
    }

    public ArrayList<String> getAnswers() {
        return mAnswers;
    }
}
