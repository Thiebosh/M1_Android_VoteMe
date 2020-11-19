package com.lesbougs.androidprojectm1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.gson.JsonObject;
import com.lesbougs.androidprojectm1.api.FormApiService;
import com.lesbougs.androidprojectm1.fragments.FormListFragment;
import com.lesbougs.androidprojectm1.interfaces.FragmentSwitcher;
import com.lesbougs.androidprojectm1.interfaces.UserAccess;
import com.lesbougs.androidprojectm1.model.Api;
import com.lesbougs.androidprojectm1.model.Form;
import com.lesbougs.androidprojectm1.model.User;

import java.sql.Time;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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

        String tmp = "2020-11-18T13:08:11.30";
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            LocalDateTime myDateObj = LocalDateTime.parse(tmp);
        }

        final TextInputLayout formCodeTextField = findViewById(R.id.act_home_form_code_text_input);
        final TextInputEditText formCodeEditText = findViewById(R.id.act_home_form_code_edit_text);

        formCodeEditText.setOnFocusChangeListener((v, focus) -> formCodeTextField.setError(null));

        ((Button) findViewById(R.id.act_home_form_button)).setOnClickListener(v -> {
            final String formCode = Objects.requireNonNull(formCodeEditText.getText()).toString();

            if (isFormCodeInvalid(formCode.length(), formCodeTextField)) return;


            FormApiService apiInterface = Api.getClient().create(FormApiService.class);
            Call<JsonObject> call = apiInterface.getForm(formCode);
            Toast.makeText(HomeActivity.this, "Loading...",Toast.LENGTH_SHORT).show();
            findViewById(R.id.act_home_form_button).setEnabled(false);

            call.enqueue(new Callback<JsonObject>() {
                @Override
                public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {

                    Log.d("TAG", response.code() + "");

                    JsonObject object = response.body();

                    if (response.code() == 200) {
                        //nettoie pour quand reviendra
                        formCodeEditText.getText().clear();
                        formCodeTextField.setError(null);

                        String arrayJSON = object.get("forms").toString();
                        arrayJSON = arrayJSON.substring(1,arrayJSON.length()-1);

                        //List<String> items = Arrays.asList(arrayJSON.split("\\s*,\\s*"));
                        //Form form = new Form();//object.get("_id").toString(), object.get("username").toString(), new Date(object.get("creationDate").toString()),items);

                        Intent intent = new Intent(HomeActivity.this, VisitorActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
/*
                        final Bundle formData = new Bundle();
                        formData.putString(Constants.Login.EXTRA_LOGIN, form);
                        intent.putExtras(formData);
*/
                        startActivity(intent);
                    } else {
                        formCodeTextField.setError(object.get("message").toString());
                        findViewById(R.id.act_home_form_button).setEnabled(true);
                    }
                }

                @Override
                public void onFailure(Call<JsonObject> call, Throwable t) {
                    Log.d("TAG", "fait iech");
                    call.cancel();
                }
            });
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
}