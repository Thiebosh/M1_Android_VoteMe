package com.lesbougs.androidprojectm1;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.lesbougs.androidprojectm1.adapters.WidgetAdapter;
import com.lesbougs.androidprojectm1.api.FormApiService;
import com.lesbougs.androidprojectm1.interfaces.Constants;
import com.lesbougs.androidprojectm1.model.Api;
import com.lesbougs.androidprojectm1.model.Form;
import com.lesbougs.androidprojectm1.model.FormAnswer;
import com.lesbougs.androidprojectm1.model.Widget;
import com.lesbougs.androidprojectm1.model.WidgetAnswer;

import java.util.ArrayList;
import java.util.Objects;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class VisitorActivity extends AppCompatActivity {

    /*
     * Section menu
     */

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.visitor, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.menu_visitor_backward) {
            startActivity((new Intent(VisitorActivity.this, HomeActivity.class))
                    .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
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
        Objects.requireNonNull(VisitorActivity.this.getSupportActionBar()).setSubtitle(mFormData.getSmallId());

        ((TextView) findViewById(R.id.act_visit_title)).setText(mFormData.getTitle());

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.act_visit_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(VisitorActivity.this));
        ArrayList<Widget> widgetArrayList = new ArrayList<>(mFormData.getContent());
        WidgetAdapter adapter = new WidgetAdapter(VisitorActivity.this, widgetArrayList);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        ((Button) findViewById(R.id.act_visit_confirm_button)).setOnClickListener(v -> {
            recyclerView.clearFocus();//call onFocusChange (loose) which update answers

            mBackgroundThread.execute(() -> {
                ArrayList<String> answerArrayList = adapter.getAnswers();

                ArrayList<WidgetAnswer> widgetAnswer = new ArrayList<>();
                for (int i = 0; i < answerArrayList.size(); ++i) {
                    widgetAnswer.add(new WidgetAnswer(widgetArrayList.get(i).get_id(), answerArrayList.get(i)));
                }

                String answerData = new Gson().toJson(new FormAnswer(widgetAnswer));
                Log.d("recyclerViewResult", answerData);

                //add here send request
                FormApiService apiInterface = Api.getClient().create(FormApiService.class);
            });

            onBackPressed();
        });
    }


    @Override
    public void onPause() {
        super.onPause();
        VisitorActivity.this.finish();
    }
}
