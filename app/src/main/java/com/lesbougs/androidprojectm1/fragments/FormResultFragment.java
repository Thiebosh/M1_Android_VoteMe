package com.lesbougs.androidprojectm1.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.lesbougs.androidprojectm1.R;
import com.lesbougs.androidprojectm1.adapter.AdapterAdminResult;
import com.lesbougs.androidprojectm1.interfaces.FragmentSwitcher;
import com.lesbougs.androidprojectm1.interfaces.UserAccess;
import com.lesbougs.androidprojectm1.model.User;
import com.lesbougs.androidprojectm1.model.Widget;

import java.util.ArrayList;
import java.util.Objects;

public class FormResultFragment extends Fragment {

    ArrayList<Widget> widget;

    public FormResultFragment(ArrayList<Widget> widget) {
        this.widget=widget;
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
        View view = inflater.inflate(R.layout.fragment_form_result, container, false);

        User current = ((UserAccess) getActivity()).getUser();//get data

        Objects.requireNonNull(((AppCompatActivity) Objects.requireNonNull(getActivity())).getSupportActionBar()).setSubtitle(current.getUsername());

        /*
        RecyclerView recyclerView = (RecyclerView)  view.findViewById(R.id.recyclerViewAdminResult);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);



        AdapterAdminResult customAdapter = new AdapterAdminResult(widget,getContext());
        recyclerView.setAdapter(customAdapter);

         */





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
