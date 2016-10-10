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

import java.util.ArrayList;

import fluke.projectsips.R;


public class DataCpiFragment extends Fragment {

    private ArrayList<String> cpiValue;
    private ArrayList<String> monthYear;
    private ArrayList<String> rateChange;
    private TableLayout tableData;
    private BarChart barChart;

    public DataCpiFragment() {
        super();
    }

    public static DataCpiFragment newInstance() {
        DataCpiFragment fragment = new DataCpiFragment();
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
        View rootView = inflater.inflate(R.layout.fragment_data_cpi, container, false);
        initInstances(rootView, savedInstanceState);

        Intent intent = getActivity().getIntent();

        cpiValue = intent.getStringArrayListExtra("listCpiValue");
        rateChange = intent.getStringArrayListExtra("listRateChange");
        monthYear = intent.getStringArrayListExtra("listMonth");

        tableView();
        chart();
        return rootView;
    }

    private void chart() {

        ArrayList<String> listRateChange = new ArrayList<String>();
        for (String tamp : rateChange) {
            listRateChange.add(tamp);
        }

        final ArrayList<String> listMonthYear = new ArrayList<String>();
        for (String tamp : monthYear) {
            listMonthYear.add(tamp);
        }

        ArrayList<BarEntry> rateChange = new ArrayList<BarEntry>();
        for (int i = 0; i < listRateChange.size(); i++) {
            rateChange.add(new BarEntry(Float.parseFloat(listRateChange.get(i)), i));
        }

        BarDataSet dataset = new BarDataSet(rateChange, "อัตราการเปลี่ยนแปลง(M)");
        BarData data = new BarData(listMonthYear, dataset);

        barChart.setDescription("");
        barChart.getAxisRight().setEnabled(false);
        barChart.setData(data);
    }

    private void tableView() {

        ArrayList<String> listCpiValue = new ArrayList<String>();
        for (String tamp : cpiValue) {
            listCpiValue.add(tamp);
        }

        ArrayList<String> listRateChange = new ArrayList<String>();
        for (String tamp : rateChange) {
            listRateChange.add(tamp);
        }

        ArrayList<String> listMonthYear = new ArrayList<String>();
        for (String tamp : monthYear) {
            listMonthYear.add(tamp);
        }

        for (int i = 0; i < listCpiValue.size(); i++) {
            TableRow row = new TableRow(getContext());
            String cpiMonthYear = listMonthYear.get(i);
            String cpiValue = listCpiValue.get(i);
            String cpiRateChange = listRateChange.get(i);
            TextView monthYear = new TextView(getContext());
            monthYear.setGravity(Gravity.CENTER_HORIZONTAL);
            monthYear.setTextColor(Color.parseColor("#FFFFFF"));
            monthYear.setText(cpiMonthYear);
            TextView value = new TextView(getContext());
            value.setGravity(Gravity.CENTER_HORIZONTAL);
            value.setTextColor(Color.parseColor("#FFFFFF"));
            value.setText(cpiValue);
            TextView rateChange = new TextView(getContext());
            rateChange.setGravity(Gravity.CENTER_HORIZONTAL);
            rateChange.setTextColor(Color.parseColor("#FFFFFF"));
            rateChange.setText(cpiRateChange);
            row.addView(monthYear);
            row.addView(value);
            row.addView(rateChange);
            tableData.addView(row);
        }

        //Log.d("FLUKE", "Date : " + listMonthYear);
        //Log.d("FLUKE", "Cpi : " + listCpiValue);
        //Log.d("FLUKE", "RateChange : " + listRateChange);

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

        tableData = (TableLayout) rootView.findViewById(R.id.tableData);
        barChart = (BarChart) rootView.findViewById(R.id.cpi);
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
