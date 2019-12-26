package com.chatme;

import androidx.fragment.app.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import android.widget.ImageView;

import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Fragment_Home extends Fragment {
    String[] Names={"name1","name2","name3"};
    String[] Chats={"Hii...?","How r u ?","last message"};
    ArrayList<String> Status_name = new ArrayList<String>();
    ArrayList<String> Status_path = new ArrayList<String>();
    View view;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {


        view=inflater.inflate(R.layout.fragment_home, container, false);


        load_status();



        RecyclerView Recycle_Chat= view.findViewById(R.id.fragment_chat_recyclerview);
        Recycle_Chat.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        Recycle_Chat.setAdapter(new ChatListAdapter(Names,Chats));

        return view;

    }

    private void load_status(){
        RequestQueue MyRequestQueue = Volley.newRequestQueue(getContext());
        String url = "http://192.168.43.191/chatme/status.php";
        StringRequest MyStringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                fill_status(response);

            }
        }, new Response.ErrorListener() { //Create an error listener to handle errors appropriately.
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(),error.toString(),1).show();
            }
        }) {
            protected Map<String, String> getParams() {
                Map<String, String> MyData = new HashMap<String, String>();
                MyData.put("ID",My_Detail.My_ID);
                return MyData;
            }
        };


        MyRequestQueue.add(MyStringRequest);

    }

    private void fill_status(String response){
        try{
            JSONArray jarray = new JSONArray(response);
            for(int n=0;n<jarray.length();n++)
            {
                JSONObject jobject = jarray.getJSONObject(n);
                Status_name.add(jobject.getString("NAME"));
                Status_path.add(jobject.getString("FILEPATH"));
            }
        }
        catch(JSONException e){}
        showStatuses();
    }
    private void showStatuses(){
        RecyclerView Recycle_Status= view.findViewById(R.id.fragment_status_recyclerview);
        Recycle_Status.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        Recycle_Status.setAdapter(new StatusAdapter(Status_name,Status_path));
    }

}