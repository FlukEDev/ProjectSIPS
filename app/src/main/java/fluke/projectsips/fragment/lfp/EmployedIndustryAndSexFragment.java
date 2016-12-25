package fluke.projectsips.fragment.lfp;

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
import fluke.projectsips.dao.LfpLaborIndustryCollectionDao;
import fluke.projectsips.manager.Contextor;
import fluke.projectsips.manager.HttpManager;
import fr.ganfra.materialspinner.MaterialSpinner;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

//จำนวนและร้อยละของผู้มีงานทำ จำแนกตามอุตสาหกรรม และเพศ

public class EmployedIndustryAndSexFragment extends Fragment {

    private MaterialSpinner sQuarter;
    private MaterialSpinner sYear;
    private Button btnSearch;
    private int quarterID;
    private String quarterName;
    private int sumYear;
    private int year;
    private String name;
    private String male;
    private String female;
    private int sum;

    public EmployedIndustryAndSexFragment() {
        super();
    }

    public static EmployedIndustryAndSexFragment newInstance() {
        EmployedIndustryAndSexFragment fragment = new EmployedIndustryAndSexFragment();
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
        View rootView = inflater.inflate(R.layout.fragment_lfp_employed_industry_and_sex, container, false);
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

    /**************
     *
     * Listeners
     *
     **************/

    View.OnClickListener searchClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Call<LfpLaborIndustryCollectionDao> call = HttpManager.getInstance().getService().getLaborIndustry(sumYear, quarterID);
            call.enqueue(new Callback<LfpLaborIndustryCollectionDao>() {
                @Override
                public void onResponse(Call<LfpLaborIndustryCollectionDao> call, Response<LfpLaborIndustryCollectionDao> response) {
                    if (response.isSuccessful()) {
                        LfpLaborIndustryCollectionDao dao = response.body();
                        if (sumYear != 0 && quarterID != 0) {
                            ArrayList<String> listName = new ArrayList<String>();
                            for (int i = 0; i < dao.getData().size(); i++) {
                                name = dao.getData().get(i).getIndustryName();
                                listName.add(name);
                            }

                            ArrayList<Integer> listMale = new ArrayList<Integer>();
                            for (int i = 0; i < dao.getData().size(); i++) {
                                male = String.valueOf(dao.getData().get(i).getMaleAmount());
                                listMale.add(Integer.valueOf(male));
                            }

                            ArrayList<Integer> listFemale = new ArrayList<Integer>();
                            for (int i = 0; i < dao.getData().size(); i++) {
                                female = String.valueOf(dao.getData().get(i).getFemaleAmount());
                                listFemale.add(Integer.valueOf(female));
                            }

                            ArrayList<Integer> listSum = new ArrayList<Integer>();
                            for (int i = 0; i < dao.getData().size(); i++) {
                                sum = dao.getData().get(i).getMaleAmount() + dao.getData().get(i).getFemaleAmount();
                                listSum.add(sum);
                            }

                            String little = "จำนวนและร้อยละของผู้มีงานทำ จำแนกตามอุตสาหกรรม และเพศ " + quarterName + "ปี " + year;

                            Intent intent = new Intent(getContext(), DataActivity.class);
                            intent.putExtra("key", 12);
                            intent.putExtra("little", little);
                            intent.putStringArrayListExtra("name", listName);
                            intent.putIntegerArrayListExtra("male", listMale);
                            intent.putIntegerArrayListExtra("female", listFemale);
                            intent.putIntegerArrayListExtra("sum", listSum);

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
                public void onFailure(Call<LfpLaborIndustryCollectionDao> call, Throwable t) {
                    Toast.makeText(Contextor.getInstance().getContext(), t.toString(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    };

    AdapterView.OnItemSelectedListener selectQuarter = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long l) {
            quarterID = position + 1;
            //Toast.makeText(getContext(), "quarterID = " + quarterID, Toast.LENGTH_SHORT).show();
            quarterName = parent.getItemAtPosition(position).toString();
        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {

        }
    };

    AdapterView.OnItemSelectedListener selectYear = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long l) {
            int val = 543;
            if (position == -1) {
                sumYear = 0;
            } else {
                try {
                    sumYear = Integer.valueOf(parent.getItemAtPosition(position).toString()) - val;
                    //Toast.makeText(getContext(), "Year = " + sumYear, Toast.LENGTH_SHORT).show();
                    year = Integer.parseInt(parent.getSelectedItem().toString());
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
            }
        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {

        }
    };

}
