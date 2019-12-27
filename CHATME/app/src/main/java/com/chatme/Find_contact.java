package com.chatme;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Find_contact extends AppCompatActivity {

    private ArrayList<String> f_ID=new ArrayList<>();
    private ArrayList<String> f_NAME=new ArrayList<>();;
    private ArrayList<String> f_PICTURE=new ArrayList<>();;
    private ArrayList<String> f_LAST_SEEN=new ArrayList<>();;
    private ArrayList<String> f_STATUS=new ArrayList<>();;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.find_contact);

        load_friend();


    }

    private void load_friend(){
        String url = "http://192.168.43.191/chatme/friendlist.php";
        StringRequest MyStringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                fill_friend(response);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),error.toString(),1).show();
            }
        }) {
            protected Map<String, String> getParams() {
                Map<String, String> MyData = new HashMap<String, String>();
                MyData.put("ID",My_Detail.My_ID);
                return MyData;
            }
        };

        MySingleton.getInstance(getApplicationContext()).addRequestQueue(MyStringRequest);


    }

    private void fill_friend(String response){
        try{
            JSONArray jarray = new JSONArray(response);
            for(int n=0;n<jarray.length();n++)
            {
                JSONObject jobject = jarray.getJSONObject(n);
              f_ID.add(jobject.getString("ID"));
              f_NAME.add(jobject.getString("NAME"));
              f_PICTURE.add(jobject.getString("PICTURE"));
              f_STATUS.add(jobject.getString("STATUS"));
              f_LAST_SEEN.add(jobject.getString("LAST_SEEN"));
            }
        }
        catch(JSONException e){}
        showlist();
    }

    private void showlist(){
        if(f_ID.size()!=0) {
            RecyclerView recycle_contact= (RecyclerView) findViewById(R.id.find_contact_recycleview);
            recycle_contact.setLayoutManager(new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL,false));
            recycle_contact.setAdapter(new ContactAdapter(f_ID,f_NAME,f_PICTURE,f_STATUS,f_LAST_SEEN,"find_contact"));
        }
    }
}
