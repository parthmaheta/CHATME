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

public class StatusAdapter extends RecyclerView.Adapter<StatusAdapter.StatusHolder> {

    private String[] Name;

    public StatusAdapter(String[] name) {
        Name = name;
    }

    @NonNull
    @Override
    public StatusHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater LV=LayoutInflater.from(parent.getContext());
        View view=LV.inflate(R.layout.status_item,parent,false);
        return  new StatusHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull StatusHolder holder, int position) {
        holder.StatusTxt.setText(Name[position]);
    }

    @Override
    public int getItemCount() {
        return Name.length;
    }

    public class StatusHolder extends RecyclerView.ViewHolder{

        ImageView StatusImg;
        TextView  StatusTxt;

        public StatusHolder(@NonNull View itemView) {
            super(itemView);
            StatusImg = itemView.findViewById(R.id.status_img);
            StatusTxt = itemView.findViewById(R.id.status_name);

            StatusImg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent in=new Intent(v.getContext(),View_Img.class);
                    in.putExtra("name",Name[getAdapterPosition()]);
                    v.getContext().startActivity(in);
                }
            });

        }
    }
}
