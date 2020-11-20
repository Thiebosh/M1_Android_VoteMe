package com.lesbougs.androidprojectm1.fragments;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.lesbougs.androidprojectm1.R;
import com.lesbougs.androidprojectm1.adapters.AdminWidgetAdapter;
import com.lesbougs.androidprojectm1.api.FormApiService;
import com.lesbougs.androidprojectm1.interfaces.FragmentSwitcher;
import com.lesbougs.androidprojectm1.interfaces.UserAccess;
import com.lesbougs.androidprojectm1.model.Api;
import com.lesbougs.androidprojectm1.model.Form;
import com.lesbougs.androidprojectm1.model.User;
import com.lesbougs.androidprojectm1.model.Widget;

import java.util.ArrayList;
import java.util.Objects;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FormCreateFragment extends Fragment {


    private final Executor mBackgroundThread = Executors.newSingleThreadExecutor();
    private String actualTypeform = "";

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_form_create, container, false);


        User currentUser = ((UserAccess) Objects.requireNonNull(getActivity())).getUser();//get data


        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recyclerViewAllFormAdded);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        ArrayList<Widget> widgetArrayList = new ArrayList<>();
        AdminWidgetAdapter adapter = new AdminWidgetAdapter(getContext(), widgetArrayList);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        Button buttonSave = (Button) view.findViewById(R.id.frag_form_edit_save);


        TextInputEditText titleTextField = (TextInputEditText) view.findViewById(R.id.edititleForm);


        buttonSave.setOnClickListener((view1) -> {
            recyclerView.clearFocus();


            JsonObject json = new JsonObject();

            if(!this.actualTypeform.equals("Text") && !this.actualTypeform.equals("Grade")){
                Objects.requireNonNull(getActivity()).runOnUiThread(() -> {
                    Toast.makeText(getContext(), "Type form undefined", Toast.LENGTH_SHORT).show();
                });
            }
            else{
                json.addProperty("title", titleTextField.getText()+"");

                JsonArray array = new JsonArray();
                for (Widget elem : widgetArrayList) {
                    JsonObject item = new JsonObject();
                    if (elem.getType() == 0) {
                        item.addProperty("type", 0);
                        item.addProperty("question", elem.getQuestion());
                    } else if (elem.getType() == 1) {
                        item.addProperty("type", 1);
                        item.addProperty("question", elem.getQuestion());
                        item.addProperty("maxPoint", elem.getMaxPoint());
                        item.addProperty("minPoint", elem.getMinPoint());
                    }
                    array.add(item);
                }

                json.add("arrayWidget", array);


                mBackgroundThread.execute(() -> {
                    String answerData = new Gson().toJson(new Form(widgetArrayList));
                    FormApiService apiInterface = Api.getClient().create(FormApiService.class);

                    Call<JsonObject> call = apiInterface.createForm(currentUser.getHeaderPayload(), currentUser.getSignature(), (JsonObject) json);
                    Objects.requireNonNull(getActivity()).runOnUiThread(() -> {
                        Toast.makeText(getContext(), R.string.api_call, Toast.LENGTH_SHORT).show();
                    });

                    call.enqueue(new Callback<JsonObject>() {
                        @Override
                        public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                            JsonObject object = response.body();


                            if (response.code() == 200) {
                                User actualUser = (new Gson()).fromJson(Objects.requireNonNull(object).toString(), User.class);

                                String headerPayload = currentUser.getHeaderPayload();
                                actualUser.setHeaderPayload(headerPayload);

                                String signature =currentUser.getSignature();
                                actualUser.setSignature(signature);


                                ((UserAccess) Objects.requireNonNull(getActivity())).setUser(actualUser);

                                Objects.requireNonNull(getActivity()).runOnUiThread(() -> {
                                    Toast.makeText(getContext(),"Form added", Toast.LENGTH_SHORT).show();
                                });

                            } else {
                                Objects.requireNonNull(getActivity()).runOnUiThread(() -> {
                                    Toast.makeText(getContext(), object.get("message").toString(), Toast.LENGTH_SHORT).show();
                                });
                            }
                        }

                        @Override
                        public void onFailure(Call<JsonObject> call, Throwable t) {
                            call.cancel();
                        }
                    });
                });
            }



        });

        ((FloatingActionButton) view.findViewById(R.id.floatingActionButton)).setOnClickListener(v -> {
            String[] type = {"Grade", "Text"};
            final Dialog dialog = new Dialog(getContext());
            dialog.setContentView(R.layout.item_pop_create_form);
            ArrayAdapter adapterType = new ArrayAdapter(getContext(), android.R.layout.simple_list_item_1, type);


            Button buttonAddForm = (Button) dialog.findViewById(R.id.buttonAddForm);
            Button buttonCancel = (Button) dialog.findViewById(R.id.buttonCancel);

            buttonAddForm.setOnClickListener((view1) ->
            {
                Widget w = new Widget();

                switch (actualTypeform) {
                    case "Text":
                        w.setType(0);
                        break;
                    case "Grade":
                        w.setType(1);
                        break;
                    default:
                        //toast d"erreur
                        return;
                }
                widgetArrayList.add(w);
                adapter.notifyItemInserted(widgetArrayList.size() - 1);
            });

            buttonCancel.setOnClickListener((view1) ->
            {
                dialog.dismiss();
            });

            AutoCompleteTextView autoComplete = (AutoCompleteTextView) dialog.findViewById(R.id.chooseTypeOfWidget);
            autoComplete.setAdapter(adapterType);
            autoComplete.setThreshold(1);



            autoComplete.setOnItemClickListener((parent, view1, position, id) -> {
                Object item = parent.getItemAtPosition(position);
                if (item instanceof String) {
                    this.actualTypeform = (String) item;
                }
            });

            autoComplete.setOnFocusChangeListener((View view3, boolean direction)->{
                autoComplete.showDropDown();
            });

            dialog.show();
        });


        ((Button) view.findViewById(R.id.frag_form_come_back)).setOnClickListener(v ->
                ((FragmentSwitcher) Objects.requireNonNull(getActivity()))
                        .loadFragment(new FormListFragment(), true)
        );



        /*ArrayList<Widget> mWidgetArrayList = new ArrayList<>();
        AdminWidgetAdapter adapter = new AdminWidgetAdapter(getContext(), mWidgetArrayList);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        ((Button) view.findViewById(R.id.frag_form_create)).setOnClickListener(v -> {
            Widget w = new Widget();
            w.setQuestion("yes!");
            w.setType(1);
            mWidgetArrayList.add(w);
            adapter.notifyItemInserted(mWidgetArrayList.size() - 1);

            //String json = new Gson().toJson(adapter.getAnswers());
        });*/


        return view;
    }
}
