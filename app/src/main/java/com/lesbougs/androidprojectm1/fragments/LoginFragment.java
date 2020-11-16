package com.lesbougs.androidprojectm1.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.lesbougs.androidprojectm1.HomeActivity;
import com.lesbougs.androidprojectm1.R;
import com.lesbougs.androidprojectm1.interfaces.FragmentSwitcher;

import java.util.Objects;


public class LoginFragment extends Fragment {

    /*
     * Section menu
     */

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        MenuItem item = menu.findItem(R.id.menu_admin_logout);
        if (item!=null) item.setVisible(false);
    }

    /*
     * Section life cycle
     */

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
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

            final String username = usernameEditText.getText().toString();
            String password = passwordEditText.getText().toString();

            if (!existUsername(username)) {
                usernameTextField.setError(getString(R.string.admin_wrong_username));
                return;
            }

            /*Data data = */getUserData(usernameEditText.getText().toString());

            //hash password
            boolean passwordMatch = true; //compare hashs
            if (!passwordMatch) {
                passwordTextField.setError(getString(R.string.admin_wrong_password));
                return;
            }

            //save on service instance? pass data by intent?
            ((FragmentSwitcher) Objects.requireNonNull(getActivity()))
                    .loadFragment(new FormListFragment(username), true);
        });


        view.findViewById(R.id.frag_log_cancel_button).setOnClickListener(v -> {
            //getActivity().onBackPressed();//plus propre mais necessite de ne pas stacker systématiquement
            startActivity((new Intent(getActivity(), HomeActivity.class))
                    .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
        });

        view.findViewById(R.id.frag_log_sign_button).setOnClickListener(v ->
                ((FragmentSwitcher) Objects.requireNonNull(getActivity()))
                    .loadFragment(new RegisterFragment(), false)
        );

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

    private boolean existUsername(final String username) {
        //api call
        return true;
    }

    private void getUserData(String username) {
        //api call
        //return data
    }

}
