package fluke.projectsips.fragment.agriculture;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import fluke.projectsips.R;
import fr.ganfra.materialspinner.MaterialSpinner;


public class AreaFragment extends Fragment {

    private MaterialSpinner sProduct;

    public AreaFragment() {
        super();
    }

    public static AreaFragment newInstance() {
        AreaFragment fragment = new AreaFragment();
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
        View rootView = inflater.inflate(R.layout.fragment_agriculture_area, container, false);
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

        String[] product = {"แหล่งน้ำ", "ข้าว",};
        ArrayAdapter<String> adapterDistrict = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, product);
        adapterDistrict.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sProduct = (MaterialSpinner) rootView.findViewById(R.id.sProduct);
        sProduct.setAdapter(adapterDistrict);
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
