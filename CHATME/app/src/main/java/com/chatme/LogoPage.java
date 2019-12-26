package com.chatme;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class LogoPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logo_page);
        TextView tv = (TextView) findViewById(R.id.logotv);

        try{
            Thread.sleep(1000);
            startActivity(new Intent(getApplicationContext(),LoginPage.class));
        }
        catch (Exception ex){
            ex.printStackTrace();
        }

    }
}
