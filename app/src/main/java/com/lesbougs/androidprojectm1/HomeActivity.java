package com.lesbougs.androidprojectm1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.gson.JsonObject;
import com.lesbougs.androidprojectm1.api.FormApiService;
import com.lesbougs.androidprojectm1.interfaces.Constants;
import com.lesbougs.androidprojectm1.model.Api;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

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

    private final Executor mBackgroundThread = Executors.newSingleThreadExecutor();

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

            mBackgroundThread.execute(() -> {
                FormApiService apiInterface = Api.getClient().create(FormApiService.class);

                Call<JsonObject> call = apiInterface.getForm(formCode);
                runOnUiThread(()-> {
                    Toast.makeText(HomeActivity.this, R.string.api_call, Toast.LENGTH_SHORT).show();
                    findViewById(R.id.act_home_form_button).setEnabled(false);
                });

                call.enqueue(new Callback<JsonObject>() {
                    @Override
                    public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                        JsonObject object = response.body();
                        if (response.code() == 200) {
                            //nettoie pour quand reviendra
                            runOnUiThread(()-> {
                                formCodeEditText.getText().clear();
                                formCodeTextField.setError(null);
                            });

                            //prepare nouvelle activity
                            Intent intent = new Intent(HomeActivity.this, VisitorActivity.class);
                            intent.putExtra(Constants.EXTRA_VISITOR_FORM, object.toString());//new Gson().toJson(formInstance);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                            //lance activity
                            runOnUiThread(()-> startActivity(intent));
                        }
                        else {
                            runOnUiThread(()-> {
                                formCodeTextField.setError(object.get("message").toString());
                                findViewById(R.id.act_home_form_button).setEnabled(true);
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