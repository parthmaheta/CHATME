package com.chatme;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.shashank.sony.fancytoastlib.FancyToast;

public class ForgotPassword extends AppCompatActivity {
    EditText email;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        email = (EditText) findViewById(R.id.emailet);

        final Button getOtp=(Button)findViewById(R.id.get_otp_btn);
        getOtp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(email.getText().toString().trim().equals("")){
                    Animation anim = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.zoomout);
                    getOtp.setVisibility(View.VISIBLE);
                    getOtp.startAnimation(anim);

                    FancyToast.makeText(getApplicationContext(),"Please Enter Email",FancyToast.LENGTH_LONG,FancyToast.WARNING,false).show();
                    return;

                }
                else{
                    Animation anim = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.zoomout);
                    getOtp.setVisibility(View.VISIBLE);
                    getOtp.startAnimation(anim);

                    Intent in=new Intent(getApplicationContext(),OtpPage.class);
                    in.putExtra("data","Forgot");
                    v.getContext().startActivity(in);
                }
            }
        });
    }
}
