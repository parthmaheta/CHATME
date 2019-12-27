package com.chatme;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
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
import com.squareup.picasso.Picasso;

import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Map;

public class Fragment_Profile extends Fragment {

    public static final int PICK_IMAGE = 1;
    ImageView profileImg;
    TextView status;
    TextView name;
    View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_profile, container, false);
        profileImg=(ImageView)view.findViewById(R.id.my_profile_img);
        name=(TextView)view.findViewById(R.id.profile_name);
        status=(TextView)view.findViewById(R.id.profile_status);

        Picasso.get().load("http://192.168.43.191/chatme/img/"+My_Detail.My_PICTURE).into(profileImg);
        name.setText(My_Detail.My_NAME);
        status.setText(My_Detail.My_STATUS);

        profileImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent viewImg=new Intent(getContext(),View_Img.class);
                viewImg.putExtra("name", My_Detail.My_NAME);
                viewImg.putExtra("path", My_Detail.My_PICTURE);
                startActivity(viewImg);
            }
        });

        Button change_btn=(Button)view.findViewById(R.id.change_profile_img_btn);
        change_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setType("image/jpeg");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent,PICK_IMAGE);
            }
        });

        Button save_btn=(Button)view.findViewById(R.id.Save_profile__btn);
        save_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isvalid()) {
                    Bitmap bm = ((BitmapDrawable) profileImg.getDrawable()).getBitmap();
                    updateProfile(getStringImage(bm));
                }
                else{
                    Toast.makeText(getContext(),"enter details",1).show();
                }
            }
        });

        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==PICK_IMAGE && resultCode==-1 &&
                data!=null && data.getData()!=null){
            Uri uri=data.getData();
            Picasso.get().load(uri).into(profileImg);
        }
    }

    private boolean isvalid(){


        if(name.getText().toString().equals("") || status.getText().toString().equals(""))
            return false;
        return true;
    }

    public String getStringImage(Bitmap bmp) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] imageBytes = baos.toByteArray();
        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        return encodedImage;

    }


    private void updateProfile(final String img){

        String url = "http://192.168.43.191/chatme/updateprofile.php";
        StringRequest MyStringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                  updateMy_Detail(response);
                }
        }, new Response.ErrorListener() { //Create an error listener to handle errors appropriately.
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(),error.toString(),1).show();
            }
        }) {
            protected Map<String, String> getParams() {
                Map<String, String> MyData = new HashMap<String, String>();
                MyData.put("file",img);
                MyData.put("ID",My_Detail.My_ID);
                MyData.put("NAME",name.getText().toString());
                MyData.put("STATUS",status.getText().toString());
                return MyData;
            }
        };


        MySingleton.getInstance(getContext()).addRequestQueue(MyStringRequest);
    }

    private void updateMy_Detail(String response){
        try{
            JSONObject jobj=new JSONObject(response);
            My_Detail.My_PICTURE=jobj.getString("PICTURE");
            My_Detail.My_STATUS=status.getText().toString();
            My_Detail.My_NAME=name.getText().toString();

            Database_Handler db=new Database_Handler(getContext());
            db.deleteuser();
            db.addusr(My_Detail.My_ID,My_Detail.My_NAME,My_Detail.My_EMAIL,My_Detail.My_PASSWORD,My_Detail.My_PICTURE, My_Detail.My_STATUS);

            Toast.makeText(getContext(),"Updated",1).show();
        }
        catch(Exception e){

        }

    }
}