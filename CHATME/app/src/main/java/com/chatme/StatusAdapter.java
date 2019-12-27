package com.chatme;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class StatusAdapter extends RecyclerView.Adapter<StatusAdapter.StatusHolder> {

    private ArrayList<String> Name;
    private ArrayList<String> Path;

    public StatusAdapter(ArrayList<String> name,ArrayList<String> path)  {
        Name=name;
        Path=path;
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
        holder.StatusTxt.setText(Name.get(position));
        Picasso.get().load("http://192.168.43.191/chatme/img/"+Path.get(position)).into(holder.StatusImg);
    }

    @Override
    public int getItemCount() {
        return Name.size();
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
                    in.putExtra("name",Name.get(getAdapterPosition()));
                    in.putExtra("path",Path.get(getAdapterPosition()));
                    v.getContext().startActivity(in);
                }
            });

        }
    }
}