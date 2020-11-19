package com.lesbougs.androidprojectm1.fragments;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.lesbougs.androidprojectm1.R;
import com.lesbougs.androidprojectm1.adapters.AdminWidgetAdapter;
import com.lesbougs.androidprojectm1.adapters.VisitorWidgetAdapter;
import com.lesbougs.androidprojectm1.interfaces.FragmentSwitcher;
import com.lesbougs.androidprojectm1.model.Widget;

import java.util.ArrayList;
import java.util.Objects;

public class FormCreateFragment extends Fragment {


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_form_edit, container, false);

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));



        ((FloatingActionButton) view.findViewById(R.id.floatingActionButton)).setOnClickListener(v ->{
                    String[] type={"Grade","Text"};
                    final Dialog dialog = new Dialog(getContext());
                    dialog.setContentView(R.layout.item_pop_create_form);
                    ArrayAdapter adapterType = new ArrayAdapter(getContext(),android.R.layout.simple_list_item_1,type);

                    AutoCompleteTextView prout = (AutoCompleteTextView) dialog.findViewById(R.id.chooseTypeOfWidget);
                    prout.setAdapter(adapterType);
                    prout.setThreshold(0);
                    dialog.show();
                });


        ((Button) view.findViewById(R.id.frag_form_come_back)).setOnClickListener(v ->
                ((FragmentSwitcher) Objects.requireNonNull(getActivity()))
                    .loadFragment(new FormListFragment(), true)
        );



        ArrayList<Widget> mWidgetArrayList = new ArrayList<>();
        AdminWidgetAdapter adapter = new AdminWidgetAdapter(getContext(), mWidgetArrayList);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        ((Button) view.findViewById(R.id.frag_form_create)).setOnClickListener(v -> {
            Widget w = new Widget();
            w.setQuestion("yes!");
            w.setType(1);
            mWidgetArrayList.add(w);
            adapter.notifyItemInserted(mWidgetArrayList.size() - 1);
        });


        return view;
    }
}
