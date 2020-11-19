package com.lesbougs.androidprojectm1.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.lesbougs.androidprojectm1.R;
import com.lesbougs.androidprojectm1.api.FormApiService;
import com.lesbougs.androidprojectm1.interfaces.UserAccess;
import com.lesbougs.androidprojectm1.interfaces.FragmentSwitcher;
import com.lesbougs.androidprojectm1.model.Api;
import com.lesbougs.androidprojectm1.model.Form;
import com.lesbougs.androidprojectm1.model.User;
import com.lesbougs.androidprojectm1.model.Widget;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class LoginFragment extends Fragment {

    /*
     * Section menu
     */

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        MenuItem item = menu.findItem(R.id.menu_admin_logout);
        if (item != null) item.setVisible(false);
    }

    /*
     * Section life cycle
     */

    private final Executor mBackgroundThread = Executors.newSingleThreadExecutor();

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        setHasOptionsMenu(true);//active le onPrepareOptionsMenu

        final TextInputLayout usernameTextField = view.findViewById(R.id.frag_log_username_text_input);
        final TextInputEditText usernameEditText = view.findViewById(R.id.frag_log_username_edit_text);
        final TextInputLayout passwordTextField = view.findViewById(R.id.frag_log_password_text_input);
        final TextInputEditText passwordEditText = view.findViewById(R.id.frag_log_password_edit_text);

        usernameEditText.setOnFocusChangeListener((v, focus) -> {
            String msg = null;
            if (!focus && isUsernameInvalid(Objects.requireNonNull(usernameEditText.getText()).length())) {
                msg = getString(R.string.admin_error_username);
            }
            usernameTextField.setError(msg);
        });

        passwordEditText.setOnFocusChangeListener((v, focus) -> {
            String msg = null;
            if (!focus && isPasswordInvalid(Objects.requireNonNull(passwordEditText.getText()).length())) {
                msg = getString(R.string.admin_error_password);
            }
            passwordTextField.setError(msg);
        });


        view.findViewById(R.id.frag_log_next_button).setOnClickListener(v -> {
            boolean isValid = true;
            if (isUsernameInvalid(Objects.requireNonNull(usernameEditText.getText()).length())) {
                isValid = false;
                usernameTextField.setError(getString(R.string.admin_error_username));
            }
            if (isPasswordInvalid(Objects.requireNonNull(passwordEditText.getText()).length())) {
                isValid = false;
                passwordTextField.setError(getString(R.string.admin_error_password));
            }
            if (!isValid) return;

            mBackgroundThread.execute(() -> {
                final String username = usernameEditText.getText().toString();
                String password = passwordEditText.getText().toString();

                FormApiService apiInterface = Api.getClient().create(FormApiService.class);

                Call<JsonObject> call = apiInterface.signIn(username, password);
                Objects.requireNonNull(getActivity()).runOnUiThread(()-> {
                    Toast.makeText(getContext(), R.string.api_call, Toast.LENGTH_SHORT).show();
                    view.findViewById(R.id.frag_log_next_button).setEnabled(false);
                });

                call.enqueue(new Callback<JsonObject>() {
                    @Override
                    public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                        JsonObject object = response.body();

                        List<String> Cookielist = response.headers().values("Set-Cookie");

                        if (response.code() == 200) {
                            User actualUser = (new Gson()).fromJson(Objects.requireNonNull(object).toString(), User.class);

                            String headerPayload = Cookielist.get(0);
                            headerPayload = headerPayload.substring(/*headerPayload.indexOf('=')+1*/0, headerPayload.indexOf(';'));
                            actualUser.setHeaderPayload(headerPayload);

                            String signature = Cookielist.get(1);
                            signature = signature.substring(/*signature.indexOf('=')+1*/0, signature.indexOf(';'));
                            actualUser.setSignature(signature);

                            ((UserAccess) Objects.requireNonNull(getActivity())).setUser(actualUser);//save on activity

                            getActivity().runOnUiThread(() ->
                                ((FragmentSwitcher) Objects.requireNonNull(getActivity()))
                                        .loadFragment(new FormListFragment(), true)
                            );
                        } else {
                            Objects.requireNonNull(getActivity()).runOnUiThread(() -> {
                                view.findViewById(R.id.frag_log_next_button).setEnabled(true);

                                assert object != null;
                                usernameTextField.setError(object.get("message").toString());
                                passwordTextField.setError(object.get("message").toString());
                            });
                        }
                    }

                    @Override
                    public void onFailure(Call<JsonObject> call, Throwable t) {
                        call.cancel();
                    }
                });
            });

        });


        view.findViewById(R.id.frag_log_cancel_button).setOnClickListener(v -> Objects.requireNonNull(getActivity()).onBackPressed());

        /*view.findViewById(R.id.frag_log_sign_button).setOnClickListener(v ->
                ((FragmentSwitcher) Objects.requireNonNull(getActivity()))
                        .loadFragment(new RegisterFragment(), false)
        );*/

        return view;
    }


    /*
     * Section private methods
     */

    private boolean isUsernameInvalid(final int usernameLength) {
        return usernameLength < Objects.requireNonNull(getContext()).getResources().getInteger((R.integer.username_min_length));
    }

    private boolean isPasswordInvalid(final int passwordLength) {
        return passwordLength < Objects.requireNonNull(getContext()).getResources().getInteger((R.integer.password_min_length));
    }
}
