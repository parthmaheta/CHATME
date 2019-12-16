package com.chatme;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

public class Find_contact extends AppCompatActivity {

    String[] Names={"friend1","friend2","friend3"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.find_contact);



        RecyclerView recycle_contact= (RecyclerView) findViewById(R.id.find_contact_recycleview);
        recycle_contact.setLayoutManager(new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL,false));
        recycle_contact.setAdapter(new ContactAdapter(Names));


    }
}
