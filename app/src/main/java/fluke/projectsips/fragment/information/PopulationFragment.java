package fluke.projectsips.fragment.information;

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
import fluke.projectsips.dao.PopulationCollectionDao;
import fluke.projectsips.manager.Contextor;
import fluke.projectsips.manager.HttpManager;
import fr.ganfra.materialspinner.MaterialSpinner;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PopulationFragment extends Fragment {

    private MaterialSpinner sDistrict;
    private MaterialSpinner sYear;
    private Button btnSearch;
    private int i = 0;
    private int sum;
    private int district;
    private int year;
    private String populationMale;
    private String populationFemale;
    private String districtName;

    public PopulationFragment() {
        super();
    }

    public static PopulationFragment newInstance() {
        PopulationFragment fragment = new PopulationFragment();
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
        View rootView = inflater.inflate(R.layout.fragment_population, container, false);
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

        btnSearch = (Button) rootView.findViewById(R.id.submit);
        btnSearch.setOnClickListener(searchClick);

        String[] district = getResources().getStringArray(R.array.district);
        ArrayAdapter<String> adapterDistrict = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, district);
        adapterDistrict.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sDistrict = (MaterialSpinner) rootView.findViewById(R.id.sDistrict);
        sDistrict.setAdapter(adapterDistrict);
        sDistrict.setOnItemSelectedListener(selectDistrict);

        String[] year = {"2555", "2554", "2553", "2552", "2551", "2550"};
        ArrayAdapter<String> adapterYear = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, year);
        adapterYear.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sYear = (MaterialSpinner) rootView.findViewById(R.id.sYear);
        sYear.setAdapter(adapterYear);
        sYear.setOnItemSelectedListener(selectYear);

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
        alertDialogBuilder.setMessage("คุณยังไม่ได้ทำการเลือก อำเภอ หรือ ปี ที่ต้องการดูข้อมูล");
        alertDialogBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    /******
     * Listener
     *********/

    View.OnClickListener searchClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Call<PopulationCollectionDao> call = HttpManager.getInstance().getService().getPopulation(district, sum);
            call.enqueue(new Callback<PopulationCollectionDao>() {
                @Override
                public void onResponse(Call<PopulationCollectionDao> call, Response<PopulationCollectionDao> response) {

                    if (response.isSuccessful()) {
                        PopulationCollectionDao dao = response.body();
                        if (district != 0 && sum != 0) {
                            ArrayList<String> listMale = new ArrayList<String>();
                            for (i = 0; i < dao.getData().size(); i++) {
                                populationMale = dao.getData().get(i).getPopulationMale();
                                listMale.add(populationMale);
                            }
                            ArrayList<String> listFemale = new ArrayList<String>();
                            for (i = 0; i < dao.getData().size(); i++) {
                                populationFemale = dao.getData().get(i).getPopulationFemale();
                                listFemale.add(populationFemale);
                            }

                            Intent intent = new Intent(getContext(), DataActivity.class);
                            intent.putExtra("key", 0);
                            intent.putStringArrayListExtra("listMale", listMale);
                            intent.putStringArrayListExtra("listFemale", listFemale);
                            intent.putExtra("districtName", districtName);
                            intent.putExtra("year", year);

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
                public void onFailure(Call<PopulationCollectionDao> call, Throwable t) {
                    Toast.makeText(Contextor.getInstance().getContext(), t.toString(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    };
    AdapterView.OnItemSelectedListener selectDistrict = new AdapterView.OnItemSelectedListener() {

        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            int val = 1;
            district = position + val;
            districtName = parent.getSelectedItem().toString();
            //Toast.makeText(getActivity(), "District Name = " + districtName, Toast.LENGTH_SHORT).show();
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
                sum = 0;
            } else {
                    sum = Integer.valueOf(parent.getItemAtPosition(position).toString()) - val;
                    year = Integer.parseInt(parent.getSelectedItem().toString());
            }
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    };
}
