package com.example.galan.tubes;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RelativeLayout;


/**
 * A simple {@link Fragment} subclass.
 */
public class account extends Fragment {

    private EditText mName;
    private EditText mEmail;
    private EditText mPass;

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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,@Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View ll = inflater.inflate(R.layout.fragment_account, container, false);
        //RelativeLayout ll = (RelativeLayout) inflater.inflate(R.layout.fragment_account, container, false);
        mName = (EditText) ll.findViewById(R.id.nameField);
        mEmail = (EditText) ll.findViewById(R.id.emailField);
        mPass = (EditText) ll.findViewById(R.id.passwordField);

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
        return ll;

    }

}
