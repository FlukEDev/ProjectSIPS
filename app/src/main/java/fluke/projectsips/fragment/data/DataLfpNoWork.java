package fluke.projectsips.fragment.data;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.text.NumberFormat;
import java.util.ArrayList;

import fluke.projectsips.R;


public class DataLfpNoWork extends Fragment {

    private TableLayout tableData;
    private PieChart pieChart;
    private String little;
    private TextView tvLittle;
    private int noWorkMale;
    private int noWorkFemale;
    private int workMale;
    private int workFemale;

    public DataLfpNoWork() {
        super();
    }

    public static DataLfpNoWork newInstance() {
        DataLfpNoWork fragment = new DataLfpNoWork();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init(savedInstanceState);

        if (savedInstanceState != null)
            onRestoreInstanceState(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_data_lfp_nowork, container, false);
        initInstances(rootView, savedInstanceState);

        Intent intent = getActivity().getIntent();
        noWorkMale = intent.getIntExtra("noWorkMale", 0);
        noWorkFemale = intent.getIntExtra("noWorkFemale", 0);
        workMale = intent.getIntExtra("workMale", 0);
        workFemale = intent.getIntExtra("workFemale", 0);
        little = intent.getStringExtra("little");

        tvLittle.setText(little);

        tableView();
        ChartView();

        return rootView;
    }

    private void ChartView() {

        ArrayList<Entry> entries = new ArrayList<>();
        entries.add(new Entry(noWorkMale, 0));
        entries.add(new Entry(noWorkFemale, 1));

        ArrayList<String> labels = new ArrayList<>();
        labels.add("ชาย");
        labels.add("หญิง");

        PieDataSet dataSet = new PieDataSet(entries, "");
        dataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        dataSet.setXValuePosition(PieDataSet.ValuePosition.OUTSIDE_SLICE);
        dataSet.setValueLinePart1Length(0.5f);
        dataSet.setValueLinePart2Length(0.5f);

        PieData data = new PieData(labels, dataSet);

        Legend l = pieChart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT);
        l.setOrientation(Legend.LegendOrientation.VERTICAL);
        l.setDrawInside(false);
        l.setXEntrySpace(8f);
        l.setYEntrySpace(1f);
        l.setYOffset(0f);
        l.setFormSize(10f);
        l.setTextColor(Color.WHITE);

        pieChart.setData(data);
        pieChart.setDescription("");
        pieChart.setData(data);
        pieChart.setHoleRadius(30);
        pieChart.setTransparentCircleRadius(40);
    }

    private void tableView() {
        TableRow rowSum = new TableRow(getContext());
        TextView tvSumStatus = new TextView(getContext());
        //tvSumStatus.setGravity(Gravity.CENTER_HORIZONTAL);
        tvSumStatus.setTextColor(Color.parseColor("#FFFFFF"));
        tvSumStatus.setText("ยอดรวม");
        TextView tvSumAll = new TextView(getContext());
        tvSumAll.setGravity(Gravity.CENTER_HORIZONTAL);
        tvSumAll.setTextColor(Color.parseColor("#FFFFFF"));
        tvSumAll.setText(String.valueOf(NumberFormat.getInstance().format(workMale + workFemale)));
        TextView tvSumMale = new TextView(getContext());
        tvSumMale.setGravity(Gravity.CENTER_HORIZONTAL);
        tvSumMale.setTextColor(Color.parseColor("#FFFFFF"));
        tvSumMale.setText(String.valueOf(NumberFormat.getInstance().format(noWorkMale + noWorkFemale)));
        rowSum.addView(tvSumStatus);
        rowSum.addView(tvSumAll);
        rowSum.addView(tvSumMale);
        tableData.addView(rowSum);

        TableRow rowMale = new TableRow(getContext());
        TextView tvMale = new TextView(getContext());
        //tvSumStatus.setGravity(Gravity.CENTER_HORIZONTAL);
        tvMale.setTextColor(Color.parseColor("#FFFFFF"));
        tvMale.setText("ชาย");
        TextView tvWorkMale = new TextView(getContext());
        tvWorkMale.setGravity(Gravity.CENTER_HORIZONTAL);
        tvWorkMale.setTextColor(Color.parseColor("#FFFFFF"));
        tvWorkMale.setText(String.valueOf(NumberFormat.getInstance().format(workMale)));
        TextView tvNoWorkMale = new TextView(getContext());
        tvNoWorkMale.setGravity(Gravity.CENTER_HORIZONTAL);
        tvNoWorkMale.setTextColor(Color.parseColor("#FFFFFF"));
        tvNoWorkMale.setText(String.valueOf(NumberFormat.getInstance().format(noWorkMale)));
        rowMale.addView(tvMale);
        rowMale.addView(tvWorkMale);
        rowMale.addView(tvNoWorkMale);
        tableData.addView(rowMale);

        TableRow rowFemale = new TableRow(getContext());
        TextView tvFemale = new TextView(getContext());
        //tvSumStatus.setGravity(Gravity.CENTER_HORIZONTAL);
        tvFemale.setTextColor(Color.parseColor("#FFFFFF"));
        tvFemale.setText("หญิง");
        TextView tvWorkFemale = new TextView(getContext());
        tvWorkFemale.setGravity(Gravity.CENTER_HORIZONTAL);
        tvWorkFemale.setTextColor(Color.parseColor("#FFFFFF"));
        tvWorkFemale.setText(String.valueOf(NumberFormat.getInstance().format(workFemale)));
        TextView tvNoWorkFemale = new TextView(getContext());
        tvNoWorkFemale.setGravity(Gravity.CENTER_HORIZONTAL);
        tvNoWorkFemale.setTextColor(Color.parseColor("#FFFFFF"));
        tvNoWorkFemale.setText(String.valueOf(NumberFormat.getInstance().format(noWorkFemale)));
        rowFemale.addView(tvFemale);
        rowFemale.addView(tvWorkFemale);
        rowFemale.addView(tvNoWorkFemale);
        tableData.addView(rowFemale);
    }

    @SuppressWarnings("UnusedParameters")
    private void init(Bundle savedInstanceState) {
        // Init Fragment level's variable(s) here
    }

    @SuppressWarnings("UnusedParameters")
    private void initInstances(View rootView, Bundle savedInstanceState) {
        // Init 'View' instance(s) with rootView.findViewById here
        // Note: State of variable initialized here could not be saved
        //       in onSavedInstanceState

        tvLittle = (TextView) rootView.findViewById(R.id.little);
        tableData = (TableLayout) rootView.findViewById(R.id.tableData);
        pieChart = (PieChart) rootView.findViewById(R.id.chart);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        // Save Instance (Fragment level's variables) State here
    }

    @SuppressWarnings("UnusedParameters")
    private void onRestoreInstanceState(Bundle savedInstanceState) {
        // Restore Instance (Fragment level's variables) State here
    }

}
