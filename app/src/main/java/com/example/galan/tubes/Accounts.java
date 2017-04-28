package com.example.galan.tubes;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import static com.example.galan.tubes.Login.EMAIL_SHARED_PREF;
import static com.example.galan.tubes.Login.ID_USER;
import static com.example.galan.tubes.Login.KEY_PASSWORD;
import static com.example.galan.tubes.Login.SHARED_PREF_NAME;

public class Accounts extends AppCompatActivity {


    private static final String REGISTER_URL = "http://pandumaliks.esy.es/UserRegistration/update.php";
    private EditText mName;
    private EditText mEmail;
    private EditText mPass;
    private Button mUpdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_account);

        mName = (EditText) findViewById(R.id.nameField);
        mEmail = (EditText) findViewById(R.id.emailField);
        mPass = (EditText) findViewById(R.id.passwordField);
        mUpdate = (Button) findViewById(R.id.applyButton);

        mEmail.setText(EMAIL_SHARED_PREF);
        mName.setText(SHARED_PREF_NAME);
        mPass.setText(KEY_PASSWORD);

        mName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mName.setText("");
            }
        });

        mEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEmail.setText("");
            }
        });

        mPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPass.setText("");
            }
        });

        mUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateUser();
            }
        });
    }

    private void updateUser() {
        String iduser = ID_USER;
        String username = mName.getText().toString();
        String email = mEmail.getText().toString();
        String password = mPass.getText().toString();
        update(username, password, email, iduser);
    }

    private void update(String username, String password, String email, String iduser) {
        String urlSuffix = "?username=" + username + "&password=" + password + "&email=" + email + "&nim=" + iduser;
        class RegisterUser extends AsyncTask<String, Void, String> {

            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(Accounts.this, "Please Wait", "updating your account", true, true);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(Accounts.this, s, Toast.LENGTH_SHORT).show();
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
}
