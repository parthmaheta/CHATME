package com.chatme;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.navigation.NavigationView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ContactHolder> {

    private ArrayList<String> Name;
    private ArrayList<String> ID;
    private ArrayList<String> Path;
    private ArrayList<String> LAST_SEEN;
    private ArrayList<String> Status;
    private String From="";

    public ContactAdapter(ArrayList<String> name,ArrayList<String> path,String from)  {
        Name=name;
        Path=path;
        From=from;
    }

    public ContactAdapter(ArrayList<String> id,ArrayList<String> name,ArrayList<String> picture,ArrayList<String> status,ArrayList<String> seen,String from)  {
        ID=id;
        Name=name;
        Path=picture;
        Status=status;
        LAST_SEEN=seen;
        From=from;
    }

    @NonNull
    @Override
    public ContactHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater LV=LayoutInflater.from(parent.getContext());
        View view=LV.inflate(R.layout.contact_list,parent,false);
        return  new ContactHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ContactHolder holder, int position) {
        holder.Txt.setText(Name.get(position));

        Picasso.get().load("http://192.168.43.191/chatme/img/"+Path.get(position)).into(holder.Img);

    }

    @Override
    public int getItemCount() {
        return Name.size();
    }

    public class ContactHolder extends RecyclerView.ViewHolder{

        ImageView Img;
        TextView  Txt;

        public ContactHolder(@NonNull View itemView) {
            super(itemView);
            Img = itemView.findViewById(R.id.contact_list_img);
            Txt = itemView.findViewById(R.id.contact_list_name);


            if (!From.equals("upload_status")) {

                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent in = new Intent(v.getContext(), ChatScreen.class);
                        in.putExtra("name", Name.get(getAdapterPosition()));
                        in.putExtra("path", Path.get(getAdapterPosition()));
                        in.putExtra("status",Status.get(getAdapterPosition()));
                        in.putExtra("id",ID.get(getAdapterPosition()));
                        in.putExtra("last_seen",LAST_SEEN.get(getAdapterPosition()));
                        v.getContext().startActivity(in);

                    }
                });


                Img.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent in = new Intent(v.getContext(), Profile_Detail.class);
                        in.putExtra("name", Name.get(getAdapterPosition()));
                        in.putExtra("path", Path.get(getAdapterPosition()));
                        in.putExtra("status",Status.get(getAdapterPosition()));
                        in.putExtra("last_seen",LAST_SEEN.get(getAdapterPosition()));
                        v.getContext().startActivity(in);
                    }
                });
            }
        }
    }
}

