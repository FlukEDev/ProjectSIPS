package fluke.projectsips.fragment.lfp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import fluke.projectsips.R;
import fr.ganfra.materialspinner.MaterialSpinner;


public class AgeEducationAndSexFragment extends Fragment {

    private MaterialSpinner sQuarter;

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

        String[] quarter = {"ไตรมาส 1", "ไตรมาส 2", "ไตรมาส 3", "ไตรมาส 4"};
        ArrayAdapter<String> adapterQuarter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, quarter);
        adapterQuarter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sQuarter = (MaterialSpinner) rootView.findViewById(R.id.sQuarter);
        sQuarter.setAdapter(adapterQuarter);
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
