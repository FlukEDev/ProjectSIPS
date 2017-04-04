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

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;

import java.text.NumberFormat;
import java.util.ArrayList;

import fluke.projectsips.R;

//จำนวนและร้อยละของผู้มีงานทำ จำแนกตามอุตสาหกรรม และเพศ

public class DataLfpLaborIndustry extends Fragment {

    private TableLayout tableData;
    private BarChart barChart;
    private ArrayList<String> name;
    private ArrayList<Integer> male;
    private ArrayList<Integer> female;
    private ArrayList<Integer> sum;
    private String little;
    private TextView tvLittle;
    private int allSum;
    private int allMale;
    private int allFemale;
    private float percentAll;
    private float percentMale;
    private float percentFemale;
    private float calAll;
    private float calMal;
    private float calFemale;
    private float outAll;
    private float outMale;
    private float outFemale;

    public DataLfpLaborIndustry() {
        super();
    }

    public static DataLfpLaborIndustry newInstance() {
        DataLfpLaborIndustry fragment = new DataLfpLaborIndustry();
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
        View rootView = inflater.inflate(R.layout.fragment_data_lfp_labor_industry, container, false);
        initInstances(rootView, savedInstanceState);

        Intent intent = getActivity().getIntent();
        name = intent.getStringArrayListExtra("name");
        male = intent.getIntegerArrayListExtra("male");
        female = intent.getIntegerArrayListExtra("female");
        sum = intent.getIntegerArrayListExtra("sum");
        little = intent.getStringExtra("little");

        tvLittle.setText(little);

        tableView();
        ChartView();

        return rootView;
    }

    private void ChartView() {

        ArrayList<Integer> listMale = new ArrayList<Integer>();
        for (Integer tamp : male) {
            listMale.add(tamp);
        }

        ArrayList<Integer> listFemale = new ArrayList<Integer>();
        for (Integer tamp : female) {
            listFemale.add(tamp);
        }

        ArrayList<Integer> listSum = new ArrayList<Integer>();
        for (Integer tamp : sum) {
            listSum.add(tamp);
        }

        ArrayList<Float> listPercentAll = new ArrayList<>();
        for (int i = 0; i < listSum.size(); i++) {
            Integer sumAll = listSum.get(i);
            float sum = ((float) sumAll * 100F) / (float) allSum;
            float cel = Math.round(sum * 10F);
            percentAll = cel / 10F;
            listPercentAll.add(percentAll);
        }

        ArrayList<Float> listPercentMale = new ArrayList<>();
        for (int i = 0; i < listSum.size(); i++) {
            Integer sumAll = listMale.get(i);
            float sum = ((float) sumAll * 100F) / (float) allSum;
            float cel = Math.round(sum * 10F);
            percentMale = cel / 10F;
            listPercentMale.add(percentMale);
        }

        ArrayList<Float> listPercentFemale = new ArrayList<>();
        for (int i = 0; i < listSum.size(); i++) {
            Integer sumAll = listFemale.get(i);
            float sum = ((float) sumAll * 100F) / (float) allSum;
            float cel = Math.round(sum * 10F);
            percentFemale = cel / 10F;
            listPercentFemale.add(percentFemale);
        }

        //Log.d("FLUKE", "PercentAll : " + listPercentAll);
        //Log.d("FLUKE", "PercentMale : " + listPercentMale);
        //Log.d("FLUKE", "PercentFemale : " + listPercentFemale);

        ArrayList<BarEntry> listAgriculture = new ArrayList<>();
        listAgriculture.add(new BarEntry(listPercentAll.get(0), 0));
        listAgriculture.add(new BarEntry(listPercentMale.get(0), 1));
        listAgriculture.add(new BarEntry(listPercentFemale.get(0), 2));

        ArrayList<BarEntry> listOutAgriculture = new ArrayList<>();
        for (int i = 1; i < listPercentAll.size(); i++) {
            calAll += listPercentAll.get(i);
            calMal += listPercentMale.get(i);
            calFemale += listPercentFemale.get(i);
        }
        float calA = Math.round(calAll * 10F);
        float calB = Math.round(calMal * 10F);
        float calC = Math.round(calFemale * 10F);

        outAll = calA / 10F;
        outMale = calB / 10F;
        outFemale = calC / 10F;

        listOutAgriculture.add(new BarEntry(outAll, 0));
        listOutAgriculture.add(new BarEntry(outMale, 1));
        listOutAgriculture.add(new BarEntry(outFemale, 2));

        ArrayList<String> labels = new ArrayList<>();
        labels.add("รวม");
        labels.add("ชาย");
        labels.add("หญิง");

        //Log.d("FLUKE", "Cal : " + listOutAgriculture);

        BarDataSet barDataSet1 = new BarDataSet(listAgriculture, "ภาคเกษตรกรรม");
        barDataSet1.setColor(Color.BLUE);

        BarDataSet barDataSet2 = new BarDataSet(listOutAgriculture, "นอคเกษตรกรรม");
        barDataSet2.setColor(Color.RED);

        ArrayList<IBarDataSet> dataSets = new ArrayList<>();
        dataSets.add(barDataSet1);
        dataSets.add(barDataSet2);

        BarData data = new BarData(labels, dataSets);
        barChart.setData(data);
        barChart.setDescription("");

    }

    private void tableView() {
        ArrayList<String> listName = new ArrayList<String>();
        for (String tamp : name) {
            listName.add(tamp);
        }

        ArrayList<Integer> listMale = new ArrayList<Integer>();
        for (Integer tamp : male) {
            listMale.add(tamp);
        }

        ArrayList<Integer> listFemale = new ArrayList<Integer>();
        for (Integer tamp : female) {
            listFemale.add(tamp);
        }

        ArrayList<Integer> listSum = new ArrayList<Integer>();
        for (Integer tamp : sum) {
            listSum.add(tamp);
        }

        for (int i = 0; i < listSum.size(); i++) {
            allSum += Integer.valueOf(listSum.get(i));
            allMale += Integer.valueOf(listMale.get(i));
            allFemale += Integer.valueOf(listFemale.get(i));
        }

        TableRow rowSum = new TableRow(getContext());
        TextView tvSumStatus = new TextView(getContext());
        tvSumStatus.setGravity(Gravity.CENTER_HORIZONTAL);
        tvSumStatus.setTextColor(Color.parseColor("#FFFFFF"));
        tvSumStatus.setText("ยอดรวม");
        TextView tvSumAll = new TextView(getContext());
        tvSumAll.setGravity(Gravity.CENTER_HORIZONTAL);
        tvSumAll.setTextColor(Color.parseColor("#FFFFFF"));
        tvSumAll.setText(String.valueOf(NumberFormat.getInstance().format(allSum)));
        TextView tvSumMale = new TextView(getContext());
        tvSumMale.setGravity(Gravity.CENTER_HORIZONTAL);
        tvSumMale.setTextColor(Color.parseColor("#FFFFFF"));
        tvSumMale.setText(String.valueOf(NumberFormat.getInstance().format(allMale)));
        TextView tvSumFemale = new TextView(getContext());
        tvSumFemale.setGravity(Gravity.CENTER_HORIZONTAL);
        tvSumFemale.setTextColor(Color.parseColor("#FFFFFF"));
        tvSumFemale.setText(String.valueOf(NumberFormat.getInstance().format(allFemale)));
        rowSum.addView(tvSumStatus);
        rowSum.addView(tvSumAll);
        rowSum.addView(tvSumMale);
        rowSum.addView(tvSumFemale);
        tableData.addView(rowSum);

        for (int i = 0; i < listName.size(); i++) {
            TableRow row = new TableRow(getContext());
            String status = listName.get(i);
            String sum = String.valueOf(NumberFormat.getInstance().format(listSum.get(i)));
            String male = String.valueOf(NumberFormat.getInstance().format(listMale.get(i)));
            String female = String.valueOf(NumberFormat.getInstance().format(listFemale.get(i)));
            TextView tvStatus = new TextView(getContext());
            tvStatus.setWidth(300);
            //tvStatus.setGravity(Gravity.CENTER_HORIZONTAL);
            tvStatus.setTextColor(Color.parseColor("#FFFFFF"));
            tvStatus.setText(status);
            TextView tvSum = new TextView(getContext());
            tvSum.setGravity(Gravity.CENTER_HORIZONTAL);
            tvSum.setTextColor(Color.parseColor("#FFFFFF"));
            tvSum.setText(sum);
            TextView tvMale = new TextView(getContext());
            tvMale.setGravity(Gravity.CENTER_HORIZONTAL);
            tvMale.setTextColor(Color.parseColor("#FFFFFF"));
            tvMale.setText(male);
            TextView tvFemale = new TextView(getContext());
            tvFemale.setGravity(Gravity.CENTER_HORIZONTAL);
            tvFemale.setTextColor(Color.parseColor("#FFFFFF"));
            tvFemale.setText(female);
            row.addView(tvStatus);
            row.addView(tvSum);
            row.addView(tvMale);
            row.addView(tvFemale);
            tableData.addView(row);
        }
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
        barChart = (BarChart) rootView.findViewById(R.id.chart);
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
