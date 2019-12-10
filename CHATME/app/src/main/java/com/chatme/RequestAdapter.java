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

public class RequestAdapter extends RecyclerView.Adapter<RequestAdapter.RequestHolder> {

    private String[] Name;

    public RequestAdapter(String[] name) {
        Name = name;
    }

    @NonNull
    @Override
    public RequestHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater LV=LayoutInflater.from(parent.getContext());
        View view=LV.inflate(R.layout.request_list,parent,false);
        return  new RequestHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull RequestHolder holder, int position) {
        holder.requestName.setText(Name[position]);
    }

    @Override
    public int getItemCount() {
        return Name.length;
    }

    public class RequestHolder extends RecyclerView.ViewHolder{

        ImageView requestImg;
        TextView  requestName;

        public RequestHolder(@NonNull View itemView) {
            super(itemView);
            requestImg = itemView.findViewById(R.id.request_img);
            requestName = itemView.findViewById(R.id.request_name);

            requestImg.setOnClickListener(new View.OnClickListener() {
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
