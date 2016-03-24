package fluke.projectsips.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Configuration;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.crashlytics.android.Crashlytics;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import fluke.projectsips.R;
import fluke.projectsips.adapter.MyExpandableAdapter;
import fluke.projectsips.fragment.CategoryCpiFragment;
import fluke.projectsips.fragment.ComingFragment;
import fluke.projectsips.fragment.CpiFragment;
import fluke.projectsips.fragment.EconomicMonthFragment;
import fluke.projectsips.fragment.GppFragment;
import fluke.projectsips.fragment.LfpFragment;
import fluke.projectsips.fragment.MainFragment;
import fluke.projectsips.fragment.PeopleFragment;
import io.fabric.sdk.android.Fabric;


public class MainActivity extends AppCompatActivity {

    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    Toolbar toolBar;

    // declare the variable needed in activity
    MyExpandableAdapter expandableAdapter;
    ExpandableListView expandableListView;
    List<String> headers;
    HashMap<String, List<String>> headerItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fabric.with(this, new Crashlytics());
        setContentView(R.layout.activity_main);

        initInstances();
        expandableListView();
        checkInternet();

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.contentContainer, MainFragment.newInstance())
                    .commit();
        }
    }

    private void checkInternet() {
        ConnectivityManager connMgr = (ConnectivityManager) this
                .getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected()) {
            // fetch data

        } else {
            // display error
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
            alertDialogBuilder.setTitle("No Internet Connection");
            alertDialogBuilder.setMessage("You are offline please check your internet connection");
            alertDialogBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface arg0, int arg1) {
                    //Toast.makeText(MainActivity.this,"No Internet Connection",Toast.LENGTH_LONG).show();
                    finish();
                }
            });

            AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.show();
        }
    }

    private void expandableListView() {

        //get expandable listview
        expandableListView = (ExpandableListView) findViewById(R.id.expandList);

        //get data to be set to list
        prepareDummyData();

        // get expandable list adapter
        expandableAdapter = new MyExpandableAdapter(this, headers, headerItems);

        //set list adapter to list
        expandableListView.setAdapter(expandableAdapter);

        //handling the header items click
        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                Fragment fragment = getSupportFragmentManager()
                        .findFragmentById(R.id.contentContainer);

                Toast.makeText(MainActivity.this, headers.get(groupPosition) + "--" + headerItems.get(headers.get(groupPosition)).get(childPosition), Toast.LENGTH_SHORT).show();
                switch (groupPosition) {
                    case 0:
                        switch (childPosition) {
                            case 6:
                                if (getSupportFragmentManager().getBackStackEntryCount() > 0)
                                    getSupportFragmentManager().popBackStack();
                                if (fragment instanceof PeopleFragment == false) {
                                    getSupportFragmentManager().beginTransaction()
                                            .replace(R.id.contentContainer, PeopleFragment.newInstance())
                                            .addToBackStack(null)
                                            .commit();
                                    drawerLayout.closeDrawer(Gravity.LEFT);
                                }
                                break;
                        }
                        break;
                    case 1:
                        switch (childPosition) {
                            case 0:
                                if (getSupportFragmentManager().getBackStackEntryCount() > 0)
                                    getSupportFragmentManager().popBackStack();
                                if (fragment instanceof CpiFragment == false) {
                                    getSupportFragmentManager().beginTransaction()
                                            .replace(R.id.contentContainer, CpiFragment.newInstance())
                                            .addToBackStack(null)
                                            .commit();
                                    drawerLayout.closeDrawer(Gravity.LEFT);
                                }
                                break;
                            case 1:
                                if (getSupportFragmentManager().getBackStackEntryCount() > 0)
                                    getSupportFragmentManager().popBackStack();
                                if (fragment instanceof CategoryCpiFragment == false) {
                                    getSupportFragmentManager().beginTransaction()
                                            .replace(R.id.contentContainer, CategoryCpiFragment.newInstance())
                                            .addToBackStack(null)
                                            .commit();
                                    drawerLayout.closeDrawer(Gravity.LEFT);
                                }
                                break;
                            case 2:
                                if (getSupportFragmentManager().getBackStackEntryCount() > 0)
                                    getSupportFragmentManager().popBackStack();
                                if (fragment instanceof GppFragment == false) {
                                    getSupportFragmentManager().beginTransaction()
                                            .replace(R.id.contentContainer, GppFragment.newInstance())
                                            .addToBackStack(null)
                                            .commit();
                                    drawerLayout.closeDrawer(Gravity.LEFT);
                                }
                                break;
                            case 3:
                                if (getSupportFragmentManager().getBackStackEntryCount() > 0)
                                    getSupportFragmentManager().popBackStack();
                                if (fragment instanceof LfpFragment == false) {
                                    getSupportFragmentManager().beginTransaction()
                                            .replace(R.id.contentContainer, LfpFragment.newInstance())
                                            .addToBackStack(null)
                                            .commit();
                                    drawerLayout.closeDrawer(Gravity.LEFT);
                                }
                                break;
                            case 4:
                                if (getSupportFragmentManager().getBackStackEntryCount() > 0)
                                    getSupportFragmentManager().popBackStack();
                                if (fragment instanceof EconomicMonthFragment == false) {
                                    getSupportFragmentManager().beginTransaction()
                                            .replace(R.id.contentContainer, EconomicMonthFragment.newInstance())
                                            .addToBackStack(null)
                                            .commit();
                                    drawerLayout.closeDrawer(Gravity.LEFT);
                                }
                                break;
                            case 5:
                                if (getSupportFragmentManager().getBackStackEntryCount() > 0)
                                    getSupportFragmentManager().popBackStack();
                                if (fragment instanceof ComingFragment == false) {
                                    getSupportFragmentManager().beginTransaction()
                                            .replace(R.id.contentContainer, ComingFragment.newInstance())
                                            .addToBackStack(null)
                                            .commit();
                                    drawerLayout.closeDrawer(Gravity.LEFT);
                                }
                                check(fragment);
                                break;
                        }
                        break;
                    case 2:
                        switch (childPosition) {
                            case 0:
                                if (getSupportFragmentManager().getBackStackEntryCount() > 0)
                                    getSupportFragmentManager().popBackStack();
                                if (fragment instanceof ComingFragment == false) {
                                    getSupportFragmentManager().beginTransaction()
                                            .replace(R.id.contentContainer, ComingFragment.newInstance())
                                            .addToBackStack(null)
                                            .commit();
                                    drawerLayout.closeDrawer(Gravity.LEFT);
                                }
                                check(fragment);
                                break;
                            case 1:
                                if (getSupportFragmentManager().getBackStackEntryCount() > 0)
                                    getSupportFragmentManager().popBackStack();
                                if (fragment instanceof ComingFragment == false) {
                                    getSupportFragmentManager().beginTransaction()
                                            .replace(R.id.contentContainer, ComingFragment.newInstance())
                                            .addToBackStack(null)
                                            .commit();
                                    drawerLayout.closeDrawer(Gravity.LEFT);
                                }
                                check(fragment);
                                break;
                            case 2:
                                if (getSupportFragmentManager().getBackStackEntryCount() > 0)
                                    getSupportFragmentManager().popBackStack();
                                if (fragment instanceof ComingFragment == false) {
                                    getSupportFragmentManager().beginTransaction()
                                            .replace(R.id.contentContainer, ComingFragment.newInstance())
                                            .addToBackStack(null)
                                            .commit();
                                    drawerLayout.closeDrawer(Gravity.LEFT);
                                }
                                check(fragment);
                                break;
                            case 3:
                                if (getSupportFragmentManager().getBackStackEntryCount() > 0)
                                    getSupportFragmentManager().popBackStack();
                                if (fragment instanceof ComingFragment == false) {
                                    getSupportFragmentManager().beginTransaction()
                                            .replace(R.id.contentContainer, ComingFragment.newInstance())
                                            .addToBackStack(null)
                                            .commit();
                                    drawerLayout.closeDrawer(Gravity.LEFT);
                                }
                                check(fragment);
                                break;
                        }
                        break;
                    case 3:
                        switch (childPosition) {
                            case 0:
                                if (getSupportFragmentManager().getBackStackEntryCount() > 0)
                                    getSupportFragmentManager().popBackStack();
                                if (fragment instanceof ComingFragment == false) {
                                    getSupportFragmentManager().beginTransaction()
                                            .replace(R.id.contentContainer, ComingFragment.newInstance())
                                            .addToBackStack(null)
                                            .commit();
                                    drawerLayout.closeDrawer(Gravity.LEFT);
                                }
                                check(fragment);
                                break;
                            case 1:
                                if (getSupportFragmentManager().getBackStackEntryCount() > 0)
                                    getSupportFragmentManager().popBackStack();
                                if (fragment instanceof ComingFragment == false) {
                                    getSupportFragmentManager().beginTransaction()
                                            .replace(R.id.contentContainer, ComingFragment.newInstance())
                                            .addToBackStack(null)
                                            .commit();
                                    drawerLayout.closeDrawer(Gravity.LEFT);
                                }
                                check(fragment);
                                break;
                            case 2:
                                if (getSupportFragmentManager().getBackStackEntryCount() > 0)
                                    getSupportFragmentManager().popBackStack();
                                if (fragment instanceof ComingFragment == false) {
                                    getSupportFragmentManager().beginTransaction()
                                            .replace(R.id.contentContainer, ComingFragment.newInstance())
                                            .addToBackStack(null)
                                            .commit();
                                    drawerLayout.closeDrawer(Gravity.LEFT);
                                }
                                check(fragment);
                                break;
                            case 3:
                                if (getSupportFragmentManager().getBackStackEntryCount() > 0)
                                    getSupportFragmentManager().popBackStack();
                                if (fragment instanceof ComingFragment == false) {
                                    getSupportFragmentManager().beginTransaction()
                                            .replace(R.id.contentContainer, ComingFragment.newInstance())
                                            .addToBackStack(null)
                                            .commit();
                                    drawerLayout.closeDrawer(Gravity.LEFT);
                                }
                                check(fragment);
                                break;
                            case 4:
                                if (getSupportFragmentManager().getBackStackEntryCount() > 0)
                                    getSupportFragmentManager().popBackStack();
                                if (fragment instanceof ComingFragment == false) {
                                    getSupportFragmentManager().beginTransaction()
                                            .replace(R.id.contentContainer, ComingFragment.newInstance())
                                            .addToBackStack(null)
                                            .commit();
                                    drawerLayout.closeDrawer(Gravity.LEFT);
                                }
                                check(fragment);
                                break;
                            case 5:
                                if (getSupportFragmentManager().getBackStackEntryCount() > 0)
                                    getSupportFragmentManager().popBackStack();
                                if (fragment instanceof ComingFragment == false) {
                                    getSupportFragmentManager().beginTransaction()
                                            .replace(R.id.contentContainer, ComingFragment.newInstance())
                                            .addToBackStack(null)
                                            .commit();
                                    drawerLayout.closeDrawer(Gravity.LEFT);
                                }
                                check(fragment);
                                break;
                            case 6:
                                if (getSupportFragmentManager().getBackStackEntryCount() > 0)
                                    getSupportFragmentManager().popBackStack();
                                if (fragment instanceof ComingFragment == false) {
                                    getSupportFragmentManager().beginTransaction()
                                            .replace(R.id.contentContainer, ComingFragment.newInstance())
                                            .addToBackStack(null)
                                            .commit();
                                    drawerLayout.closeDrawer(Gravity.LEFT);
                                }
                                check(fragment);
                                break;
                            case 7:
                                if (getSupportFragmentManager().getBackStackEntryCount() > 0)
                                    getSupportFragmentManager().popBackStack();
                                if (fragment instanceof ComingFragment == false) {
                                    getSupportFragmentManager().beginTransaction()
                                            .replace(R.id.contentContainer, ComingFragment.newInstance())
                                            .addToBackStack(null)
                                            .commit();
                                    drawerLayout.closeDrawer(Gravity.LEFT);
                                }
                                check(fragment);
                                break;
                            case 8:
                                if (getSupportFragmentManager().getBackStackEntryCount() > 0)
                                    getSupportFragmentManager().popBackStack();
                                if (fragment instanceof ComingFragment == false) {
                                    getSupportFragmentManager().beginTransaction()
                                            .replace(R.id.contentContainer, ComingFragment.newInstance())
                                            .addToBackStack(null)
                                            .commit();
                                    drawerLayout.closeDrawer(Gravity.LEFT);
                                }
                                check(fragment);
                                break;
                            case 9:
                                if (getSupportFragmentManager().getBackStackEntryCount() > 0)
                                    getSupportFragmentManager().popBackStack();
                                if (fragment instanceof ComingFragment == false) {
                                    getSupportFragmentManager().beginTransaction()
                                            .replace(R.id.contentContainer, ComingFragment.newInstance())
                                            .addToBackStack(null)
                                            .commit();
                                    drawerLayout.closeDrawer(Gravity.LEFT);
                                }
                                check(fragment);
                                break;
                        }
                        break;
                    case 4:
                        switch (childPosition) {
                            case 0:
                                if (getSupportFragmentManager().getBackStackEntryCount() > 0)
                                    getSupportFragmentManager().popBackStack();
                                if (fragment instanceof ComingFragment == false) {
                                    getSupportFragmentManager().beginTransaction()
                                            .replace(R.id.contentContainer, ComingFragment.newInstance())
                                            .addToBackStack(null)
                                            .commit();
                                    drawerLayout.closeDrawer(Gravity.LEFT);
                                }
                                check(fragment);
                                break;
                            case 1:
                                if (getSupportFragmentManager().getBackStackEntryCount() > 0)
                                    getSupportFragmentManager().popBackStack();
                                if (fragment instanceof ComingFragment == false) {
                                    getSupportFragmentManager().beginTransaction()
                                            .replace(R.id.contentContainer, ComingFragment.newInstance())
                                            .addToBackStack(null)
                                            .commit();
                                    drawerLayout.closeDrawer(Gravity.LEFT);
                                }
                                check(fragment);
                                break;
                            case 2:
                                if (getSupportFragmentManager().getBackStackEntryCount() > 0)
                                    getSupportFragmentManager().popBackStack();
                                if (fragment instanceof ComingFragment == false) {
                                    getSupportFragmentManager().beginTransaction()
                                            .replace(R.id.contentContainer, ComingFragment.newInstance())
                                            .addToBackStack(null)
                                            .commit();
                                    drawerLayout.closeDrawer(Gravity.LEFT);
                                }check(fragment);
                                break;
                            case 3:
                                if (getSupportFragmentManager().getBackStackEntryCount() > 0)
                                    getSupportFragmentManager().popBackStack();
                                if (fragment instanceof ComingFragment == false) {
                                    getSupportFragmentManager().beginTransaction()
                                            .replace(R.id.contentContainer, ComingFragment.newInstance())
                                            .addToBackStack(null)
                                            .commit();
                                    drawerLayout.closeDrawer(Gravity.LEFT);
                                }check(fragment);
                                break;
                            case 4:
                                if (getSupportFragmentManager().getBackStackEntryCount() > 0)
                                    getSupportFragmentManager().popBackStack();
                                if (fragment instanceof ComingFragment == false) {
                                    getSupportFragmentManager().beginTransaction()
                                            .replace(R.id.contentContainer, ComingFragment.newInstance())
                                            .addToBackStack(null)
                                            .commit();
                                    drawerLayout.closeDrawer(Gravity.LEFT);
                                }
                                check(fragment);
                                break;
                            case 5:
                                if (getSupportFragmentManager().getBackStackEntryCount() > 0)
                                    getSupportFragmentManager().popBackStack();
                                if (fragment instanceof ComingFragment == false) {
                                    getSupportFragmentManager().beginTransaction()
                                            .replace(R.id.contentContainer, ComingFragment.newInstance())
                                            .addToBackStack(null)
                                            .commit();
                                    drawerLayout.closeDrawer(Gravity.LEFT);
                                }
                                check(fragment);
                                break;
                            case 6:
                                if (getSupportFragmentManager().getBackStackEntryCount() > 0)
                                    getSupportFragmentManager().popBackStack();
                                if (fragment instanceof ComingFragment == false) {
                                    getSupportFragmentManager().beginTransaction()
                                            .replace(R.id.contentContainer, ComingFragment.newInstance())
                                            .addToBackStack(null)
                                            .commit();
                                    drawerLayout.closeDrawer(Gravity.LEFT);
                                }
                                check(fragment);
                                break;
                            case 7:
                                if (getSupportFragmentManager().getBackStackEntryCount() > 0)
                                    getSupportFragmentManager().popBackStack();
                                if (fragment instanceof ComingFragment == false) {
                                    getSupportFragmentManager().beginTransaction()
                                            .replace(R.id.contentContainer, ComingFragment.newInstance())
                                            .addToBackStack(null)
                                            .commit();
                                    drawerLayout.closeDrawer(Gravity.LEFT);
                                }
                                check(fragment);
                                break;
                            case 8:
                                if (getSupportFragmentManager().getBackStackEntryCount() > 0)
                                    getSupportFragmentManager().popBackStack();
                                if (fragment instanceof ComingFragment == false) {
                                    getSupportFragmentManager().beginTransaction()
                                            .replace(R.id.contentContainer, ComingFragment.newInstance())
                                            .addToBackStack(null)
                                            .commit();
                                    drawerLayout.closeDrawer(Gravity.LEFT);
                                }
                                check(fragment);
                                break;
                        }
                        break;
                    case 5:
                        switch (childPosition) {
                            case 0:
                                if (getSupportFragmentManager().getBackStackEntryCount() > 0)
                                    getSupportFragmentManager().popBackStack();
                                if (fragment instanceof ComingFragment == false) {
                                    getSupportFragmentManager().beginTransaction()
                                            .replace(R.id.contentContainer, ComingFragment.newInstance())
                                            .addToBackStack(null)
                                            .commit();
                                    drawerLayout.closeDrawer(Gravity.LEFT);
                                }
                                check(fragment);
                                break;
                            case 1:
                                if (getSupportFragmentManager().getBackStackEntryCount() > 0)
                                    getSupportFragmentManager().popBackStack();
                                if (fragment instanceof ComingFragment == false) {
                                    getSupportFragmentManager().beginTransaction()
                                            .replace(R.id.contentContainer, ComingFragment.newInstance())
                                            .addToBackStack(null)
                                            .commit();
                                    drawerLayout.closeDrawer(Gravity.LEFT);
                                }
                                check(fragment);
                                break;
                            case 2:
                                if (getSupportFragmentManager().getBackStackEntryCount() > 0)
                                    getSupportFragmentManager().popBackStack();
                                if (fragment instanceof ComingFragment == false) {
                                    getSupportFragmentManager().beginTransaction()
                                            .replace(R.id.contentContainer, ComingFragment.newInstance())
                                            .addToBackStack(null)
                                            .commit();
                                    drawerLayout.closeDrawer(Gravity.LEFT);
                                }
                                check(fragment);
                                break;
                            case 3:
                                if (getSupportFragmentManager().getBackStackEntryCount() > 0)
                                    getSupportFragmentManager().popBackStack();
                                if (fragment instanceof ComingFragment == false) {
                                    getSupportFragmentManager().beginTransaction()
                                            .replace(R.id.contentContainer, ComingFragment.newInstance())
                                            .addToBackStack(null)
                                            .commit();
                                    drawerLayout.closeDrawer(Gravity.LEFT);
                                }
                                check(fragment);
                                break;
                            case 4:
                                if (getSupportFragmentManager().getBackStackEntryCount() > 0)
                                    getSupportFragmentManager().popBackStack();
                                if (fragment instanceof ComingFragment == false) {
                                    getSupportFragmentManager().beginTransaction()
                                            .replace(R.id.contentContainer, ComingFragment.newInstance())
                                            .addToBackStack(null)
                                            .commit();
                                    drawerLayout.closeDrawer(Gravity.LEFT);
                                }
                                check(fragment);
                                break;
                            case 5:
                                if (getSupportFragmentManager().getBackStackEntryCount() > 0)
                                    getSupportFragmentManager().popBackStack();
                                if (fragment instanceof ComingFragment == false) {
                                    getSupportFragmentManager().beginTransaction()
                                            .replace(R.id.contentContainer, ComingFragment.newInstance())
                                            .addToBackStack(null)
                                            .commit();
                                    drawerLayout.closeDrawer(Gravity.LEFT);
                                }
                                check(fragment);
                                break;
                            case 6:
                                if (getSupportFragmentManager().getBackStackEntryCount() > 0)
                                    getSupportFragmentManager().popBackStack();
                                if (fragment instanceof ComingFragment == false) {
                                    getSupportFragmentManager().beginTransaction()
                                            .replace(R.id.contentContainer, ComingFragment.newInstance())
                                            .addToBackStack(null)
                                            .commit();
                                    drawerLayout.closeDrawer(Gravity.LEFT);
                                }
                                check(fragment);
                                break;
                            case 7:
                                if (getSupportFragmentManager().getBackStackEntryCount() > 0)
                                    getSupportFragmentManager().popBackStack();
                                if (fragment instanceof ComingFragment == false) {
                                    getSupportFragmentManager().beginTransaction()
                                            .replace(R.id.contentContainer, ComingFragment.newInstance())
                                            .addToBackStack(null)
                                            .commit();
                                    drawerLayout.closeDrawer(Gravity.LEFT);
                                }
                                check(fragment);
                                break;
                            case 8:
                                if (getSupportFragmentManager().getBackStackEntryCount() > 0)
                                    getSupportFragmentManager().popBackStack();
                                if (fragment instanceof ComingFragment == false) {
                                    getSupportFragmentManager().beginTransaction()
                                            .replace(R.id.contentContainer, ComingFragment.newInstance())
                                            .addToBackStack(null)
                                            .commit();
                                    drawerLayout.closeDrawer(Gravity.LEFT);
                                }
                                check(fragment);
                                break;
                            case 9:
                                if (getSupportFragmentManager().getBackStackEntryCount() > 0)
                                    getSupportFragmentManager().popBackStack();
                                if (fragment instanceof ComingFragment == false) {
                                    getSupportFragmentManager().beginTransaction()
                                            .replace(R.id.contentContainer, ComingFragment.newInstance())
                                            .addToBackStack(null)
                                            .commit();
                                    drawerLayout.closeDrawer(Gravity.LEFT);
                                }
                                check(fragment);
                                break;
                        }
                        break;
                    case 6:
                        switch (childPosition) {
                            case 0:
                                if (getSupportFragmentManager().getBackStackEntryCount() > 0)
                                    getSupportFragmentManager().popBackStack();
                                if (fragment instanceof ComingFragment == false) {
                                    getSupportFragmentManager().beginTransaction()
                                            .replace(R.id.contentContainer, ComingFragment.newInstance())
                                            .addToBackStack(null)
                                            .commit();
                                    drawerLayout.closeDrawer(Gravity.LEFT);
                                }
                                check(fragment);
                                break;
                            case 1:
                                if (getSupportFragmentManager().getBackStackEntryCount() > 0)
                                    getSupportFragmentManager().popBackStack();
                                if (fragment instanceof ComingFragment == false) {
                                    getSupportFragmentManager().beginTransaction()
                                            .replace(R.id.contentContainer, ComingFragment.newInstance())
                                            .addToBackStack(null)
                                            .commit();
                                    drawerLayout.closeDrawer(Gravity.LEFT);
                                }
                                check(fragment);
                                break;
                            case 2:
                                if (getSupportFragmentManager().getBackStackEntryCount() > 0)
                                    getSupportFragmentManager().popBackStack();
                                if (fragment instanceof ComingFragment == false) {
                                    getSupportFragmentManager().beginTransaction()
                                            .replace(R.id.contentContainer, ComingFragment.newInstance())
                                            .addToBackStack(null)
                                            .commit();
                                    drawerLayout.closeDrawer(Gravity.LEFT);
                                }
                                check(fragment);
                                break;
                            case 3:
                                if (getSupportFragmentManager().getBackStackEntryCount() > 0)
                                    getSupportFragmentManager().popBackStack();
                                if (fragment instanceof ComingFragment == false) {
                                    getSupportFragmentManager().beginTransaction()
                                            .replace(R.id.contentContainer, ComingFragment.newInstance())
                                            .addToBackStack(null)
                                            .commit();
                                    drawerLayout.closeDrawer(Gravity.LEFT);
                                }
                                check(fragment);
                                break;
                            case 4:
                                if (getSupportFragmentManager().getBackStackEntryCount() > 0)
                                    getSupportFragmentManager().popBackStack();
                                if (fragment instanceof ComingFragment == false) {
                                    getSupportFragmentManager().beginTransaction()
                                            .replace(R.id.contentContainer, ComingFragment.newInstance())
                                            .addToBackStack(null)
                                            .commit();
                                    drawerLayout.closeDrawer(Gravity.LEFT);
                                }
                                check(fragment);
                                break;
                        }
                        break;
                    case 7:
                        switch (childPosition) {
                            case 0:
                                if (getSupportFragmentManager().getBackStackEntryCount() > 0)
                                    getSupportFragmentManager().popBackStack();
                                if (fragment instanceof ComingFragment == false) {
                                    getSupportFragmentManager().beginTransaction()
                                            .replace(R.id.contentContainer, ComingFragment.newInstance())
                                            .addToBackStack(null)
                                            .commit();
                                    drawerLayout.closeDrawer(Gravity.LEFT);
                                }
                                check(fragment);
                                break;
                            case 1:
                                if (getSupportFragmentManager().getBackStackEntryCount() > 0)
                                    getSupportFragmentManager().popBackStack();
                                if (fragment instanceof ComingFragment == false) {
                                    getSupportFragmentManager().beginTransaction()
                                            .replace(R.id.contentContainer, ComingFragment.newInstance())
                                            .addToBackStack(null)
                                            .commit();
                                    drawerLayout.closeDrawer(Gravity.LEFT);
                                }
                                check(fragment);
                                break;
                            case 2:
                                if (getSupportFragmentManager().getBackStackEntryCount() > 0)
                                    getSupportFragmentManager().popBackStack();
                                if (fragment instanceof ComingFragment == false) {
                                    getSupportFragmentManager().beginTransaction()
                                            .replace(R.id.contentContainer, ComingFragment.newInstance())
                                            .addToBackStack(null)
                                            .commit();
                                    drawerLayout.closeDrawer(Gravity.LEFT);
                                }
                                check(fragment);
                                break;
                            case 3:
                                if (getSupportFragmentManager().getBackStackEntryCount() > 0)
                                    getSupportFragmentManager().popBackStack();
                                if (fragment instanceof ComingFragment == false) {
                                    getSupportFragmentManager().beginTransaction()
                                            .replace(R.id.contentContainer, ComingFragment.newInstance())
                                            .addToBackStack(null)
                                            .commit();
                                    drawerLayout.closeDrawer(Gravity.LEFT);
                                }
                                check(fragment);
                                break;
                            case 4:
                                if (getSupportFragmentManager().getBackStackEntryCount() > 0)
                                    getSupportFragmentManager().popBackStack();
                                if (fragment instanceof ComingFragment == false) {
                                    getSupportFragmentManager().beginTransaction()
                                            .replace(R.id.contentContainer, ComingFragment.newInstance())
                                            .addToBackStack(null)
                                            .commit();
                                    drawerLayout.closeDrawer(Gravity.LEFT);
                                }
                                check(fragment);
                                break;
                            case 5:
                                if (getSupportFragmentManager().getBackStackEntryCount() > 0)
                                    getSupportFragmentManager().popBackStack();
                                if (fragment instanceof ComingFragment == false) {
                                    getSupportFragmentManager().beginTransaction()
                                            .replace(R.id.contentContainer, ComingFragment.newInstance())
                                            .addToBackStack(null)
                                            .commit();
                                    drawerLayout.closeDrawer(Gravity.LEFT);
                                }
                                check(fragment);
                                break;
                            case 6:
                                if (getSupportFragmentManager().getBackStackEntryCount() > 0)
                                    getSupportFragmentManager().popBackStack();
                                if (fragment instanceof ComingFragment == false) {
                                    getSupportFragmentManager().beginTransaction()
                                            .replace(R.id.contentContainer, ComingFragment.newInstance())
                                            .addToBackStack(null)
                                            .commit();
                                    drawerLayout.closeDrawer(Gravity.LEFT);
                                }
                                check(fragment);
                                break;
                        }
                        break;
                    case 8:
                        switch (childPosition) {
                            case 0:
                                if (getSupportFragmentManager().getBackStackEntryCount() > 0)
                                    getSupportFragmentManager().popBackStack();
                                if (fragment instanceof ComingFragment == false) {
                                    getSupportFragmentManager().beginTransaction()
                                            .replace(R.id.contentContainer, ComingFragment.newInstance())
                                            .addToBackStack(null)
                                            .commit();
                                    drawerLayout.closeDrawer(Gravity.LEFT);
                                }
                                check(fragment);
                                break;
                            case 1:
                                if (getSupportFragmentManager().getBackStackEntryCount() > 0)
                                    getSupportFragmentManager().popBackStack();
                                if (fragment instanceof ComingFragment == false) {
                                    getSupportFragmentManager().beginTransaction()
                                            .replace(R.id.contentContainer, ComingFragment.newInstance())
                                            .addToBackStack(null)
                                            .commit();
                                    drawerLayout.closeDrawer(Gravity.LEFT);
                                }
                                check(fragment);
                                break;
                            case 2:
                                if (getSupportFragmentManager().getBackStackEntryCount() > 0)
                                    getSupportFragmentManager().popBackStack();
                                if (fragment instanceof ComingFragment == false) {
                                    getSupportFragmentManager().beginTransaction()
                                            .replace(R.id.contentContainer, ComingFragment.newInstance())
                                            .addToBackStack(null)
                                            .commit();
                                    drawerLayout.closeDrawer(Gravity.LEFT);
                                }
                                check(fragment);
                                break;
                            case 3:
                                if (getSupportFragmentManager().getBackStackEntryCount() > 0)
                                    getSupportFragmentManager().popBackStack();
                                if (fragment instanceof ComingFragment == false) {
                                    getSupportFragmentManager().beginTransaction()
                                            .replace(R.id.contentContainer, ComingFragment.newInstance())
                                            .addToBackStack(null)
                                            .commit();
                                    drawerLayout.closeDrawer(Gravity.LEFT);
                                }
                                check(fragment);
                                break;
                            case 4:
                                if (getSupportFragmentManager().getBackStackEntryCount() > 0)
                                    getSupportFragmentManager().popBackStack();
                                if (fragment instanceof ComingFragment == false) {
                                    getSupportFragmentManager().beginTransaction()
                                            .replace(R.id.contentContainer, ComingFragment.newInstance())
                                            .addToBackStack(null)
                                            .commit();
                                    drawerLayout.closeDrawer(Gravity.LEFT);
                                }
                                check(fragment);
                                break;
                            case 5:
                                if (getSupportFragmentManager().getBackStackEntryCount() > 0)
                                    getSupportFragmentManager().popBackStack();
                                if (fragment instanceof ComingFragment == false) {
                                    getSupportFragmentManager().beginTransaction()
                                            .replace(R.id.contentContainer, ComingFragment.newInstance())
                                            .addToBackStack(null)
                                            .commit();
                                    drawerLayout.closeDrawer(Gravity.LEFT);
                                }
                                check(fragment);
                                break;
                        }
                        break;
                    case 9:
                        switch (childPosition) {
                            case 0:
                                if (getSupportFragmentManager().getBackStackEntryCount() > 0)
                                    getSupportFragmentManager().popBackStack();
                                if (fragment instanceof ComingFragment == false) {
                                    getSupportFragmentManager().beginTransaction()
                                            .replace(R.id.contentContainer, ComingFragment.newInstance())
                                            .addToBackStack(null)
                                            .commit();
                                    drawerLayout.closeDrawer(Gravity.LEFT);
                                }
                                check(fragment);
                                break;
                            case 1:
                                if (getSupportFragmentManager().getBackStackEntryCount() > 0)
                                    getSupportFragmentManager().popBackStack();
                                if (fragment instanceof ComingFragment == false) {
                                    getSupportFragmentManager().beginTransaction()
                                            .replace(R.id.contentContainer, ComingFragment.newInstance())
                                            .addToBackStack(null)
                                            .commit();
                                    drawerLayout.closeDrawer(Gravity.LEFT);
                                }
                                check(fragment);
                                break;
                            case 2:
                                if (getSupportFragmentManager().getBackStackEntryCount() > 0)
                                    getSupportFragmentManager().popBackStack();
                                if (fragment instanceof ComingFragment == false) {
                                    getSupportFragmentManager().beginTransaction()
                                            .replace(R.id.contentContainer, ComingFragment.newInstance())
                                            .addToBackStack(null)
                                            .commit();
                                    drawerLayout.closeDrawer(Gravity.LEFT);
                                }
                                check(fragment);
                                break;
                            case 3:
                                if (getSupportFragmentManager().getBackStackEntryCount() > 0)
                                    getSupportFragmentManager().popBackStack();
                                if (fragment instanceof ComingFragment == false) {
                                    getSupportFragmentManager().beginTransaction()
                                            .replace(R.id.contentContainer, ComingFragment.newInstance())
                                            .addToBackStack(null)
                                            .commit();
                                    drawerLayout.closeDrawer(Gravity.LEFT);
                                }
                                check(fragment);
                                break;
                            case 4:
                                if (getSupportFragmentManager().getBackStackEntryCount() > 0)
                                    getSupportFragmentManager().popBackStack();
                                if (fragment instanceof ComingFragment == false) {
                                    getSupportFragmentManager().beginTransaction()
                                            .replace(R.id.contentContainer, ComingFragment.newInstance())
                                            .addToBackStack(null)
                                            .commit();
                                    drawerLayout.closeDrawer(Gravity.LEFT);
                                }
                                check(fragment);
                                break;
                            case 5:
                                if (getSupportFragmentManager().getBackStackEntryCount() > 0)
                                    getSupportFragmentManager().popBackStack();
                                if (fragment instanceof ComingFragment == false) {
                                    getSupportFragmentManager().beginTransaction()
                                            .replace(R.id.contentContainer, ComingFragment.newInstance())
                                            .addToBackStack(null)
                                            .commit();
                                    drawerLayout.closeDrawer(Gravity.LEFT);
                                }
                                check(fragment);
                                break;
                        }
                        break;
                    case 10:
                        switch (childPosition) {
                            case 0:
                                if (getSupportFragmentManager().getBackStackEntryCount() > 0)
                                    getSupportFragmentManager().popBackStack();
                                if (fragment instanceof ComingFragment == false) {
                                    getSupportFragmentManager().beginTransaction()
                                            .replace(R.id.contentContainer, ComingFragment.newInstance())
                                            .addToBackStack(null)
                                            .commit();
                                    drawerLayout.closeDrawer(Gravity.LEFT);
                                }
                                check(fragment);
                                break;
                            case 1:
                                if (getSupportFragmentManager().getBackStackEntryCount() > 0)
                                    getSupportFragmentManager().popBackStack();
                                if (fragment instanceof ComingFragment == false) {
                                    getSupportFragmentManager().beginTransaction()
                                            .replace(R.id.contentContainer, ComingFragment.newInstance())
                                            .addToBackStack(null)
                                            .commit();
                                    drawerLayout.closeDrawer(Gravity.LEFT);
                                }
                                check(fragment);
                                break;
                        }
                        break;
                }
                return false;
            }
        });
        //get the expand of headers
        expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int groupPosition) {
                Toast.makeText(MainActivity.this, headers.get(groupPosition), Toast.LENGTH_SHORT).show();
            }
        });
        //get the collapse of headers
        expandableListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {
            @Override
            public void onGroupCollapse(int groupPosition) {
                Toast.makeText(MainActivity.this, headers.get(groupPosition), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void check(Fragment fragment) {
        if (fragment instanceof ComingFragment == true) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.contentContainer, ComingFragment.newInstance())
                    .addToBackStack(null)
                    .commit();
            drawerLayout.closeDrawer(Gravity.LEFT);
        }
    }

    public void prepareDummyData() {
        headers = new ArrayList<String>();
        headerItems = new HashMap<String, List<String>>();

        //adding headers
        //adding headers
        headers.add("ข้อมูลทั่วไป");
        headers.add("ภาวะเศรษฐกิจการค้า");
        headers.add("การลงทุน");
        headers.add("การค้าชายแดน");
        headers.add("OTOP/SMEs");
        headers.add("การเกษตร");
        headers.add("อุตสาหกรรม");
        headers.add("การท่องเที่ยว");
        headers.add("โครงสร้างพื้นฐานด้านการขนส่ง");
        headers.add("ประชาคมอาเซียน");
        headers.add("พืชพลังงาน");

        //preparing header items data
        List<String> information = new ArrayList<String>();
        information.add("ข้อมูลพื้นฐาน");
        information.add("วิสัยทัศน์และยุทธศาสตร์");
        information.add("ที่ตั้งและอาณาเขตติดต่อ");
        information.add("สภาพภูมิประเทศ");
        information.add("สภาพภูมิอากาศ");
        information.add("ข้อมูลเขตการปกครอง");
        information.add("ข้อมูลประชากร");

        List<String> economicTrade = new ArrayList<String>();
        economicTrade.add("ดัชนีราคาผู้บริโภค");
        economicTrade.add("ดัชนีราคาผู้บริโภค จำแนกตามหมวดหมู่");
        economicTrade.add("ผลิตภัณฑ์มวลรวมจังหวัด");
        economicTrade.add("ภาวะการทำงานของประชากร");
        economicTrade.add("ภาวะเศรษฐกิจรายเดือน");
        economicTrade.add("บทวิเคราะห์");

        List<String> investment = new ArrayList<String>();
        investment.add("งบประมาณโครงการตามแผนพัฒนาจังหวัด");
        investment.add("จำนวนโครงการและงบประมาณ");
        investment.add("การลงทุนนิติบุคคล");
        investment.add("บทวิเคราะห์");

        List<String> borderTrade = new ArrayList<String>();
        borderTrade.add("สถิติปริมาณใบขนสินค้า และมูลค่าสินค้านำเข้า - ส่งออก");
        borderTrade.add("การเปรียบเทียบสถิติมูลค่ารวมสินค้านำเข้า-ส่งออก");
        borderTrade.add("รายงานอันดับสินค้านำเข้า-ส่งออก");
        borderTrade.add("การจัดเก็บรายได้");
        borderTrade.add("สถิติบุคคลเข้าออก ราชอาณาจักรไทย-กัมพูชา");
        borderTrade.add("สถิติยานพาหนะเข้าออก ราชอาณาจักรไทย-กัมพูชา");
        borderTrade.add("ผู้ประกอบการการค้าชายแดน");
        borderTrade.add("จุดผ่านแดนบริเวณชายแดนจังหวัดสระแก้ว");
        borderTrade.add("กฎ ระเบียบ แนวทางวิธีปฏิบัติต่างๆ");
        borderTrade.add("บทวิเคราะห์");

        List<String> otop = new ArrayList<String>();
        otop.add("ความเป็นมา");
        otop.add("แนวคิดและหลักการ");
        otop.add("การขับเคลื่อน OTOP");
        otop.add("ผู้ประกอบการ OTOP/SMEs");
        otop.add("ข้อมูลสินค้า OTOP/SMEs");
        otop.add("ยอดจำหน่ายสินค้า OTOP/SMEs");
        otop.add("ปฏิทินการจัดจำหน่ายสินค้า");
        otop.add("ความต้องการช่วยเหลือ");
        otop.add("บทวิเคราะห์");

        List<String> agriculture = new ArrayList<String>();
        agriculture.add("ระบบการผลิต");
        agriculture.add("ศักยภาพการผลิต");
        agriculture.add("มูลค่ารวมภาคเกษตร");
        agriculture.add("สถิติแสดงจำนวนพื้นที่เพาะปลูกผลิตภัณฑ์ทางการเกษตร");
        agriculture.add("สถิติการนำเข้า-ส่งออกสินค้าเกษตร");
        agriculture.add("พื้นที่ทางการเกษตร");
        agriculture.add("รายชื่อเกษตรกร");
        agriculture.add("ที่ตั้งของสถานที่เลี้ยงสัตว์");
        agriculture.add("ทะเบียนฟาร์ม");
        agriculture.add("บทวิเคราะห์");

        List<String> industry = new ArrayList<String>();
        industry.add("ข้อมูลสถานประกอบการอุตสาหกรรม จำแนกตามกลุ่มอุตสาหกรรม");
        industry.add("ข้อมูลสถานประกอบการอุตสาหกรรม จำแนกตามมาตรฐานอุตสาหกรรม");
        industry.add("การลงทุนอุตสาหกรรม");
        industry.add("ข้อมูลการส่งเสริมการลงทุน (BOI)");
        industry.add("บทวิเคราะห์");

        List<String> tourism = new ArrayList<String>();
        tourism.add("เส้นทางท่องเที่ยวในจังหวัดสระแก้ว");
        tourism.add("ปฏิทินเทศกาลท่องเที่ยว");
        tourism.add("ข้อมูลสถานที่ท่องเที่ยว/ที่พัก");
        tourism.add("สถิตินักท่องเที่ยวไทย-ต่างชาติ");
        tourism.add("สถิติค่าใช้จ่ายในการท่องเที่ยว");
        tourism.add("สถิติจำนวนห้องพักกับอัตราการเข้าพัก");
        tourism.add("บทวิเคราะห์");

        List<String> transport = new ArrayList<String>();
        transport.add("โครงข่ายการคมนาคมและการขนส่ง");
        transport.add("การพัฒนาโครงข่ายการคมนาคมและการขนส่ง");
        transport.add("การเดินทาง");
        transport.add("การอำนวยความสะดวก");
        transport.add("ไฟฟ้าและประปา");
        transport.add("บทวิเคราะห์");

        List<String> asean = new ArrayList<String>();
        asean.add("ความหมายของประชาคมอาเซียน");
        asean.add("ประชาคมการเมืองและความมั่นคงอาเซียน(APSC)");
        asean.add("ประชาคมเศรษฐกิจอาเซียน(AEC)");
        asean.add("ประชาคมสังคมและวัฒนธรรม(ASCC)");
        asean.add("ข้อมูลประเทศ สมาชิกในอาเซียน");
        asean.add("บทวิเคราะห์");

        List<String> powerPlant = new ArrayList<String>();
        powerPlant.add("มูลค่ารวมการเกษตร");
        powerPlant.add("พื้นที่การเกษตร");

        //contain header and header items
        headerItems.put(headers.get(0), information);
        headerItems.put(headers.get(1), economicTrade);
        headerItems.put(headers.get(2), investment);
        headerItems.put(headers.get(3), borderTrade);
        headerItems.put(headers.get(4), otop);
        headerItems.put(headers.get(5), agriculture);
        headerItems.put(headers.get(6), industry);
        headerItems.put(headers.get(7), tourism);
        headerItems.put(headers.get(8), transport);
        headerItems.put(headers.get(9), asean);
        headerItems.put(headers.get(10), powerPlant);
    }

    private void initInstances() {

        toolBar = (Toolbar) findViewById(R.id.toolBar);
        setSupportActionBar(toolBar);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);

        actionBarDrawerToggle = new ActionBarDrawerToggle(MainActivity.this, drawerLayout,
                toolBar, R.string.open_drawer, R.string.close_drawer);

        drawerLayout.addDrawerListener(actionBarDrawerToggle);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        actionBarDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        actionBarDrawerToggle.onConfigurationChanged(newConfig);
    }
}
