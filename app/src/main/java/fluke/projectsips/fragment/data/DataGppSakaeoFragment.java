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

import java.util.ArrayList;

import fluke.projectsips.R;


public class DataGppSakaeoFragment extends Fragment {

    private String little;
    private TextView tvLittle;
    private ArrayList<String> name;
    private ArrayList<Integer> price;
    private TableLayout tableData;
    private int sumDataAgricultural;
    private int sumDataNonAgricultural;
    private int sumGpp;
    private String population;
    private int type;
    private int i;

    public DataGppSakaeoFragment() {
        super();
    }

    public static DataGppSakaeoFragment newInstance() {
        DataGppSakaeoFragment fragment = new DataGppSakaeoFragment();
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
        View rootView = inflater.inflate(R.layout.fragment_data_gpp_sakaeo, container, false);
        initInstances(rootView, savedInstanceState);
        showData();
        return rootView;
    }

    private void showData() {

        Intent intent = getActivity().getIntent();
        little = intent.getStringExtra("little");
        population = intent.getStringExtra("population");
        type = intent.getIntExtra("type", 0);
        name = intent.getStringArrayListExtra("name");
        price = intent.getIntegerArrayListExtra("price");

        tvLittle.setText(little);

        ArrayList<String> listGppSakaeoName = new ArrayList<>();
        for (String tamp : name) {
            listGppSakaeoName.add(tamp);
        }

        ArrayList<Integer> listGppSakaeoPrice = new ArrayList<>();
        for (Integer tamp : price) {
            listGppSakaeoPrice.add(tamp);
        }

        ArrayList<Integer> listPrice = new ArrayList<>();
        for (i = 0; i < price.size(); i++) {
            listPrice.add(price.get(i));
        }

        Log.d("FLUKE", "Price : " + listPrice);

        // ภาคการเกษตร
        TableRow rowAgricultural = new TableRow(getContext());
        rowAgricultural.setBackgroundColor(Color.parseColor("#607D8B"));
        TextView agricultural = new TextView(getContext());
        agricultural.setTextColor(Color.parseColor("#FFFFFF"));
        agricultural.setText("ภาคเกษตร");
        TextView sumAgricultural = new TextView(getContext());
        sumAgricultural.setGravity(Gravity.CENTER_HORIZONTAL);
        sumAgricultural.setTextColor(Color.parseColor("#FFFFFF"));
        rowAgricultural.addView(agricultural);
        rowAgricultural.addView(sumAgricultural);
        tableData.addView(rowAgricultural);

        for (int i = 0; i < 2; i++) {
            TableRow rowDataAgricultural = new TableRow(getContext());
            String name = listGppSakaeoName.get(i);
            String price = String.valueOf(listGppSakaeoPrice.get(i));
            TextView trName = new TextView(getContext());
            trName.setTextColor(Color.parseColor("#FFFFFF"));
            trName.setText("  " + name);
            TextView trPrice = new TextView(getContext());
            trPrice.setGravity(Gravity.CENTER_HORIZONTAL);
            trPrice.setTextColor(Color.parseColor("#FFFFFF"));
            trPrice.setText(price);

            sumDataAgricultural += Integer.valueOf(price);

            rowDataAgricultural.addView(trName);
            rowDataAgricultural.addView(trPrice);
            tableData.addView(rowDataAgricultural);
        }

        sumAgricultural.setText(String.valueOf(sumDataAgricultural));

        // ภาคนอกการเกษตร
        TableRow rowNonAgricultural = new TableRow(getContext());
        rowNonAgricultural.setBackgroundColor(Color.parseColor("#607D8B"));
        TextView nonAgricultural = new TextView(getContext());
        nonAgricultural.setTextColor(Color.parseColor("#FFFFFF"));
        nonAgricultural.setText("ภาคนอกเกษตร");
        TextView sumNonAgricultural = new TextView(getContext());
        sumNonAgricultural.setGravity(Gravity.CENTER_HORIZONTAL);
        sumNonAgricultural.setTextColor(Color.parseColor("#FFFFFF"));
        rowNonAgricultural.addView(nonAgricultural);
        rowNonAgricultural.addView(sumNonAgricultural);
        tableData.addView(rowNonAgricultural);

        for (int i = 2; i < listGppSakaeoPrice.size(); i++) {
            TableRow rowDataNonAgricultural = new TableRow(getContext());
            String name = listGppSakaeoName.get(i);
            String price = String.valueOf(listGppSakaeoPrice.get(i));
            TextView trName = new TextView(getContext());
            trName.setTextColor(Color.parseColor("#FFFFFF"));
            trName.setText("  " + name);
            TextView trPrice = new TextView(getContext());
            trPrice.setGravity(Gravity.CENTER_HORIZONTAL);
            trPrice.setTextColor(Color.parseColor("#FFFFFF"));
            trPrice.setText(price);

            sumDataNonAgricultural += Integer.valueOf(price);

            rowDataNonAgricultural.addView(trName);
            rowDataNonAgricultural.addView(trPrice);
            tableData.addView(rowDataNonAgricultural);
        }
        sumNonAgricultural.setText(String.valueOf(sumDataNonAgricultural));

        sumGpp = sumDataAgricultural + sumDataNonAgricultural;

        TableRow gpp = new TableRow(getContext());
        gpp.setBackgroundColor(Color.parseColor("#607D8B"));
        TextView trName = new TextView(getContext());
        trName.setTextColor(Color.parseColor("#FFFFFF"));
        trName.setText("Gross Provincial Product (GPP)");
        TextView trGpp = new TextView(getContext());
        trGpp.setGravity(Gravity.CENTER_HORIZONTAL);
        trGpp.setTextColor(Color.parseColor("#FFFFFF"));
        trGpp.setText(String.valueOf(sumGpp));
        gpp.addView(trName);
        gpp.addView(trGpp);
        tableData.addView(gpp);

        if (type == 1) {
            TableRow rowPopulation = new TableRow(getContext());
            TextView trPopulation = new TextView(getContext());
            trPopulation.setTextColor(Color.parseColor("#FFFFFF"));
            trPopulation.setText("Population (1000 persons)");
            TextView trDataPopulation = new TextView(getContext());
            trDataPopulation.setGravity(Gravity.CENTER_HORIZONTAL);
            trDataPopulation.setTextColor(Color.parseColor("#FFFFFF"));
            trDataPopulation.setText(String.valueOf(population));
            rowPopulation.addView(trPopulation);
            rowPopulation.addView(trDataPopulation);
            tableData.addView(rowPopulation);
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
