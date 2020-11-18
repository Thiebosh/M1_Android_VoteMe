package com.lesbougs.androidprojectm1;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.lesbougs.androidprojectm1.interfaces.Constants;
import com.lesbougs.androidprojectm1.model.Form;

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
            mFormData = (new Gson()).fromJson(extras.getString(Constants.EXTRA_VISITOR_FORM), Form.class);
        });

        setContentView(R.layout.activity_visitor);
        Objects.requireNonNull(VisitorActivity.this.getSupportActionBar()).setSubtitle(mFormData.getTitle());

        ((Button) findViewById(R.id.act_visit_confirm_button)).setOnClickListener(v -> onBackPressed());
    }


    @Override
    public void onPause() {
        super.onPause();
        VisitorActivity.this.finish();
    }
}
