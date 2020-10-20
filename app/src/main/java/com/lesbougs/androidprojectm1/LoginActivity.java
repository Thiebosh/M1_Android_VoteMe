package com.lesbougs.androidprojectm1;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    private boolean mFlagChangeActivity = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //methode 2, 1 dans HomeActivity
        ((Button) findViewById(R.id.loginButton)).setOnClickListener(v -> {
            startActivity((new Intent(LoginActivity.this, ListFormActivity.class)).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
        });
    }

    @Override
    public void onRestart() {//retour à l'écran principal
        super.onRestart();
        if (!mFlagChangeActivity) {
            startActivity((new Intent(LoginActivity.this, HomeActivity.class)).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
        }
        else mFlagChangeActivity = false;
    }
}
