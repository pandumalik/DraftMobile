package com.example.galan.tubes;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


/**
 * A simple {@link Fragment} subclass.
 */
public class account extends Fragment {

    private EditText mName;
    private EditText mEmail;
    private EditText mPass;
    private TextView userOld;
    private Button mUpdate;
    private static final String REGISTER_URL = "http://pandumalik.esy.es/UserRegistration/update.php";

    public account() {
        // Required empty public constructor
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getActivity().setTitle("ACCOUNT");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View ll = inflater.inflate(R.layout.fragment_account, container, false);
        //RelativeLayout ll = (RelativeLayout) inflater.inflate(R.layout.fragment_account, container, false);
        mName = (EditText) ll.findViewById(R.id.nameField);
        mEmail = (EditText) ll.findViewById(R.id.emailField);
        mPass = (EditText) ll.findViewById(R.id.passwordField);
        mUpdate = (Button) ll.findViewById(R.id.applyButton);
        userOld = (TextView) ll.findViewById(R.id.oldnames);

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
        return ll;
    }

    private void updateUser() {
        String lama = userOld.getText().toString();
        String username = mName.getText().toString();
        String email = mEmail.getText().toString();
        String password = mPass.getText().toString();
        update(username, password, email, lama);
    }

    private void update(String username, String password, String email, String lama) {
        String urlSuffix = "?username=" + username + "&password=" + password + "&email=" + email + "&olduser=" + lama;
        class RegisterUser extends AsyncTask<String, Void, String> {

            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(getActivity(), "Please Wait", "updating your account", true, true);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                userOld.setText(mName.toString());
                Toast.makeText(getContext(), s, Toast.LENGTH_SHORT).show();
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
