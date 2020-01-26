package com.chatme;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.squareup.picasso.Picasso;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChatListAdapter extends RecyclerView.Adapter<ChatListAdapter.ChatHolder> {

    ArrayList<String> Name = new ArrayList<String>();
    ArrayList<String> Picture = new ArrayList<String>();
    ArrayList<String> Id = new ArrayList<String>();
    ArrayList<String> Status = new ArrayList<String>();
    ArrayList<String> Last_seen = new ArrayList<String>();
    Context CTX;

    public ChatListAdapter(Context ctx,ArrayList<String> id,ArrayList<String> name,ArrayList<String> picture,ArrayList<String> status, ArrayList<String> lastseen) {
        Name = name;
        Picture=picture;
        Id=id;
        Status=status;
        Last_seen = lastseen;
        CTX=ctx;
    }

    @NonNull
    @Override
    public ChatHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater LV=LayoutInflater.from(parent.getContext());
        View view=LV.inflate(R.layout.chat_list,parent,false);

        return  new ChatHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ChatHolder holder, int position) {
        Picasso.get().load("http://192.168.43.191/chatme/img/"+Picture.get(position)).into(holder.ChatImg);
        holder.ChatName.setText(Name.get(position));
        load_lastMsg(holder,position);
    }

    private void load_lastMsg(final ChatHolder holder,final int position){


        String url = "http://192.168.43.191/chatme/newmessage.php";
        StringRequest MyStringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                fillText(holder,response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {
            protected Map<String, String> getParams() {
                Map<String, String> MyData = new HashMap<String, String>();
                MyData.put("ID",My_Detail.My_ID);
                MyData.put("FID",Id.get(position));
                return MyData;
            }
        };
        MySingleton.getInstance(CTX).addRequestQueue(MyStringRequest);

    }

    private void fillText(ChatHolder holder,String response){

        try {
            JSONObject jobj = new JSONObject(response);
            if (!jobj.getString("row").equals("0")) {
                holder.ChatTxt.setText(jobj.getString("row") + "  new Message");
                holder.ChatTxt.setTextColor(Color.parseColor("#ff0000"));
            }
        }
        catch(Exception e){}
    }


    @Override
    public int getItemCount() {
        return Name.size();
    }

    public class ChatHolder extends RecyclerView.ViewHolder{

        ImageView ChatImg;
        TextView  ChatName;
        TextView  ChatTxt;


        public ChatHolder(@NonNull View itemView) {
            super(itemView);
            ChatImg = itemView.findViewById(R.id.chat_friend_img);
            ChatName = itemView.findViewById(R.id.chat_friend_name);
            ChatTxt =itemView.findViewById(R.id.chat_friend_text);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent in=new Intent(v.getContext(),ChatScreen.class);
                    in.putExtra("name",Name.get(getAdapterPosition()));
                    in.putExtra("path",Picture.get(getAdapterPosition()));
                    in.putExtra("id",Id.get(getAdapterPosition()));
                    v.getContext().startActivity(in);

                }
            });

            ChatImg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent in=new Intent(v.getContext(),Profile_Detail.class);
                    in.putExtra("name",Name.get(getAdapterPosition()));
                    in.putExtra("status",Status.get(getAdapterPosition()));
                    in.putExtra("last_seen",Last_seen.get(getAdapterPosition()));
                    in.putExtra("path",Picture.get(getAdapterPosition()));
                    v.getContext().startActivity(in);
                }
            });

        }
    }
}