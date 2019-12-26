package com.chatme;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FriendAdapter extends RecyclerView.Adapter<FriendAdapter.FriendHolder> {

    ArrayList<String> Name = new ArrayList<String>();
    ArrayList<String> Picture = new ArrayList<String>();
    ArrayList<String> Id = new ArrayList<String>();
    Context CTX;

    public FriendAdapter(Context ctx,ArrayList<String> id,ArrayList<String> name, ArrayList<String> picture) {
        Name = name;
        Picture = picture;
        Id = id;
        CTX=ctx;
    }

    @NonNull
    @Override
    public FriendHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater LV=LayoutInflater.from(parent.getContext());
        View view=LV.inflate(R.layout.friend_list,parent,false);
        return  new FriendHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull FriendHolder holder, int position) {
        holder.FriendTxt.setText(Name.get(position));
        Picasso.get().load("http://192.168.43.191/chat_me/img/"+Picture.get(position)).into(holder.FriendImg);
    }

    @Override
    public int getItemCount() {
        return Name.size();
    }

    public void removeItem(int position) {
        Name.remove(position);
        notifyItemRemoved(position);
    }

    public class FriendHolder extends RecyclerView.ViewHolder{

        ImageView FriendImg;
        TextView  FriendTxt;
        Button FriendBtn;

        public FriendHolder(@NonNull View itemView) {
            super(itemView);
            FriendImg = itemView.findViewById(R.id.friend_img);
            FriendTxt = itemView.findViewById(R.id.friend_name);
            FriendBtn = itemView.findViewById(R.id.addfriendbtn);

            FriendBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    send_request(Id.get(getAdapterPosition()));
                    removeItem(getAdapterPosition());
                }
            });
        }
    }
    public void send_request(final String id){

        RequestQueue MyRequestQueue = Volley.newRequestQueue(CTX);

        String url = "http://192.168.43.191/chatme/send_request.php";
        StringRequest MyStringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(CTX,"Request Sent",1).show();
            }
        }, new Response.ErrorListener() { //Create an error listener to handle errors appropriately.
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(CTX,error.toString(),1).show();
            }
        }) {
            protected Map<String, String> getParams() {
                Map<String, String> MyData = new HashMap<String, String>();
                MyData.put("UID",My_Detail.My_ID);
                MyData.put("FID",id);

                return MyData;
            }
        };

        MyRequestQueue.add(MyStringRequest);

    }

}