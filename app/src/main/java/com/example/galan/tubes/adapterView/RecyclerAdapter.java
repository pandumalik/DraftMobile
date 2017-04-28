package com.example.galan.tubes.adapterView;

/**
 * Created by pandu on 25/03/17.
 */


import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.galan.tubes.R;
import com.example.galan.tubes.Upload_Download.PDFviewer;
import com.example.galan.tubes.setter_getter.contributorFromList;
import com.example.galan.tubes.setter_getter.isiMateri;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import static com.example.galan.tubes.Login.ID_USER;

/**
 * Created by galan on 23/03/2017.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.RecyclerViewHolder> {
    private static final int TYPE_HEAD = 0;
    private static final String REGISTER_URL = "http://pandumaliks.esy.es/UserRegistration/favorite.php";
    private static final int TYPE_LIST = 1;
    String NAMA;
    String CONTACT;
    ArrayList<isiMateri> arrayList = new ArrayList<>();
    Context ctx;

    public RecyclerAdapter(ArrayList<isiMateri> arrayList, Context ctx) {
        this.arrayList = arrayList;
        this.ctx = ctx;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_layout, parent, false);
        RecyclerViewHolder recyclerViewHolder = new RecyclerViewHolder(view);
        return recyclerViewHolder;
    }

    @Override
    public void onBindViewHolder(final RecyclerViewHolder holder, int position) {
        isiMateri isiMateris = arrayList.get(position);
        holder.title.setText(isiMateris.getTitle());
        holder.description.setText(isiMateris.getDescription());
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

    private void onItemClicked(int position) {
        isiMateri is = arrayList.get(position);
        Intent intent = new Intent(this.ctx, PDFviewer.class);
        intent.putExtra("link", is.getLink());
        intent.putExtra("title", is.getTitle());
        this.ctx.startActivity(intent);
    }

    private void onDownload(int position) {
        isiMateri is = arrayList.get(position);
        Intent intent = new Intent(this.ctx, PDFviewer.class);
        intent.putExtra("link", is.getLink());
        intent.putExtra("title", is.getTitle());
        this.ctx.startActivity(intent);
    }

    private void showPopupMenu(View view, int position) {
        // inflate menu
        PopupMenu popup = new PopupMenu(view.getContext(), view);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.card_button_menu, popup.getMenu());
        popup.setOnMenuItemClickListener(new MyMenuItemClickListener(position));
        popup.show();
    }

    private void showContributor() {
        TextView name, contact;

        AlertDialog.Builder mBuilder = new AlertDialog.Builder(this.ctx);
        LayoutInflater inflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.contributor, null);
        name = (TextView) view.findViewById(R.id.contributorName);
        contact = (TextView) view.findViewById(R.id.contributorDetail);

        name.setText(NAMA);
        contact.setText(CONTACT);

        mBuilder.setView(view);
        final AlertDialog dialog = mBuilder.create();
        dialog.show();
    }

    private void insertfavorite(int position) {
        isiMateri isiMateris = arrayList.get(position);
        String namaMateri = isiMateris.getTitle();
        String idmateri = isiMateris.getIdmateri();
        String iduser = ID_USER;
        favorite(idmateri, iduser, namaMateri);
    }

    private void favorite(String idmateri, String iduser, String namaMateri) {
        String urlSuffix = "?idmateri=" + idmateri + "&iduser=" + iduser + "&title=" + namaMateri;
        class RegisterUser extends AsyncTask<String, Void, String> {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
            }

            @Override
            protected String doInBackground(String... params) {
                String s = params[0];
                BufferedReader bufferReader = null;
                try {
                    URL url = new URL(REGISTER_URL + s);
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    bufferReader = new BufferedReader(new InputStreamReader(con.getInputStream()));
                    String result;
                    result = bufferReader.readLine();
                    return result;

                } catch (Exception e) {
                    return null;
                }
            }
        }
        RegisterUser ur = new RegisterUser();
        ur.execute(urlSuffix);
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder {
        TextView title, description;
        CardView cardView;
        Button mOpen;

        public RecyclerViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.item_title);
            description = (TextView) view.findViewById(R.id.item_detail);
            cardView = (CardView) view.findViewById(R.id.card_view);
            mOpen = (Button) view.findViewById(R.id.open);

            mOpen.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showPopupMenu(mOpen, getAdapterPosition());
                    //onItemClicked(getAdapterPosition());
                }
            });
        }
    }

    class MyMenuItemClickListener implements PopupMenu.OnMenuItemClickListener {

        private int position;

        public MyMenuItemClickListener(int positon) {
            this.position = positon;
        }

        @Override
        public boolean onMenuItemClick(MenuItem menuItem) {
            switch (menuItem.getItemId()) {

                case R.id.addfavorite:
                    insertfavorite(position);
                    return true;
                case R.id.download:
                    onDownload(position);
                    //showContributor();
                    //showContributor();
                    //getContributor(position);
                    return true;
                case R.id.contentOpen:
                    onItemClicked(position);
                default:
            }
            return false;
        }
    }


}

