package com.lesbougs.androidprojectm1;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.lesbougs.androidprojectm1.api.FormApiService;
import com.lesbougs.androidprojectm1.fragments.LoginFragment;
import com.lesbougs.androidprojectm1.model.FAPIData;
import com.lesbougs.androidprojectm1.model.User;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class AdminActivity extends AppCompatActivity implements NavigationHost {

    private FormApiService fapiService;
    private Executor backgroundExecutor = Executors.newSingleThreadExecutor(); //pas de pb de concurrence

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.act_adm_fragmentContainer, new LoginFragment())
                    .commit();
        }


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://swapi.dev/api/")
                .addConverterFactory(GsonConverterFactory.create()) //ajoute parseur json
                .build();

        fapiService = retrofit.create(FormApiService.class);

        backgroundExecutor.execute(() -> {
            //recup des perso depuis api
            try {
                //Response<List<StarWarsPerson>> response = swapiService.listPerson().execute();
                Response<FAPIData> response = fapiService.getData().execute();
                if (response.isSuccessful()) {//sinon, vérifier avec .code()
                    /*
                    List<User> persons = response.body().results;
                    Log.d("demo app", "People:"+persons.size());

                    */

                    String content = "";
                    for (User user : response.body().results) {
                        content += "ID: " + user.getUserName() + "\n";
                        content += "User ID: " + user.getPassword() + "\n";
                    }
                    String finalContent = content;
                    runOnUiThread(()-> {
                        Toast.makeText(getApplicationContext(), finalContent, Toast.LENGTH_SHORT).show();
                    });
                }
                else Log.w("Demo app", "pb avec requête");
            } catch (IOException ignore) {}
        });
    }

    @Override
    public void onRestart() {//retour à l'écran principal
        super.onRestart();
        startActivity((new Intent(AdminActivity.this, HomeActivity.class))
                .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
    }

    @Override
    public void navigateTo(Fragment fragment, boolean addToBackstack) {
        FragmentTransaction transaction =
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.act_adm_fragmentContainer, fragment);

        if (addToBackstack) {
            transaction.addToBackStack(null);
        }

        transaction.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.admin, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()) {
            case R.id.menu_admin_logout:
                startActivity((new Intent(AdminActivity.this, HomeActivity.class))
                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
