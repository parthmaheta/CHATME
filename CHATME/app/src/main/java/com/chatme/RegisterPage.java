package com.chatme;
import android.app.DownloadManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.chatme.R;
import com.google.android.material.snackbar.Snackbar;
import com.shashank.sony.fancytoastlib.FancyToast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.security.MessageDigest;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterPage extends AppCompatActivity {
    Animation anim;
    EditText name,user,pass;
    String Name,UserId,Password;
    LinearLayout ll;
    String url = "http://192.168.43.191/chatme/check.php";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_page);
        name = (EditText) findViewById(R.id.nameet);
        user = (EditText) findViewById(R.id.emailet);
        pass = (EditText)findViewById(R.id.passwordet);
        ll = (LinearLayout) findViewById(R.id.register_linear);

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
            public void onClick(final View v) {
                if(isValid()) {
                    anim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.zoomout);
                    registerbtn.setVisibility(View.VISIBLE);
                    registerbtn.startAnimation(anim);

                    Name = name.getText().toString().trim();
                    UserId = user.getText().toString().trim();
                    Password = md5(pass.getText().toString().trim());

                    StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {

                                    try{
                                        JSONArray jsonArray = new JSONArray(response);
                                        JSONObject jsonObject = jsonArray.getJSONObject(0);
                                        if(jsonObject.getString("msg").toString().equals("Register User")){

                                            Snackbar.make(ll,"Your Email "+UserId+"\nis Already Exist, Enter Another Email",Snackbar.LENGTH_LONG).show();
                                            return;
                                        }
                                        if(jsonObject.getString("msg").toString().equals("UnRegister User")){

                                            name.setText("");
                                            user.setText("");
                                            pass.setText("");

                                            String otpcode = code();
                                            FancyToast.makeText(getApplicationContext(),"OTP :- "+otpcode,Toast.LENGTH_LONG,FancyToast.INFO,false).show();

                                            Intent in=new Intent(getApplicationContext(),OtpPage.class);
                                            in.putExtra("data","Register");
                                            in.putExtra("code",otpcode);
                                            in.putExtra("r_name",Name);
                                            in.putExtra("r_email",UserId);
                                            in.putExtra("r_pass",Password);
                                            v.getContext().startActivity(in);
                                            finish();
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
                            params.put("check_mail",UserId);
                            return params;
                        }
                    };
                    MySingleton.getInstance(RegisterPage.this).addRequestQueue(stringRequest);

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

    private boolean isValid() {
        if(name.getText().toString().trim().equals("")){
            FancyToast.makeText(getApplicationContext(),"Enter Your Name",FancyToast.LENGTH_LONG,FancyToast.WARNING,false).show();
            name.requestFocus();
            return false;
        }
        if(user.getText().toString().trim().equals("")){
            FancyToast.makeText(getApplicationContext(),"Enter Your Email",FancyToast.LENGTH_LONG,FancyToast.WARNING,false).show();
            user.requestFocus();
            return false;
        }
        if(pass.getText().toString().trim().equals("")){
            FancyToast.makeText(getApplicationContext(),"Enter Your Password",FancyToast.LENGTH_LONG,FancyToast.WARNING,false).show();
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
