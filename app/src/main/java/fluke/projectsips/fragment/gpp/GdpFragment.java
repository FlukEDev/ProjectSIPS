package fluke.projectsips.fragment.gpp;

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
import fluke.projectsips.dao.GdpCollectionDao;
import fluke.projectsips.manager.Contextor;
import fluke.projectsips.manager.HttpManager;
import fr.ganfra.materialspinner.MaterialSpinner;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class GdpFragment extends Fragment {

    private MaterialSpinner sYear;
    private RadioGroup rgType;
    private Button btnSearch;
    private int sumYear;
    private int year;
    private int type = 1;

    public GdpFragment() {
        super();
    }

    public static GdpFragment newInstance() {
        GdpFragment fragment = new GdpFragment();
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
        View rootView = inflater.inflate(R.layout.fragment_gdp, container, false);
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
        alertDialogBuilder.setMessage("คุณยังไม่ได้ทำการเลือก ปี หรือ ประเภท ที่ต้องการดูข้อมูล");
        alertDialogBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    private String grpPrice;
    private float price;
    private int sum;
    private String provinceName;
    private String population;
    /**************
     *
     * Listeners
     *
     **************/

    View.OnClickListener searchClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Call<GdpCollectionDao> call = HttpManager.getInstance().getService().getGdp(type, sumYear);
            call.enqueue(new Callback<GdpCollectionDao>() {
                @Override
                public void onResponse(Call<GdpCollectionDao> call, Response<GdpCollectionDao> response) {
                    if (response.isSuccessful()) {
                        GdpCollectionDao dao = response.body();
                        if (sumYear != 0) {
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

                                String little = "ผลิตภัณฑ์มวลรวมในประเทศ (GDP) ตามราคาประจำปี พ.ศ. "+ year;

                                Intent intent = new Intent(getContext(), DataActivity.class);
                                intent.putExtra("key", 7);
                                intent.putStringArrayListExtra("name", listProvinceName);
                                intent.putIntegerArrayListExtra("price", listPrice);
                                intent.putStringArrayListExtra("population", listPopulation);
                                intent.putExtra("little", little);

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

                                String little = "ผลิตภัณฑ์มวลรวมในประเทศ (GDP) ตามระดับราคาคงที่ พ.ศ. " + year;

                                Intent intent = new Intent(getContext(), DataActivity.class);
                                intent.putExtra("key", 7);
                                intent.putStringArrayListExtra("name", listProvinceName);
                                intent.putIntegerArrayListExtra("price", listPrice);
                                intent.putStringArrayListExtra("population", listPopulation);
                                intent.putExtra("little", little);

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
                public void onFailure(Call<GdpCollectionDao> call, Throwable t) {
                    Toast.makeText(Contextor.getInstance().getContext(), t.toString(), Toast.LENGTH_SHORT).show();
                }
            });

        }
    };

    AdapterView.OnItemSelectedListener selectYear = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long l) {
            int val = 543;
            if (position == -1) {
                sumYear = 0;
            } else {
                try {
                    sumYear = Integer.valueOf(parent.getItemAtPosition(position).toString()) - val;
                    //Toast.makeText(getContext(), "Year = " + sumYear, Toast.LENGTH_SHORT).show();
                    year = Integer.parseInt(parent.getSelectedItem().toString());
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
            }
        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {

        }
    };

    RadioGroup.OnCheckedChangeListener selectType = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup radioGroup, int i) {
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
