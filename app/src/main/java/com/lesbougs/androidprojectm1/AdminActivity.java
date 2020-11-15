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

import com.lesbougs.androidprojectm1.api.FormApiService;
import com.lesbougs.androidprojectm1.fragments.LoginFragment;
import com.lesbougs.androidprojectm1.fragments.FragmentSwitcher;
import com.lesbougs.androidprojectm1.model.FAPIData;
import com.lesbougs.androidprojectm1.model.User;

import java.io.IOException;
import java.util.Objects;
import java.util.Stack;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class AdminActivity extends AppCompatActivity implements FragmentSwitcher {

    /*
     * Section FragmentSwitcher
     */

    @Override
    public void loadFragment(Fragment fragment, boolean addToBackstack) {//pas encore de cas faux
        String searched = fragment.getClass().getSimpleName();
        Stack<Fragment> searcher = (Stack<Fragment>) mFragmentStack.clone();
        int position = searcher.size() - 1;//size -> index
        while (!searcher.empty()) {
            if (searcher.pop().getClass().getSimpleName().equals(searched)) break;
            --position;
        }
        if (position != -1) for (int i = mFragmentStack.size(); i > position; --i) mFragmentStack.pop();

        mFragmentStack.push(fragment);//renouvellement du frament stocké
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.act_adm_fragmentContainer, fragment)
                .commit();
    }

    @Override
    public void onBackPressed() {
        mFragmentStack.pop();

        if (mFragmentStack.empty()) {
            startActivity((new Intent(AdminActivity.this, HomeActivity.class))
                    .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
        }
        else loadFragment(mFragmentStack.peek(), true);//reason why it's not a String stack
    }

    /*
     * Section menu
     */

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.admin, menu);//menu de l'activité entière
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        //un seul menu pour tous les fragments : définit tous les cas ici et affiche / masque dans les fragments
        switch(item.getItemId()) {
            case R.id.menu_admin_logout:
                startActivity((new Intent(AdminActivity.this, HomeActivity.class))
                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /*
     * Section cycle de vie
     */

    private FormApiService fApiService;
    private Executor backgroundExecutor = Executors.newSingleThreadExecutor(); //pas de pb de concurrence

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        if (savedInstanceState == null) loadFragment(new LoginFragment(), false);

        //retrofitTest();
    }

    @Override
    public void onRestart() {//retour à l'écran principal
        super.onRestart();
        startActivity((new Intent(AdminActivity.this, HomeActivity.class))
                .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Objects.requireNonNull(AdminActivity.this.getSupportActionBar()).setSubtitle(null);
    }

    /*
     * Section autres méthodes
     */

    private void retrofitTest() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://swapi.dev/api/")
                .addConverterFactory(GsonConverterFactory.create()) //ajoute parseur json
                .build();

        fApiService = retrofit.create(FormApiService.class);

        backgroundExecutor.execute(() -> {
            //recup des perso depuis api
            try {
                //Response<List<StarWarsPerson>> response = swapiService.listPerson().execute();
                Response<FAPIData> response = fApiService.getData().execute();
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
}
