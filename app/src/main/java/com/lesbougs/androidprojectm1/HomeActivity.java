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

import org.jetbrains.annotations.NotNull;

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

    private TextInputLayout mFormCodeTextField;
    private TextInputEditText mFormCodeEditText;
    private Button mFormCodeButton;
    private final Executor mBackgroundThread = Executors.newSingleThreadExecutor();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mFormCodeTextField = findViewById(R.id.act_home_form_code_text_input);
        mFormCodeEditText = findViewById(R.id.act_home_form_code_edit_text);
        mFormCodeButton = findViewById(R.id.act_home_form_button);

        mFormCodeEditText.setOnFocusChangeListener((v, focus) -> mFormCodeTextField.setError(null));

        findViewById(R.id.act_home_form_button).setOnClickListener(v -> {
            final String formCode = Objects.requireNonNull(mFormCodeEditText.getText()).toString();

            if (isFormCodeInvalid(formCode.length(), mFormCodeTextField)) return;

            mBackgroundThread.execute(() -> {
                FormApiService apiInterface = Api.getClient().create(FormApiService.class);

                Call<JsonObject> call = apiInterface.getForm(formCode);
                runOnUiThread(()-> {
                    Toast.makeText(HomeActivity.this, R.string.api_call, Toast.LENGTH_SHORT).show();
                    mFormCodeButton.setEnabled(false);
                });

                call.enqueue(new Callback<JsonObject>() {
                    @Override
                    public void onResponse(@NotNull Call<JsonObject> call, @NotNull Response<JsonObject> response) {
                        JsonObject object = response.body();
                        if (response.code() == 200) {
                            //prepare nouvelle activity
                            Intent intent = new Intent(HomeActivity.this, VisitorActivity.class);
                            intent.putExtra(Constants.EXTRA_VISITOR_FORM, Objects.requireNonNull(object).toString());//new Gson().toJson(formInstance);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                            //setFormCode(formCode);

                            //lance activity
                            runOnUiThread(()-> startActivity(intent));
                        }
                        else   if (response.code() == 202) {
                            runOnUiThread(()-> {
                                mFormCodeTextField.setError(Objects.requireNonNull(object).get("message").toString());
                                mFormCodeButton.setEnabled(true);
                            });
                        }
                        else {
                            runOnUiThread(()-> {
                                mFormCodeTextField.setError(Objects.requireNonNull(object).get("message").toString());
                                mFormCodeButton.setEnabled(true);
                            });
                        }
                    }

                    @Override
                    public void onFailure(@NotNull Call<JsonObject> call, @NotNull Throwable t) {
                        call.cancel();
                    }
                });
            });
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        //nettoie pour quand reviendra
        Objects.requireNonNull(mFormCodeEditText.getText()).clear();
        mFormCodeTextField.setError(null);
        mFormCodeButton.setEnabled(true);
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