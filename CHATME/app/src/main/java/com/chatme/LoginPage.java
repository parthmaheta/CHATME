package com.chatme;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class LoginPage extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        Button  login_Button=(Button)findViewById(R.id.loginbtn);
        login_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in=new Intent(getApplicationContext(),Main.class);
                startActivity(in);

            }
        });

        Button  sign_Button=(Button)findViewById(R.id.register_btn);
        sign_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in=new Intent(getApplicationContext(),RegisterPage.class);
                startActivity(in);

            }
        });

        Button  forgot_Button=(Button)findViewById(R.id.forgot_btn);
        forgot_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in=new Intent(getApplicationContext(),ForgotPassword.class);
                startActivity(in);

            }
        });
    }
}
