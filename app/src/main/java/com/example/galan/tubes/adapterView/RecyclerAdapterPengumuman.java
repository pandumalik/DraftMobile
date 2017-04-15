package com.example.galan.tubes.adapterView;

/**
 * Created by pandu on 25/03/17.
 */


import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.galan.tubes.R;
import com.example.galan.tubes.setter_getter.isiPengumuman;

import java.util.ArrayList;

/**
 * Created by galan on 23/03/2017.
 */

public class RecyclerAdapterPengumuman extends RecyclerView.Adapter<RecyclerAdapterPengumuman.RecyclerViewHolder> {
    private static final int TYPE_HEAD = 0;
    private static final int TYPE_LIST = 1;
    ArrayList<isiPengumuman> arrayList = new ArrayList<>();

    public RecyclerAdapterPengumuman(ArrayList<isiPengumuman> arrayList) {
        this.arrayList = arrayList;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_layout, parent, false);
        RecyclerViewHolder recyclerViewHolder = new RecyclerViewHolder(view);
        return recyclerViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {
        isiPengumuman pengumuman = arrayList.get(position);
        holder.title.setText(pengumuman.getTitle());
        holder.description.setText(pengumuman.getPengumuman());
        holder.cardView.setCardBackgroundColor(Color.parseColor("#e74c3c"));
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0)
            return TYPE_HEAD;
        return TYPE_LIST;

    }


    public static class RecyclerViewHolder extends RecyclerView.ViewHolder {
        TextView title, description;
        CardView cardView;

        public RecyclerViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.item_title);
            description = (TextView) view.findViewById(R.id.item_detail);
            cardView = (CardView) view.findViewById(R.id.card_view);
        }
    }
}

