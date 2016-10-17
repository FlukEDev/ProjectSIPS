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
import fluke.projectsips.dao.GppSakaeoCollectionDao;
import fluke.projectsips.manager.Contextor;
import fluke.projectsips.manager.HttpManager;
import fr.ganfra.materialspinner.MaterialSpinner;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class GppSakaeoFragment extends Fragment {

    private MaterialSpinner sYear;
    private int sumYear;
    private int year;
    private RadioGroup rgType;
    private Button btnSearch;
    private int i;
    private String gppSakaeoName;
    private String gppSakaeoPrice;
    private String population;
    private int type = 1;
    private double price;
    private int sum;

    public GppSakaeoFragment() {
        super();
    }

    public static GppSakaeoFragment newInstance() {
        GppSakaeoFragment fragment = new GppSakaeoFragment();
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
        View rootView = inflater.inflate(R.layout.fragment_gpp_sakaeo, container, false);
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

    /*********
     * Listeners
     **********/

    View.OnClickListener searchClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Call<GppSakaeoCollectionDao> call = HttpManager.getInstance().getService().getGppSakaeo(type, sumYear);
            call.enqueue(new Callback<GppSakaeoCollectionDao>() {
                @Override
                public void onResponse(Call<GppSakaeoCollectionDao> call, Response<GppSakaeoCollectionDao> response) {
                    if (response.isSuccessful()) {
                        GppSakaeoCollectionDao dao = response.body();
                        if (sumYear != 0) {
                            if (type == 1) {
                                ArrayList<String> listGppSakaeoName = new ArrayList<String>();
                                for (i = 0; i < dao.getData().size(); i++) {
                                    gppSakaeoName = dao.getData().get(i).getEcoSubSecName();
                                    listGppSakaeoName.add(gppSakaeoName);
                                }

                                ArrayList<Integer> listGppSakaeoPrice = new ArrayList<Integer>();
                                for (i = 0; i < dao.getData().size(); i++) {
                                    gppSakaeoPrice = dao.getData().get(i).getYearPrice();
                                    price = Float.parseFloat(gppSakaeoPrice.trim());
                                    int val = 1000000;
                                    sum = (int) Math.round(price / val);
                                    listGppSakaeoPrice.add(sum);
                                }

                                population = dao.getData().get(0).getPopulation();

                                String little = "ข้อมูลผลิตภัณฑ์มวลรวมจังหวัดสระแก้ว (GPP) ปี พ.ศ. " + year + " ณ ราคาประจำปี แบบ TOP DOWN";

                                Intent intent = new Intent(getContext(), DataActivity.class);
                                intent.putExtra("key", 3);
                                intent.putStringArrayListExtra("name", listGppSakaeoName);
                                intent.putIntegerArrayListExtra("price", listGppSakaeoPrice);
                                intent.putExtra("little", little);
                                intent.putExtra("population", population);
                                intent.putExtra("type", type);

                                startActivity(intent);
                            }

                            if (type == 2) {
                                ArrayList<String> listGppSakaeoName = new ArrayList<String>();
                                for (i = 0; i < dao.getData().size(); i++) {
                                    gppSakaeoName = dao.getData().get(i).getEcoSubSecName();
                                    listGppSakaeoName.add(gppSakaeoName);
                                }

                                ArrayList<Integer> listGppSakaeoPrice = new ArrayList<Integer>();
                                for (i = 0; i < dao.getData().size(); i++) {
                                    gppSakaeoPrice = dao.getData().get(i).getStablePrice();
                                    price = Double.parseDouble(gppSakaeoPrice.trim());
                                    int val = 1000000;
                                    sum = (int) Math.round(price / val);
                                    listGppSakaeoPrice.add(sum);
                                }

                                population = dao.getData().get(0).getPopulation();

                                String little = "ข้อมูลผลิตภัณฑ์มวลรวมจังหวัดสระแก้ว (GPP) ปี พ.ศ. " + year + " ณ ระดับราคาคงที่ แบบ TOP DOWN";

                                Intent intent = new Intent(getContext(), DataActivity.class);
                                intent.putExtra("key", 3);
                                intent.putStringArrayListExtra("name", listGppSakaeoName);
                                intent.putIntegerArrayListExtra("price", listGppSakaeoPrice);
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
                public void onFailure(Call<GppSakaeoCollectionDao> call, Throwable t) {
                    Toast.makeText(Contextor.getInstance().getContext(), t.toString(), Toast.LENGTH_SHORT).show();
                }
            });
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
}
