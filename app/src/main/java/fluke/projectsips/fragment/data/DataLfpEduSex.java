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
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.text.NumberFormat;
import java.util.ArrayList;

import fluke.projectsips.R;

// จำนวนและร้อยละของประชากรอายุ 15 ปีขึ้นไป จำแนกตามระดับการศึกษาที่สำเร็จ และเพศ

public class DataLfpEduSex extends Fragment {

    private TableLayout tableData;
    private ArrayList<String> name;
    private ArrayList<Integer> male;
    private ArrayList<Integer> female;
    private ArrayList<Integer> sum;
    private PieChart pieChart;
    private String little;
    private TextView tvLittle;

    public DataLfpEduSex() {
        super();
    }

    public static DataLfpEduSex newInstance() {
        DataLfpEduSex fragment = new DataLfpEduSex();
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
        View rootView = inflater.inflate(R.layout.fragment_data_lfp_edu_sex, container, false);
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
        ArrayList<Integer> listSum = new ArrayList<Integer>();
        for (Integer tamp : sum) {
            listSum.add(tamp);
        }

        ArrayList<Entry> entries = new ArrayList<>();
        entries.add(new Entry(listSum.get(0), 0));
        entries.add(new Entry(listSum.get(1), 1));
        entries.add(new Entry(listSum.get(2), 2));
        entries.add(new Entry(listSum.get(3), 3));
        entries.add(new Entry(listSum.get(4), 4));
        entries.add(new Entry(listSum.get(8), 8));
        entries.add(new Entry(listSum.get(12), 12));
        entries.add(new Entry(listSum.get(13), 13));

        ArrayList<String> lables = new ArrayList<>();
        lables.add("1. ไม่มีการศึกษา");
        lables.add("2. ต่ำกว่าประถมศึกษา");
        lables.add("3. ประถมศึกษา");
        lables.add("4. มัธยมศึกษาตอนต้น");
        lables.add("5. มัธยมศึกษาตอนปลาย");
        lables.add("6. อุดมศึกษา");
        lables.add("7. อื่น ๆ");
        lables.add("8. ไม่ทราบ");

        PieDataSet dataSet = new PieDataSet(entries, "");
        dataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        dataSet.setXValuePosition(PieDataSet.ValuePosition.OUTSIDE_SLICE);
        dataSet.setValueFormatter(new PercentFormatter());
        dataSet.setValueLinePart1Length(0.5f);
        dataSet.setValueLinePart2Length(0.5f);

        PieData data = new PieData(lables, dataSet);

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
        pieChart.setUsePercentValues(true);
        pieChart.setHoleRadius(30);
        pieChart.setTransparentCircleRadius(40);

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

        TableRow rowSum = new TableRow(getContext());
        String sumAll = String.valueOf(NumberFormat.getInstance().format(listSum.get(0)
                + listSum.get(1)
                + listSum.get(2)
                + listSum.get(3)
                + listSum.get(4)
                + listSum.get(8)
                + listSum.get(12)
                + listSum.get(13)));

        String sumMale = String.valueOf(NumberFormat.getInstance().format(listMale.get(0)
                + listMale.get(1)
                + listMale.get(2)
                + listMale.get(3)
                + listMale.get(4)
                + listMale.get(8)
                + listMale.get(12)
                + listMale.get(13)));

        String sumFemale = String.valueOf(NumberFormat.getInstance().format(listFemale.get(0)
                + listFemale.get(1)
                + listFemale.get(2)
                + listFemale.get(3)
                + listFemale.get(4)
                + listFemale.get(8)
                + listFemale.get(12)
                + listFemale.get(13)));

        TextView tvSumStatus = new TextView(getContext());
        tvSumStatus.setGravity(Gravity.CENTER_HORIZONTAL);
        tvSumStatus.setTextColor(Color.parseColor("#FFFFFF"));
        tvSumStatus.setText("ยอดรวม");
        TextView tvSumAll = new TextView(getContext());
        tvSumAll.setGravity(Gravity.CENTER_HORIZONTAL);
        tvSumAll.setTextColor(Color.parseColor("#FFFFFF"));
        tvSumAll.setText(sumAll);
        TextView tvSumMale = new TextView(getContext());
        tvSumMale.setGravity(Gravity.CENTER_HORIZONTAL);
        tvSumMale.setTextColor(Color.parseColor("#FFFFFF"));
        tvSumMale.setText(sumMale);
        TextView tvSumFemale = new TextView(getContext());
        tvSumFemale.setGravity(Gravity.CENTER_HORIZONTAL);
        tvSumFemale.setTextColor(Color.parseColor("#FFFFFF"));
        tvSumFemale.setText(sumFemale);
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
            tvStatus.setGravity(Gravity.CENTER_HORIZONTAL);
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
