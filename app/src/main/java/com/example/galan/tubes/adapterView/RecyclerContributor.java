package com.example.galan.tubes.adapterView;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.galan.tubes.Accounts;
import com.example.galan.tubes.MapsActivity;
import com.example.galan.tubes.R;
import com.example.galan.tubes.setter_getter.isiContributor;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by galan on 15/04/2017.
 */

public class RecyclerContributor extends RecyclerView.Adapter<RecyclerContributor.RecyclerViewHolder> {
    Context ctx;
    private ArrayList<isiContributor> arrayList = new ArrayList<>();

    public RecyclerContributor(ArrayList<isiContributor> arrayList, Context ctx) {
        this.arrayList = arrayList;
        this.ctx = ctx;
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

    private void onItemClicked(int position) {
        Intent intent = new Intent(this.ctx, Accounts.class);
        this.ctx.startActivity(intent);
    }

    public void onClickOpenAddressButton(){
        // TODO 18 : Store an address in a String
        String address = "1600 Ampitheatre Parkway, CA";
        Uri location = Uri.parse("geo:0,0?q=" + address);
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(location);
        PackageManager packageManager = this.ctx.getPackageManager();
        List activities = packageManager.queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY);
        boolean isIntentSafe = activities.size() >0;
        if (isIntentSafe){
            this.ctx.startActivity(intent);
        }}

    public class RecyclerViewHolder extends RecyclerView.ViewHolder {
        TextView title, detail;
        Button mcall, mdirection;
        //ImageView photo, cover;

        public RecyclerViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.contributorName);
            detail = (TextView) view.findViewById(R.id.contributorDetail);
            mcall = (Button) view.findViewById(R.id.callCont);
            mdirection = (Button) view.findViewById(R.id.directionCont);
            mdirection.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onClickOpenAddressButton();
                }
            });
        }

    }
}
