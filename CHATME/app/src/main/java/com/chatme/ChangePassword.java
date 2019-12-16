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

public class ChangePassword extends AppCompatActivity {
    EditText pass1,pass2;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        pass1 = (EditText) findViewById(R.id.password1);
        pass2 = (EditText) findViewById(R.id.password2);

        final Button changePassbtn=(Button)findViewById(R.id.change_password_btn);
        changePassbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isValid()){

                    if(pass1.getText().toString().trim().equals(pass2.getText().toString().trim())) {

                        Animation anim = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.bounce);
                        changePassbtn.setVisibility(View.VISIBLE);
                        changePassbtn.startAnimation(anim);

                        FancyToast.makeText(getApplicationContext(),"Password Change Success", Toast.LENGTH_LONG,FancyToast.INFO,false).show();
                        Intent in = new Intent(getApplicationContext(), Main.class);
                        v.getContext().startActivity(in);
                    }
                    else{
                        Animation anim = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.zoomout);
                        changePassbtn.setVisibility(View.VISIBLE);
                        changePassbtn.startAnimation(anim);
                        FancyToast.makeText(getApplicationContext(), "Both Password are not same", FancyToast.LENGTH_LONG, FancyToast.WARNING, false).show();
                        return;
                    }
                }
                else{
                    Animation anim = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.zoomout);
                    changePassbtn.setVisibility(View.VISIBLE);
                    changePassbtn.startAnimation(anim);
                    return;
                }
            }
        });
    }

    private boolean isValid() {
        if(pass1.getText().toString().trim().equals("")){
            FancyToast.makeText(getApplicationContext(),"Enter New Password",FancyToast.LENGTH_LONG,FancyToast.WARNING,false).show();
            return false;
        }
        if(pass2.getText().toString().trim().equals("")){
            FancyToast.makeText(getApplicationContext(),"Re-Enter Password",FancyToast.LENGTH_LONG,FancyToast.WARNING,false).show();
            return false;
        }
        return true;
    }
}
