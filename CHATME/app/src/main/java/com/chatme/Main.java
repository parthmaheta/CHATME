package com.chatme;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;

import com.google.android.material.bottomnavigation.BottomNavigationView;


public class Main extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar=findViewById(R.id.top_toolbar);
        setSupportActionBar(toolbar);



        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomnav);
        bottomNavigationView.setOnNavigationItemSelectedListener(navListener);

        //set fragment_home to current activity
        getSupportFragmentManager().beginTransaction().replace(R.id.frame, new Fragment_Home()).commit();
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            Fragment selectFragment = null;

            switch (menuItem.getItemId()) {
                case R.id.nav_home:
                    selectFragment = new Fragment_Home();
                    break;
                case R.id.nav_search:
                    selectFragment = new Fragment_Search();
                    break;
                case R.id.nav_profile:
                    selectFragment = new Fragment_Profile();
                    break;

                case R.id.nav_request:
                    selectFragment = new Fragment_Request();
                    break;
            }
            getSupportFragmentManager().beginTransaction().replace(R.id.frame, selectFragment).commit();
            return true;
        }


    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.top_nav,menu);


        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        return true;
    }
}