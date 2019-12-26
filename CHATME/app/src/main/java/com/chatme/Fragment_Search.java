package com.chatme;

import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Fragment_Search extends Fragment {

    ImageButton search_btn;
    TextView search_text;

    String[] Names;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.fragement_search, container, false);
        //RecyclerView recycle_Friend= view.findViewById(R.id.fragment_search_recycleview);
        //recycle_Friend.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        //recycle_Friend.setAdapter(new FriendAdapter(Names));
        search_btn = (ImageButton) view.findViewById(R.id.search_btn);
        search_text = (TextView) view.findViewById(R.id.search_friend);

        search_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isValid()){
                    load_friend();
                }
            }
        });
        return view;

    }

    private boolean isValid(){
        if(search_text.getText().toString().equals(""))
        {   Toast.makeText(getContext(),"Empty",1).show();
            return false;
        }
        else
            return true;
    }

    private void load_friend(){

        RequestQueue MyRequestQueue = Volley.newRequestQueue(getContext());
        String url = "http://192.168.43.191/chatme/search_friend.php";
        StringRequest MyStringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                fill_list(response);

            }
        }, new Response.ErrorListener() { //Create an error listener to handle errors appropriately.
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(),error.toString(),1).show();
            }
        }) {
            protected Map<String, String> getParams() {
                Map<String, String> MyData = new HashMap<String, String>();
                MyData.put("NAME",search_text.getText().toString());
                return MyData;
            }
        };


        MyRequestQueue.add(MyStringRequest);
    }

    private void fill_list(String response){
        Intent in=new Intent(getContext(),search_result.class);
        in.putExtra("response",response);
        startActivity(in);
    }

}