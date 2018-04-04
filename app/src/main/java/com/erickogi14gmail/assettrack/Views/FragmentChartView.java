package com.erickogi14gmail.assettrack.Views;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.erickogi14gmail.assettrack.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class FragmentChartView  extends Fragment{
    private BarChart mChart;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return  inflater.inflate(R.layout.chart_view,container,false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);






        mChart = (BarChart) view.findViewById(R.id.chart1);

        mChart.getDescription().setEnabled(false);

        // if more than 60 entries are displayed in the chart, no values will be
        // drawn
        mChart.setMaxVisibleValueCount(60);

        // scaling can now only be done on x- and y-axis separately
        mChart.setPinchZoom(false);

        mChart.setDrawBarShadow(false);
        mChart.setDrawGridBackground(false);

        mChart.animateY(2500);

        mChart.getLegend().setEnabled(false);


















    }

    private void  lineChart(View view){
        LineChart chart = (LineChart) view.findViewById(R.id.chart1);

        LinkedList<dtata> data   = new LinkedList<>();
        for(int a=0;a<30;a++){
            dtata f=new dtata();
            f.setValueX(1.0f+a);
            f.setValueY(2.0f+a);

            data.add(f);
        }

        List<Entry> entries = new ArrayList<Entry>();

        for (dtata d : data) {

            // turn your data into Entry objects
            entries.add(new Entry(d.getValueX(), d.getValueY()));
        }

        LineDataSet dataSet = new LineDataSet(entries, "Label"); // add entries to dataset
        dataSet.setColor(getContext().getResources().getColor(R.color.accent));
        dataSet.setValueTextColor(getContext().getResources().getColor(R.color.colorA));

        LineData lineData = new LineData(dataSet);
        chart.setData(lineData);
        chart.invalidate(); // refresh
    }

    private class  dtata{
        private Float valueX;
        private Float valueY;

        public Float getValueX() {
            return valueX;
        }

        public void setValueX(Float valueX) {
            this.valueX = valueX;
        }

        public Float getValueY() {
            return valueY;
        }

        public void setValueY(Float valueY) {
            this.valueY = valueY;
        }
    }
}
