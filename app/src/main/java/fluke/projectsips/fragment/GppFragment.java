package fluke.projectsips.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import fluke.projectsips.R;
import fluke.projectsips.activity.SecondActivity;
import fluke.projectsips.adapter.GppListAdapter;


public class GppFragment extends Fragment {

    ListView listView;
    GppListAdapter gppListAdapter;

    public GppFragment() {
        super();
    }

    public static GppFragment newInstance() {
        GppFragment fragment = new GppFragment();
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
        View rootView = inflater.inflate(R.layout.fragment_gpp, container, false);
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
        listView = (ListView) rootView.findViewById(R.id.listView);
        gppListAdapter = new GppListAdapter();
        listView.setAdapter(gppListAdapter);
        listView.setOnItemClickListener(listViewItemClickListener);
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

    /******************
     *
     * Listener Zone
     *
     ******************/

    AdapterView.OnItemClickListener listViewItemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            switch (position) {
                case 0:
                    Intent intent = new Intent(getContext(), SecondActivity.class);
                    intent.putExtra("gpp", 0);
                    startActivity(intent);
                    break;
                case 1:
                    Intent intent1 = new Intent(getContext(), SecondActivity.class);
                    intent1.putExtra("gpp", 1);
                    startActivity(intent1);
                    break;
                case 2:
                    Intent intent2 = new Intent(getContext(), SecondActivity.class);
                    intent2.putExtra("gpp", 2);
                    startActivity(intent2);
                    break;
                case 3:
                    Intent intent3 = new Intent(getContext(), SecondActivity.class);
                    intent3.putExtra("gpp", 3);
                    startActivity(intent3);
                    break;
                case 4:
                    Intent intent4 = new Intent(getContext(), SecondActivity.class);
                    intent4.putExtra("gpp", 4);
                    startActivity(intent4);
                    break;
                case 5:
                    Intent intent5 = new Intent(getContext(), SecondActivity.class);
                    intent5.putExtra("gpp", 5);
                    startActivity(intent5);
                    break;
            }
        }
    };
}
