package com.chatme;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
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

public class Fragment_Request extends Fragment {

    View view;
    ArrayList<String> f_NAME = new ArrayList<String>();
    ArrayList<String> f_PICTURE = new ArrayList<String>();
    ArrayList<String> f_ID = new ArrayList<String>();
    ArrayList<String> f_STATUS = new ArrayList<String>();
    ArrayList<String> f_LAST_SEEN = new ArrayList<String>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_request, container, false);

        load_request();

        return view;
    }

    private void load_request(){
        RequestQueue MyRequestQueue = Volley.newRequestQueue(getContext());
        String url = "http://192.168.43.191/chatme/friend_requests.php";
        StringRequest MyStringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                fill_request(response);

            }
        }, new Response.ErrorListener() { //Create an error listener to handle errors appropriately.
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(),error.toString(),4).show();
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

    private void fill_request(String response){
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
        showRequest();
    }

    private void showRequest() {
        RecyclerView recycle_Requests = view.findViewById(R.id.request_recycleview);
        recycle_Requests.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        recycle_Requests.setAdapter(new RequestAdapter(this,f_ID,f_NAME,f_PICTURE,f_STATUS,f_LAST_SEEN));
    }

    public void  request_answer(final String id, final String answer){

        RequestQueue MyRequestQueue = Volley.newRequestQueue(getContext());

        String url = "http://192.168.43.191/chatme/request_answer.php";
        StringRequest MyStringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

            }
        }, new Response.ErrorListener() { //Create an error listener to handle errors appropriately.
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(),error.toString(),1).show();
            }
        }) {
            protected Map<String, String> getParams() {
                Map<String, String> MyData = new HashMap<String, String>();
                MyData.put("UID",My_Detail.My_ID);
                MyData.put("FID",id);
                MyData.put("ANSWER",answer);
                return MyData;
            }
        };

        MyRequestQueue.add(MyStringRequest);

    }
}