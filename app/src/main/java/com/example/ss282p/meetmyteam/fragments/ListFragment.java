package com.example.ss282p.meetmyteam.fragments;


import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.transition.Slide;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ss282p.meetmyteam.DetailsTransition;
import com.example.ss282p.meetmyteam.models.Employee;
import com.example.ss282p.meetmyteam.EmployeeClickListener;
import com.example.ss282p.meetmyteam.R;
import com.example.ss282p.meetmyteam.adapters.RecyclerViewAdapter;
import com.example.ss282p.meetmyteam.RecyclerViewHolders;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class ListFragment extends Fragment implements EmployeeClickListener {

    private GridLayoutManager lLayout;
    private List<Employee> rowListItem;

    public ListFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rowListItem = getAllItemList();
        lLayout = new GridLayoutManager(getActivity(), 3);

        RecyclerView rView = (RecyclerView)view.findViewById(R.id.rvEmpList);
        rView.setHasFixedSize(true);
        rView.setLayoutManager(lLayout);

        RecyclerViewAdapter rcAdapter = new RecyclerViewAdapter(getActivity(), rowListItem, this);
        rView.setAdapter(rcAdapter);
    }

    private List<Employee> getAllItemList(){
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();
        return gson.fromJson(loadJSONFromAsset(), new TypeToken<List<Employee>>() {}.getType());
    }

    public String loadJSONFromAsset() {
        String json = null;
        try {
            InputStream is = getActivity().getAssets().open("team.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    @Override
    public void onEmployeeClicked(RecyclerViewHolders holder, int position) {
        DetailsFragment employeeDetails = DetailsFragment.newInstance(rowListItem.get(position));

        // Note that we need the API version check here because the actual transition classes (e.g. Slide)
        // are not in the support library and are only available in API 21+. The methods we are calling on the Fragment
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            employeeDetails.setSharedElementEnterTransition(new DetailsTransition());
            setSharedElementReturnTransition(new DetailsTransition());
            setExitTransition(new Slide());
        }

       getActivity().getSupportFragmentManager()
                .beginTransaction()
                .addSharedElement(holder.empPhoto, "empphoto")
                .replace(R.id.container, employeeDetails)
                .addToBackStack(null)
                .commit();
    }
}
