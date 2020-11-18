package com.lesbougs.androidprojectm1.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
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


public class RegisterFragment extends Fragment {

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
        View view = inflater.inflate(R.layout.fragment_register, container, false);

        setHasOptionsMenu(true);//active le onPrepareOptionsMenu


        final TextInputLayout usernameTextField = view.findViewById(R.id.frag_reg_username_text_input);
        final TextInputEditText usernameEditText = view.findViewById(R.id.frag_reg_username_edit_text);
        final TextInputLayout passwordTextField = view.findViewById(R.id.frag_reg_password_text_input);
        final TextInputEditText passwordEditText = view.findViewById(R.id.frag_reg_password_edit_text);
        final TextInputLayout confirmPasswordTextField = view.findViewById(R.id.frag_reg_confirm_password_text_input);
        final TextInputEditText confirmPasswordEditText = view.findViewById(R.id.frag_reg_confirm_password_edit_text);

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

        confirmPasswordEditText.setOnFocusChangeListener((v, focus) -> {
            String msg = null;
            Editable password = Objects.requireNonNull(passwordEditText.getText());
            Editable confirmPassword = Objects.requireNonNull(confirmPasswordEditText.getText());
            if (!focus) {
                if (isPasswordInvalid(confirmPassword.length())) {
                    msg = getString(R.string.admin_error_password);
                }
                else if (isConfirmPasswordInvalid(password, confirmPassword)) {
                    msg = getString(R.string.admin_error_confirm_password);
                }
            }
            confirmPasswordTextField.setError(msg);
        });


        view.findViewById(R.id.frag_reg_next_button).setOnClickListener(v -> {
            boolean isValid = true;
            if (isUsernameInvalid(Objects.requireNonNull(usernameEditText.getText()).length())) {
                isValid = false;
                usernameTextField.setError(getString(R.string.admin_error_username));
            }
            if (isPasswordInvalid(Objects.requireNonNull(passwordEditText.getText()).length())) {
                isValid = false;
                passwordTextField.setError(getString(R.string.admin_error_password));
            }
            if (isPasswordInvalid(Objects.requireNonNull(confirmPasswordEditText.getText()).length())) {
                isValid = false;
                confirmPasswordTextField.setError(getString(R.string.admin_error_password));
            }
            else if (isConfirmPasswordInvalid(passwordEditText.getText(), confirmPasswordEditText.getText())) {
                isValid = false;
                confirmPasswordTextField.setError(getString(R.string.admin_error_confirm_password));
            }
            if (!isValid) return;

            final String username = usernameEditText.getText().toString();
            String password = passwordEditText.getText().toString();

            if (existUsername(username)) {
                usernameTextField.setError(getString(R.string.admin_wrong_username));
                return;
            }

            registerNewUser(username, password);

            //save on service instance? pass data by intent?
            ((FragmentSwitcher) Objects.requireNonNull(getActivity()))
                    .loadFragment(new FormListFragment(), true);
        });


        view.findViewById(R.id.frag_reg_cancel_button).setOnClickListener(v -> {
            //getActivity().onBackPressed();//plus propre mais necessite de ne pas stacker systématiquement
            startActivity((new Intent(getActivity(), HomeActivity.class))
                    .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
        });

        view.findViewById(R.id.frag_reg_login_button).setOnClickListener(v ->
                ((FragmentSwitcher) Objects.requireNonNull(getActivity()))
                    .loadFragment(new LoginFragment(), false)
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

    private boolean isConfirmPasswordInvalid(final Editable password, final Editable confirmPassword) {
        boolean cond1 = password.length() < Objects.requireNonNull(getContext()).getResources().getInteger((R.integer.password_min_length));
        boolean cond2 = !password.toString().equals(confirmPassword.toString());
        return cond1 || cond2;
    }

    private boolean existUsername(final String username) {
        //api call
        return false;
    }

    private void registerNewUser(final String username, String password) {
        //password hash
        //api call
        //return data?
    }

}
