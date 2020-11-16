package com.lesbougs.androidprojectm1;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visitor);

        String formName = "titre formulaire";
        Objects.requireNonNull(VisitorActivity.this.getSupportActionBar()).setSubtitle(formName);

        ((Button) findViewById(R.id.act_vis_confirmButton)).setOnClickListener(v -> onBackPressed());
    }

    @Override
    public void onPause() {//facultatif : retour à l'écran principal
        super.onPause();
        VisitorActivity.this.finish();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Objects.requireNonNull(VisitorActivity.this.getSupportActionBar()).setSubtitle(null);
    }
}
