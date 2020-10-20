package com.lesbougs.androidprojectm1;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class FormActivity extends AppCompatActivity {

    //terminal donc pas besoin de flag

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);
    }

    @Override
    public void onRestart() {//facultatif : retour à l'écran principal
        super.onRestart();
        startActivity((new Intent(FormActivity.this, HomeActivity.class)).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.visitor, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()) {
            case R.id.menu_visitor_backward:
                startActivity((new Intent(FormActivity.this, HomeActivity.class)).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
