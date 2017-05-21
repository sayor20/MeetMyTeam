package com.example.ss282p.meetmyteam.adapters;

import android.content.Context;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ss282p.meetmyteam.EmployeeClickListener;
import com.example.ss282p.meetmyteam.R;
import com.example.ss282p.meetmyteam.RecyclerViewHolders;
import com.example.ss282p.meetmyteam.models.Employee;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewHolders> {

    private List<Employee> empList;
    private Context context;
    private EmployeeClickListener mListener;


    public RecyclerViewAdapter(Context context, List<Employee> empList, EmployeeClickListener mListener) {
        this.empList = empList;
        this.context = context;
        this.mListener = mListener;
    }

    @Override
    public RecyclerViewHolders onCreateViewHolder(ViewGroup parent, int viewType) {

        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_grid, null);
        RecyclerViewHolders rcv = new RecyclerViewHolders(layoutView);
        return rcv;
    }

    @Override
    public void onBindViewHolder(final RecyclerViewHolders holder, final int position) {
        holder.empName.setText(empList.get(position).getfName());
        Picasso.with(context).load(empList.get(position).getAvatar()).placeholder(R.drawable.bg_gray).into(holder.empPhoto);

        // It is important that each shared element in the source screen has a unique transition name.
        // For example, we can't just give all the images in our grid the transition name "kittenImage"
        // because then we would have conflicting transition names.
        // By appending "_empimage" to the position, we can support having multiple shared elements in each
        // grid cell in the future.
        ViewCompat.setTransitionName(holder.empPhoto, String.valueOf(position) + "_empimage");

        holder.empPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.onEmployeeClicked(holder, position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return this.empList.size();
    }
}