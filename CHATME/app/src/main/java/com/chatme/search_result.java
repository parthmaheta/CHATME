package com.chatme;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class search_result extends AppCompatActivity {
    ArrayList<String> f_NAME = new ArrayList<String>();
    ArrayList<String> f_PICTURE = new ArrayList<String>();
    ArrayList<String> f_ID = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_result);

        Toolbar titleBar=(Toolbar)findViewById(R.id.search_result_toolbar);
        setSupportActionBar(titleBar);
        titleBar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        titleBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //getting intent from Fragment_search
        Intent i=getIntent();
        String response=i.getStringExtra("response");
        load_friend(response);

    }

    private void load_friend(String response){
        try{
            JSONArray jarray = new JSONArray(response);
            for(int n=0;n<jarray.length();n++)
            {
                JSONObject jobject = jarray.getJSONObject(n);
                f_ID.add(jobject.getString("ID"));
                f_NAME.add(jobject.getString("NAME"));
                f_PICTURE.add(jobject.getString("PICTURE"));


            }

        }
        catch(JSONException e){}
        showlist();
    }

    private void showlist(){
        if(f_ID.size()!=0) {
            RecyclerView recycle_Friend = findViewById(R.id.search_result_recycleview);
            recycle_Friend.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
            recycle_Friend.setAdapter(new FriendAdapter(getApplicationContext(),f_ID, f_NAME, f_PICTURE));
        }
        else{
            TextView tv=(TextView)findViewById(R.id.searchresult);
            tv.setText("No Result Found");
        }
    }
}
