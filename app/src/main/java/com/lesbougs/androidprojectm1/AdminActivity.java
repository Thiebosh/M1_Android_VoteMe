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
import com.lesbougs.androidprojectm1.interfaces.FragmentSwitcher;
import com.lesbougs.androidprojectm1.model.Api;
import com.lesbougs.androidprojectm1.model.User;

import java.util.Objects;
import java.util.Stack;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import retrofit2.Call;
import retrofit2.Callback;
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

        if (addToBackstack) mFragmentStack.push(fragment);//renouvellement du frament stocké

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.act_adm_fragmentContainer, fragment)
                .commit();
    }

    @Override
    public void onBackPressed() {
        if (mFragmentStack.size() < 2) {//besoin d'écran actuel et précédent
            mFragmentStack.clear();
            AdminActivity.this.finish();//revient à l'activité précédente
        }
        else {
            mFragmentStack.pop();//ecran actuel
            loadFragment(mFragmentStack.peek(), true);//ecran précédent / can't be a String stack
        }
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
        if (item.getItemId() == R.id.menu_admin_logout) {
            AdminActivity.this.finish();//revient à l'activité précédente
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /*
     * Section life cycle
     */

    private FormApiService fApiService;
    private final Executor backgroundExecutor = Executors.newSingleThreadExecutor(); //pas de pb de concurrence

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        if (savedInstanceState == null) loadFragment(new LoginFragment(), false);

        retrofitTest();
    }

    @Override
    public void onPause() {//retour à l'écran principal
        //selon ordre, voit ou non cette activité dans les apps actives
        super.onPause();
        AdminActivity.this.finish();//revient à l'activité précédente
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Objects.requireNonNull(AdminActivity.this.getSupportActionBar()).setSubtitle(null);
    }

    /*
     * Section private methods
     */

    private void retrofitTest() {
        FormApiService apiInterface = Api.getClient().create(FormApiService.class);

        Call<User> call = apiInterface.registration("pipi","lol");

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {

                Log.d("TAG",response.code()+"");
                Log.d("TAG",response.body().getUserName()+"");

                String displayResponse = "";


            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.d("TAG","fait iech");
                call.cancel();
            }
        });

    }
}
