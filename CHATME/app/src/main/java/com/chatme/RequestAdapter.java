package com.chatme;

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

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class RequestAdapter extends RecyclerView.Adapter<RequestAdapter.RequestHolder> {

    private ArrayList<String> Id;
    private ArrayList<String> Name;
    private ArrayList<String> Picture;
    private ArrayList<String> Status;
    private ArrayList<String> Last_Seen;
    private Fragment_Request  fragment;

    public RequestAdapter(Fragment_Request  frag,ArrayList<String> id, ArrayList<String> name, ArrayList<String> picture, ArrayList<String> status, ArrayList<String> last_Seen) {
        Id = id;
        Name = name;
        Picture = picture;
        Status = status;
        Last_Seen = last_Seen;
        fragment=frag;
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
        Picasso.get().load("http://192.168.43.191/chatme/img/"+Picture.get(position)).into(holder.RequestImg);
        holder.RequestTxt.setText(Name.get(position));
    }

    @Override
    public int getItemCount() {
        return Name.size();
    }

    public void removeItem(int position) {
        Name.remove(position);
        notifyItemRemoved(position);
    }

    public class RequestHolder extends RecyclerView.ViewHolder{

        ImageView RequestImg;
        TextView  RequestTxt;
        Button Request_accept;
        Button Request_reject;

        public RequestHolder(@NonNull View itemView) {
            super(itemView);
            RequestImg = itemView.findViewById(R.id.request_img);
            RequestTxt = itemView.findViewById(R.id.request_name);
            Request_accept = itemView.findViewById(R.id.request_accept_btn);
            Request_reject = itemView.findViewById(R.id.request_reject_btn);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent in=new Intent(v.getContext(),Profile_Detail.class);
                    in.putExtra("name",Name.get(getAdapterPosition()));
                    in.putExtra("path",Picture.get(getAdapterPosition()));
                    in.putExtra("status",Status.get(getAdapterPosition()));
                    in.putExtra("last_seen",Last_Seen.get(getAdapterPosition()));
                    v.getContext().startActivity(in);

                }
            });

            Request_accept.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    fragment.request_answer(Id.get(getAdapterPosition()),"y");
                    removeItem(getAdapterPosition());
                    Toast.makeText(v.getContext(), "Accepted", 1).show();

                }
            });

            Request_reject.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    fragment.request_answer(Id.get(getAdapterPosition()),"n");
                    removeItem(getAdapterPosition());
                    Toast.makeText(v.getContext(),"Rejected",1).show();
                }
            });


        }
    }
}



