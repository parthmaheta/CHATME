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

public class OtpPage extends AppCompatActivity {
    EditText otpverify;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp_page);

        otpverify = (EditText) findViewById(R.id.otptv);
        final Button verifyBtn=(Button)findViewById(R.id.verify_otp_btn);
        verifyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(otpverify.getText().toString().trim().equals("") || otpverify.getText().toString().trim().length() != 6 ) {
                    Animation anim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.zoomout);
                    verifyBtn.setVisibility(View.VISIBLE);
                    verifyBtn.startAnimation(anim);
                    FancyToast.makeText(getApplicationContext(),"Enter 6 Digit Code",FancyToast.LENGTH_LONG,FancyToast.WARNING,false).show();
                    return;
                }
                else{
                    Animation anim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.bounce);
                    verifyBtn.setVisibility(View.VISIBLE);
                    verifyBtn.startAnimation(anim);
                    if(getIntent().getStringExtra("data").toString().equals("Register")){

                        FancyToast.makeText(getApplicationContext(),"Account Create Success",Toast.LENGTH_LONG,FancyToast.INFO,false).show();
                        Intent in = new Intent(getApplicationContext(), Main.class);
                        startActivity(in);
                    }
                    else if(getIntent().getStringExtra("data").toString().equals("Forgot")){

                        Intent in = new Intent(getApplicationContext(), ChangePassword.class);
                        startActivity(in);
                    }
                }
            }
        });
    }
}
