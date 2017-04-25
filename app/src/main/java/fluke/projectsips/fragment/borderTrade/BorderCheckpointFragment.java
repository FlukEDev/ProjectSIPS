package fluke.projectsips.fragment.borderTrade;

import android.content.DialogInterface;
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

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.ArrayList;

import fluke.projectsips.R;
import fluke.projectsips.dao.BorderCheckpointCollectionDao;
import fluke.projectsips.dao.BorderCheckpointTypeCollection;
import fluke.projectsips.manager.Contextor;
import fluke.projectsips.manager.HttpManager;
import fr.ganfra.materialspinner.MaterialSpinner;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class BorderCheckpointFragment extends Fragment {

    private MaterialSpinner sBorderCheckpoint;
    private String nameType;
    private Button btnSearch;
    private int borderTrade;
    private GoogleMap objGoogleMap;
    private LatLng objLatLng, zoom;
    private String objTitle, objSnippet;

    public BorderCheckpointFragment() {
        super();
    }

    public static BorderCheckpointFragment newInstance() {
        BorderCheckpointFragment fragment = new BorderCheckpointFragment();
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
        View rootView = inflater.inflate(R.layout.fragment_border_checkpoint, container, false);
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

        // SetUp Center for Map
        zoom = new LatLng(13.817988458889692, 102.07078260793753);

        // Create Google Map
        objGoogleMap = ((SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map)).getMap();
        objGoogleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(zoom, 9));
        objGoogleMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);

        Call<BorderCheckpointTypeCollection> callAreaType = HttpManager.getInstance().getService().getBorderCheckpointType();
        callAreaType.enqueue(new Callback<BorderCheckpointTypeCollection>() {
            @Override
            public void onResponse(Call<BorderCheckpointTypeCollection> call, Response<BorderCheckpointTypeCollection> response) {
                if (response.isSuccessful()) {
                    BorderCheckpointTypeCollection daoBorderCheckpointType = response.body();
                    ArrayList<String> listTypeName = new ArrayList<String>();
                    for (int i = 0; i < daoBorderCheckpointType.getData().size(); i++) {
                        nameType = daoBorderCheckpointType.getData().get(i).getBorderTypeName();
                        listTypeName.add(nameType);
                    }

                    ArrayAdapter<String> adapterTypeName = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, listTypeName);
                    adapterTypeName.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    sBorderCheckpoint = (MaterialSpinner) rootView.findViewById(R.id.sBorderCheckpoint);
                    sBorderCheckpoint.setAdapter(adapterTypeName);
                    sBorderCheckpoint.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            borderTrade = position + 1;
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });

                } else {
                    try {
                        Toast.makeText(Contextor.getInstance().getContext(), response.errorBody().string(), Toast.LENGTH_LONG).show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<BorderCheckpointTypeCollection> call, Throwable t) {
                Toast.makeText(Contextor.getInstance().getContext(), t.toString(), Toast.LENGTH_SHORT).show();
            }
        });

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
        alertDialogBuilder.setMessage("คุณยังไม่ได้ทำการเลือก ประเภทจุดผ่านแดน ที่ต้องการดูข้อมูล");
        alertDialogBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    /***********
     *
     * Listeners
     *
     ***********/

    View.OnClickListener searchClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Call<BorderCheckpointCollectionDao> call = HttpManager.getInstance().getService().getBorderCheckpoint(borderTrade);
            call.enqueue(new Callback<BorderCheckpointCollectionDao>() {
                @Override
                public void onResponse(Call<BorderCheckpointCollectionDao> call, Response<BorderCheckpointCollectionDao> response) {
                    if (response.isSuccessful()) {
                        BorderCheckpointCollectionDao dao = response.body();

                        objGoogleMap.clear();

                        if (borderTrade != 0) {
                            // Create LatLng
                            for (int i = 0; i < dao.getData().size(); i++) {
                                objLatLng = new LatLng(Double.valueOf(dao.getData().get(i).getCheckpointLatitude()),
                                        Double.valueOf(dao.getData().get(i).getCheckpointLongitude()));
                                objTitle = dao.getData().get(i).getCheckpointName();
                                objSnippet = dao.getData().get(i).getCheckpointLocation();
                                objGoogleMap.addMarker(new MarkerOptions()
                                        .position(objLatLng)
                                        .title(objTitle)
                                        .snippet(objSnippet));
                            }
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
                public void onFailure(Call<BorderCheckpointCollectionDao> call, Throwable t) {
                    Toast.makeText(Contextor.getInstance().getContext(), t.toString(), Toast.LENGTH_SHORT).show();
                }
            });

        }
    };

}
