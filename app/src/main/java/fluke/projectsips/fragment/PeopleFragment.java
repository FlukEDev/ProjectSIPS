package fluke.projectsips.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.jaredrummler.materialspinner.MaterialSpinner;

import fluke.projectsips.R;


public class PeopleFragment extends Fragment {

    private MaterialSpinner sDistrict;
    private MaterialSpinner sYear;
    private Button btnSearch;


    public PeopleFragment() {
        super();
    }

    public static PeopleFragment newInstance() {
        PeopleFragment fragment = new PeopleFragment();
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
        View rootView = inflater.inflate(R.layout.fragment_people, container, false);
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
            sDistrict.setOnItemSelectedListener(districtSelectItem);
            sYear.setOnItemSelectedListener(yearSelectItem);
        }
    };

    MaterialSpinner.OnItemSelectedListener districtSelectItem = new MaterialSpinner.OnItemSelectedListener() {
        @Override
        public void onItemSelected(MaterialSpinner view, int position, long id, Object item) {
            //Toast.makeText(getContext(), "District Position = " + position, Toast.LENGTH_SHORT).show();
        }
    };

    MaterialSpinner.OnItemSelectedListener yearSelectItem = new MaterialSpinner.OnItemSelectedListener() {
        @Override
        public void onItemSelected(MaterialSpinner view, int position, long id, Object item) {
            //Toast.makeText(getContext(), "Year Position = " + position, Toast.LENGTH_SHORT).show();
        }
    };
}
