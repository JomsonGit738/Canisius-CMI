package com.brocodz.devamathacmi.fuctions.prayers;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.brocodz.devamathacmi.R;


public class FragmentTwo extends Fragment {

   View v2;
    public FragmentTwo() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v2 = inflater.inflate(R.layout.fragment_two,container,false);
        return v2;
    }
}