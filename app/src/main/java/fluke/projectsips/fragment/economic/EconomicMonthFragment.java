package fluke.projectsips.fragment.economic;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Toast;

import java.io.IOException;

import fluke.projectsips.R;
import fluke.projectsips.dao.EcoMonthCollectionDao;
import fluke.projectsips.manager.Contextor;
import fluke.projectsips.manager.HttpManager;
import fr.ganfra.materialspinner.MaterialSpinner;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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

    /**************
     *
     * Listeners
     *
     **************/

    View.OnClickListener searchClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            date = sumYear + "" + sumMonth + "00";

            Call<EcoMonthCollectionDao> call = HttpManager.getInstance().getService().getEcoMonth(date, side);
            call.enqueue(new Callback<EcoMonthCollectionDao>() {
                @Override
                public void onResponse(Call<EcoMonthCollectionDao> call, Response<EcoMonthCollectionDao> response) {
                    if (response.isSuccessful()) {
                        EcoMonthCollectionDao dao = response.body();

                        name = dao.getData().get(0).getKpiName();
                        Log.d("FLUKE", "Name : " + name);

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
