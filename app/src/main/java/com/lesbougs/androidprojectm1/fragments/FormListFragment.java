package com.lesbougs.androidprojectm1.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.lesbougs.androidprojectm1.R;
import com.lesbougs.androidprojectm1.interfaces.FragmentSwitcher;

import java.util.Objects;

public class FormListFragment extends Fragment {

    /*
     * Section construct
     */

    private final String mUsername;

    public FormListFragment() { mUsername = null; }
    public FormListFragment(final String username,final Object actualUser) {
        mUsername = username;
    }

    /*
     * Section menu
     */

    /*
     * Section life cycle
     */

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_form_list, container, false);
        if (mUsername != null) {
            Objects.requireNonNull(((AppCompatActivity) Objects.requireNonNull(getActivity())).getSupportActionBar()).setSubtitle(mUsername);
        }

        ((Button) view.findViewById(R.id.frag_form_list_resultButton)).setOnClickListener(v ->
                ((FragmentSwitcher) Objects.requireNonNull(getActivity()))
                    .loadFragment(new FormResultFragment(), true)
        );

        ((Button) view.findViewById(R.id.frag_form_list_editButton)).setOnClickListener(v ->
                ((FragmentSwitcher) Objects.requireNonNull(getActivity()))
                    .loadFragment(new FormEditFragment(), true)
        );


        return view;
    }

    /*
     * Section private methods
     */
}
