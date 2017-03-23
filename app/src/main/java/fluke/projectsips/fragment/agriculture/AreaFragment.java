package fluke.projectsips.fragment.agriculture;

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
import fluke.projectsips.dao.FarmAreaCollectionDao;
import fluke.projectsips.dao.FarmAreaTypeCollectionDao;
import fluke.projectsips.manager.Contextor;
import fluke.projectsips.manager.HttpManager;
import fr.ganfra.materialspinner.MaterialSpinner;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class AreaFragment extends Fragment {

    private MaterialSpinner sProduct;
    private String nameType;
    private Button btnSearch;
    private int product;
    private GoogleMap objGoogleMap;
    private LatLng objLatLng, zoom;
    private String objTitle, objSnippet;

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
    private void initInstances(final View rootView, Bundle savedInstanceState) {
        // Init 'View' instance(s) with rootView.findViewById here
        // Note: State of variable initialized here could not be saved
        //       in onSavedInstanceState

        // SetUp Center for Map
        zoom = new LatLng(13.817988458889692, 102.07078260793753);

        // Create Google Map
        objGoogleMap = ((SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map)).getMap();
        objGoogleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(zoom, 9));
        objGoogleMap.setMapType(com.google.android.gms.maps.GoogleMap.MAP_TYPE_TERRAIN);

        Call<FarmAreaTypeCollectionDao> callAreaType = HttpManager.getInstance().getService().getFarmAreaType();
        callAreaType.enqueue(new Callback<FarmAreaTypeCollectionDao>() {
            @Override
            public void onResponse(Call<FarmAreaTypeCollectionDao> call, Response<FarmAreaTypeCollectionDao> response) {
                if (response.isSuccessful()) {
                    FarmAreaTypeCollectionDao daoAreaType = response.body();
                    ArrayList<String> listTypeName = new ArrayList<String>();
                    for (int i = 0; i < daoAreaType.getData().size(); i++) {
                        nameType = daoAreaType.getData().get(i).getAreaTypeName();
                        listTypeName.add(nameType);
                    }

                    ArrayAdapter<String> adapterTypeName = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, listTypeName);
                    adapterTypeName.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    sProduct = (MaterialSpinner) rootView.findViewById(R.id.sProduct);
                    sProduct.setAdapter(adapterTypeName);
                    sProduct.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                            switch (position) {
                                case 0:
                                    product = 1;
                                    break;
                                case 1:
                                    product = 2;
                                    break;
                                case 2:
                                    product = 4;
                                    break;
                                case 3:
                                    product = 6;
                                    break;
                                case 4:
                                    product = 7;
                                    break;
                                case 5:
                                    product = 8;
                                    break;
                                case 6:
                                    product = 9;
                                    break;
                                default:
                                    break;
                            }

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
            public void onFailure(Call<FarmAreaTypeCollectionDao> call, Throwable t) {
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
        alertDialogBuilder.setMessage("คุณยังไม่ได้ทำการเลือก ประเภทเกษตรกรรม ที่ต้องการดูข้อมูล");
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
            Call<FarmAreaCollectionDao> call = HttpManager.getInstance().getService().getFarmArea(product);
            call.enqueue(new Callback<FarmAreaCollectionDao>() {
                @Override
                public void onResponse(Call<FarmAreaCollectionDao> call, Response<FarmAreaCollectionDao> response) {
                    if (response.isSuccessful()) {
                        FarmAreaCollectionDao dao = response.body();

                        objGoogleMap.clear();

                        if (product != 0) {
                            // Create LatLng
                            for (int i = 0; i < dao.getData().size(); i++) {
                                objLatLng = new LatLng(Double.valueOf(dao.getData().get(i).getAreaLatitude()),
                                        Double.valueOf(dao.getData().get(i).getAreaLongitude()));
                                objTitle = dao.getData().get(i).getAreaName();
                                objSnippet = dao.getData().get(i).getAreaDetail();
                                objGoogleMap.addMarker(new MarkerOptions()
                                        .position(objLatLng)
                                        .title(objTitle)
                                        .snippet(objSnippet));
                            }
                        } else {
                            MsgBox();
                        }

                        // Create Marker for Center Map
                        //objGoogleMap.addMarker(new MarkerOptions().position(objLatLng));

                        //Log.d("FLUKE", "Name : " + listName);
                        //Log.d("FLUKE", "Detail : " + listDetail);
                        //Log.d("FLUKE", "Latitude : " + listLatitude);
                        //Log.d("FLUKE", "Longitude : " + listLongitude);


                    } else {
                        try {
                            Toast.makeText(Contextor.getInstance().getContext(), response.errorBody().string(), Toast.LENGTH_LONG).show();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }

                @Override
                public void onFailure(Call<FarmAreaCollectionDao> call, Throwable t) {
                    Toast.makeText(Contextor.getInstance().getContext(), t.toString(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    };

}
