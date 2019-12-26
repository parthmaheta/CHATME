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
import com.google.android.material.snackbar.Snackbar;
import com.shashank.sony.fancytoastlib.FancyToast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class ForgotPassword extends AppCompatActivity {
    EditText email;
    String url = "http://192.168.43.191/chatme/check.php";
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

                    request(email.getText().toString().trim());
                }
            }
        });
    }

    private void request(final String email) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try{
                            JSONArray jsonArray = new JSONArray(response);
                            JSONObject jsonObject = jsonArray.getJSONObject(0);
                            if(jsonObject.getString("msg").toString().equals("UnRegister User")){
                                FancyToast.makeText(getApplicationContext(),"Enter Email is not Register",Toast.LENGTH_LONG,FancyToast.INFO,false).show();
                                return;
                            }
                            if(jsonObject.getString("msg").toString().equals("Register User")){

                                String code = code();

                                FancyToast.makeText(getApplicationContext(),"OTP :- "+code,Toast.LENGTH_LONG,FancyToast.INFO,false).show();

                                Intent in=new Intent(getApplicationContext(),OtpPage.class);
                                in.putExtra("data","Forgot");
                                in.putExtra("code",code);
                                in.putExtra("mail",email);
                                startActivity(in);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
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
                params.put("check_mail",email);
                return params;
            }
        };
        MySingleton.getInstance(ForgotPassword.this).addRequestQueue(stringRequest);
    }

    private String code(){
        Random r = new Random();
        int i1 = r.nextInt(9);
        int i2 = r.nextInt(9);
        int i3 = r.nextInt(9);
        int i4 = r.nextInt(9);
        int i5 = r.nextInt(9);
        int i6 = r.nextInt(9);
        return i1+""+i2+""+i3+""+i4+""+""+i5+""+i6;
    }
}
