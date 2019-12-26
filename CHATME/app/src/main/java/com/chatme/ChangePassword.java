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

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.shashank.sony.fancytoastlib.FancyToast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.security.MessageDigest;
import java.util.HashMap;
import java.util.Map;

public class ChangePassword extends AppCompatActivity {
    EditText pass1,pass2;
    String url = "http://192.168.43.191/chatme/changepass.php";
    String pass;
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

                        pass = md5(pass1.getText().toString().trim());

                        request();
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

    private void request() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {

                            FancyToast.makeText(getApplicationContext(), "Password Change Success \nPlease Login Now", Toast.LENGTH_LONG, FancyToast.INFO, false).show();
                            Intent in = new Intent(getApplicationContext(), LoginPage.class);

                            startActivity(in);
                            finish();
                        }
                        catch(Exception e){}

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        FancyToast.makeText(getApplicationContext(),"Connection Fail",Toast.LENGTH_LONG,FancyToast.ERROR,false).show();
                    }
                }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<String,String>();
                params.put("mail",getIntent().getStringExtra("mail").toString());
                params.put("pass",pass);
                return params;
            }
        };
        MySingleton.getInstance(ChangePassword.this).addRequestQueue(stringRequest);
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

    private String md5(String ps) {
        try{
            MessageDigest digest = java.security.MessageDigest.getInstance("MD5");
            digest.update(ps.getBytes());
            byte messageDigest[] = digest.digest();

            StringBuffer hexString = new StringBuffer();
            for(int i=0; i<messageDigest.length; i++){
                hexString.append(Integer.toHexString(0xFF & messageDigest[i]));
            }
            return hexString.toString();
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
        return "";
    }
}
