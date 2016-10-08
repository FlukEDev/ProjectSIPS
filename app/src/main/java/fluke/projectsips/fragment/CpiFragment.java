package fluke.projectsips.fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Toast;

import java.io.IOException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import fluke.projectsips.R;
import fluke.projectsips.activity.DataActivity;
import fluke.projectsips.dao.CpiCollectionDao;
import fluke.projectsips.manager.Contextor;
import fluke.projectsips.manager.HttpManager;
import fr.ganfra.materialspinner.MaterialSpinner;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CpiFragment extends Fragment {

    private MaterialSpinner sMonth;
    private MaterialSpinner sYear;
    private Button btnSearch;
    private int sumYear;
    AdapterView.OnItemSelectedListener selectYear = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            int val = 543;
            if (position == -1) {
                sumYear = 0;
            } else {
                try {
                    sumYear = Integer.valueOf(parent.getItemAtPosition(position).toString()) - val;
                    //Toast.makeText(getContext(), "Year = " + sumYear, Toast.LENGTH_SHORT).show();
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
            }
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    };
    private int month;
    private String sumMonth;
    AdapterView.OnItemSelectedListener selectMonth = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int i, long id) {
            int val = 1;
            month = i + val;
            if (month < 10) {
                sumMonth = "0" + month;
            } else {
                sumMonth = "" + month;
            }
            //Toast.makeText(getContext(), "Month = " + sumMonth, Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    };
    private int j;
    private int i;
    private String date;
    private String cpi;
    private Float cpiValue;
    private Float beforeCpiValue;
    private Number rateChange;
    private String finalValue;
    private Date monthYear;
    /*************
     * Listener
     *************/

    View.OnClickListener searchClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            date = sumYear + "" + sumMonth + "00";

            Call<CpiCollectionDao> call = HttpManager.getInstance().getService().getCpi(date);
            call.enqueue(new Callback<CpiCollectionDao>() {
                @Override
                public void onResponse(Call<CpiCollectionDao> call, Response<CpiCollectionDao> response) {
                    if (response.isSuccessful()) {
                        CpiCollectionDao dao = response.body();
                        if (month != 0 && sumYear != 0) {
                            ArrayList<String> listRateChange = new ArrayList<String>();
                            if (sumYear == 2008) {
                                listRateChange.add(String.valueOf(0));
                                for (i = 0, j = 1; i < dao.getData().size() && j < dao.getData().size(); i++, j++) {
                                    cpiValue = Float.valueOf(dao.getData().get(j).getCpiValue());
                                    beforeCpiValue = Float.valueOf(dao.getData().get(i).getCpiValue());
                                    DecimalFormat df = new DecimalFormat("0.0");
                                    finalValue = df.format(cpiValue - beforeCpiValue);
                                    try {
                                        rateChange = df.parse(finalValue);
                                    } catch (ParseException e) {
                                        e.printStackTrace();
                                    }
                                    listRateChange.add(String.valueOf(rateChange));
                                }

                                ArrayList<String> listMonth = new ArrayList<String>();
                                for (i = 0; i < dao.getData().size(); i++) {
                                    monthYear = dao.getData().get(i).getMountYear();
                                    Log.d("FLUKE", "Date : " + monthYear);
                                    SimpleDateFormat dateParse = new SimpleDateFormat("MMM yyyy", new Locale("th", "TH"));
                                    listMonth.add(dateParse.format(monthYear));
                                }

                                Log.d("FLUKE", "Date : " + listMonth);

                                ArrayList<String> listCpiValue = new ArrayList<String>();
                                for (i = 0; i < dao.getData().size(); i++) {
                                    cpi = dao.getData().get(i).getCpiValue();
                                    listCpiValue.add(cpi);
                                }

                                Intent intent = new Intent(getContext(), DataActivity.class);
                                intent.putExtra("key", 1);
                                intent.putStringArrayListExtra("listCpiValue", listCpiValue);
                                intent.putStringArrayListExtra("listRateChange", listRateChange);
                                intent.putStringArrayListExtra("listMonth", listMonth);

                                startActivity(intent);

                            } else {
                                for (i = 0; i < 13; i++) {
                                    cpiValue = Float.valueOf(dao.getData().get(i + 1).getCpiValue());
                                    beforeCpiValue = Float.valueOf(dao.getData().get(i).getCpiValue());
                                    DecimalFormat df = new DecimalFormat("0.0");
                                    finalValue = df.format(cpiValue - beforeCpiValue);
                                    try {
                                        rateChange = df.parse(finalValue);
                                    } catch (ParseException e) {
                                        e.printStackTrace();
                                    }
                                    listRateChange.add(String.valueOf(rateChange));
                                }

                                Log.d("FLUKE", "Data = " + listRateChange);

                                ArrayList<String> listMonth = new ArrayList<String>();
                                for (i = 1; i < 14; i++) {
                                    //monthYear = dao.getData().get(i).getMountYear();
                                    //listMonth.add(monthYear);
                                }

                                ArrayList<String> listCpiValue = new ArrayList<String>();
                                for (i = 1; i < 14; i++) {
                                    cpi = dao.getData().get(i).getCpiValue();
                                    listCpiValue.add(cpi);
                                }

                                Intent intent = new Intent(getContext(), DataActivity.class);
                                intent.putExtra("key", 1);
                                intent.putStringArrayListExtra("listCpiValue", listCpiValue);
                                intent.putStringArrayListExtra("listRateChange", listRateChange);
                                intent.putStringArrayListExtra("listMonth", listMonth);

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
                public void onFailure(Call<CpiCollectionDao> call, Throwable t) {
                    Toast.makeText(Contextor.getInstance().getContext(), t.toString(), Toast.LENGTH_SHORT).show();
                }

            });

        }
    };

    public CpiFragment() {
        super();
    }

    public static CpiFragment newInstance() {
        CpiFragment fragment = new CpiFragment();
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
        View rootView = inflater.inflate(R.layout.fragment_cpi, container, false);
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

        String[] year = {"2555", "2554", "2553", "2552", "2551"};
        ArrayAdapter<String> adapterYear = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, year);
        adapterYear.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sYear = (MaterialSpinner) rootView.findViewById(R.id.sYear);
        sYear.setAdapter(adapterYear);
        sYear.setOnItemSelectedListener(selectYear);

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
        alertDialogBuilder.setMessage("คุณยังไม่ได้ทำการเลือก เดือน หรือ ปี ที่ต้องการดูข้อมูล");
        alertDialogBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

}
