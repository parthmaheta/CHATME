package com.chatme;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.chatme.R;
import com.squareup.picasso.Picasso;

public class ChatScreen extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chat_screen);

        Toolbar titleBar=(Toolbar)findViewById(R.id.chat_title_toolbar);
        setSupportActionBar(titleBar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

       titleBar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
       titleBar.setNavigationOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               finish();
           }
       });


       final Intent in=getIntent();


        TextView tv=(TextView)findViewById(R.id.chat_profile_name);
        tv.setText(in.getStringExtra("name"));

        ImageView iv=(ImageView)findViewById(R.id.chat_profile_img);
        Picasso.get().load("http://192.168.43.191/chatme/img/"+in.getStringExtra("path")).into(iv);

        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent showimg=new Intent(getApplicationContext(),Profile_Detail.class);
                showimg.putExtra("path",in.getStringExtra("path"));
                showimg.putExtra("name",in.getStringExtra("name"));
                showimg.putExtra("last_seen",in.getStringExtra("last_seen"));
                showimg.putExtra("status",in.getStringExtra("status"));
                v.getContext().startActivity(showimg);
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.chat_setting,menu);


        return true;
    }

    public void get_file(View view) {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("*/*");
        startActivityForResult(intent,7);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        switch(requestCode){

            case 7:

                if(resultCode==RESULT_OK){

                    String PathHolder = data.getData().getPath();

                    Toast.makeText(this, PathHolder , Toast.LENGTH_LONG).show();

                }
                break;

        }
    }
}
