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


public class DataEcoMonthFragment extends Fragment {

    private ArrayList<String> name;
    private ArrayList<String> unit;
    private ArrayList<String> transaction;
    private String nameMonth;
    private String nameYear;
    private TableLayout tableData;
    private TextView tvMonth;

    public DataEcoMonthFragment() {
        super();
    }

    public static DataEcoMonthFragment newInstance() {
        DataEcoMonthFragment fragment = new DataEcoMonthFragment();
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
        View rootView = inflater.inflate(R.layout.fragment_data_eco_month, container, false);
        initInstances(rootView, savedInstanceState);

        Intent intent = getActivity().getIntent();

        name = intent.getStringArrayListExtra("name");
        unit = intent.getStringArrayListExtra("unit");
        transaction = intent.getStringArrayListExtra("transaction");
        nameMonth = intent.getStringExtra("nameMonth");
        nameYear = intent.getStringExtra("nameYear");

        tableView();

        return rootView;
    }

    private void tableView() {

        ArrayList<String> listName = new ArrayList<String>();
        for (String tamp : name) {
            listName.add(tamp);
        }

        ArrayList<String> listUnit = new ArrayList<String>();
        for (String tamp : unit) {
            listUnit.add(tamp);
        }

        ArrayList<String> listTransaction = new ArrayList<String>();
        for (String tamp : transaction) {
            listTransaction.add(tamp);
        }

        tvMonth.setText(nameMonth + " " + nameYear);

        for (int i = 0; i < listName.size(); i++) {
            TableRow row = new TableRow(getContext());
            String name = listName.get(i);
            String unit = listUnit.get(i);
            String transaction = listTransaction.get(i);
            TextView tvName = new TextView(getContext());
            tvName.setTextColor(Color.parseColor("#FFFFFF"));
            tvName.setText(name);
            TextView tvUnit = new TextView(getContext());
            tvUnit.setGravity(Gravity.CENTER_HORIZONTAL);
            tvUnit.setTextColor(Color.parseColor("#FFFFFF"));
            tvUnit.setText(unit);
            TextView tvTransaction = new TextView(getContext());
            tvTransaction.setGravity(Gravity.CENTER_HORIZONTAL);
            tvTransaction.setTextColor(Color.parseColor("#FFFFFF"));
            tvTransaction.setText(transaction);
            row.addView(tvName);
            row.addView(tvUnit);
            row.addView(tvTransaction);
            tableData.addView(row);
        }

        //Log.d("FLUKE", "Name : " + listName);
        //Log.d("FLUKE", "Unit : " + listUnit);
        //Log.d("FLUKE", "Transaction : " + listTransaction);

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
        tvMonth = (TextView) rootView.findViewById(R.id.tvMonth);

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
