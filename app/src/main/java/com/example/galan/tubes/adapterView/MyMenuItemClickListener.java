package com.example.galan.tubes.adapterView;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.PopupMenu;
import android.view.MenuItem;

import com.example.galan.tubes.R;
import com.example.galan.tubes.Upload_Download.PDFviewer;
import com.example.galan.tubes.setter_getter.isiMateri;

import java.util.ArrayList;

/**
 * Created by galan on 19/04/2017.
 */

public class MyMenuItemClickListener implements PopupMenu.OnMenuItemClickListener {
    ArrayList<isiMateri> arrayList = new ArrayList<>();
    Context ctx;

    public MyMenuItemClickListener(ArrayList<isiMateri> arrayList, Context ctx) {
        this.arrayList = arrayList;
        this.ctx = ctx;
    }

    private int position;
    public MyMenuItemClickListener(int positon) {
        this.position=positon;
    }

    @Override
    public boolean onMenuItemClick(MenuItem menuItem) {
        switch (menuItem.getItemId()) {

            case R.id.addfavorite:

                return true;
            case R.id.uploader:

                return true;
            case R.id.contentOpen:
                isiMateri is = arrayList.get(position);
                Intent intent = new Intent(this.ctx, PDFviewer.class);
                intent.putExtra("link", is.getLink());
                intent.putExtra("title", is.getTitle());
                this.ctx.startActivity(intent);
            default:
        }
        return false;
    }
}