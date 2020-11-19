package com.lesbougs.androidprojectm1.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.lesbougs.androidprojectm1.R;
import com.lesbougs.androidprojectm1.adapter.AdapterAdminResult;
import com.lesbougs.androidprojectm1.adapters.AdminFormAdapter;
import com.lesbougs.androidprojectm1.interfaces.FragmentSwitcher;
import com.lesbougs.androidprojectm1.interfaces.UserAccess;
import com.lesbougs.androidprojectm1.model.Form;
import com.lesbougs.androidprojectm1.model.User;
import com.lesbougs.androidprojectm1.model.Widget;

import java.util.ArrayList;
import java.util.Objects;

public class FormResultFragment extends Fragment {

    ArrayList<Widget> mWidget;

    public FormResultFragment(ArrayList<Widget> widget) {
        this.mWidget = widget;
    }

    /*
     * Section life cycle
     */

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        User current = ((UserAccess) getActivity()).getUser();//get data

        View view = inflater.inflate(R.layout.fragment_form_result, container, false);

        RecyclerView recyclerView = (RecyclerView)  view.findViewById(R.id.frag_form_result_recycler_view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);



        AdapterAdminResult customAdapter = new AdapterAdminResult(mWidget,getContext());
        recyclerView.setAdapter(customAdapter);


        /*
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.frag_form_result_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        ArrayList<Widget> formArrayList = new ArrayList<>(mWidget);
        AdminWidgetResultAdapter adapter = new AdminWidgetResultAdapter(getActivity(),
                getContext(),
                formArrayList,
                currentUser.getHeaderPayload(),
                currentUser.getSignature());
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        */



        ((Button) view.findViewById(R.id.frag_form_result_returnButton)).setOnClickListener(v ->
                ((FragmentSwitcher) Objects.requireNonNull(getActivity()))
                    .loadFragment(new FormListFragment(), true)
        );

        return view;
    }
}
