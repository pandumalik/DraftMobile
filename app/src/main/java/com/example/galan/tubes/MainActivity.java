package com.example.galan.tubes;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.galan.tubes.Upload_Download.Uploader;

import java.util.ArrayList;
import java.util.List;

import static com.example.galan.tubes.Login.SHARED_PREF_DATA;
import static com.example.galan.tubes.Login.USER_TYPE;
import static com.example.galan.tubes.Login.nlg;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    public static Activity fa;

    DrawerLayout mDrawerLayout;
    NavigationView mNavigationView;
    ViewPager viewPager;
    TabLayout tabs;
    Toolbar toolbar;
    FloatingActionButton fabs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setLogo(R.drawable.moeramhdpi);

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabs = (TabLayout) findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);

        fa = this;
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mNavigationView = (NavigationView) findViewById(R.id.nav_view);
        mNavigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();
        fabs = (FloatingActionButton) findViewById(R.id.fab);

        fabs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (USER_TYPE.equals("mahasiswa")) {
                    Snackbar.make(view, "KIRIM PESAN", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                } else if (USER_TYPE.equals("dosen")) {
                    Snackbar.make(view, "Dosen", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                    Intent upload = new Intent(MainActivity.this, Uploader.class);
                    startActivity(upload);
                } else {
                    fabs.setVisibility(View.GONE);
                }
            }
        });

    }

    private void setupViewPager(ViewPager viewPager) {
        Adapter adapter = new Adapter(getSupportFragmentManager());
        adapter.addFragment(new TabMateri(), "MATERI");
        adapter.addFragment(new TabPengumuman(), "NOTICES");
        adapter.addFragment(new Favorite(), "FAVORITE");
        adapter.addFragment(new switcher(), "•••");

        viewPager.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem menuItem) {
        mDrawerLayout.closeDrawers();
        if (menuItem.getItemId() == R.id.setting) {
            //Toast.makeText(MainActivity.this, "Available soon", Toast.LENGTH_SHORT).show();

            Intent Cont = new Intent(this, Contributor.class);
            startActivity(Cont);
        }

        if (menuItem.getItemId() == R.id.logout) {
            logout();
        }

        return false;
    }

    public boolean logout() {
        SharedPreferences sharedPreferences = MainActivity.this.getSharedPreferences(SHARED_PREF_DATA, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
        fa.finish();
        return true;
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setMessage("Apakah Anda Yakin Ingin Keluar");
        builder.setCancelable(true);
        builder.setPositiveButton("IYA", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                nlg.finish();
                finish();
            }
        });
        builder.setNegativeButton("TIDAK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }

    static class Adapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public Adapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }
}
