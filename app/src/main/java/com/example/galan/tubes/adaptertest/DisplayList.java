package com.example.galan.tubes.adaptertest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.galan.tubes.R;

public class DisplayList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_materi);

        BackgroundTask backgroundTask =new BackgroundTask(DisplayList.this);
        backgroundTask.execute();

    }

    @Override
    public void onBackPressed() {
    finish();
    }

}
