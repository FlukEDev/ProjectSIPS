package fluke.projectsips.fragment.economic.lfp;

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

import fluke.projectsips.R;
import fluke.projectsips.activity.DataActivity;
import fluke.projectsips.dao.LfpLaborNoWorkCollectionDao;
import fluke.projectsips.manager.Contextor;
import fluke.projectsips.manager.HttpManager;
import fr.ganfra.materialspinner.MaterialSpinner;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

//จำนวนและอัตราการว่างงาน จำแนกตามเพศ

public class UnemployedSexFragment extends Fragment {

    private MaterialSpinner sQuarter;
    private MaterialSpinner sYear;
    private Button btnSearch;
    private int quarterID;
    private String quarterName;
    private int sumYear;
    private int year;
    public Integer noWorkMale;
    public Integer noWorkFemale;
    public Integer workMale;
    public Integer workFemale;

    public UnemployedSexFragment() {
        super();
    }

    public static UnemployedSexFragment newInstance() {
        UnemployedSexFragment fragment = new UnemployedSexFragment();
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
        View rootView = inflater.inflate(R.layout.fragment_lfp_unemployed_sex, container, false);
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

        String[] quarter = getResources().getStringArray(R.array.quarter);
        ArrayAdapter<String> adapterQuarter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, quarter);
        adapterQuarter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sQuarter = (MaterialSpinner) rootView.findViewById(R.id.sQuarter);
        sQuarter.setAdapter(adapterQuarter);
        sQuarter.setOnItemSelectedListener(selectQuarter);

        String[] year = getResources().getStringArray(R.array.lfp_year);
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
        alertDialogBuilder.setMessage("คุณยังไม่ได้ทำการเลือก ปี หรือ ไตรมาส ที่ต้องการดูข้อมูล");
        alertDialogBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    /*************
     *
     * Listeners
     *
     * ***********/

    View.OnClickListener searchClick = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            Call<LfpLaborNoWorkCollectionDao> call = HttpManager.getInstance().getService().getLaborNoWork(sumYear, quarterID);
            call.enqueue(new Callback<LfpLaborNoWorkCollectionDao>() {
                @Override
                public void onResponse(Call<LfpLaborNoWorkCollectionDao> call, Response<LfpLaborNoWorkCollectionDao> response) {
                    if (response.isSuccessful()) {
                        LfpLaborNoWorkCollectionDao dao = response.body();
                        if (sumYear != 0 && quarterID != 0) {
                            noWorkMale = dao.getData().get(0).getLaborNoworkMaleAmount();
                            noWorkFemale = dao.getData().get(0).getLaborNoworkFemaleAmount();
                            workMale = dao.getData().get(0).getLaborWorkMaleAmount();
                            workFemale = dao.getData().get(0).getLaborWorkFemaleAmount();

                            String little = "จำนวนและอัตราการว่างงาน จำแนกตามเพศ " + quarterName + " ปี " + year;

                            Intent intent = new Intent(getContext(), DataActivity.class);
                            intent.putExtra("key", 15);
                            intent.putExtra("little", little);
                            intent.putExtra("noWorkMale", noWorkMale);
                            intent.putExtra("noWorkFemale", noWorkFemale);
                            intent.putExtra("workMale", workMale);
                            intent.putExtra("workFemale", workFemale);

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
                public void onFailure(Call<LfpLaborNoWorkCollectionDao> call, Throwable t) {
                    Toast.makeText(Contextor.getInstance().getContext(), t.toString(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    };

    AdapterView.OnItemSelectedListener selectQuarter = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            quarterID = position + 1;
            //Toast.makeText(getContext(), "quarterID = " + quarterID, Toast.LENGTH_SHORT).show();
            quarterName = parent.getItemAtPosition(position).toString();
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
                    year = Integer.parseInt(parent.getSelectedItem().toString());
            }
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    };

}
