package com.hammer67.tutorialrecylclerviewjava.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.hammer67.tutorialrecylclerviewjava.R;
import com.hammer67.tutorialrecylclerviewjava.activities.DetailActivity;
import com.hammer67.tutorialrecylclerviewjava.models.Mountain;

import java.util.ArrayList;

public class ItemCardAdapter extends RecyclerView.Adapter<ItemCardAdapter.CardViewHolder> {

    private Context context;
    private ArrayList<Mountain> listMountain;

    public ItemCardAdapter(Context context) {
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
    public CardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cardview, parent, false);
        return new CardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CardViewHolder holder, int position) {
        holder.tvName.setText(getListMountain().get(position).getName());
        holder.tvDescription.setText(getListMountain().get(position).getDescription());
        Glide.with(context).load(getListMountain().get(position).getPhoto()).into(holder.ivPhoto);

        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra(DetailActivity.EXTRA_MOUNTAIN, listMountain.get(position));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return getListMountain().size();
    }

    public class CardViewHolder extends RecyclerView.ViewHolder {

        TextView tvName, tvDescription;
        ImageView ivPhoto;
        Button button;

        public CardViewHolder(@NonNull View itemView) {
            super(itemView);

            tvName = itemView.findViewById(R.id.tv_name_card);
            tvDescription = itemView.findViewById(R.id.tv_desc_card);
            ivPhoto = itemView.findViewById(R.id.img_card);
            button = itemView.findViewById(R.id.button);
        }
    }
}
