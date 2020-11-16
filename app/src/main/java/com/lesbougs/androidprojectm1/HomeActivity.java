package com.lesbougs.androidprojectm1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Objects;

public class HomeActivity extends AppCompatActivity {

    /*
     * Section menu
     */

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.menu_home_login) {
            startActivity((new Intent(HomeActivity.this, AdminActivity.class))
                    .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /*
     * Section life cycle
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        final TextInputLayout formCodeTextField = findViewById(R.id.act_home_form_code_text_input);
        final TextInputEditText formCodeEditText = findViewById(R.id.act_home_form_code_edit_text);

        formCodeEditText.setOnFocusChangeListener((v, focus) -> formCodeTextField.setError(null));

        ((Button) findViewById(R.id.act_home_form_button)).setOnClickListener(v -> {
            final String formCode = Objects.requireNonNull(formCodeEditText.getText()).toString();

            if (isFormCodeInvalid(formCode.length(), formCodeTextField)) return;

            if (!existForm(formCode)) {
                formCodeTextField.setError(getString(R.string.home_wrong_form_code));
                return;
            }

            //nettoie
            formCodeEditText.getText().clear();
            formCodeTextField.setError(null);

            /*Data data = */getFormData(formCode);

            //transmet data par classe statique ou par intent. peut être intent pour lui :
            // un seul écran derrière et fait bosser les deux méthodes (classe statique pour admin)
            //peut déjà passer form code par intent pour l'exemple
            startActivity((new Intent(HomeActivity.this, VisitorActivity.class))
                .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
        });
    }

    /*
     * Section private methods
     */

    private boolean isFormCodeInvalid(final int codeLength, final TextInputLayout formCodeTextField) {
        final int length = HomeActivity.this.getResources().getInteger(R.integer.form_code_min_length);
        if (codeLength < length) {
            formCodeTextField.setError(getString(R.string.home_error_form_code_short));
            return true;
        }
        if (codeLength > length) {
            formCodeTextField.setError(getString(R.string.home_error_form_code_long));
            return true;
        }
        return false;
    }

    private boolean existForm(final String code) {
        //api call
        return true;
    }

    private void getFormData(final String formCode) {
        //api call
        //return data
    }
}