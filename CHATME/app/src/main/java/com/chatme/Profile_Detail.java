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

public class Profile_Detail extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_detail);

        final Intent in=getIntent();
        Toolbar titleBar=(Toolbar)findViewById(R.id.profile_detail_toolbar);
        setSupportActionBar(titleBar);

        TextView name=(TextView)findViewById(R.id.profile_detail_name);
        TextView status=(TextView)findViewById(R.id.profile_detail_status);

        ImageView image=(ImageView)findViewById(R.id.profile_detail_img);

        titleBar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        titleBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        Picasso.get().load("http://192.168.43.191/chatme/img/"+in.getStringExtra("path")).into(image);
        name.setText(in.getStringExtra("name"));
        status.setText(in.getStringExtra("status"));

        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent viewImg=new Intent(getApplicationContext(),View_Img.class);
                viewImg.putExtra("path",in.getStringExtra("path"));
                viewImg.putExtra("name",in.getStringExtra("name"));
                startActivity(viewImg);
            }
        });
    }
}
