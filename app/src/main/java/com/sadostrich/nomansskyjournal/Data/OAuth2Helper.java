package com.sadostrich.nomansskyjournal.Data;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.accounts.AccountManagerCallback;
import android.accounts.AccountManagerFuture;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

/**
 * Created by jacewardell on 8/14/16.
 */
public class OAuth2Helper implements AccountManagerCallback<Bundle> {
    private static final String TAG = "OAuth2Helper";

    private static OAuth2Helper instance;
    private AccountManager accountManager;

    private OAuth2Helper(Context context) {
        accountManager = AccountManager.get(context);
    }

    public static OAuth2Helper getAccountManager(Context context) {
        if (instance == null) {
            instance = new OAuth2Helper(context);
        }

        return instance;
    }

    public String getAuthToken(String authTokenType, Bundle options, Activity loginActivity) {
        accountManager.getAuthToken(getGoogleAccount(), authTokenType, options, loginActivity, this, new Handler());
        return "";
    }

    private Account getGoogleAccount() {
        Account[] account = accountManager.getAccounts();
        Log.d(TAG, "getGoogleAccount: " + account.length);
        return account[0];
    }

    @Override
    public void run(AccountManagerFuture<Bundle> future) {

    }
}
