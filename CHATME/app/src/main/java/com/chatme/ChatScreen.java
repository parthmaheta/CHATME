package com.chatme;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.chatme.R;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class ChatScreen extends AppCompatActivity {

     ArrayList<String> Messages=new ArrayList<String>();
     ArrayList<String> From=new ArrayList<String>();
      ChatAdapter Chats;
      RecyclerView recycle_chat;
      Intent in;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chat_screen);

        Toolbar titleBar = (Toolbar) findViewById(R.id.chat_title_toolbar);
        setSupportActionBar(titleBar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        load_chat();
        in = getIntent();


        Button sendBtn=(Button)findViewById(R.id.send_chat_btn);
        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText editchat=(EditText)findViewById(R.id.send_chat_text);
                String Text= editchat.getText().toString();
                if(!Text.equals("")){

                    Messages.add(Text);
                    From.add(My_Detail.My_ID);
                    editchat.setText("");

                    //update list
                    Chats.notifyDataSetChanged();
                    if(Messages.size()>1)
                    recycle_chat.smoothScrollToPosition(Messages.size()-1);

                    addText(Text);
                }
            }
        });


        titleBar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        titleBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });





        TextView tv = (TextView) findViewById(R.id.chat_profile_name);
        tv.setText(in.getStringExtra("name"));

        ImageView iv = (ImageView) findViewById(R.id.chat_profile_img);
        Picasso.get().load("http://192.168.43.191/chatme/img/" + in.getStringExtra("path")).into(iv);

        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent showimg = new Intent(getApplicationContext(), Profile_Detail.class);
                showimg.putExtra("path", in.getStringExtra("path"));
                showimg.putExtra("name", in.getStringExtra("name"));
                showimg.putExtra("last_seen", in.getStringExtra("last_seen"));
                showimg.putExtra("status", in.getStringExtra("status"));
                v.getContext().startActivity(showimg);
            }
        });

        Timer timer = new Timer();
        timer.schedule(new GetMessage(), 0, 5000);

    }
    class GetMessage extends TimerTask {
        public void run() {
            newMessage();
        }
    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.chat_setting, menu);
        return true;
    }


    private void load_chat(){

        String url = "http://192.168.43.191/chatme/showchat.php";
        StringRequest MyStringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                fill_chat(response);
            }
        }, new Response.ErrorListener() { //Create an error listener to handle errors appropriately.
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),error.toString(),1).show();
            }
        }) {
            protected Map<String, String> getParams() {
                Map<String, String> MyData = new HashMap<String, String>();
                MyData.put("ID",My_Detail.My_ID);
                MyData.put("FID",in.getStringExtra("id"));
                return MyData;
            }
        };

        MySingleton.getInstance(getApplicationContext()).addRequestQueue(MyStringRequest);


    }

    private void fill_chat(String response){
        try{
            JSONArray jarray = new JSONArray(response);
            for(int n=0;n<jarray.length();n++)
            {
                JSONObject jobject = jarray.getJSONObject(n);
                Messages.add(jobject.getString("MESSAGE"));
                From.add(jobject.getString("_FROM"));
            }
        }
        catch(JSONException e){}
        showChat();
    }
    private void showChat(){
        Chats=new ChatAdapter(Messages,From);
        recycle_chat= findViewById(R.id.chat_screen_recycle);
        recycle_chat.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
        recycle_chat.setAdapter(Chats);
        if(Messages.size()>1)
        recycle_chat.smoothScrollToPosition(Messages.size()-1);
    }

    private void addText(final String text){

        String url = "http://192.168.43.191/chatme/addchat.php";
        StringRequest MyStringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

            }
        }, new Response.ErrorListener() { //Create an error listener to handle errors appropriately.
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),error.toString(),1).show();
            }
        }) {
            protected Map<String, String> getParams() {
                Map<String, String> MyData = new HashMap<String, String>();
                MyData.put("ID",My_Detail.My_ID);
                MyData.put("FID",in.getStringExtra("id"));
                MyData.put("MSG",text);
                return MyData;
            }
        };

        MySingleton.getInstance(getApplicationContext()).addRequestQueue(MyStringRequest);

    }

    private void newMessage(){
        String url = "http://192.168.43.191/chatme/newmessage.php";

        StringRequest MyStringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
               try {
                   JSONObject jobj = new JSONObject(response);
                   if (!jobj.getString("row").equals("0")) {
                       load_newMessage();
                   }
               }
               catch (JSONException e){

               }
            }
        }, new Response.ErrorListener() { //Create an error listener to handle errors appropriately.
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),error.toString(),1).show();
            }
        }) {
            protected Map<String, String> getParams() {
                Map<String, String> MyData = new HashMap<String, String>();
                MyData.put("ID",My_Detail.My_ID);
                MyData.put("FID",in.getStringExtra("id"));

                return MyData;
            }
        };

        MySingleton.getInstance(getApplicationContext()).addRequestQueue(MyStringRequest);

    }

    private void load_newMessage(){
        String url = "http://192.168.43.191/chatme/getnewmessage.php";

        StringRequest MyStringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                fill_newchat(response);

            }
        }, new Response.ErrorListener() { //Create an error listener to handle errors appropriately.
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),error.toString(),1).show();
            }
        }) {
            protected Map<String, String> getParams() {
                Map<String, String> MyData = new HashMap<String, String>();
                MyData.put("ID",My_Detail.My_ID);
                MyData.put("FID",in.getStringExtra("id"));

                return MyData;
            }
        };

        MySingleton.getInstance(getApplicationContext()).addRequestQueue(MyStringRequest);



    }

    private void fill_newchat(String response){
        try{
            JSONArray jarray = new JSONArray(response);
            for(int n=0;n<jarray.length();n++)
            {
                JSONObject jobject = jarray.getJSONObject(n);
                Messages.add(jobject.getString("MESSAGE"));
                From.add(in.getStringExtra("id"));
            }

            Chats.notifyDataSetChanged();
        }
        catch(JSONException e){}
        showChat();
    }


    private void deletechat(){
        String url = "http://192.168.43.191/chatme/deletechat.php";

        StringRequest MyStringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Messages.clear();
                From.clear();
                Chats.notifyDataSetChanged();
            }
        }, new Response.ErrorListener() { //Create an error listener to handle errors appropriately.
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),error.toString(),1).show();
            }
        }) {
            protected Map<String, String> getParams() {
                Map<String, String> MyData = new HashMap<String, String>();
                MyData.put("ID",My_Detail.My_ID);
                MyData.put("FID",in.getStringExtra("id"));

                return MyData;
            }
        };

        MySingleton.getInstance(getApplicationContext()).addRequestQueue(MyStringRequest);


    }

    private void unfriend(){
        deletechat();

        String url = "http://192.168.43.191/chatme/unfriend.php";
        StringRequest MyStringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
              finish();

            }
        }, new Response.ErrorListener() { //Create an error listener to handle errors appropriately.
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),error.toString(),1).show();
            }
        }) {
            protected Map<String, String> getParams() {
                Map<String, String> MyData = new HashMap<String, String>();
                MyData.put("UID",My_Detail.My_ID);
                MyData.put("FID",in.getStringExtra("id"));

                return MyData;
            }
        };

        MySingleton.getInstance(getApplicationContext()).addRequestQueue(MyStringRequest);

    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case R.id.deletechat:
                deletechat();
                break;
            case R.id.Unfriend:
                unfriend();
                break;
        }

        return true;
    }

}

