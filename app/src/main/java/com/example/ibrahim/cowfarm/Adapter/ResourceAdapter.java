package com.example.ibrahim.cowfarm.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ibrahim.cowfarm.R;

import java.util.ArrayList;

/**
 * Created by Ibrahim on 10/7/2018.
 */

public class ResourceAdapter extends RecyclerView.Adapter<ResourceAdapter.MyViewHolder> {


    ArrayList<String> nameList ;
    Context context;

    public ResourceAdapter(Context context,ArrayList<String> nameList)
    {
        this.context = context;
        this.nameList = nameList;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.item_resource,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return nameList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public MyViewHolder(View itemView) {
            super(itemView);
        }
    }
}
