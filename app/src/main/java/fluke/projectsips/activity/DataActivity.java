package fluke.projectsips.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import fluke.projectsips.R;
import fluke.projectsips.fragment.data.DataCategCpiFragment;
import fluke.projectsips.fragment.data.DataCpiFragment;
import fluke.projectsips.fragment.data.DataGdpFragment;
import fluke.projectsips.fragment.data.DataGppProvinceFragment;
import fluke.projectsips.fragment.data.DataGppSakaeoFragment;
import fluke.projectsips.fragment.data.DataGrpBuraphaFragment;
import fluke.projectsips.fragment.data.DataGrpFragment;
import fluke.projectsips.fragment.data.DataPopulationFragment;

public class DataActivity extends AppCompatActivity {

    Toolbar toolBar;
    int key = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);

        initInstances();

        Intent intentGpp = getIntent();
        key = intentGpp.getIntExtra("key", 0);

        if (key == 0) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.contentContainer, DataPopulationFragment.newInstance())
                    .commit();
        }
        else if (key == 1) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.contentContainer, DataCpiFragment.newInstance())
                    .commit();
        }
        else if (key == 2) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.contentContainer, DataCategCpiFragment.newInstance())
                    .commit();
        }
        else if (key == 3) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.contentContainer, DataGppSakaeoFragment.newInstance())
                    .commit();
        }
        else if (key == 4) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.contentContainer, DataGppProvinceFragment.newInstance())
                    .commit();
        }
        else if (key == 5) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.contentContainer, DataGrpFragment.newInstance())
                    .commit();
        }
        else if (key == 6) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.contentContainer, DataGrpBuraphaFragment.newInstance())
                    .commit();
        }
        else if (key == 7) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.contentContainer, DataGdpFragment.newInstance())
                    .commit();
        }
    }

    private void initInstances() {

        toolBar = (Toolbar) findViewById(R.id.toolBar);
        setSupportActionBar(toolBar);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
