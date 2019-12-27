package com.chatme;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.shashank.sony.fancytoastlib.FancyToast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LogoPage extends AppCompatActivity {
    String url = "http://192.168.43.191/chatme/login.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logo_page);
        TextView tv = (TextView) findViewById(R.id.logotv);
        Database_Handler db_handler = new Database_Handler(getApplicationContext());

        try {
            Thread.sleep(1000);
            if (db_handler.isUser()) {
                String[] info = db_handler.getMailPass();
                request(info[0].toString(),info[1].toString());
            } else {
                startActivity(new Intent(getApplicationContext(),LoginPage.class));
            }
        }
        catch (Exception ex){
            ex.printStackTrace();
        }

    }

    private void request(final String mail, final String pass) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try{
                            JSONArray jsonArray = new JSONArray(response);
                            JSONObject jsonObject = jsonArray.getJSONObject(0);

                            if(jsonObject.getString("login").toString().equals("Login Success")) {
                                addToMy_Detail(jsonObject);
                                startActivity(new Intent(getApplicationContext(),Main.class));
                            }
                            else if(jsonObject.getString("login").toString().equals("Login Fail")){
                                startActivity(new Intent(getApplicationContext(),LoginPage.class));
                            }

                        }catch (Exception ex){
                            ex.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        FancyToast.makeText(getApplicationContext(),"Connection Fail",Toast.LENGTH_LONG,FancyToast.ERROR,false).show();
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<String,String>();
                params.put("l_user",mail);
                params.put("l_pass",pass);
                return params;
            }
        };
        MySingleton.getInstance(LogoPage.this).addRequestQueue(stringRequest);
    }

    private void addToMy_Detail(JSONObject jsonObject){
        try{
            My_Detail.My_ID=jsonObject.getString("ID");
            My_Detail.My_NAME=jsonObject.getString("NAME");
            My_Detail.My_EMAIL=jsonObject.getString("EMAIL");
            My_Detail.My_PASSWORD=jsonObject.getString("PASSWORD");
            My_Detail.My_PICTURE=jsonObject.getString("PICTURE");
            My_Detail.My_STATUS=jsonObject.getString("STATUS");}
        catch(Exception e){
        }
    }
}
