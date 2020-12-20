package com.hammer67.tutorialrecylclerviewjava.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.hammer67.tutorialrecylclerviewjava.R;
import com.hammer67.tutorialrecylclerviewjava.models.Mountain;

import java.util.ArrayList;

public class ItemGridAdapter extends RecyclerView.Adapter<ItemGridAdapter.GridViewholder> {

    private Context context;
    private ArrayList<Mountain> listMountain;

    public ItemGridAdapter(Context context) {
        this.context = context;
    }

    public ArrayList<Mountain> getListMountain() {
        return listMountain;
    }

    public void setListMountain(ArrayList<Mountain> listMountain) {
        this.listMountain = listMountain;
    }

    @NonNull
    @Override
    public GridViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_grid, parent, false);
        return new GridViewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GridViewholder holder, int position) {
        Glide.with(context).load(getListMountain().get(position).getPhoto()).into(holder.img_grid);
    }

    @Override
    public int getItemCount() {
        return getListMountain().size();
    }

    public class GridViewholder extends RecyclerView.ViewHolder {

        ImageView img_grid;

        public GridViewholder(@NonNull View itemView) {
            super(itemView);

            img_grid = itemView.findViewById(R.id.img_grid);
        }
    }
}
