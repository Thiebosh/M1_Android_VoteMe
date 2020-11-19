package com.lesbougs.androidprojectm1.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.github.mikephil.charting.charts.BarChart;
import com.lesbougs.androidprojectm1.R;
import com.lesbougs.androidprojectm1.model.Widget;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

/**
 * Created by anupamchugh on 09/02/16.
 */
public class AdapterAdminResult extends RecyclerView.Adapter {

    ArrayList<Widget> dataSet = new ArrayList<>();
    Context mContext;

    public static class Type0 extends RecyclerView.ViewHolder {

        TextView textField;

        public Type0(View itemView) {
            super(itemView);
            this.textField = (TextView) itemView.findViewById(R.id.textViewResult);
        }
    }


    public static class Type1 extends RecyclerView.ViewHolder {

        TextView textField;
        BarChart barChart;

        public Type1(View itemView) {
            super(itemView);
            this.barChart = (BarChart) itemView.findViewById(R.id.barChartResult);
            this.textField = (TextView) itemView.findViewById(R.id.averageResult);
        }
    }

     public AdapterAdminResult(ArrayList<Widget> data, Context context) {
        this.dataSet = data;
        this.mContext = context;
    }

    //@NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view;
        switch (viewType) {
            case 0:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_result_type_0, parent, false);
                return new Type0(view);
            case 1:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_result_type_1, parent, false);
                return new Type1(view);
        }
        return null;
    }


    @Override
    public void onBindViewHolder(@NotNull final RecyclerView.ViewHolder holder, final int listPosition) {

        Widget object = dataSet.get(listPosition);
        Log.d("TAG",object.toString());
        if (object != null) {
            switch (object.type) {
                case 0:
                    ((Type0) holder).textField.setText(object.getTitle());
                    break;
                case 1:
                    ((Type1) holder).textField.setText("lol");
                    break;

            }
        }
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }
}