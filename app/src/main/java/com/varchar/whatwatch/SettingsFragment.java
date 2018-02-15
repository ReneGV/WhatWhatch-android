package com.varchar.whatwatch;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.service.carrier.CarrierMessagingService;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;

//import com.varchar.WhatWatch.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SettingsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SettingsFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    public RadioGroup themeRadioGroup = null;
    private RadioButton whiteRadioButton = null;
    private RadioButton blackRadioButton = null;
    final String  APPLICATION_THEME = "ApplicationTheme";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    public SettingsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SettingsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SettingsFragment newInstance(String param1, String param2) {
        SettingsFragment fragment = new SettingsFragment();
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

        View fragment = inflater.inflate(R.layout.fragment_settings, container, false);
        themeRadioGroup = (RadioGroup)fragment.findViewById(R.id.themeRadioGroup);
        blackRadioButton = (RadioButton)fragment.findViewById(R.id.blackRadioButton);
        whiteRadioButton = (RadioButton)fragment.findViewById(R.id.whiteRadioButton);

        // TODO: Apply Button in this view is for more settings in future version


        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        final SharedPreferences.Editor editor = preferences.edit();

        // Setting Radio Button Selection
        int theme = preferences.getInt(APPLICATION_THEME,0);
        switch (theme){
            case 0:
                blackRadioButton.setChecked(true);
                whiteRadioButton.setChecked(false);
                break;
            case 1:
            default:
                blackRadioButton.setChecked(false);
                whiteRadioButton.setChecked(true);
                break;
        }

        //  Radio Group Listener
        // save in preferences the selected theme
        themeRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                //Snackbar.make(getView(), "Cierra y vuelve a abrir",Snackbar.LENGTH_LONG).show();
                Snackbar.make(getActivity().findViewById(android.R.id.content), "Cierra y vuelve a abrir para ver los cambios",Snackbar.LENGTH_LONG).show();
                switch (checkedId) {
                    case R.id.whiteRadioButton:
                        editor.putInt(APPLICATION_THEME, 1);
                        break;
                    case R.id.blackRadioButton:
                        editor.putInt(APPLICATION_THEME,0);
                        break;
                    default:
                        editor.putInt(APPLICATION_THEME, 1);
                        break;
                }
                editor.apply();

            }
        });
        return fragment;
    }

}
