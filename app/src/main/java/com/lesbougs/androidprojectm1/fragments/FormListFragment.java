package com.lesbougs.androidprojectm1.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.lesbougs.androidprojectm1.R;
import com.lesbougs.androidprojectm1.adapter.AdapterAdminAllForm;
import com.lesbougs.androidprojectm1.interfaces.UserAccess;
import com.lesbougs.androidprojectm1.model.Form;
import com.lesbougs.androidprojectm1.model.User;

import java.util.ArrayList;
import java.util.Objects;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class FormListFragment extends Fragment {

    /*
     * Section menu
     */

    /*
     * Section life cycle
     */


    private final Executor backgroundThread = Executors.newSingleThreadExecutor();


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("TAG", "bonjour");
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_form_list, container, false);

        User current = ((UserAccess) getActivity()).getUser();//get data

        Objects.requireNonNull(((AppCompatActivity) Objects.requireNonNull(getActivity())).getSupportActionBar()).setSubtitle(current.getUserName());


        RecyclerView recyclerView = (RecyclerView)  view.findViewById(R.id.recyclerViewAdminResult);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);


        ArrayList<String> allNameForm = new ArrayList<>();
        ArrayList<Boolean> allClosed = new ArrayList<>();
        for (Form elem : current.getForms()){
            allNameForm.add(elem.getTitle());
            allClosed.add(elem.isClosed());
        }



        AdapterAdminAllForm customAdapter = new AdapterAdminAllForm(getContext(),getActivity(),current);
        recyclerView.setAdapter(customAdapter);




        return view;
    }

    /*
     * Section private methods
     */
}
