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

public class OtpPage extends AppCompatActivity {
    EditText otpverify;
    String url = "http://192.168.43.191/chatme/register.php";
    Database_Handler db_handler;
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

                        if(codeValid()){
                            request();
                        }
                        else{
                            FancyToast.makeText(getApplicationContext(),"Enter Currect OTP",FancyToast.LENGTH_LONG,FancyToast.WARNING,false).show();
                        }

                    }
                    else if(getIntent().getStringExtra("data").toString().equals("Forgot")){
                        if(codeValid()){
                            Intent in = new Intent(getApplicationContext(), ChangePassword.class);
                            in.putExtra("mail",getIntent().getStringExtra("mail").toString());
                            startActivity(in);
                        }
                        else{
                            FancyToast.makeText(getApplicationContext(),"Enter Currect OTP",FancyToast.LENGTH_LONG,FancyToast.WARNING,false).show();
                        }
                    }
                }
            }


        });
    }

    private void request() {
        final String u_name = getIntent().getStringExtra("r_name");
        final String u_email = getIntent().getStringExtra("r_email");
        final String u_pass = getIntent().getStringExtra("r_pass");

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        // add user info in SqLite database
                        try {
                            JSONObject jobject=new JSONObject(response);
                            String id=jobject.getString("ID");
                            db_handler = new Database_Handler(getApplicationContext());
                            db_handler.deleteuser();
                            db_handler.addUser(id,u_name, u_email, u_pass);
                            My_Detail.My_ID = id;
                            My_Detail.My_NAME =u_name;
                            My_Detail.My_EMAIL =u_email;
                            My_Detail.My_PASSWORD =u_pass;
                        }
                        catch (Exception ex){
                            ex.printStackTrace();
                        }


                        Intent in = new Intent(getApplicationContext(), Main.class);
                        startActivity(in);
                        finish();
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
                params.put("user_name",u_name);
                params.put("user_email",u_email);
                params.put("user_password",u_pass);
                return params;
            }
        };
        MySingleton.getInstance(OtpPage.this).addRequestQueue(stringRequest);
    }

    private boolean codeValid() {
        if(!getIntent().getStringExtra("code").toString().equals(otpverify.getText().toString().trim())){
            return false;
        }
        return true;
    }
}
