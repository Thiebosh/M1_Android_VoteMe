package com.lesbougs.androidprojectm1.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.lesbougs.androidprojectm1.R;
import com.lesbougs.androidprojectm1.adapters.AdminResultAdapter;
import com.lesbougs.androidprojectm1.interfaces.FragmentSwitcher;
import com.lesbougs.androidprojectm1.model.Widget;

import java.util.ArrayList;
import java.util.Objects;

public class FormResultFragment extends Fragment {

    private final ArrayList<Widget> mWidget;
    private final String mFormName;

    public FormResultFragment(String formName, ArrayList<Widget> widget) {
        this.mFormName = formName;
        this.mWidget = widget;
    }

    /*
     * Section life cycle
     */

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_form_result, container, false);

        ((TextView) view.findViewById(R.id.textViewNameForm)).setText(getString(R.string.admin_title_result_form, mFormName));

        RecyclerView recyclerView = view.findViewById(R.id.frag_form_result_recycler_view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);

        AdminResultAdapter customAdapter = new AdminResultAdapter(mWidget,getContext());
        recyclerView.setAdapter(customAdapter);


        view.findViewById(R.id.frag_form_result_returnButton).setOnClickListener(v ->
                ((FragmentSwitcher) Objects.requireNonNull(getActivity()))
                    .loadFragment(new FormListFragment(), true)
        );

        return view;
    }
}
