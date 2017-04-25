package fluke.projectsips.fragment.data;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.util.ArrayList;

import fluke.projectsips.R;


public class DataPopulationFragment extends Fragment {

    private LineChart mChart;
    private ArrayList<String> listMale;
    private ArrayList<String> listFemale;
    private LineDataSet set1;
    private LineDataSet set2;
    private String districtName;
    private TextView little;
    private int year;
    private int sum;

    public DataPopulationFragment() {
        super();
    }

    public static DataPopulationFragment newInstance() {
        DataPopulationFragment fragment = new DataPopulationFragment();
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

    private void showData() {

        Intent intent = getActivity().getIntent();

        listMale = intent.getStringArrayListExtra("listMale");
        listFemale = intent.getStringArrayListExtra("listFemale");
        districtName = intent.getStringExtra("districtName");
        year = intent.getIntExtra("year", 0);

        ArrayList<Entry> male = new ArrayList<Entry>();
        for (int i = 0; i < listMale.size(); i++) {
            male.add(new Entry(Float.parseFloat(listMale.get(i)), i));
            //Log.d("FLUKE", "Num Male : " + male);
        }

        ArrayList<Entry> female = new ArrayList<Entry>();
        for (int i = 0; i < listFemale.size(); i++) {
            female.add(new Entry(Float.parseFloat(listFemale.get(i)), i));
            //Log.d("FLUKE", "Num Male : " + male);
        }

        ArrayList<String> labels = new ArrayList<String>();
        for (int i = 0; i < male.size(); i++) {
            sum = year - i;
            labels.add(String.valueOf(sum));
        }

        YAxis left = mChart.getAxisLeft();
        left.setDrawLabels(false); // no axis labels
        left.setDrawAxisLine(false); // no axis line
        left.setDrawGridLines(false); // no grid lines
        left.setDrawZeroLine(true); // draw a zero line

        mChart.getAxisRight().setEnabled(false); // no right axis
        mChart.setDescription("ข้อมูลประชากร");

        set1 = new LineDataSet(male, "เพศชาย");
        set1.setColor(Color.BLUE);
        set1.setCircleColor(Color.WHITE);
        set1.setLineWidth(2f);
        set1.setCircleRadius(3f);

        set2 = new LineDataSet(female, "เพศหญิง");
        set2.setCircleColor(Color.WHITE);
        set2.setColor(Color.RED);
        set2.setLineWidth(2f);
        set2.setCircleRadius(3f);

        ArrayList<ILineDataSet> dataSets = new ArrayList<ILineDataSet>();
        dataSets.add(set2);
        dataSets.add(set1);

        // create a data object with the datasets
        LineData data = new LineData(labels, dataSets);
        data.setValueTextColor(Color.WHITE);
        data.setValueTextSize(11f);

        // set data
        mChart.setData(data);

        mChart.animateXY(3000, 3000);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_data_population, container, false);
        initInstances(rootView, savedInstanceState);
        showData();
        showYearData();

        little.setText("จำนวนประชากรแยกตามปรเภท ชาย - หญิง อำเภอ" + districtName);

        return rootView;
    }

    private void showYearData() {

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

        mChart = (LineChart) rootView.findViewById(R.id.population);
        little = (TextView) rootView.findViewById(R.id.subLittle);
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
