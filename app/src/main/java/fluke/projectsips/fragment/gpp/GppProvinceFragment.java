package fluke.projectsips.fragment.gpp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;

import fluke.projectsips.R;
import fluke.projectsips.dao.ProvinceCollectionDao;
import fluke.projectsips.manager.Contextor;
import fluke.projectsips.manager.HttpManager;
import fr.ganfra.materialspinner.MaterialSpinner;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class GppProvinceFragment extends Fragment {

    private String province;
    private MaterialSpinner sProvince;
    private int i;
    private MaterialSpinner sYear;
    private RadioGroup rgType;
    private Button btnSearch;

    public GppProvinceFragment() {
        super();
    }

    public static GppProvinceFragment newInstance() {
        GppProvinceFragment fragment = new GppProvinceFragment();
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
        View rootView = inflater.inflate(R.layout.fragment_gpp_province, container, false);
        initInstances(rootView, savedInstanceState);
        return rootView;
    }

    @SuppressWarnings("UnusedParameters")
    private void init(Bundle savedInstanceState) {
        // Init Fragment level's variable(s) here
    }

    @SuppressWarnings("UnusedParameters")
    private void initInstances(final View rootView, Bundle savedInstanceState) {
        // Init 'View' instance(s) with rootView.findViewById here
        // Note: State of variable initialized here could not be saved
        //       in onSavedInstanceState

        Call<ProvinceCollectionDao> callProvince = HttpManager.getInstance().getService().getProvince();
        callProvince.enqueue(new Callback<ProvinceCollectionDao>() {
            @Override
            public void onResponse(Call<ProvinceCollectionDao> call, Response<ProvinceCollectionDao> response) {
                if (response.isSuccessful()) {
                    ProvinceCollectionDao daoProvince = response.body();
                    ArrayList<String> listProvince = new ArrayList<String>();
                    for (i = 0; i < daoProvince.getData().size(); i++) {
                        province = daoProvince.getData().get(i).getProvinceName();
                        listProvince.add(province);
                    }

                    Log.d("FLUKE", "Data : " + listProvince);

                    ArrayAdapter<String> adapterProvince = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, listProvince);
                    adapterProvince.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    sProvince = (MaterialSpinner) rootView.findViewById(R.id.sProvince);
                    sProvince.setAdapter(adapterProvince);

                } else {
                    try {
                        Toast.makeText(Contextor.getInstance().getContext(), response.errorBody().string(), Toast.LENGTH_LONG).show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<ProvinceCollectionDao> call, Throwable t) {
                Toast.makeText(Contextor.getInstance().getContext(), t.toString(), Toast.LENGTH_SHORT).show();
            }
        });

        String[] year = getResources().getStringArray(R.array.gpp_year);
        ArrayAdapter<String> adapterYear = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, year);
        adapterYear.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sYear = (MaterialSpinner) rootView.findViewById(R.id.sYear);
        sYear.setAdapter(adapterYear);

        rgType = (RadioGroup) rootView.findViewById(R.id.rgType);

        btnSearch = (Button) rootView.findViewById(R.id.submit);

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
