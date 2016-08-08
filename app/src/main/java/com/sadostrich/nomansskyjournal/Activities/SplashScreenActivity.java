package com.sadostrich.nomansskyjournal.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.sadostrich.nomansskyjournal.Data.NMSOriginsService;
import com.sadostrich.nomansskyjournal.Data.NMSOriginsServiceHelper;
import com.sadostrich.nomansskyjournal.Models.Authentication;
import com.sadostrich.nomansskyjournal.R;
import com.sadostrich.nomansskyjournal.Utils.SharedPreferencesHelper;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SplashScreenActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        String[] loginInfo = SharedPreferencesHelper.getLoginInfo(this);
        String username = loginInfo[0];
        String password = loginInfo[1];

        if (username.isEmpty() || password.isEmpty()) {
            goToLoginPage();
        } else {
            Retrofit retrofit = new Retrofit.Builder().baseUrl(NMSOriginsService.BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
            NMSOriginsService nmsOriginsService = retrofit.create(NMSOriginsService.class);

            Call<Authentication> loginCall = nmsOriginsService.login(NMSOriginsServiceHelper.getLoginBodyHashMap(username, password));
            loginCall.enqueue(new Callback<Authentication>() {
                @Override
                public void onResponse(Call<Authentication> call, Response<Authentication> response) {
                    if (response != null && response.body() != null && response.code() == 200) {
                        Authentication auth = response.body();
                        Authentication.setInstance(auth);
                        auth.setCookie(response.headers());
                        goToHomePage();
                    } else {
                        // Show login error message
                        View contentView = findViewById(android.R.id.content);
                        if (contentView != null) {
                            Snackbar.make(contentView, R.string.error_login, Snackbar.LENGTH_LONG).show();
                            goToLoginPage();
                        }
                    }
                }

                @Override
                public void onFailure(Call<Authentication> call, Throwable t) {
                    // Show login error message
                    View contentView = findViewById(android.R.id.content);
                    if (contentView != null) {
                        Snackbar.make(contentView, R.string.error_login, Snackbar.LENGTH_LONG).show();
                        goToLoginPage();
                    }
                }
            });
        }
    }

    private void goToHomePage() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    private void goToLoginPage() {
        Intent intent = new Intent(SplashScreenActivity.this, LoginActivity.class);
        startActivity(intent);
    }
}
