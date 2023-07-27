package com.brocodz.devamathacmi.fuctions.prayers;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.brocodz.devamathacmi.R;

public class FragmentFive extends Fragment {
   View v5;
    public FragmentFive() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v5= inflater.inflate(R.layout.fragment_five, container, false);
        return v5;
    }
}