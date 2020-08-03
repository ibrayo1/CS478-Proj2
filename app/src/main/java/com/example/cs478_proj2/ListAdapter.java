package com.example.cs478_proj2;

import android.content.Context;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.MyViewHolder> implements View.OnCreateContextMenuListener {

    private Context mContext;
    private CustomItemClickListener clickListener;
    private ArrayList<String> phoneList;
    private ArrayList<String> prcList;
    private int itemPosition;

    public ListAdapter(Context mContext, ArrayList<String> phoneList, ArrayList<String> prcList, CustomItemClickListener clickListener){
        this.mContext = mContext;
        this.phoneList = phoneList;
        this.prcList = prcList;
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){

        View view = LayoutInflater.from(mContext).inflate(R.layout.listitem_layout, parent, false);

        final MyViewHolder viewHolder = new MyViewHolder(view);

        // set the short click listener for this item
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickListener.onItemClick(view, viewHolder.getPosition());
            }
        });

        view.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                itemPosition = viewHolder.getPosition(); // store position of the itme long clicked on
                return false;
            }
        });

        return viewHolder;
    }

    // inflate context menu from menu resource file
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        MenuInflater mInflater = new MenuInflater(mContext.getApplicationContext());
        mInflater.inflate(R.menu.menu, menu);
    }

    // returns the position of the item the user long clicked on
    public int getItemPosition(){ return this.itemPosition; }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position){
        // set the text with the name of the phone
        holder.phoneName.setText(phoneList.get(position));

        // set the price and screen size of phone
        String[] desc = prcList.get(position).split(",");
        holder.phoneDesc.setText(desc[0] + " in - $" + desc[1]);

        // set the image for the item in the list
        holder.phoneImage.setImageResource(mContext.getResources().getIdentifier(phoneList.get(position).replace(" ", "_").toLowerCase(),
                "drawable", mContext.getPackageName()));
    }


    @Override
    public int getItemCount(){
        return phoneList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView phoneImage;
        TextView phoneName;
        TextView phoneDesc;

        public MyViewHolder(@NonNull View itemView){
            super(itemView);

            phoneImage = (ImageView) itemView.findViewById(R.id.phone_thumb);
            phoneName = (TextView) itemView.findViewById(R.id.phone_name);
            phoneDesc = (TextView) itemView.findViewById(R.id.phone_desc);

            itemView.setOnCreateContextMenuListener(ListAdapter.this::onCreateContextMenu); // create context menu from long click
        }
    }
}
