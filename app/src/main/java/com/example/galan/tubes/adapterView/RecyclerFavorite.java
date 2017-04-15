package com.example.galan.tubes.adapterView;

import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.galan.tubes.R;
import com.example.galan.tubes.setter_getter.isiFavorite;

import java.util.ArrayList;

/**
 * Created by galan on 08/04/2017.
 */

public class RecyclerFavorite extends RecyclerView.Adapter<RecyclerFavorite.RecyclerViewHolder> {

    private static final int TYPE_HEAD = 0;
    private static final int TYPE_LIST = 1;
    ArrayList<isiFavorite> arrayList = new ArrayList<>();

    public RecyclerFavorite(ArrayList<isiFavorite> arrayList) {
        this.arrayList = arrayList;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.favorite_view, parent, false);
        RecyclerViewHolder recyclerViewHolder = new RecyclerViewHolder(view);
        return recyclerViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {
        isiFavorite favorite = arrayList.get(position);
        holder.title.setText(favorite.getNamaMateri());
        holder.date.setText(favorite.getDate());
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
        TextView title, date;
        CardView cardView;

        public RecyclerViewHolder(View view) {
            super(view);
            title       = (TextView) view.findViewById(R.id.favTitle);
            date        = (TextView) view.findViewById(R.id.favDate);
            cardView    = (CardView) view.findViewById(R.id.cardfavorite);
        }
    }
}
