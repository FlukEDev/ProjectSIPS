package fluke.projectsips.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import fluke.projectsips.R;
import fluke.projectsips.fragment.gpp.GdpBuraphaGroupFragment;
import fluke.projectsips.fragment.gpp.GdpFragment;
import fluke.projectsips.fragment.gpp.GdpUserFragment;
import fluke.projectsips.fragment.gpp.GppProvinceFragment;
import fluke.projectsips.fragment.gpp.GppSakaeoFragment;
import fluke.projectsips.fragment.gpp.GrpFragment;
import fluke.projectsips.fragment.lfp.AgeEducationAndSexFragment;
import fluke.projectsips.fragment.lfp.EmployedEducationAndSexFragment;
import fluke.projectsips.fragment.lfp.EmployedHourWeekAndSexFragment;
import fluke.projectsips.fragment.lfp.EmployedIndustryAndSexFragment;
import fluke.projectsips.fragment.lfp.EmployedJobAndSexFragment;
import fluke.projectsips.fragment.lfp.EmployedWorkAndSexFragment;
import fluke.projectsips.fragment.lfp.LaborAndSexFragment;
import fluke.projectsips.fragment.lfp.UnemployedSexFragment;


public class SecondActivity extends AppCompatActivity {

    Toolbar toolBar;
    int lfpKey = 0;
    int gppKey = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        initInstances();

        Intent intent = getIntent();
        lfpKey = intent.getIntExtra("lfp", 0);
        gppKey = intent.getIntExtra("gpp", 0);

        if (lfpKey == 0) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.contentContainer, LaborAndSexFragment.newInstance())
                    .commit();
        } else if (lfpKey == 1) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.contentContainer, AgeEducationAndSexFragment.newInstance())
                    .commit();
        } else if (lfpKey == 2) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.contentContainer, EmployedEducationAndSexFragment.newInstance())
                    .commit();
        } else if (lfpKey == 3) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.contentContainer, EmployedJobAndSexFragment.newInstance())
                    .commit();
        } else if (lfpKey == 4) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.contentContainer, EmployedIndustryAndSexFragment.newInstance())
                    .commit();
        } else if (lfpKey == 5) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.contentContainer, EmployedWorkAndSexFragment.newInstance())
                    .commit();
        } else  if (lfpKey == 6) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.contentContainer, EmployedHourWeekAndSexFragment.newInstance())
                    .commit();
        } else if (lfpKey == 7) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.contentContainer, UnemployedSexFragment.newInstance())
                    .commit();
        }

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
                    .add(R.id.contentContainer, GdpBuraphaGroupFragment.newInstance())
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
