package com.example.studentattendent;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class timeteacher extends Fragment {
    private  String URL_TIMETeach = "http://203.154.83.137/StudentAttendent/loadteachtime.php";


    public timeteacher() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_timeteacher, container, false);
    }

}
