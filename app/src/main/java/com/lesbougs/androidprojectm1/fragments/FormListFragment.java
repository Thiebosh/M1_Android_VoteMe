package com.lesbougs.androidprojectm1.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.lesbougs.androidprojectm1.R;
import com.lesbougs.androidprojectm1.adapters.AdminFormAdapter;
import com.lesbougs.androidprojectm1.interfaces.UserAccess;
import com.lesbougs.androidprojectm1.model.Form;
import com.lesbougs.androidprojectm1.model.User;

import java.util.ArrayList;
import java.util.Objects;

public class FormListFragment extends Fragment {

    /*
     * Section life cycle
     */

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        User currentUser = ((UserAccess) Objects.requireNonNull(getActivity())).getUser();//get data

        View view = inflater.inflate(R.layout.fragment_form_list, container, false);

        assert getActivity() != null;
        assert ((AppCompatActivity) getActivity()).getSupportActionBar() != null;
        String str = "Welcome "+currentUser.getUsername()+"!";
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(str);

        RecyclerView recyclerView = (RecyclerView)  view.findViewById(R.id.frag_form_list_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        ArrayList<Form> formArrayList = new ArrayList<>(currentUser.getForms());
        AdminFormAdapter adapter = new AdminFormAdapter(getActivity(),
                                                        getContext(),
                                                        formArrayList,
                                                        currentUser.getHeaderPayload(),
                                                        currentUser.getSignature());
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        return view;
    }
}
