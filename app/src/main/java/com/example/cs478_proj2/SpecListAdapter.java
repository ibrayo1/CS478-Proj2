package com.example.cs478_proj2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class SpecListAdapter extends RecyclerView.Adapter<SpecListAdapter.MyViewHolder> {

    private Context mContext;
    private String[] specList;

    public SpecListAdapter(Context mContext, String[] specList){
        this.mContext = mContext;
        this.specList = specList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){

        View view = LayoutInflater.from(mContext).inflate(R.layout.specitem_layout, parent, false);

        final MyViewHolder viewHolder = new MyViewHolder(view);

        return viewHolder;
    }



    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position){
        // set the text with specific text
        holder.spec.setText(specList[position]);
    }

    @Override
    public int getItemCount(){ return specList.length; }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView spec;

        public MyViewHolder(@NonNull View itemView){
            super(itemView);

            spec = (TextView) itemView.findViewById(R.id.spec_item_desc);
        }
    }
}
