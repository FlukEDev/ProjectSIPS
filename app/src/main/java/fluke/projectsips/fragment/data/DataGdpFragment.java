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

import java.util.ArrayList;

import fluke.projectsips.R;


public class DataGdpFragment extends Fragment {

    private String little;
    private ArrayList<String> name;
    private ArrayList<String> population;
    private ArrayList<Integer> price;
    private TextView tvLittle;
    private TableLayout tableData;
    private String nameProvince;
    private Integer grpPrice;
    private float grpPopulation;

    public DataGdpFragment() {
        super();
    }

    public static DataGdpFragment newInstance() {
        DataGdpFragment fragment = new DataGdpFragment();
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
        View rootView = inflater.inflate(R.layout.fragment_data_gdp, container, false);
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
            price.setText(String.valueOf(grpPrice));
            TextView population = new TextView(getContext());
            population.setGravity(Gravity.CENTER_HORIZONTAL);
            population.setTextColor(Color.parseColor("#FFFFFF"));
            population.setText(String.valueOf(grpPopulation));
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
