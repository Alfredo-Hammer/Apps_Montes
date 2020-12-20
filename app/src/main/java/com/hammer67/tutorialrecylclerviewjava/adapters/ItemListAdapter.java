package com.hammer67.tutorialrecylclerviewjava.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.hammer67.tutorialrecylclerviewjava.R;
import com.hammer67.tutorialrecylclerviewjava.models.Mountain;

import java.util.ArrayList;

public class ItemListAdapter extends RecyclerView.Adapter<ItemListAdapter.ListViewHolder> {

    private Context context;
    private ArrayList<Mountain> listMountains;

    public ItemListAdapter(Context context) {
        this.context = context;
    }

    public ArrayList<Mountain> getListMountains() {
        return listMountains;
    }

    public void setListMountains(ArrayList<Mountain> listMountains) {
        this.listMountains = listMountains;
    }

    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.items_list,parent, false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListViewHolder holder, int position) {
        holder.tvName.setText(getListMountains().get(position).getName());
        holder.tvElevation.setText(getListMountains().get(position).getElevation());

        Glide.with(context).load(getListMountains().get(position).getPhoto()).into(holder.imgList);

        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, getListMountains().get(position).getName(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return getListMountains().size();
    }

    public class ListViewHolder extends RecyclerView.ViewHolder {

        TextView tvName, tvElevation;
        ImageView imgList;
        RelativeLayout relativeLayout;

        public ListViewHolder(@NonNull View itemView) {
            super(itemView);

            tvName = itemView.findViewById(R.id.tv_name_list);
            tvElevation = itemView.findViewById(R.id.tv_elevation_list);
            imgList = itemView.findViewById(R.id.img_list);
            relativeLayout = itemView.findViewById(R.id.relative_layout);
        }
    }
}
