package com.example.galan.tubes.Upload_Download;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.example.galan.tubes.R;

import net.gotev.uploadservice.MultipartUploadRequest;
import net.gotev.uploadservice.UploadNotificationConfig;

import java.util.UUID;

public class Uploader extends AppCompatActivity {

    public static final String PDF_UPLOAD_HTTP_URL = "http://pandumaliks.esy.es/UserRegistration/uploadernew.php";
    public int PDF_REQ_CODE = 1;
    String PdfNameHolder, PdfPathHolder, PdfID, PdfDescHolder;
    private Button SelectButton, UploadButton;
    private EditText PdfNameEditText, uploaderpath,uploaderDesc;
    private Uri uri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uploader);
        AllowRunTimePermission();

        SelectButton = (Button) findViewById(R.id.buttonChoser);
        UploadButton = (Button) findViewById(R.id.buttonUpload);
        PdfNameEditText = (EditText) findViewById(R.id.uploadEditTitle);
        uploaderpath = (EditText) findViewById(R.id.uploaderPath);
        uploaderDesc = (EditText) findViewById(R.id.uploadEditDescription);

        SelectButton.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View view) {
                // PDF selection code start from here .
                Intent intent = new Intent();
                intent.setType("application/pdf");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Pdf"), PDF_REQ_CODE);

            }
        });

        UploadButton.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View view) {
                PdfUploadFunction();
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PDF_REQ_CODE && resultCode == RESULT_OK && data != null && data.getData() != null) {
            uri = data.getData();
            SelectButton.setText("OK");
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void PdfUploadFunction() {

        PdfNameHolder = PdfNameEditText.getText().toString().trim();
        PdfDescHolder = uploaderDesc.getText().toString().trim();
        PdfPathHolder = FilePath.getPath(this, uri);
        if (PdfPathHolder == null) {
            Toast.makeText(this, "Please move your PDF file to internal storage & try again.", Toast.LENGTH_LONG).show();
        } else {
            uploaderpath.setText(PdfPathHolder);
            try {
                PdfID = UUID.randomUUID().toString();
                new MultipartUploadRequest(this, PdfID, PDF_UPLOAD_HTTP_URL).addFileToUpload(PdfPathHolder, "pdf").addParameter("name", PdfNameHolder).addParameter("description", PdfDescHolder)
                        .setNotificationConfig(new UploadNotificationConfig())
                        .setMaxRetries(2)
                        .startUpload();

            } catch (Exception exception) {
                Toast.makeText(this, exception.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }


    public void AllowRunTimePermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(Uploader.this, Manifest.permission.READ_EXTERNAL_STORAGE)) {
            Toast.makeText(Uploader.this, "READ_EXTERNAL_STORAGE permission Access Dialog", Toast.LENGTH_LONG).show();
        } else {
            ActivityCompat.requestPermissions(Uploader.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
        }
    }

    @Override
    public void onRequestPermissionsResult(int RC, String per[], int[] Result) {
        switch (RC) {
            case 1:
                if (Result.length > 0 && Result[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(Uploader.this, "Permission Granted", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(Uploader.this, "Permission Canceled", Toast.LENGTH_LONG).show();
                }
                break;
        }
    }
}

