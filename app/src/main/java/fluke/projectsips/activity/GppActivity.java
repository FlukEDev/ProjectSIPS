package fluke.projectsips.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import fluke.projectsips.R;
import fluke.projectsips.fragment.gpp.GdpFragment;
import fluke.projectsips.fragment.gpp.GdpUserFragment;
import fluke.projectsips.fragment.gpp.GppProvinceFragment;
import fluke.projectsips.fragment.gpp.GppSakaeoFragment;
import fluke.projectsips.fragment.gpp.GrpBuraphaFragment;
import fluke.projectsips.fragment.gpp.GrpFragment;

public class GppActivity extends AppCompatActivity {

    Toolbar toolBar;
    int gppKey = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gpp);

        initInstances();

        Intent intentGpp = getIntent();
        gppKey = intentGpp.getIntExtra("gpp", 0);

        if (gppKey == 0) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.contentContainer, GppSakaeoFragment.newInstance())
                    .commit();
        } else if (gppKey == 1) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.contentContainer, GppProvinceFragment.newInstance())
                    .commit();
        } else if (gppKey == 2) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.contentContainer, GrpFragment.newInstance())
                    .commit();
        } else if (gppKey == 3) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.contentContainer, GrpBuraphaFragment.newInstance())
                    .commit();
        } else if (gppKey == 4) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.contentContainer, GdpFragment.newInstance())
                    .commit();
        } else if (gppKey == 5) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.contentContainer, GdpUserFragment.newInstance())
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
