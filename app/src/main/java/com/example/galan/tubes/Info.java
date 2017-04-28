package com.example.galan.tubes;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Info extends AppCompatActivity {
    private EditText title, description, date;
    private Button submit, clear;
    private String EVENT_URL="http://pandumaliks.esy.es/UserRegistration/EventUploader.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        title = (EditText) findViewById(R.id.eventTitle);
        description = (EditText) findViewById(R.id.eventDescription);
        submit = (Button) findViewById(R.id.submitEvent);
        clear = (Button) findViewById(R.id.clearEvent);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadEvent();
            }
        });

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                title.setText("");
                description.setText("");
            }
        });
    }

    private void uploadEvent() {
        String etitle = title.getText().toString().trim();
        String edescription = description.getText().toString().trim();
        nowUpload(etitle, edescription);
    }

    private void nowUpload(String judul, String desc) {
        String urlSuffix = "?title=" + judul + "&description=" + desc;
        class eventUpload extends AsyncTask<String, Void,String>{
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
                    URL url = new URL(EVENT_URL + s);
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
        eventUpload eu = new eventUpload();
        eu.execute(urlSuffix);
    }
}
