package com.lesbougs.androidprojectm1.fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.MultiAutoCompleteTextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.lesbougs.androidprojectm1.R;
import com.lesbougs.androidprojectm1.interfaces.FragmentSwitcher;

import java.util.Objects;

public class FormCreateFragment extends Fragment {



    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_form_edit, container, false);




        ((FloatingActionButton) view.findViewById(R.id.floatingActionButton)).setOnClickListener(v ->{
                    String[] type={"Grade","Text"};
                    final Dialog dialog = new Dialog(getContext());
                    dialog.setContentView(R.layout.item_pop_create_form);
                    ArrayAdapter adapterType = new ArrayAdapter(getContext(),android.R.layout.simple_list_item_1,type);

                    AutoCompleteTextView prout = (AutoCompleteTextView) dialog.findViewById(R.id.chooseTypeOfWidget);
                    prout.setAdapter(adapterType);
                    prout.setThreshold(2);
                    dialog.show();
                });


        ((Button) view.findViewById(R.id.frag_form_edit_addButton)).setOnClickListener(v ->
                ((FragmentSwitcher) Objects.requireNonNull(getActivity()))
                    .loadFragment(new FormListFragment(), true)
        );

        return view;
    }
}
