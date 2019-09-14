package com.example.studentattendent;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;


/**
 * A simple {@link Fragment} subclass.
 */
public class qrcode extends Fragment {
    private ZXingScannerView zXingScannerView;


    public qrcode() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_qrcode, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        zXingScannerView = new ZXingScannerView(getActivity());
        getActivity().setContentView(zXingScannerView);
        zXingScannerView.startCamera();
        zXingScannerView.setResultHandler(new ZXingScannerView.ResultHandler() {
            @Override
            public void handleResult(Result result) {
                //zXingScannerView.stopCamera();
                getActivity().setContentView(R.layout.activity_main);
                String resultString = result.getText().toString();
                Toast.makeText(getActivity(), "QR Code = "+resultString, Toast.LENGTH_SHORT).show();
                Log.d("12MarchV1","QR Code = "+resultString);
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.frameLayout, new checkname()).commit();
            }
        });
    }

}
