package fluke.projectsips.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.jaredrummler.materialspinner.MaterialSpinner;

import java.io.IOException;

import fluke.projectsips.R;
import fluke.projectsips.dao.PopulationCollectionDao;
import fluke.projectsips.manager.HttpManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class PopulationFragment extends Fragment {

    private MaterialSpinner sDistrict;
    private MaterialSpinner sYear;
    private Button btnSearch;


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

        sDistrict = (MaterialSpinner) rootView.findViewById(R.id.sDistrict);
        sDistrict.setItems("เมืองสระแก้ว", "คลองหาด", "ตาพระยา", "วังน้ำเย็น", "วัฒนานคร", "อรัญประเทศ",
                "เขาฉกรรจ์", "โคกสูง", "วังสมบูรณ์");

        sYear = (MaterialSpinner) rootView.findViewById(R.id.sYear);
        sYear.setItems("2555", "2554", "2553", "2552", "2551", "2550");

        sDistrict.setOnItemSelectedListener(districtSelectItem);
        sYear.setOnItemSelectedListener(yearSelectItem);

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

    /******
     * Listener
     *********/

    View.OnClickListener searchClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Call<PopulationCollectionDao> call = HttpManager.getInstance().getService().getPopulation(9, 2011);
            call.enqueue(new Callback<PopulationCollectionDao>() {
                @Override
                public void onResponse(Call<PopulationCollectionDao> call, Response<PopulationCollectionDao> response) {

                    if (response.isSuccessful()) {
                        PopulationCollectionDao dao = response.body();
                        Toast.makeText(getActivity(), dao.getData().get(0).getPopulationFemale(), Toast.LENGTH_LONG).show();
                        Toast.makeText(getActivity(), dao.getData().get(0).getPopulationMale(), Toast.LENGTH_LONG).show();
                    } else {
                        try {
                            Toast.makeText(getActivity(), response.errorBody().string(), Toast.LENGTH_LONG).show();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                }

                @Override
                public void onFailure(Call<PopulationCollectionDao> call, Throwable t) {
                    Toast.makeText(getActivity(), t.toString(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    };

    MaterialSpinner.OnItemSelectedListener districtSelectItem = new MaterialSpinner.OnItemSelectedListener() {
        @Override
        public void onItemSelected(MaterialSpinner view, int position, long id, Object item) {
            Toast.makeText(getContext(), "District Position = " + position, Toast.LENGTH_SHORT).show();
        }
    };

    MaterialSpinner.OnItemSelectedListener yearSelectItem = new MaterialSpinner.OnItemSelectedListener() {
        @Override
        public void onItemSelected(MaterialSpinner view, int position, long id, Object item) {
            Toast.makeText(getContext(), "Year Position = " + position, Toast.LENGTH_SHORT).show();
        }
    };
}
