package com.chatme;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class Fragment_Request extends Fragment {

    String[] Names={"friend1","friend2","friend3"};

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_request, container, false);
        RecyclerView recycle_Requests= view.findViewById(R.id.request_recycleview);
        recycle_Requests.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        recycle_Requests.setAdapter(new RequestAdapter(Names));


        return view;
    }
}
