package com.lesbougs.androidprojectm1;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.lesbougs.androidprojectm1.adapters.VisitorWidgetAdapter;
import com.lesbougs.androidprojectm1.api.FormApiService;
import com.lesbougs.androidprojectm1.interfaces.Constants;
import com.lesbougs.androidprojectm1.model.Api;
import com.lesbougs.androidprojectm1.model.Form;
import com.lesbougs.androidprojectm1.model.FormAnswer;
import com.lesbougs.androidprojectm1.model.Widget;
import com.lesbougs.androidprojectm1.model.WidgetAnswer;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Objects;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VisitorActivity extends AppCompatActivity {

    /*
     * Section menu
     */

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.visitor, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.menu_visitor_backward) {
            VisitorActivity.this.finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /*
     * Section life cycle
     */

    private Form mFormData;
    private final Executor mBackgroundThread = Executors.newSingleThreadExecutor();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mBackgroundThread.execute(() -> {
            Bundle extras = getIntent().getExtras();
            if (extras == null) {
                //toast d'erreur?
                VisitorActivity.this.finish();
            }

            mFormData = (new Gson()).fromJson(Objects.requireNonNull(extras).getString(Constants.EXTRA_VISITOR_FORM), Form.class);
        });

        setContentView(R.layout.activity_visitor);

        assert VisitorActivity.this.getSupportActionBar() != null;
        String str = "Form code : "+mFormData.getSmallId();
        VisitorActivity.this.getSupportActionBar().setTitle(str);

        ((TextView) findViewById(R.id.act_visit_title)).setText(mFormData.getTitle());


        RecyclerView recyclerView = findViewById(R.id.act_visit_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(VisitorActivity.this));

        ArrayList<Widget> widgetArrayList = new ArrayList<>(mFormData.getWidget());
        VisitorWidgetAdapter adapter = new VisitorWidgetAdapter(VisitorActivity.this, widgetArrayList);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();


        findViewById(R.id.act_visit_confirm_button).setOnClickListener(v -> {
            recyclerView.clearFocus();//call onFocusChange (loose) which update answers

            mBackgroundThread.execute(() -> {
                ArrayList<String> answerArrayList = adapter.getAnswers();

                ArrayList<WidgetAnswer> widgetAnswer = new ArrayList<>();
                for (int i = 0; i < answerArrayList.size(); ++i) {
                    if (answerArrayList.get(i).equals("")) {
                        runOnUiThread(() -> {
                            findViewById(R.id.act_visit_confirm_button).setEnabled(true);
                            Toast.makeText(VisitorActivity.this, "Empty fields remaining", Toast.LENGTH_SHORT).show();
                        });
                        return;
                    }
                    if (widgetArrayList.get(i).getType() == 1) {
                        int value;
                        try {
                            value = Integer.parseInt(answerArrayList.get(i));
                        }
                        catch (Exception ignore) {
                            runOnUiThread(() -> {
                                findViewById(R.id.act_visit_confirm_button).setEnabled(true);
                                Toast.makeText(VisitorActivity.this, "Non numeric values", Toast.LENGTH_SHORT).show();
                            });
                            return;
                        }
                        if (value < widgetArrayList.get(i).getMinPoint() || value > widgetArrayList.get(i).getMaxPoint()) {
                            runOnUiThread(() -> {
                                findViewById(R.id.act_visit_confirm_button).setEnabled(true);
                                Toast.makeText(VisitorActivity.this, "Incorrect numeric values", Toast.LENGTH_SHORT).show();
                            });
                            return;
                        }
                    }

                    widgetAnswer.add(new WidgetAnswer(widgetArrayList.get(i).get_id(), answerArrayList.get(i)));
                }

                String answerData = new Gson().toJson(new FormAnswer(widgetAnswer));
                answerData = answerData.substring(10, answerData.length()-1);//retire "result:"

                FormApiService apiInterface = Api.getClient().create(FormApiService.class);

                Call<JsonObject> call = apiInterface.setFormResult(mFormData.get_id(), answerData);

                runOnUiThread(() -> {
                    Toast.makeText(VisitorActivity.this, R.string.api_call, Toast.LENGTH_SHORT).show();
                    findViewById(R.id.act_visit_confirm_button).setEnabled(false);
                });

                call.enqueue(new Callback<JsonObject>() {
                    @Override
                    public void onResponse(@NotNull Call<JsonObject> call, @NotNull Response<JsonObject> response) {
                        if (response.code() == 200)
                            runOnUiThread(() -> {
                                Toast.makeText(VisitorActivity.this, "Successfully send answer", Toast.LENGTH_SHORT).show();
                                onBackPressed();
                            });//fini : revient à l'écran principal
                        else {
                            runOnUiThread(() -> {
                                assert response.body() != null;
                                String str = response.body().get("message").toString();
                                Toast.makeText(VisitorActivity.this, str, Toast.LENGTH_SHORT).show();
                                findViewById(R.id.act_visit_confirm_button).setEnabled(true);
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
    public void onPause() {
        super.onPause();
        VisitorActivity.this.finish();
    }
}
