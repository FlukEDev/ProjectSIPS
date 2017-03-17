package fluke.projectsips.fragment.economic;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;

import fluke.projectsips.R;
import fluke.projectsips.activity.DataActivity;
import fluke.projectsips.dao.EcoMonthCollectionDao;
import fluke.projectsips.manager.Contextor;
import fluke.projectsips.manager.HttpManager;
import fr.ganfra.materialspinner.MaterialSpinner;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

// ภาวะเศรษฐกิจรายเดือน

public class EconomicMonthFragment extends Fragment {

    private MaterialSpinner sMonth;
    private MaterialSpinner sYear;
    private MaterialSpinner sSide;
    private Button btnSearch;
    private int side;
    private int sumYear;
    private int month;
    private String sumMonth;
    private String date;
    private String name;
    private String unit;
    private String transaction;
    private String nameMonth;
    private String nameYear;

    public EconomicMonthFragment() {
        super();
    }

    public static EconomicMonthFragment newInstance() {
        EconomicMonthFragment fragment = new EconomicMonthFragment();
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
        View rootView = inflater.inflate(R.layout.fragment_economic_month, container, false);
        initInstances(rootView, savedInstanceState);
        return rootView;
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

        String[] month = getResources().getStringArray(R.array.month);
        ArrayAdapter<String> adapterMonth = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, month);
        adapterMonth.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sMonth = (MaterialSpinner) rootView.findViewById(R.id.sMonth);
        sMonth.setAdapter(adapterMonth);
        sMonth.setOnItemSelectedListener(selectMonth);

        String[] year = {"2556", "2555", "2554", "2553"};
        ArrayAdapter<String> adapterYear = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, year);
        adapterYear.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sYear = (MaterialSpinner) rootView.findViewById(R.id.sYear);
        sYear.setAdapter(adapterYear);
        sYear.setOnItemSelectedListener(selectYear);

        String[] side = {"อุปทาน (ด้านการผลิต)", "อุปสงค์ (ด้านการใช้จ่าย)", "เศรษฐกิจด้านการเงิน", "เสถียรภาพทางเศรษฐกิจ"};
        ArrayAdapter<String> adapterSide = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, side);
        adapterSide.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sSide = (MaterialSpinner) rootView.findViewById(R.id.sSide);
        sSide.setAdapter(adapterSide);
        sSide.setOnItemSelectedListener(selectSide);

        btnSearch = (Button) rootView.findViewById(R.id.submit);
        btnSearch.setOnClickListener(searchClick);
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

    private void MsgBox() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
        alertDialogBuilder.setMessage("คุณยังไม่ได้ทำการเลือก เดือน ปี หรือ ด้าน ที่ต้องการดูข้อมูล");
        alertDialogBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    /**************
     *
     * Listeners
     *
     **************/

    View.OnClickListener searchClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            date = sumYear + "" + sumMonth + "00";

            Call<EcoMonthCollectionDao> call = HttpManager.getInstance().getService().getEcoMonth(side, date);
            call.enqueue(new Callback<EcoMonthCollectionDao>() {
                @Override
                public void onResponse(Call<EcoMonthCollectionDao> call, Response<EcoMonthCollectionDao> response) {
                    if (response.isSuccessful()) {
                        EcoMonthCollectionDao dao = response.body();

                        if (month != 0 && sumYear != 0 && side != 0) {

                            if (side == 1) {
                                ArrayList<String> listName = new ArrayList<String>();
                                ArrayList<String> listUnit = new ArrayList<String>();
                                ArrayList<String> listTransaction = new ArrayList<String>();

                                for (int i = 0; i <= 11; i++) {
                                    name = dao.getData().get(i).getKpiName();
                                    unit = dao.getData().get(i).getUnit();
                                    transaction = dao.getData().get(i).getTransactionAmount();
                                    listName.add(name);
                                    listUnit.add(unit);
                                    listTransaction.add(transaction);
                                }

                                listName.add(dao.getData().get(18).getKpiName());
                                listUnit.add(dao.getData().get(18).getUnit());
                                listTransaction.add(dao.getData().get(18).getTransactionAmount());

                                for (int i = 12; i <= 17; i++) {
                                    name = dao.getData().get(i).getKpiName();
                                    unit = dao.getData().get(i).getUnit();
                                    transaction = dao.getData().get(i).getTransactionAmount();
                                    listName.add(name);
                                    listUnit.add(unit);
                                    listTransaction.add(transaction);
                                }

                                listName.add(dao.getData().get(19).getKpiName());
                                listName.add(dao.getData().get(20).getKpiName());
                                listUnit.add(dao.getData().get(19).getUnit());
                                listUnit.add(dao.getData().get(20).getUnit());
                                listTransaction.add(dao.getData().get(19).getTransactionAmount());
                                listTransaction.add(dao.getData().get(20).getTransactionAmount());

                                for (int i = 21; i < dao.getData().size(); i++) {
                                    name = dao.getData().get(i).getKpiName();
                                    unit = dao.getData().get(i).getUnit();
                                    transaction = dao.getData().get(i).getTransactionAmount();
                                    listName.add(name);
                                    listUnit.add(unit);
                                    listTransaction.add(transaction);
                                }

                                //Log.d("FLUKE", "Name : " + listName);
                                //Log.d("FLUKE", "Unit : " + listUnit);
                                //Log.d("FLUKE", "Transaction : " + listTransaction);

                                Intent intent = new Intent(getContext(), DataActivity.class);
                                intent.putExtra("key", 16);
                                intent.putStringArrayListExtra("name", listName);
                                intent.putStringArrayListExtra("unit", listUnit);
                                intent.putStringArrayListExtra("transaction", listTransaction);
                                intent.putExtra("nameMonth", nameMonth);
                                intent.putExtra("nameYear", nameYear);

                                startActivity(intent);

                            } else {
                                ArrayList<String> listName = new ArrayList<String>();
                                ArrayList<String> listUnit = new ArrayList<String>();
                                ArrayList<String> listTransaction = new ArrayList<String>();

                                for (int i = 0; i < dao.getData().size(); i++) {
                                    name = dao.getData().get(i).getKpiName();
                                    unit = dao.getData().get(i).getUnit();
                                    transaction = dao.getData().get(i).getTransactionAmount();
                                    listName.add(name);
                                    listUnit.add(unit);
                                    listTransaction.add(transaction);
                                }

                                //Log.d("FLUKE", "Name : " + listName);
                                //Log.d("FLUKE", "Unit : " + listUnit);
                                //Log.d("FLUKE", "Transaction : " + listTransaction);

                                Intent intent = new Intent(getContext(), DataActivity.class);
                                intent.putExtra("key", 16);
                                intent.putStringArrayListExtra("name", listName);
                                intent.putStringArrayListExtra("unit", listUnit);
                                intent.putStringArrayListExtra("transaction", listTransaction);
                                intent.putExtra("nameMonth", nameMonth);
                                intent.putExtra("nameYear", nameYear);

                                startActivity(intent);
                            }

                        } else {
                            MsgBox();
                        }

                    } else {
                        try {
                            Toast.makeText(Contextor.getInstance().getContext(), response.errorBody().string(), Toast.LENGTH_LONG).show();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }

                @Override
                public void onFailure(Call<EcoMonthCollectionDao> call, Throwable t) {
                    Toast.makeText(Contextor.getInstance().getContext(), t.toString(), Toast.LENGTH_SHORT).show();
                }
            });

        }
    };

    AdapterView.OnItemSelectedListener selectMonth = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            int val = 1;
            month = position + val;
            if (month < 10) {
                sumMonth = "0" + month;
            } else {
                sumMonth = "" + month;
            }
            //Toast.makeText(getContext(), "Month = " + sumMonth, Toast.LENGTH_SHORT).show();
            nameMonth = parent.getSelectedItem().toString();
            //Toast.makeText(getContext(), "Month : " + nameMonth, Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    };

    AdapterView.OnItemSelectedListener selectYear = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            int val = 543;
            if (position == -1) {
                sumYear = 0;
            } else {
                sumYear = Integer.valueOf(parent.getItemAtPosition(position).toString()) - val;
                //Toast.makeText(getContext(), "Year = " + sumYear, Toast.LENGTH_SHORT).show();
            }

            nameYear = parent.getSelectedItem().toString();

        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    };

    AdapterView.OnItemSelectedListener selectSide = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            switch (position) {
                // อุปทาน (ด้านการผลิต)
                case 0:
                    side = 1;
                    break;
                // อุปสงค์ (ด้านการใช้จ่าย)
                case 1:
                    side = 2;
                    break;
                // เศรษฐกิจด้านการเงิน
                case 2:
                    side = 3;
                    break;
                // เสถียรภาพทางเศรษฐกิจ
                case 3:
                    side = 4;
                    break;
            }
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    };

}
