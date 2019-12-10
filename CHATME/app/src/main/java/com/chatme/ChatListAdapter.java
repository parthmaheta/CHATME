package com.chatme;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ChatListAdapter extends RecyclerView.Adapter<ChatListAdapter.ChatHolder> {

    private String[] Name;
    private String[] last_chats;
    private String[] chat_Time;
    public ChatListAdapter(String[] name,String[] lastchat,String[] chatTime) {
        Name = name;
        last_chats = lastchat;
        chat_Time=chatTime;
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
        holder.ChatName.setText(Name[position]);
        holder.ChatTxt.setText(last_chats[position]);
        holder.ChatTime.setText(chat_Time[position]);

    }

    @Override
    public int getItemCount() {
        return Name.length;
    }

    public class ChatHolder extends RecyclerView.ViewHolder{

        ImageView ChatImg;
        TextView  ChatName;
        TextView  ChatTxt;
        TextView  ChatTime;

        public ChatHolder(@NonNull View itemView) {
            super(itemView);
            ChatImg = itemView.findViewById(R.id.chat_friend_img);
            ChatName = itemView.findViewById(R.id.chat_friend_name);
            ChatTxt = itemView.findViewById(R.id.chat_friend_text);
            ChatTime = itemView.findViewById(R.id.chat_friend_time);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent in=new Intent(v.getContext(),ChatScreen.class);
                    in.putExtra("name",Name[getAdapterPosition()]);
                    v.getContext().startActivity(in);

                }
            });

            ChatImg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent in=new Intent(v.getContext(),Profile_Detail.class);
                    in.putExtra("name",Name[getAdapterPosition()]);
                    v.getContext().startActivity(in);
                }
            });

        }


    }
}
