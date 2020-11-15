package com.lesbougs.androidprojectm1.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.lesbougs.androidprojectm1.HomeActivity;
import com.lesbougs.androidprojectm1.R;

import java.util.Objects;

public class FormListFragment extends Fragment {

    private final String mUsername;

    public FormListFragment(final String username) {
        mUsername = username;
    }

    public FormListFragment() {
        mUsername = null;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_form_list, container, false);
        if (mUsername != null) {
            Objects.requireNonNull(((AppCompatActivity) Objects.requireNonNull(getActivity())).getSupportActionBar()).setSubtitle(mUsername);
        }

        ((Button) view.findViewById(R.id.fra_for_lis_resultButton)).setOnClickListener(v -> {
            ((FragmentSwitcher) Objects.requireNonNull(getActivity()))
                    .loadFragment(new FormResultFragment(), true);
        });

        ((Button) view.findViewById(R.id.fra_for_lis_editButton)).setOnClickListener(v -> {
            ((FragmentSwitcher) Objects.requireNonNull(getActivity()))
                    .loadFragment(new FormEditFragment(), true);
        });


        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //temporaire... ne repasse pas par connexion
        startActivity((new Intent(getActivity(), HomeActivity.class))
                .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
    }
}
