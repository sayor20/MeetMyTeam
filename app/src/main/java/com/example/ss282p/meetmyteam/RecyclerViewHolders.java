package com.example.ss282p.meetmyteam;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class RecyclerViewHolders extends RecyclerView.ViewHolder{

    public TextView empName;
    public ImageView empPhoto;

    public RecyclerViewHolders(View itemView) {
        super(itemView);
        empName = (TextView)itemView.findViewById(R.id.emp_name);
        empPhoto = (ImageView)itemView.findViewById(R.id.emp_photo);
    }
}
