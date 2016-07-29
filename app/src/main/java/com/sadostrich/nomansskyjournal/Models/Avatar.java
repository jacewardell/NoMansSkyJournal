package com.sadostrich.nomansskyjournal.Models;

import com.sadostrich.nomansskyjournal.Data.NMSOriginsService;

/**
 * TODO JavaDoc
 * <p/>
 * Created by Jace Wardell on 7/27/16.
 */
public class Avatar {
	private String small, big;

	public Avatar(String small, String big) {
		this.small = small.substring(1);
		this.big = big.substring(1);
	}

	public String getSmall() {
		return NMSOriginsService.BASE_URL + small;
	}

	public void setSmall(String small) {
		this.small = small;
	}

	public String getBig() {
		return big;
	}

	public void setBig(String big) {
		this.big = big;
	}
}
