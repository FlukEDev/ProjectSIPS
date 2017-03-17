package fluke.projectsips.fragment.data;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.text.NumberFormat;
import java.util.ArrayList;

import fluke.projectsips.R;

// ผลิตภัณฑ์มวลรวมรายภูมิภาค (GRP)

public class DataGrpFragment extends Fragment {

    private String little;
    private TextView tvLittle;
    private TableLayout tableData;
    private ArrayList<String> name;
    private ArrayList<Integer> price;
    private ArrayList<String> population;
    private String region;
    private int sumPrice;
    private String nameProvince;
    private Integer grpPrice;
    private float sumPopulation;
    private float grpPopulation;

    public DataGrpFragment() {
        super();
    }

    public static DataGrpFragment newInstance() {
        DataGrpFragment fragment = new DataGrpFragment();
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
        View rootView = inflater.inflate(R.layout.fragment_data_grp, container, false);
        initInstances(rootView, savedInstanceState);
        tableView();
        return rootView;
    }

    private void tableView() {
        Intent intent = getActivity().getIntent();
        little = intent.getStringExtra("little");
        name = intent.getStringArrayListExtra("name");
        price = intent.getIntegerArrayListExtra("price");
        population = intent.getStringArrayListExtra("population");
        region = intent.getStringExtra("region");

        tvLittle.setText(little);

        ArrayList<String> listGrpProvinceName = new ArrayList<>();
        for (String tamp : name) {
            listGrpProvinceName.add(tamp);
        }

        ArrayList<Integer> listGrpProvincePrice = new ArrayList<>();
        for (Integer tamp : price) {
            listGrpProvincePrice.add(tamp);
        }

        ArrayList<String> listPopulation = new ArrayList<>();
        for (String tamp : population) {
            listPopulation.add(tamp);
        }

        Log.d("FLUKE", "Population : " + listPopulation);

        for (int i = 0; i < listGrpProvincePrice.size(); i++) {
            grpPrice = listGrpProvincePrice.get(i);
            grpPopulation = Float.parseFloat(listPopulation.get(i));
            sumPrice += grpPrice;
            sumPopulation += grpPopulation;

        }

        TableRow rowSumGrp = new TableRow(getContext());
        TextView regionName = new TextView(getContext());
        regionName.setTextColor(Color.parseColor("#FFFFFF"));
        regionName.setText(region);
        TextView sumGrpPrice = new TextView(getContext());
        sumGrpPrice.setGravity(Gravity.CENTER_HORIZONTAL);
        sumGrpPrice.setTextColor(Color.parseColor("#FFFFFF"));
        sumGrpPrice.setText(String.valueOf(NumberFormat.getInstance().format(sumPrice)));
        TextView sumGrpPopulation = new TextView(getContext());
        sumGrpPopulation.setGravity(Gravity.CENTER_HORIZONTAL);
        sumGrpPopulation.setTextColor(Color.parseColor("#FFFFFF"));
        sumGrpPopulation.setText(String.valueOf(NumberFormat.getInstance().format(sumPopulation)));
        rowSumGrp.addView(regionName);
        rowSumGrp.addView(sumGrpPrice);
        rowSumGrp.addView(sumGrpPopulation);
        tableData.addView(rowSumGrp);

        for (int i = 0; i < listGrpProvincePrice.size(); i++) {
            nameProvince = listGrpProvinceName.get(i);
            grpPrice = listGrpProvincePrice.get(i);
            grpPopulation = Float.parseFloat(listPopulation.get(i));

            TableRow rowRegion = new TableRow(getContext());
            TextView name = new TextView(getContext());
            name.setGravity(Gravity.CENTER_HORIZONTAL);
            name.setTextColor(Color.parseColor("#FFFFFF"));
            name.setText(nameProvince);
            TextView price = new TextView(getContext());
            price.setGravity(Gravity.CENTER_HORIZONTAL);
            price.setTextColor(Color.parseColor("#FFFFFF"));
            price.setText(String.valueOf(NumberFormat.getInstance().format(grpPrice)));
            TextView population = new TextView(getContext());
            population.setGravity(Gravity.CENTER_HORIZONTAL);
            population.setTextColor(Color.parseColor("#FFFFFF"));
            population.setText(String.valueOf(NumberFormat.getInstance().format(grpPopulation)));
            rowRegion.addView(name);
            rowRegion.addView(price);
            rowRegion.addView(population);
            tableData.addView(rowRegion);
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
