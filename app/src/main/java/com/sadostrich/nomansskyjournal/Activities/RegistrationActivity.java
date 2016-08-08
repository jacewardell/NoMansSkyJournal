package com.sadostrich.nomansskyjournal.Activities;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

import com.sadostrich.nomansskyjournal.Data.NMSOriginsService;
import com.sadostrich.nomansskyjournal.Data.NMSOriginsServiceHelper;
import com.sadostrich.nomansskyjournal.Models.Authentication;
import com.sadostrich.nomansskyjournal.R;
import com.sadostrich.nomansskyjournal.Utils.Formatter;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RegistrationActivity extends AppCompatActivity {
    private static final String TAG = "RegistrationActivity";

    private EditText etUsername, etEmail, etPassword, etPasswordConfirm;
    private CheckBox cbTerms;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        getViewRefs();
    }

    private void getViewRefs() {
        etUsername = (EditText) findViewById(R.id.et_register_username);
        etEmail = (EditText) findViewById(R.id.et_register_email_address);
        etPassword = (EditText) findViewById(R.id.et_register_password1);
        etPasswordConfirm = (EditText) findViewById(R.id.et_register_password2);
        cbTerms = (CheckBox) findViewById(R.id.cb_register_terms);
    }

    public void registerTheUser(View view) {
        String username = etUsername.getText().toString();
        String email = etEmail.getText().toString();
        String password = etPassword.getText().toString();
        String passwordConfirm = etPasswordConfirm.getText().toString();

        if (!cbTerms.isChecked()) {
            View contentView = findViewById(android.R.id.content);
            if (contentView != null) {
                Snackbar.make(contentView, R.string.error_terms_and_conditions, Snackbar.LENGTH_LONG).show();
            }
            return;
        }

        if (isValidRegistration(username, email, password, passwordConfirm)) {
            Date acceptanceDate = Calendar.getInstance().getTime();
            DateFormat detailedDateFormat = Formatter.detailedDateFormat;
            detailedDateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
            String dateString = detailedDateFormat.format(acceptanceDate);

            Retrofit retrofit = new Retrofit.Builder().baseUrl(NMSOriginsService.BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
            NMSOriginsService nmsOriginsService = retrofit.create(NMSOriginsService.class);
            nmsOriginsService.register(NMSOriginsServiceHelper.getRegistrationRequestBody(username, email, password, passwordConfirm, dateString))
                    .enqueue(new Callback<Authentication>() {
                @Override
                public void onResponse(Call<Authentication> call, Response<Authentication> response) {
                    if (response != null && response.body() != null) {
                        View contentView = findViewById(android.R.id.content);
                        if (contentView != null) {
                            showSuccessfulRegistrationsDialog();
                        }
                    } else {

                    }
                }

                @Override
                public void onFailure(Call<Authentication> call, Throwable t) {
                    Log.d(TAG, "onFailure: ");
                }
            });
        } else {
            View contentView = findViewById(android.R.id.content);
            if (contentView != null) {
                Snackbar.make(contentView, R.string.error_registration, Snackbar.LENGTH_LONG).show();
            }
        }
        Log.d(TAG, "registerTheUser: ");
    }

    private void showSuccessfulRegistrationsDialog() {
        new AlertDialog.Builder(RegistrationActivity.this).setTitle(R.string.registration_successful).setMessage(R.string.registration_confirmation)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        }).show();
    }

    private boolean isValidRegistration(String username, String email, String password, String passwordConfirm) {
        return !username.isEmpty() && isValidPassword(password, passwordConfirm) && isValidEmail(email);
    }

    private boolean isValidEmail(String email) {
        return !email.isEmpty() && email.contains("@") && email.substring(email.indexOf("@")).length() > 0;
    }

    private boolean isValidPassword(String password, String passwordConfirm) {
        return password.equals(passwordConfirm) && password.length() >= 4;
    }
}
