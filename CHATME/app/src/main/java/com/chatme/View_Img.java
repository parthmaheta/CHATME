package com.chatme;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.squareup.picasso.Picasso;

public class View_Img extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_img);




        Toolbar titleBar=(Toolbar)findViewById(R.id.view_img_toolbar);
        ImageView img=(ImageView)findViewById(R.id.profile_detail_img);

        setSupportActionBar(titleBar);
        Intent in=getIntent();

        Picasso.get().load("http://192.168.43.191/chatme/img/"+in.getStringExtra("path")).into(img);
        titleBar.setTitle(in.getStringExtra("name"));
        titleBar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        titleBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }
}