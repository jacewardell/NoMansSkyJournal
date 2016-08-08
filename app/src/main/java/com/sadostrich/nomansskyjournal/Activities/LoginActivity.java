package com.sadostrich.nomansskyjournal.Activities;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.sadostrich.nomansskyjournal.Data.NMSOriginsService;
import com.sadostrich.nomansskyjournal.Data.NMSOriginsServiceHelper;
import com.sadostrich.nomansskyjournal.Models.Authentication;
import com.sadostrich.nomansskyjournal.R;
import com.sadostrich.nomansskyjournal.Utils.InputHelper;
import com.sadostrich.nomansskyjournal.Utils.SharedPreferencesHelper;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity {

	private static final String TAG = "LoginActivity";

	private AutoCompleteTextView mEmailView;
	private EditText mPasswordView;
	private View mProgressView;
	private View mLoginFormView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);

		// Set up the login form.
		mEmailView = (AutoCompleteTextView) findViewById(R.id.email);

		mPasswordView = (EditText) findViewById(R.id.password);
		mPasswordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
			@Override
			public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
				if (id == R.id.login || id == EditorInfo.IME_NULL) {
					attemptLogin();
					return true;
				}
				return false;
			}
		});

		// TODO Remove test login creds!
		mEmailView.setText("xixdubyxix");
		mPasswordView.setText("password");
		Log.w(TAG, "@ onCreate(): Setting test login creds. Remove before release!");

		Button emailSignInButton = (Button) findViewById(R.id.email_sign_in_button);
		if (emailSignInButton != null) {
			emailSignInButton.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View view) {
					attemptLogin();
				}
			});
		}

		mLoginFormView = findViewById(R.id.login_form);
		mProgressView = findViewById(R.id.layout_login_progress);
	}

	/**
	 * Attempts to sign in or register the account specified by the login form. If there are form
	 * errors (invalid email, missing fields, etc.), the errors are presented and no actual login
	 * attempt is made.
	 */
	private void attemptLogin() {
		// Reset errors.
		mEmailView.setError(null);
		mPasswordView.setError(null);

		// Store values at the time of the login attempt.
		final String email = mEmailView.getText().toString();
		final String password = mPasswordView.getText().toString();

		// Hide soft keyboard
		InputHelper.hideSoftKeyboard(this);
		// Show loading progress
		showProgress(true);

		Retrofit retrofit = new Retrofit.Builder().baseUrl(NMSOriginsService.BASE_URL)
				.addConverterFactory(GsonConverterFactory.create()).build();
		NMSOriginsService nmsOriginsService = retrofit.create(NMSOriginsService.class);

		Call<Authentication> loginCall = nmsOriginsService
				.login(NMSOriginsServiceHelper.getLoginBodyHashMap(email, password));
		loginCall.enqueue(new Callback<Authentication>() {
			@Override
			public void onResponse(Call<Authentication> call,
					Response<Authentication> response) {
				if (response != null && response.body() != null
						&& response.code() == 200) {
					Authentication auth = response.body();
					Authentication.setInstance(auth);
					auth.setCookie(response.headers());

					SharedPreferencesHelper.saveLoginInfo(LoginActivity.this, email,
														  password);

					goToHomePage();
				} else {
					Log.w(TAG, "@ onResponse(): login failed!");

					//  Hide progress
					showProgress(false);

					// Show login error message
					View contentView = findViewById(android.R.id.content);
					if (contentView != null) {
						Snackbar.make(contentView, R.string.error_login,
									  Snackbar.LENGTH_LONG).show();
					}
				}
			}

			@Override
			public void onFailure(Call<Authentication> call, Throwable t) {
				Log.w(TAG, "@ onFailure(): login failed!");

				//  Hide progress
				showProgress(false);

				// Show login error message
				View contentView = findViewById(android.R.id.content);
				if (contentView != null) {
					Snackbar.make(contentView, R.string.error_login, Snackbar.LENGTH_LONG)
							.show();
				}
			}
		});
	}

	private void goToHomePage() {
		Intent intent = new Intent(this, MainActivity.class);
		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
		startActivity(intent);
	}

	/**
	 * Shows the progress UI and hides the login form.
	 */
	@TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
	private void showProgress(final boolean show) {
		// On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
		// for very easy animations. If available, use these APIs to fade-in
		// the progress spinner.
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
			int shortAnimTime = getResources()
					.getInteger(android.R.integer.config_shortAnimTime);

			mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
			mLoginFormView.animate().setDuration(shortAnimTime).alpha(show ? 0 : 1)
					.setListener(new AnimatorListenerAdapter() {
						@Override
						public void onAnimationEnd(Animator animation) {
							mLoginFormView.clearAnimation();
							mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
						}
					});

			mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
			mProgressView.animate().setDuration(shortAnimTime).alpha(show ? 1 : 0)
					.setListener(new AnimatorListenerAdapter() {
						@Override
						public void onAnimationEnd(Animator animation) {
							mProgressView.clearAnimation();
							mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
						}
					});
		} else {
			// The ViewPropertyAnimator APIs are not available, so simply show
			// and hide the relevant UI components.
			mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
			mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
		}
	}

	public void showRegistrationPage(View view) {
		Intent intent = new Intent(LoginActivity.this, RegistrationActivity.class);
		startActivity(intent);
	}

}
