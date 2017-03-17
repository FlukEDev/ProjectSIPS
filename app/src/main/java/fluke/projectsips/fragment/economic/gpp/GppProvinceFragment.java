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
import fluke.projectsips.dao.GppProvinceCollectionDao;
import fluke.projectsips.dao.ProvinceCollectionDao;
import fluke.projectsips.manager.Contextor;
import fluke.projectsips.manager.HttpManager;
import fr.ganfra.materialspinner.MaterialSpinner;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

// ผลิตภัณฑ์มวลรวมรายจังหวัด (GPP)

public class GppProvinceFragment extends Fragment {

    private String province;
    private MaterialSpinner sProvince;
    private int i;
    private MaterialSpinner sYear;
    private RadioGroup rgType;
    private Button btnSearch;
    private int type = 1;
    private int sumYear;
    private int year;
    private String gppProvinceName;
    private String gppProvincePrice;
    private String population;
    private double price;
    private int sum;
    private int provinceID;
    private String nameProvince;

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

                    ArrayAdapter<String> adapterProvince = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, listProvince);
                    adapterProvince.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    sProvince = (MaterialSpinner) rootView.findViewById(R.id.sProvince);
                    sProvince.setAdapter(adapterProvince);
                    sProvince.setOnItemSelectedListener(selectProvince);

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
        alertDialogBuilder.setMessage("คุณยังไม่ได้ทำการเลือก ปี จังหวัด หรือ ประเภท ที่ต้องการดูข้อมูล");
        alertDialogBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    /***********
     * Listeners
     ***********/

    View.OnClickListener searchClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Call<GppProvinceCollectionDao> call = HttpManager.getInstance().getService().getGppProvince(type, sumYear, provinceID);
            call.enqueue(new Callback<GppProvinceCollectionDao>() {
                @Override
                public void onResponse(Call<GppProvinceCollectionDao> call, Response<GppProvinceCollectionDao> response) {
                    if (response.isSuccessful()) {
                        GppProvinceCollectionDao dao = response.body();
                        if (sumYear != 0 && provinceID != 0) {
                            if (type == 1) {
                                ArrayList<String> listGppProvinceName = new ArrayList<String>();
                                for (i = 0; i < dao.getData().size(); i++) {
                                    gppProvinceName = dao.getData().get(i).getEcoSubSecName();
                                    listGppProvinceName.add(gppProvinceName);
                                }

                                ArrayList<Integer> listGppProvincePrice = new ArrayList<Integer>();
                                for (i = 0; i < dao.getData().size(); i++) {
                                    gppProvincePrice = dao.getData().get(i).getYearPrice();
                                    price = Float.parseFloat(gppProvincePrice.trim());
                                    int val = 1000000;
                                    sum = (int) Math.round(price / val);
                                    listGppProvincePrice.add(sum);
                                }

                                population = dao.getData().get(0).getPopulation();

                                String little = "ข้อมูลผลิตภัณฑ์มวลรวมจังหวัด" + nameProvince + " (GPP) ปี พ.ศ. " + year + " ณ ราคาประจำปี แบบ TOP DOWN";

                                Intent intent = new Intent(getContext(), DataActivity.class);
                                intent.putExtra("key", 4);
                                intent.putStringArrayListExtra("name", listGppProvinceName);
                                intent.putIntegerArrayListExtra("price", listGppProvincePrice);
                                intent.putExtra("little", little);
                                intent.putExtra("population", population);
                                intent.putExtra("type", type);

                                startActivity(intent);
                            }

                            if (type == 2) {
                                ArrayList<String> listGppProvinceName = new ArrayList<String>();
                                for (i = 0; i < dao.getData().size(); i++) {
                                    gppProvinceName = dao.getData().get(i).getEcoSubSecName();
                                    listGppProvinceName.add(gppProvinceName);
                                }

                                ArrayList<Integer> listGppProvincePrice = new ArrayList<Integer>();
                                for (i = 0; i < dao.getData().size(); i++) {
                                    gppProvincePrice = dao.getData().get(i).getStablePrice();
                                    price = Double.parseDouble(gppProvincePrice.trim());
                                    int val = 1000000;
                                    sum = (int) Math.round(price / val);
                                    listGppProvincePrice.add(sum);
                                }

                                population = dao.getData().get(0).getPopulation();

                                String little = "ข้อมูลผลิตภัณฑ์มวลรวมจังหวัด" + nameProvince + "(GPP) ปี พ.ศ. " + year + " ณ ระดับราคาคงที่ แบบ TOP DOWN";

                                Intent intent = new Intent(getContext(), DataActivity.class);
                                intent.putExtra("key", 4);
                                intent.putStringArrayListExtra("name", listGppProvinceName);
                                intent.putIntegerArrayListExtra("price", listGppProvincePrice);
                                intent.putExtra("little", little);
                                intent.putExtra("population", population);
                                intent.putExtra("type", type);

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
                public void onFailure(Call<GppProvinceCollectionDao> call, Throwable t) {
                    Toast.makeText(Contextor.getInstance().getContext(), t.toString(), Toast.LENGTH_SHORT).show();
                }
            });
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

    AdapterView.OnItemSelectedListener selectProvince = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            provinceID = position + 1;
            nameProvince = parent.getItemAtPosition(position).toString();
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    };
}
