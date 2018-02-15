package com.varchar.whatwatch;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

//import com.varchar.WhatWatch.R;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MailFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MailFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private TextInputLayout emailTextInputLayout;
    private Button sendButton;


    public MailFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MailFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MailFragment newInstance(String param1, String param2) {
        MailFragment fragment = new MailFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View fragment = inflater.inflate(R.layout.fragment_mail, container, false);
        emailTextInputLayout = (TextInputLayout)fragment.findViewById(R.id.emailInputText);
        sendButton = (Button)fragment.findViewById(R.id.sendButton);
        sendButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String email = emailTextInputLayout.getEditText().getText().toString();
                if (isEmailValid(email)){
                    // Setting values to send an e-mail
                    Intent intent = new Intent(Intent.ACTION_SEND);
                    intent.setData(Uri.parse("mailto:"));
                    String[] to = {email};
                    intent.putExtra(Intent.EXTRA_EMAIL, to);
                    intent.putExtra(Intent.EXTRA_SUBJECT, "Bienvenido a What Watch");
                    intent.putExtra(Intent.EXTRA_TEXT,"Ahora recibirás notificaciones de los estrenos lo antes posible gracias a What Watch");
                    intent.setType("message/rfc822");
                    Intent chooser = Intent.createChooser(intent, "Send Email");
                    startActivity(chooser);
                }
                else{
                    Snackbar.make(getActivity().findViewById(android.R.id.content), "Email no válido",Snackbar.LENGTH_LONG).show();
                }
            }
        });
        return fragment;
    }

    //Validating email addres
    public static boolean isEmailValid(String email){
        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    private String getEmail(){
        String email = emailTextInputLayout.getEditText().getText().toString();
        if (isEmailValid(email)){
            emailTextInputLayout.setErrorEnabled(false);
        }
        else{
            emailTextInputLayout.setErrorEnabled(true);
            emailTextInputLayout.setError("Email no válido");
        }
        return email;
    }

}
