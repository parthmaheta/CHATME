package com.chatme;

import android.content.Intent;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.MessageHolder> {

    private ArrayList<String> Messages;
    private ArrayList<String> From;


    public ChatAdapter(ArrayList<String> messages,ArrayList<String> from) {
        Messages = messages; From = from;
    }

    @NonNull
    @Override
    public MessageHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater LV=LayoutInflater.from(parent.getContext());
        View view=LV.inflate(R.layout.message_list,parent,false);

        return  new MessageHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull MessageHolder holder, int position) {

        holder.msgTxt.setText(Messages.get(position));

        //set my message at right side
        if(From.get(position).equals(My_Detail.My_ID)){
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.MATCH_PARENT);
            params.weight = 1.0f;
            params.gravity = Gravity.RIGHT;
            params.setMargins(20,0,0,0);
            holder.msgTxt.setLayoutParams(params);
        }
    }

    @Override
    public int getItemCount() {
        return Messages.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    public class MessageHolder extends RecyclerView.ViewHolder{


        TextView  msgTxt;


        public MessageHolder(@NonNull View itemView) {
            super(itemView);

            msgTxt = itemView.findViewById(R.id.message_text);

        }
    }
}