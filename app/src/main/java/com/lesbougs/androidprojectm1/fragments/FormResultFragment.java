package com.lesbougs.androidprojectm1.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.lesbougs.androidprojectm1.R;
import com.lesbougs.androidprojectm1.interfaces.FragmentSwitcher;

import java.util.Objects;

public class FormResultFragment extends Fragment {

    /*
     * Section menu
     */

    /*
     * Section life cycle
     */

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_form_result, container, false);

        ((Button) view.findViewById(R.id.frag_form_result_returnButton)).setOnClickListener(v ->
                ((FragmentSwitcher) Objects.requireNonNull(getActivity()))
                    .loadFragment(new FormListFragment(), true)
        );

        return view;
    }

    /*
     * Section private methods
     */

}
