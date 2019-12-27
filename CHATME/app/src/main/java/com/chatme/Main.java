package com.chatme;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class Main extends AppCompatActivity {
    FloatingActionButton addbtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar=findViewById(R.id.top_toolbar);
        setSupportActionBar(toolbar);



        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Database_Handler db = new Database_Handler(getApplicationContext());
                switch (item.getItemId()){
                    case R.id.top_nav_setting:
                        Toast.makeText(getApplicationContext(),db.myinfo(),Toast.LENGTH_LONG).show();
                        break;
                    case R.id.top_nav_add_status:
                        Intent i=new Intent(getApplicationContext(),upload_status.class);
                        startActivity(i);
                        break;
                    case R.id.top_nav_logout:
                        Intent in=new Intent(getApplicationContext(),LoginPage.class);
                        startActivity(in);
                        break;
                }
                return true;
            }
        });


        addbtn = (FloatingActionButton) findViewById(R.id.floatingaddbtn);

        addbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in=new Intent(getApplicationContext(),Find_contact.class);
                startActivity(in);
            }
        });

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