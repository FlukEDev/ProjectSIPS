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
import fluke.projectsips.dao.LfpEducationSexCollectionDao;
import fluke.projectsips.manager.Contextor;
import fluke.projectsips.manager.HttpManager;
import fr.ganfra.materialspinner.MaterialSpinner;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

//จำนวนและร้อยละของประชากรอายุ 15 ปีขึ้นไป จำแนกตามระดับการศึกษาที่สำเร็จ และเพศ

public class AgeEducationAndSexFragment extends Fragment {

    private MaterialSpinner sQuarter;
    private MaterialSpinner sYear;
    private Button btnSearch;
    private int quarterID;
    private String quarterName;
    private int sumYear;
    private int year;

    public AgeEducationAndSexFragment() {
        super();
    }

    public static AgeEducationAndSexFragment newInstance() {
        AgeEducationAndSexFragment fragment = new AgeEducationAndSexFragment();
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
        View rootView = inflater.inflate(R.layout.fragment_lfp_age_education_and_sex, container, false);
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
     **************/

    View.OnClickListener searchClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Call<LfpEducationSexCollectionDao> call = HttpManager.getInstance().getService().getEduSex(sumYear, quarterID);
            call.enqueue(new Callback<LfpEducationSexCollectionDao>() {
                @Override
                public void onResponse(Call<LfpEducationSexCollectionDao> call, Response<LfpEducationSexCollectionDao> response) {
                    if (response.isSuccessful()) {
                        LfpEducationSexCollectionDao dao = response.body();
                        if (sumYear != 0 && quarterID != 0) {
                            ArrayList<String> listEduName = new ArrayList<String>();
                            listEduName.add(dao.getData().get(0).getEduDetail());
                            listEduName.add(dao.getData().get(1).getEduDetail());
                            listEduName.add(dao.getData().get(2).getEduDetail());
                            listEduName.add(dao.getData().get(3).getEduDetail());
                            listEduName.add(dao.getData().get(4).getEduDetail());
                            listEduName.add(dao.getData().get(8).getEduDetail());
                            listEduName.add(dao.getData().get(9).getEduDetail());
                            listEduName.add(dao.getData().get(10).getEduDetail());
                            listEduName.add(dao.getData().get(5).getEduDetail());
                            listEduName.add(dao.getData().get(11).getEduDetail());
                            listEduName.add(dao.getData().get(12).getEduDetail());
                            listEduName.add(dao.getData().get(13).getEduDetail());
                            listEduName.add(dao.getData().get(6).getEduDetail());
                            listEduName.add(dao.getData().get(7).getEduDetail());

                            ArrayList<Integer> listMale = new ArrayList<Integer>();
                            listMale.add(dao.getData().get(0).getMaleAmount());
                            listMale.add(dao.getData().get(1).getMaleAmount());
                            listMale.add(dao.getData().get(2).getMaleAmount());
                            listMale.add(dao.getData().get(3).getMaleAmount());
                            listMale.add(dao.getData().get(4).getMaleAmount());
                            listMale.add(dao.getData().get(8).getMaleAmount());
                            listMale.add(dao.getData().get(9).getMaleAmount());
                            listMale.add(dao.getData().get(10).getMaleAmount());
                            listMale.add(dao.getData().get(5).getMaleAmount());
                            listMale.add(dao.getData().get(11).getMaleAmount());
                            listMale.add(dao.getData().get(12).getMaleAmount());
                            listMale.add(dao.getData().get(13).getMaleAmount());
                            listMale.add(dao.getData().get(6).getMaleAmount());
                            listMale.add(dao.getData().get(7).getMaleAmount());

                            ArrayList<Integer> listFemale = new ArrayList<Integer>();
                            listFemale.add(dao.getData().get(0).getFemaleAmount());
                            listFemale.add(dao.getData().get(1).getFemaleAmount());
                            listFemale.add(dao.getData().get(2).getFemaleAmount());
                            listFemale.add(dao.getData().get(3).getFemaleAmount());
                            listFemale.add(dao.getData().get(4).getFemaleAmount());
                            listFemale.add(dao.getData().get(8).getFemaleAmount());
                            listFemale.add(dao.getData().get(9).getFemaleAmount());
                            listFemale.add(dao.getData().get(10).getFemaleAmount());
                            listFemale.add(dao.getData().get(5).getFemaleAmount());
                            listFemale.add(dao.getData().get(11).getFemaleAmount());
                            listFemale.add(dao.getData().get(12).getFemaleAmount());
                            listFemale.add(dao.getData().get(13).getFemaleAmount());
                            listFemale.add(dao.getData().get(6).getFemaleAmount());
                            listFemale.add(dao.getData().get(7).getFemaleAmount());

                            ArrayList<Integer> listSum = new ArrayList<Integer>();
                            listSum.add(dao.getData().get(0).getMaleAmount() + dao.getData().get(0).getFemaleAmount());
                            listSum.add(dao.getData().get(1).getMaleAmount() + dao.getData().get(1).getFemaleAmount());
                            listSum.add(dao.getData().get(2).getMaleAmount() + dao.getData().get(2).getFemaleAmount());
                            listSum.add(dao.getData().get(3).getMaleAmount() + dao.getData().get(3).getFemaleAmount());
                            listSum.add(dao.getData().get(4).getMaleAmount() + dao.getData().get(4).getFemaleAmount());
                            listSum.add(dao.getData().get(8).getMaleAmount() + dao.getData().get(8).getFemaleAmount());
                            listSum.add(dao.getData().get(9).getMaleAmount() + dao.getData().get(9).getFemaleAmount());
                            listSum.add(dao.getData().get(10).getMaleAmount() + dao.getData().get(10).getFemaleAmount());
                            listSum.add(dao.getData().get(5).getMaleAmount() + dao.getData().get(5).getFemaleAmount());
                            listSum.add(dao.getData().get(11).getMaleAmount() + dao.getData().get(11).getFemaleAmount());
                            listSum.add(dao.getData().get(12).getMaleAmount() + dao.getData().get(12).getFemaleAmount());
                            listSum.add(dao.getData().get(13).getMaleAmount() + dao.getData().get(13).getFemaleAmount());
                            listSum.add(dao.getData().get(6).getMaleAmount() + dao.getData().get(6).getFemaleAmount());
                            listSum.add(dao.getData().get(7).getMaleAmount() + dao.getData().get(7).getFemaleAmount());


                            String little = "จำนวนและร้อยละของประชากรอายุ 15 ปีขึ้นไป จำแนกตามระดับการศึกษาที่สำเร็จ และเพศ " + quarterName + "ปี " + year;

                            Intent intent = new Intent(getContext(), DataActivity.class);
                            intent.putExtra("key", 9);
                            intent.putExtra("little", little);
                            intent.putStringArrayListExtra("name", listEduName);
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
                public void onFailure(Call<LfpEducationSexCollectionDao> call, Throwable t) {
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
