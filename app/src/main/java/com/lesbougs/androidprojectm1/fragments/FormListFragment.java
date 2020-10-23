package com.lesbougs.androidprojectm1.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.lesbougs.androidprojectm1.NavigationHost;
import com.lesbougs.androidprojectm1.R;

public class FormListFragment extends Fragment {

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_form_list, container, false);

        ((Button) view.findViewById(R.id.resultButton)).setOnClickListener(v -> {
            ((NavigationHost) getActivity()).navigateTo(new FormResultFragment(), true); // Navigate to the next Fragment
        });

        ((Button) view.findViewById(R.id.editButton)).setOnClickListener(v -> {
            ((NavigationHost) getActivity()).navigateTo(new FormEditFragment(), true); // Navigate to the next Fragment
        });


        return view;
    }
}
