package fluke.projectsips.fragment.economic.gpp;

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
import android.widget.RadioGroup;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;

import fluke.projectsips.R;
import fluke.projectsips.activity.DataActivity;
import fluke.projectsips.dao.GrpCollectionDao;
import fluke.projectsips.dao.RegionCollectionDao;
import fluke.projectsips.manager.Contextor;
import fluke.projectsips.manager.HttpManager;
import fr.ganfra.materialspinner.MaterialSpinner;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

// ผลิตภัณฑ์มวลรวมรายภูมิภาค (GRP)

public class GrpFragment extends Fragment {

    private MaterialSpinner sYear;
    private MaterialSpinner sRegion;
    private RadioGroup rgType;
    private Button btnSearch;
    private String region;
    private String population;
    private int sumYear;
    private int year;
    private int type = 1;
    private int regionID;
    private String grpPrice;
    private String provinceName;
    private float price;
    private int sum;
    private String nameRegion;

    public GrpFragment() {
        super();
    }

    public static GrpFragment newInstance() {
        GrpFragment fragment = new GrpFragment();
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
        View rootView = inflater.inflate(R.layout.fragment_grp, container, false);
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

        Call<RegionCollectionDao> call = HttpManager.getInstance().getService().getRegion();
        call.enqueue(new Callback<RegionCollectionDao>() {
            @Override
            public void onResponse(Call<RegionCollectionDao> call, Response<RegionCollectionDao> response) {
                if (response.isSuccessful()) {
                    RegionCollectionDao daoRegion = response.body();
                    ArrayList<String> listRegion = new ArrayList<String>();
                    for (int i = 0; i < daoRegion.getData().size(); i++) {
                        region = daoRegion.getData().get(i).getRegionName();
                        listRegion.add(region);
                    }

                    ArrayAdapter<String> adapterRegion = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, listRegion);
                    adapterRegion.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    sRegion = (MaterialSpinner) rootView.findViewById(R.id.sRegion);
                    sRegion.setAdapter(adapterRegion);
                    sRegion.setOnItemSelectedListener(selectRegion);

                } else {
                    try {
                        Toast.makeText(Contextor.getInstance().getContext(), response.errorBody().string(), Toast.LENGTH_LONG).show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<RegionCollectionDao> call, Throwable t) {
                Toast.makeText(Contextor.getInstance().getContext(), t.toString(), Toast.LENGTH_SHORT).show();
            }
        });

        String[] year = getResources().getStringArray(R.array.gpp_year);
        ArrayAdapter<String> adapterYear = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, year);
        adapterYear.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sYear = (MaterialSpinner) rootView.findViewById(R.id.sYear);
        sYear.setAdapter(adapterYear);
        sYear.setOnItemSelectedListener(selectYear);

        rgType = (RadioGroup) rootView.findViewById(R.id.rgType);
        rgType.setOnCheckedChangeListener(selectType);

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
        alertDialogBuilder.setMessage("คุณยังไม่ได้ทำการเลือก ปี ภาค หรือ ประเภท ที่ต้องการดูข้อมูล");
        alertDialogBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    /***************
     *
     * Listeners
     *
     **************/

    View.OnClickListener searchClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Call<GrpCollectionDao> call = HttpManager.getInstance().getService().getGrp(type, sumYear, regionID);
            call.enqueue(new Callback<GrpCollectionDao>() {
                @Override
                public void onResponse(Call<GrpCollectionDao> call, Response<GrpCollectionDao> response) {
                    if (response.isSuccessful()) {
                        GrpCollectionDao dao = response.body();
                        if (sumYear != 0 && regionID != 0) {
                            if (type == 1) {

                                ArrayList<Integer> listPrice = new ArrayList<Integer>();
                                for (int i = 0; i < dao.getData().size(); i++) {
                                    grpPrice = dao.getData().get(i).getSUMEconomicGPPProvinceTransactionYearPrice();
                                    price = Float.parseFloat(grpPrice.trim());
                                    int val = 1000000;
                                    sum = Math.round(price / val);
                                    listPrice.add(sum);
                                }

                                ArrayList<String> listProvinceName = new ArrayList<String>();
                                for (int i = 0; i < dao.getData().size(); i++) {
                                    provinceName = dao.getData().get(i).getProvinceName();
                                    listProvinceName.add(provinceName);
                                }

                                ArrayList<String> listPopulation = new ArrayList<String>();
                                for (int i = 0; i< dao.getData().size(); i++) {
                                    population = dao.getData().get(i).getPopulation();
                                    listPopulation.add(population);
                                }

                                String little = "ผลิตภัณฑ์มวลรวมใน " + nameRegion +" (GRP) ตามราคาประจำปี พ.ศ. "+ year;

                                Intent intent = new Intent(getContext(), DataActivity.class);
                                intent.putExtra("key", 5);
                                intent.putStringArrayListExtra("name", listProvinceName);
                                intent.putIntegerArrayListExtra("price", listPrice);
                                intent.putStringArrayListExtra("population", listPopulation);
                                intent.putExtra("little", little);
                                intent.putExtra("region", nameRegion);

                                startActivity(intent);
                            }

                            if (type == 2) {
                                ArrayList<Integer> listPrice = new ArrayList<Integer>();
                                for (int i = 0; i < dao.getData().size(); i++) {
                                    grpPrice = dao.getData().get(i).getSUMEconomicGPPProvinceTransactionStablePrice();
                                    price = Float.parseFloat(grpPrice.trim());
                                    int val = 1000000;
                                    sum = Math.round(price / val);
                                    listPrice.add(sum);
                                }

                                ArrayList<String> listProvinceName = new ArrayList<String>();
                                for (int i = 0; i < dao.getData().size(); i++) {
                                    provinceName = dao.getData().get(i).getProvinceName();
                                    listProvinceName.add(provinceName);
                                }

                                ArrayList<String> listPopulation = new ArrayList<String>();
                                for (int i = 0; i< dao.getData().size(); i++) {
                                    population = dao.getData().get(i).getPopulation();
                                    listPopulation.add(population);
                                }

                                String little = "ผลิตภัณฑ์มวลรวมใน" + nameRegion + "(GRP) ตามระดับราคาคงที่ พ.ศ. " + year;

                                Intent intent = new Intent(getContext(), DataActivity.class);
                                intent.putExtra("key", 5);
                                intent.putStringArrayListExtra("name", listProvinceName);
                                intent.putIntegerArrayListExtra("price", listPrice);
                                intent.putStringArrayListExtra("population", listPopulation);
                                intent.putExtra("little", little);
                                intent.putExtra("region", nameRegion);

                                startActivity(intent);
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
                public void onFailure(Call<GrpCollectionDao> call, Throwable t) {
                    Toast.makeText(Contextor.getInstance().getContext(), t.toString(), Toast.LENGTH_SHORT).show();
                }
            });
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
                    year = Integer.parseInt(parent.getSelectedItem().toString());
            }
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    };

    AdapterView.OnItemSelectedListener selectRegion = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            regionID = position + 1;
            nameRegion = parent.getItemAtPosition(position).toString();
            //Toast.makeText(getContext(), "Name : " + nameRegion, Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    };

    RadioGroup.OnCheckedChangeListener selectType = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            int id = rgType.getCheckedRadioButtonId();
            switch (id) {
                case R.id.rbYearPrice:
                    type = 1;
                    //Toast.makeText(getContext(), "Type = " + type, Toast.LENGTH_SHORT).show();
                    break;
                case R.id.rbStablePrice:
                    type = 2;
                    //Toast.makeText(getContext(), "Type = " + type, Toast.LENGTH_SHORT).show();
                    break;
                default:
                    break;
            }
        }
    };

}
