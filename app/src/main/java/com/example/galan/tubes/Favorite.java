package com.example.galan.tubes;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.galan.tubes.R;
import com.example.galan.tubes.adapterView.RecyclerFavorite;
import com.example.galan.tubes.setter_getter.isiFavorite;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import static com.example.galan.tubes.Login.ID_USER;

public class Favorite extends Fragment {
    RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        LinearLayout ll = (LinearLayout) inflater.inflate(R.layout.fragment_favorite, container, false);
        recyclerView = (RecyclerView) ll.findViewById(R.id.favorite_recycler);
        doLoad();
        return ll;
    }

    public void doLoad() {
        new BackgroundTask(getActivity(), recyclerView).execute();
    }

    class BackgroundTask extends AsyncTask<Void, isiFavorite, Void> {

        Context ctx;
        RecyclerView recyclerView;
        RecyclerView.Adapter adapter;
        RecyclerView.LayoutManager layoutManager;
        ArrayList<isiFavorite> arrayList = new ArrayList<>();
        String URLdata = "http://pandumalik.esy.es/UserRegistration/getFavorite.php?iduser="+ID_USER;

        public BackgroundTask(Activity ctx, RecyclerView rview) {
            this.ctx = ctx;
            this.recyclerView = rview;
        }

        @Override
        protected void onPreExecute() {
            recyclerView = (RecyclerView) recyclerView.findViewById(R.id.favorite_recycler);
            layoutManager = new LinearLayoutManager(ctx);
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setHasFixedSize(true);
            adapter = new RecyclerFavorite(arrayList);
            recyclerView.setAdapter(adapter);
        }

        @Override
        protected void onProgressUpdate(isiFavorite... values) {
            arrayList.add(values[0]);
            adapter.notifyDataSetChanged();
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
        }

        @Override
        protected Void doInBackground(Void... params) {
            try {
                URL url = new URL(URLdata);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                StringBuilder stringBuilder = new StringBuilder();
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    stringBuilder.append(line + "\n");
                }

                httpURLConnection.disconnect();
                String json_string = stringBuilder.toString().trim();

                JSONObject jsonObject = new JSONObject(json_string);
                JSONArray jsonArray = jsonObject.optJSONArray("server_response");
                int count = 0;
                while (count < jsonArray.length()) {
                    JSONObject JO = jsonArray.getJSONObject(count);
                    count++;
                    isiFavorite fav = new isiFavorite(JO.getString("namaMateri"), JO.getString("date"), JO.getString("idMateri"));
                    publishProgress(fav);
                }
                Log.d("JSON STRING", json_string);

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }
    }


}
