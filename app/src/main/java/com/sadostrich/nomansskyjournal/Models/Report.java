package com.sadostrich.nomansskyjournal.Models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by jacewardell on 8/8/16.
 */
public class Report {
    @SerializedName("_item")
    private String id;
    @SerializedName("_submittedBy")
    private String submiteeId;
    private String complaintText;
    private String complaintType;
}
