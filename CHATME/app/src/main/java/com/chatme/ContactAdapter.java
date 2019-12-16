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

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ContactHolder> {

    private String[] Name;

    public ContactAdapter(String[] name) {
        Name = name;
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
        holder.ContactTxt.setText(Name[position]);
    }

    @Override
    public int getItemCount() {
        return Name.length;
    }

    public class ContactHolder extends RecyclerView.ViewHolder{

        ImageView ContactImg;
        TextView  ContactTxt;

        public ContactHolder(@NonNull View itemView) {
            super(itemView);
            ContactImg = itemView.findViewById(R.id.contact_list_img);
            ContactTxt = itemView.findViewById(R.id.contact_list_name);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent in=new Intent(v.getContext(),ChatScreen.class);
                    in.putExtra("name",Name[getAdapterPosition()]);
                    v.getContext().startActivity(in);

                }
            });

            ContactImg.setOnClickListener(new View.OnClickListener() {
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
