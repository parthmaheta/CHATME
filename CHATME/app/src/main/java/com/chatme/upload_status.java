package com.chatme;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class upload_status extends AppCompatActivity {

    public static final int PICK_IMAGE = 1;
    ImageView statusImg;
    Button choose;
    Button save;
    ArrayList<String> name = new ArrayList<String>();
    ArrayList<String> path = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.upload_status);

        statusImg=(ImageView)findViewById(R.id.status_img);
        choose=(Button)findViewById(R.id.choose_status_btn);
        save=(Button)findViewById(R.id.upload_status__btn);

        //show list of my uploaded status
        load_status();



        choose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent,PICK_IMAGE);

            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bitmap bm = ((BitmapDrawable) statusImg.getDrawable()).getBitmap();
                addStatus(getStringImage(bm));

            }
        });

    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==PICK_IMAGE && resultCode==-1 &&
                data!=null && data.getData()!=null){
            Uri uri=data.getData();
            Picasso.get().load(uri).into(statusImg);
            save.setVisibility(View.VISIBLE);
        }
    }

    public String getStringImage(Bitmap bmp) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] imageBytes = baos.toByteArray();
        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        return encodedImage;

    }

    private void addStatus(final String img){
        String url = "http://192.168.43.191/chatme/addstatus.php";
        StringRequest MyStringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(getApplicationContext(),"Added",1).show();
                Intent i=new Intent(getApplicationContext(),upload_status.class);
                startActivity(i);
                finish();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),error.toString(),1).show();
            }
        }) {
            protected Map<String, String> getParams() {
                Map<String, String> MyData = new HashMap<String, String>();
                MyData.put("file",img);
                MyData.put("ID",My_Detail.My_ID);

                return MyData;
            }
        };

        MySingleton.getInstance(getApplicationContext()).addRequestQueue(MyStringRequest);
    }

    private void load_status(){

        String url = "http://192.168.43.191/chatme/mystatus.php";
        StringRequest MyStringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                fill_status(response);

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

    private void fill_status(String response){
        try{
            JSONArray jarray = new JSONArray(response);

            for(int n=0;n<jarray.length();n++)
            {
                int index=n+1;
                JSONObject jobject = jarray.getJSONObject(n);
                name.add("STATUS "+index);
                path.add(jobject.getString("FILEPATH"));

            }
            showStatuses();
        }
        catch(JSONException e){}

    }
    private void showStatuses(){
        RecyclerView recycle_status= (RecyclerView) findViewById(R.id.my_statuses_recycleview);
        recycle_status.setLayoutManager(new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL,false));
        recycle_status.setAdapter(new ContactAdapter(name,path,"upload_status"));
    }
}
