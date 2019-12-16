package com.chatme;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;
import com.shashank.sony.fancytoastlib.FancyToast;

import javax.net.ssl.CertPathTrustManagerParameters;

public class LoginPage extends AppCompatActivity {
    Animation anim;
    EditText user,pass;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        user = (EditText) findViewById(R.id.emailet);
        pass = (EditText) findViewById(R.id.passwordet);

        final Button  login_Button=(Button)findViewById(R.id.loginbtn);
        login_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(isValid()){
                    anim = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.zoomout);
                    login_Button.setVisibility(View.VISIBLE);
                    login_Button.startAnimation(anim);

                    Intent in=new Intent(getApplicationContext(),Main.class);
                    startActivity(in);
                }
                else{
                    anim = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.bounce);
                    login_Button.setVisibility(View.VISIBLE);
                    login_Button.startAnimation(anim);
                    return;
                }
            }
        });

        final Button  sign_Button=(Button)findViewById(R.id.register_btn);
        sign_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                anim = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.bounce);
                sign_Button.setVisibility(View.VISIBLE);
                sign_Button.startAnimation(anim);

                Intent in=new Intent(getApplicationContext(),RegisterPage.class);
                startActivity(in);

            }
        });

        final Button  forgot_Button=(Button)findViewById(R.id.forgot_btn);
        forgot_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                anim = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.bounce);
                forgot_Button.setVisibility(View.VISIBLE);
                forgot_Button.startAnimation(anim);

                Intent in=new Intent(getApplicationContext(),ForgotPassword.class);
                startActivity(in);

            }
        });
    }

    private boolean isValid() {
        if(user.getText().toString().trim().equals("")){
            FancyToast.makeText(getApplicationContext(),"Please Enter Email",FancyToast.LENGTH_LONG,FancyToast.WARNING,false).show();
            return false;
        }
        if(pass.getText().toString().trim().equals("")){
            FancyToast.makeText(getApplicationContext(),"Please Enter Password",FancyToast.LENGTH_LONG,FancyToast.WARNING,false).show();
            return false;
        }
        return true;
    }
}
