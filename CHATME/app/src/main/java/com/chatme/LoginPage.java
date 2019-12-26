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

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.android.material.textfield.TextInputLayout;
import com.shashank.sony.fancytoastlib.FancyToast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.security.MessageDigest;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.net.ssl.CertPathTrustManagerParameters;

public class LoginPage extends AppCompatActivity {
    Animation anim;
    EditText user,pass;
    String UserId,Password;
    String url = "http://192.168.43.191/chatme/login.php";
    Database_Handler db_handler;
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

                    UserId = user.getText().toString().trim();
                    Password = md5(pass.getText().toString().trim());

                    StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {

                                    try {
                                        JSONArray jsonArray = new JSONArray(response);
                                        JSONObject jsonObject = jsonArray.getJSONObject(0);

                                        if(jsonObject.getString("login").equals("Login Success")) {
                                            user.setText("");
                                            pass.setText("");

                                            try{
                                                db_handler = new Database_Handler(getApplicationContext());
                                                db_handler.deleteuser();
                                                db_handler.addusr(jsonObject.getString("ID"),jsonObject.getString("NAME"),jsonObject.getString("EMAIL"),jsonObject.getString("PASSWORD"),jsonObject.getString("PICTURE"),jsonObject.getString("STATUS"));
                                            }
                                            catch (Exception ex){
                                                ex.printStackTrace();
                                            }


                                            My_Detail.My_ID =jsonObject.getString("ID");
                                            Intent in = new Intent(getApplicationContext(), Main.class);
                                            startActivity(in);
                                        }
                                        else if(jsonObject.getString("login").toString().equals("Login Fail")){
                                            FancyToast.makeText(getApplicationContext(),"Login Fail\nEnter Currect UserId And Password",Toast.LENGTH_LONG,FancyToast.ERROR,false).show();
                                            return;
                                        }
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                            },
                            new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    FancyToast.makeText(getApplicationContext(),"Error :- "+error.getMessage().toString(),Toast.LENGTH_LONG,FancyToast.ERROR,false).show();
                                }
                            }){
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            Map<String,String> params = new HashMap<String,String>();
                            params.put("l_user",UserId);
                            params.put("l_pass",Password);
                            return params;
                        }
                    };
                    MySingleton.getInstance(LoginPage.this).addRequestQueue(stringRequest);

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

    private boolean isValid() {

        if(user.getText().toString().trim().equals("")){
            FancyToast.makeText(getApplicationContext(),"Please Enter Email",FancyToast.LENGTH_LONG,FancyToast.WARNING,false).show();
            user.requestFocus();
            return false;
        }
        if(pass.getText().toString().trim().equals("")){
            FancyToast.makeText(getApplicationContext(),"Please Enter Password",FancyToast.LENGTH_LONG,FancyToast.WARNING,false).show();
            pass.requestFocus();
            return false;
        }
        String emailvalidation = "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}"+"\\@"+"[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}"+"("+"\\."+"[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}"+")";
        Matcher match  = Pattern.compile(emailvalidation).matcher(user.getText().toString().trim());
        if(!match.matches()){
            FancyToast.makeText(getApplicationContext(),"Enter Valid Email",FancyToast.LENGTH_LONG,FancyToast.WARNING,false).show();
            user.requestFocus();
            return false;
        }
        return true;
    }
}
