package com.example.galan.tubes;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.galan.tubes.setter_getter.UserData;

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

public class Login extends AppCompatActivity {
    public static final String LOGIN_URL = "http://pandumalik.esy.es/UserRegistration/login.php";
    public static String LOGIN_PREFERENCE = "LOGIN";
    public static String SHARED_PREF_DATA = "unck";
    public static String ID_USER;
    public static String SHARED_PREF_NAME;
    public static String EMAIL_SHARED_PREF;
    public static String KEY_PASSWORD;
    public static String USER_TYPE = "null";
    public static Activity nlg;
    private EditText editTextEmail;
    private EditText editTextPassword;
    private TextView signUp;
    private Button BtnLogin;
    private Button BtnClear;
    private boolean loggedIn = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);
        editTextEmail = (EditText) findViewById(R.id.editText_email);
        editTextPassword = (EditText) findViewById(R.id.editText_password);
        BtnLogin = (Button) findViewById(R.id.btn_login);
        BtnClear = (Button) findViewById(R.id.btn_clear);
        signUp = (TextView) findViewById(R.id.signupaccess);
        nlg = this;

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(Login.this, Signup.class);
                startActivity(myIntent);
            }
        });

        BtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });

        BtnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editTextEmail.setText("");
                editTextPassword.setText("");
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREF_DATA, Context.MODE_PRIVATE);
        loggedIn = sharedPreferences.getBoolean(LOGIN_PREFERENCE, false);
        if (loggedIn) {
            Intent intent = new Intent(Login.this, MainActivity.class);
            startActivity(intent);
        }
    }

    private void login() {
        String username = editTextEmail.getText().toString();
        String password = editTextPassword.getText().toString();
        loginUser(username, password);
    }

    private void loginUser(String username, String password) {
        String urlSuffix = "?username=" + username + "&password=" + password;
        class logintry extends AsyncTask<String, Void, String> {

            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(Login.this, "Please Wait", "Login to your Account", true, true);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                BackgroundTask bt = new BackgroundTask();
                if (s.equalsIgnoreCase("success")) {
                    bt.execute();
                    SharedPreferences sharedPreferences = Login.this.getSharedPreferences(SHARED_PREF_DATA, Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putBoolean(LOGIN_PREFERENCE, true);
                    editor.apply();
                    Toast.makeText(getApplicationContext(), "LOGIN " + s, Toast.LENGTH_SHORT).show();
                    Intent myIntent = new Intent(Login.this, MainActivity.class);
                    startActivity(myIntent);
                } else if (s.equalsIgnoreCase("failure")) {
                    Toast.makeText(getApplicationContext(), "Invalid Password or Username", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Check your Internet", Toast.LENGTH_SHORT).show();
                }
            }

            protected String doInBackground(String... params) {
                String s = params[0];
                BufferedReader bufferReader = null;
                try {
                    URL url = new URL(LOGIN_URL + s);
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
        logintry ur = new logintry();
        ur.execute(urlSuffix);
    }


    class BackgroundTask extends AsyncTask<Void, UserData, Void> {
        String username = editTextEmail.getText().toString();
        String URLdata = "http://pandumalik.esy.es/UserRegistration/getUser.php?username=" + username;
        ProgressDialog loading;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(Void avoid) {
            super.onPostExecute(avoid);
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
                    UserData UD = new UserData(JO.getString("nim"), JO.getString("name"), JO.getString("email"), JO.getString("password"), JO.getString("type"));
                    publishProgress(UD);
                    ID_USER = JO.getString("nim");
                    EMAIL_SHARED_PREF = JO.getString("email");
                    SHARED_PREF_NAME = JO.getString("name");
                    KEY_PASSWORD = JO.getString("password");
                    USER_TYPE = JO.getString("type");

                }
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
