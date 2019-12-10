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

public class FriendAdapter extends RecyclerView.Adapter<FriendAdapter.FriendHolder> {

    private String[] Name;

    public FriendAdapter(String[] name) {
        Name = name;
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
        holder.friendTxt.setText(Name[position]);
    }

    @Override
    public int getItemCount() {
        return Name.length;
    }

    public class FriendHolder extends RecyclerView.ViewHolder{

        ImageView friendImg;
        TextView  friendTxt;

        public FriendHolder(@NonNull View itemView) {
            super(itemView);
            friendImg = itemView.findViewById(R.id.friend_img);
            friendTxt = itemView.findViewById(R.id.friend_name);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent in=new Intent(v.getContext(),ChatScreen.class);
                    in.putExtra("name",Name[getAdapterPosition()]);
                    v.getContext().startActivity(in);

                }
            });

            friendImg.setOnClickListener(new View.OnClickListener() {
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
