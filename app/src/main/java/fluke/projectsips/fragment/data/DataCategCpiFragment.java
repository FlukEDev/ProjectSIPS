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


public class DataCategCpiFragment extends Fragment {

    private ArrayList<String> CpiCategName;
    private ArrayList<String> CpiValue;
    private ArrayList<String> CpiWeight;
    private TableLayout tableData;

    public DataCategCpiFragment() {
        super();
    }

    public static DataCategCpiFragment newInstance() {
        DataCategCpiFragment fragment = new DataCategCpiFragment();
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
        View rootView = inflater.inflate(R.layout.fragment_data_categ_cpi, container, false);
        initInstances(rootView, savedInstanceState);
        tableView();
        return rootView;
    }

    private void tableView() {

        Intent intent = getActivity().getIntent();

        CpiCategName = intent.getStringArrayListExtra("listCpiCategName");
        CpiValue = intent.getStringArrayListExtra("listCpiValue");
        CpiWeight = intent.getStringArrayListExtra("listCpiWeight");

        ArrayList<String> listCpiCategName = new ArrayList<String>();
        for (String tamp : CpiCategName) {
            listCpiCategName.add(tamp);
        }

        ArrayList<String> listCpiValue = new ArrayList<String>();
        for (String tamp : CpiValue) {
            listCpiValue.add(tamp);
        }

        ArrayList<String> listCpiWeight = new ArrayList<String>();
        for (String tamp : CpiWeight) {
            listCpiWeight.add(tamp);
        }

        for (int i = 0; i < listCpiCategName.size(); i++) {
            TableRow row = new TableRow(getContext());
            String cpiName = listCpiCategName.get(i);
            String cpiWeight = listCpiWeight.get(i);
            String cpiValue = listCpiValue.get(i);
            TextView name = new TextView(getContext());
            name.setWidth(200);
            name.setTextColor(Color.parseColor("#FFFFFF"));
            name.setText(cpiName);
            TextView weight = new TextView(getContext());
            weight.setGravity(Gravity.CENTER_HORIZONTAL);
            weight.setTextColor(Color.parseColor("#FFFFFF"));
            weight.setText(cpiWeight);
            TextView value = new TextView(getContext());
            value.setWidth(40);
            value.setGravity(Gravity.CENTER_HORIZONTAL);
            value.setTextColor(Color.parseColor("#FFFFFF"));
            value.setText(cpiValue);
            row.addView(name);
            row.addView(weight);
            row.addView(value);
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