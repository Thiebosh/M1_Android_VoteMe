package com.lesbougs.androidprojectm1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

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

        ((Button) findViewById(R.id.act_hom_formButton)).setOnClickListener(v ->
                startActivity((new Intent(HomeActivity.this, VisitorActivity.class))
                    .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
        );
    }

}