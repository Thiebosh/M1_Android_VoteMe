package com.lesbougs.androidprojectm1.adapter;

import android.content.Context;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.lesbougs.androidprojectm1.R;
import com.lesbougs.androidprojectm1.model.Widget;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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

    @NotNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view;
        Log.d("TAG", viewType + "");

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

    public int getItemViewType(int position) {
        return dataSet.get(position).type;
    }


    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int listPosition) {

        Widget object = dataSet.get(listPosition);
        switch (object.type) {
            case 0:

                String allResult = "";

                if (object.getTextFieldResult().size() == 0) {
                    allResult = "Aucun résultat";
                } else {
                    for (String element : object.getTextFieldResult()) {
                        allResult = allResult.concat(element + "\n");
                    }
                }


                ((Type0) holder).textField.setText(allResult);
                break;
            case 1:
                ArrayList<BarEntry> entries = new ArrayList<>();

                Map<Integer, Integer> countMap = new HashMap<Integer, Integer>();
                for (int i = 0; i < object.getResultPoint().size(); i++) {
                    int key = object.getResultPoint().get(i);
                    if (countMap.containsKey(key)) {
                        int count = countMap.get(key);
                        count++;
                        countMap.put(key, count);
                    } else {
                        countMap.put(key, 1);
                    }
                }
                Log.d("TAG",countMap+"");
                for (int i = object.getMinPoint(); i < object.getMaxPoint(); i++) {
                    if (countMap.containsKey(i)) {
                        entries.add(new BarEntry(i, countMap.get(i)));
                    } else {
                        entries.add(new BarEntry(i, 0));
                    }
                }


                BarChart chart = ((Type1) holder).barChart;

                YAxis leftAxis = chart.getAxis(YAxis.AxisDependency.LEFT);
                YAxis rightAxis = chart.getAxisRight();
                XAxis xAxis = chart.getXAxis();


                leftAxis.setDrawGridLines(false);

                xAxis.setTextSize(10f);
                xAxis.setDrawAxisLine(true);
                xAxis.setDrawGridLines(false);


                rightAxis.setDrawAxisLine(false);
                rightAxis.setDrawGridLines(false);
                rightAxis.setDrawLabels(false);


                BarDataSet set = new BarDataSet(entries, "");
                set.setColor(Color.rgb(155, 155, 155));
                set.setValueTextColor(Color.rgb(155, 155, 155));

                BarData data = new BarData(set);
                chart.setData(data);
                chart.setFitBars(true); // make the x-axis fit exactly all bars
                chart.invalidate(); // refresh
                chart.setScaleEnabled(false);
                chart.setDoubleTapToZoomEnabled(false);
                chart.setBackgroundColor(Color.rgb(255, 255, 255));
                chart.animateXY(2000, 2000);
                chart.setDrawBorders(false);
                chart.setDrawValueAboveBar(true);


                break;

        }
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }
}