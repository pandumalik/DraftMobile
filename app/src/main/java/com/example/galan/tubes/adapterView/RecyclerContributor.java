package com.example.galan.tubes.adapterView;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.galan.tubes.R;
import com.example.galan.tubes.setter_getter.isiContributor;

import java.util.ArrayList;

/**
 * Created by galan on 15/04/2017.
 */

public class RecyclerContributor extends RecyclerView.Adapter<RecyclerContributor.RecyclerViewHolder> {

    private ArrayList<isiContributor> arrayList = new ArrayList<>();

    public RecyclerContributor(ArrayList<isiContributor> arrayList) {
        this.arrayList = arrayList;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.contributor, parent, false);
        RecyclerViewHolder recyclerViewHolder = new RecyclerViewHolder(view);
        return recyclerViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {
        isiContributor contributor = arrayList.get(position);
        holder.title.setText(contributor.getName());
        holder.detail.setText(contributor.getContact());
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }


    public static class RecyclerViewHolder extends RecyclerView.ViewHolder {
        TextView title, detail;
        //ImageView photo, cover;

        public RecyclerViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.contributorName);
            detail = (TextView) view.findViewById(R.id.contributorDetail);
            //photo = (ImageView) view.findViewById(R.id.profilepic);
            //cover = (ImageView) view.findViewById(R.id.profileCover);
        }
    }
}
