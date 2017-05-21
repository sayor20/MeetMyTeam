package com.example.ss282p.meetmyteam.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ss282p.meetmyteam.models.Employee;
import com.example.ss282p.meetmyteam.R;
import com.squareup.picasso.Picasso;
import java.util.List;


public class DetailsFragment extends Fragment {
    private static final String EMP = "employee";
    private List<Employee> empList;

    public static DetailsFragment newInstance(Employee employee) {
        Bundle args = new Bundle();
        args.putParcelable(EMP, employee);

        DetailsFragment fragment = new DetailsFragment();
        fragment.setArguments(args);

        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_details, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ImageView ivAvatar = (ImageView) view.findViewById(R.id.ivAvatar);
        TextView tvFirstName = (TextView) view.findViewById(R.id.tvFirstName);
        TextView tvLastName = (TextView) view.findViewById(R.id.tvLastName);
        TextView tvTitle = (TextView) view.findViewById(R.id.tvTitle);
        TextView tvBio = (TextView) view.findViewById(R.id.tvBio);

        Bundle args = getArguments();
        Employee emp =  args.getParcelable(EMP);

        if(emp!=null){
            Picasso.with(getActivity()).load(emp.getAvatar()).placeholder(R.drawable.bg_gray).into(ivAvatar);
            tvFirstName.setText(emp.getfName());
            tvLastName.setText(emp.getlName());
            tvTitle.setText(emp.getTitle());
            tvBio.setText(emp.getBio());
        }
    }
}