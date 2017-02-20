package com.sadostrich.nomansskyjournal.Data;

import android.util.Base64;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Random;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

/**
 * Created by jacewardell on 8/18/16.
 */
public class TwitterServiceHelper {
	/**
	 * These values are found here <i>https://apps.twitter.com/app/12720894</i>
	 * after proper login to the SadOstrichMedia Twitter account
	 */
	public static final String consumerKey = "8fIbvLuIqXaEfQZhzKQK9e5PC"; // API
																			// Key
	public static final String consumerSecret = "0pTP70bHSTsWlIXqcSoI48XkNDhsVbT8TqQTySQGBGIsgfR9Kr"; // API
																										// Secret
	public static final String requestTokenSigningKey = consumerSecret + "&";
	public static final String appOnlyAuthUrl = "oauth2/token";
	public static final String requestTokenUrl = "oauth/request_token";
	public static final String authorizeUrl = "oauth/authorize";
	public static final String accessTokenUrl = "oauth/access_token";

	public String generateOAuthNonce() {
		char[] characterSet = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789"
				.toCharArray();
		Random random = new SecureRandom();
		char[] result = new char[32];
		for (int i = 0; i < result.length; i++) {
			int randomCharIndex = random.nextInt(characterSet.length);
			result[i] = characterSet[randomCharIndex];
		}
		return new String(result);
	}

	public String calculateSignature() {
		Mac mac = null;
		String result = null;
		try {
			mac = Mac.getInstance("HmacSHA1");
			SecretKeySpec secret = new SecretKeySpec(requestTokenSigningKey.getBytes(),
					mac.getAlgorithm());
			mac.init(secret);
			byte[] digest = mac.doFinal(TwitterService.BASE_URL.getBytes());
			result = Base64.encodeToString(digest, Base64.DEFAULT);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		}

		return result;
	}
}
