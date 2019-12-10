package com.chatme;

import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import android.widget.ImageView;

import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class Fragment_Home extends Fragment {
    String[] Names={"name1","name2","name3"};
    String[] Chats={"Hii...?","How r u ?","last message"};
    String[] Status={"Status1","Status2","Status3","Status4","Status5","Status6"};
    String[] chat_time={"12:12","08:00","04:05"};

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {


        View view=inflater.inflate(R.layout.fragment_home, container, false);

        RecyclerView Recycle_Status= view.findViewById(R.id.fragment_status_recyclerview);
        Recycle_Status.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        Recycle_Status.setAdapter(new StatusAdapter(Status));

        RecyclerView Recycle_Chat= view.findViewById(R.id.fragment_chat_recyclerview);
        Recycle_Chat.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        Recycle_Chat.setAdapter(new ChatListAdapter(Names,Chats,chat_time));

        return view;

    }


    }
