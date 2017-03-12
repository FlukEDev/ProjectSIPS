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
import fluke.projectsips.dao.CpiCategCollectionDao;
import fluke.projectsips.manager.Contextor;
import fluke.projectsips.manager.HttpManager;
import fr.ganfra.materialspinner.MaterialSpinner;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class CategCpiFragment extends Fragment {

    private MaterialSpinner sMonth;
    private MaterialSpinner sYear;
    private Button btnSearch;
    private int i = 0;
    private int sumYear;
    private int month;
    private String date;
    private String sumMonth;
    private String cpiCategName;
    private String cpiValue;
    private String cpiWeight;

    public CategCpiFragment() {
        super();
    }

    public static CategCpiFragment newInstance() {
        CategCpiFragment fragment = new CategCpiFragment();
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
        View rootView = inflater.inflate(R.layout.fragment_category_cpi, container, false);
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

    /********
     * Listener
     **********/

    View.OnClickListener searchClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            date = sumYear + "" + sumMonth + "00";

            Call<CpiCategCollectionDao> call = HttpManager.getInstance().getService().getCpiCateg(date);
            call.enqueue(new Callback<CpiCategCollectionDao>() {
                @Override
                public void onResponse(Call<CpiCategCollectionDao> call, Response<CpiCategCollectionDao> response) {

                    if (response.isSuccessful()) {
                        CpiCategCollectionDao dao = response.body();
                        if (month != 0 && sumYear != 0) {
                            ArrayList<String> listCpiCategName = new ArrayList<String>();
                            for (i = 0; i <= 3; i++) {
                                cpiCategName = dao.getData().get(i).getCpiCategName();
                                listCpiCategName.add(cpiCategName);
                            }
                            for (i = 21; i <= 23; i++) {
                                cpiCategName = dao.getData().get(i).getCpiCategName();
                                listCpiCategName.add(cpiCategName);
                            }
                            for (i = 4; i <= 5; i++) {
                                cpiCategName = dao.getData().get(i).getCpiCategName();
                                listCpiCategName.add(cpiCategName);
                            }
                            for (i = 24; i <= 25; i++) {
                                cpiCategName = dao.getData().get(i).getCpiCategName();
                                listCpiCategName.add(cpiCategName);
                            }
                            for (i = 6; i <= 20; i++) {
                                cpiCategName = dao.getData().get(i).getCpiCategName();
                                listCpiCategName.add(cpiCategName);
                            }
                            for (i = 26; i <= 29; i++) {
                                cpiCategName = dao.getData().get(i).getCpiCategName();
                                listCpiCategName.add(cpiCategName);
                            }

                            ArrayList<String> listCpiValue = new ArrayList<String>();
                            for (i = 0; i <= 3; i++) {
                                cpiValue = dao.getData().get(i).getCpiValue();
                                listCpiValue.add(cpiValue);
                            }
                            for (i = 21; i <= 23; i++) {
                                cpiValue = dao.getData().get(i).getCpiValue();
                                listCpiValue.add(cpiValue);
                            }
                            for (i = 4; i <= 5; i++) {
                                cpiValue = dao.getData().get(i).getCpiValue();
                                listCpiValue.add(cpiValue);
                            }
                            for (i = 24; i <= 25; i++) {
                                cpiValue = dao.getData().get(i).getCpiValue();
                                listCpiValue.add(cpiValue);
                            }
                            for (i = 6; i <= 20; i++) {
                                cpiValue = dao.getData().get(i).getCpiValue();
                                listCpiValue.add(cpiValue);
                            }
                            for (i = 26; i <= 29; i++) {
                                cpiValue = dao.getData().get(i).getCpiValue();
                                listCpiValue.add(cpiValue);
                            }

                            ArrayList<String> listCpiWeight = new ArrayList<String>();
                            for (i = 0; i <= 3; i++) {
                                cpiWeight = dao.getData().get(i).getWeight();
                                listCpiWeight.add(cpiWeight);
                            }
                            for (i = 21; i <= 23; i++) {
                                cpiWeight = dao.getData().get(i).getWeight();
                                listCpiWeight.add(cpiWeight);
                            }
                            for (i = 4; i <= 5; i++) {
                                cpiWeight = dao.getData().get(i).getWeight();
                                listCpiWeight.add(cpiWeight);
                            }
                            for (i = 24; i <= 25; i++) {
                                cpiWeight = dao.getData().get(i).getWeight();
                                listCpiWeight.add(cpiWeight);
                            }
                            for (i = 6; i <= 20; i++) {
                                cpiWeight = dao.getData().get(i).getWeight();
                                listCpiWeight.add(cpiWeight);
                            }
                            for (i = 26; i <= 29; i++) {
                                cpiWeight = dao.getData().get(i).getWeight();
                                listCpiWeight.add(cpiWeight);
                            }

                            Intent intent = new Intent(getContext(), DataActivity.class);
                            intent.putExtra("key", 2);
                            intent.putStringArrayListExtra("listCpiCategName", listCpiCategName);
                            intent.putStringArrayListExtra("listCpiValue", listCpiValue);
                            intent.putStringArrayListExtra("listCpiWeight", listCpiWeight);

                            startActivity(intent);

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
                public void onFailure(Call<CpiCategCollectionDao> call, Throwable t) {
                    Toast.makeText(Contextor.getInstance().getContext(), t.toString(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    };

    AdapterView.OnItemSelectedListener selectYear = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long l) {
            int val = 543;
            if (position == -1) {
                sumYear = 0;
            } else {
                    sumYear = Integer.valueOf(parent.getItemAtPosition(position).toString()) - val;
                    //Toast.makeText(getContext(), "Year = " + sumYear, Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {

        }
    };

    AdapterView.OnItemSelectedListener selectMonth = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            int val = 1;
            month = i + val;
            if (month < 10) {
                sumMonth = "0" + month;
            } else {
                sumMonth = "" + month;
            }
            //districtName = adapterView.getSelectedItem().toString();
            //Toast.makeText(getActivity(), "District Name = " + districtName, Toast.LENGTH_SHORT).show();
            //Toast.makeText(getContext(), "Month = " + sumMonth, Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {

        }
    };
}
