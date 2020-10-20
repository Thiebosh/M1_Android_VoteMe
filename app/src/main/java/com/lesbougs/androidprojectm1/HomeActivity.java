package com.lesbougs.androidprojectm1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

public class HomeActivity extends AppCompatActivity {

    private Button mLoginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //methode 1, 2 dans LoginActivity
        mLoginButton = (Button) findViewById(R.id.formButton);
        mLoginButton.setOnClickListener(v -> {
            startActivity((new Intent(HomeActivity.this, FormActivity.class)).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()) {
            case R.id.menu_home_login:
                startActivity((new Intent(HomeActivity.this, LoginActivity.class)).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}