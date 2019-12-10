package com.chatme;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class Fragment_Profile extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_profile, container, false);
        ImageView profileImg=(ImageView)view.findViewById(R.id.my_profile_img);

        profileImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent viewImg=new Intent(getContext(),View_Img.class);
                viewImg.putExtra("name","ChatMe");
                startActivity(viewImg);
            }
        });

        Button btn=(Button)view.findViewById(R.id.change_profile_img_btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("*/*");
                startActivityForResult(intent,7);
            }
        });

        return view;
    }




    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        switch(requestCode){

            case 7:

                if(resultCode== Activity.RESULT_OK){

                    String PathHolder = data.getData().getPath();

                    Toast.makeText(getContext(), PathHolder , Toast.LENGTH_LONG).show();

                }
                break;

        }
    }


}
