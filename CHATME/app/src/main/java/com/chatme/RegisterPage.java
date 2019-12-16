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

import com.chatme.R;
import com.shashank.sony.fancytoastlib.FancyToast;

public class RegisterPage extends AppCompatActivity {
    Animation anim;
    EditText name,user,pass;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_page);
        name = (EditText) findViewById(R.id.nameet);
        user = (EditText) findViewById(R.id.emailet);
        pass = (EditText)findViewById(R.id.passwordet);

        final Button backbtn=(Button)findViewById(R.id.register_page_backbtn);
        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                anim = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.bounce);
                backbtn.setVisibility(View.VISIBLE);
                backbtn.startAnimation(anim);

                finish();
            }
        });

        final Button registerbtn=(Button)findViewById(R.id.register_page_btn);
        registerbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(isValid()) {
                    anim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.zoomout);
                    registerbtn.setVisibility(View.VISIBLE);
                    registerbtn.startAnimation(anim);

                    Intent in=new Intent(getApplicationContext(),OtpPage.class);
                    in.putExtra("data","Register");
                    v.getContext().startActivity(in);
                }
                else{
                    anim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.bounce);
                    registerbtn.setVisibility(View.VISIBLE);
                    registerbtn.startAnimation(anim);
                    return;
                }
            }
        });
    }

    private boolean isValid() {
        if(name.getText().toString().trim().equals("")){
            FancyToast.makeText(getApplicationContext(),"Enter Your Name",FancyToast.LENGTH_LONG,FancyToast.WARNING,false).show();
            return false;
        }
        if(user.getText().toString().trim().equals("")){
            FancyToast.makeText(getApplicationContext(),"Enter Your Email",FancyToast.LENGTH_LONG,FancyToast.WARNING,false).show();
            return false;
        }
        if(pass.getText().toString().trim().equals("")){
            FancyToast.makeText(getApplicationContext(),"Enter Your Password",FancyToast.LENGTH_LONG,FancyToast.WARNING,false).show();
            return false;
        }
        return true;
    }
}
