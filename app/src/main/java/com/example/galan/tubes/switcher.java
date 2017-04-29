package com.example.galan.tubes;

/**
 * Created by pandu on 25/03/17.
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 */
public class switcher extends Fragment {

    private static final String REGISTER_URL = "http://pandumaliks.esy.es/UserRegistration/update.php";
    private TextView mAccount, mCategory;
    private CardView cAccount, cCategory;
    private ImageButton iAccount, iCategory;

    public switcher() {
        // Required empty public constructor
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View ll = inflater.inflate(R.layout.fragment_setting, container, false);
        //RelativeLayout ll = (RelativeLayout) inflater.inflate(R.layout.fragment_account, container, false);
        iAccount = (ImageButton) ll.findViewById(R.id.accountButt);
        iAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent akun = new Intent(getActivity(), Accounts.class);
                startActivity(akun);
            }
        });
        return ll;
    }
}
