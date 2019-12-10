package com.chatme;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class Profile_Detail extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_detail);

        Toolbar titleBar=(Toolbar)findViewById(R.id.profile_detail_toolbar);
        setSupportActionBar(titleBar);


        titleBar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        titleBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

           final Intent in=getIntent();
           TextView name=(TextView)findViewById(R.id.profile_detail_name);
           name.setText(in.getStringExtra("name"));

        ImageView image=(ImageView)findViewById(R.id.profile_detail_img);
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent viewImg=new Intent(getApplicationContext(),View_Img.class);
                viewImg.putExtra("name",in.getStringExtra("name"));
                startActivity(viewImg);
            }
        });
    }
}
