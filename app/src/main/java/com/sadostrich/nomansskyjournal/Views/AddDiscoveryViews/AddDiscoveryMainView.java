package com.sadostrich.nomansskyjournal.Views.AddDiscoveryViews;

import android.app.DatePickerDialog;
import android.content.Context;
import android.graphics.PorterDuff;
import android.support.annotation.ColorRes;
import android.support.annotation.StyleRes;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.sadostrich.nomansskyjournal.Activities.AddDiscoveryActivity;
import com.sadostrich.nomansskyjournal.Interfaces.IAddDiscoveryListener;
import com.sadostrich.nomansskyjournal.R;
import com.sadostrich.nomansskyjournal.Utils.Enums;
import com.sadostrich.nomansskyjournal.Utils.Formatter;
import com.sadostrich.nomansskyjournal.Utils.MiscUtil;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.TimeZone;

/**
 *
 */
public class AddDiscoveryMainView extends RelativeLayout {
    private static final String TAG = "AddDiscoveryMainView";

    private int dialogStyleRes;
    private EditText etName, etVideoEvidence, etDescription, etTags;
    private ImageButton btnAddTag;
    private TextView tvTagTotal, tvShowTags, tvStardate;
    private Button btnDate, btnNext;

    private int tagTotal = 0;
    private List<String> tags = new ArrayList<>();

    private AddDiscoveryActivity activity;
    private IAddDiscoveryMainViewListener listener;
    private int pageAccentColor;
    private OnClickListener nextButtonListener;

    public AddDiscoveryMainView(Context context, AddDiscoveryActivity listener, @StyleRes int dialogStyleRes, @ColorRes int backgroundColorRes) {
        super(context);
        init(listener, dialogStyleRes, backgroundColorRes);
    }

    public AddDiscoveryMainView(Context context, AttributeSet attrs, AddDiscoveryActivity listener) {
        super(context, attrs);
        init(listener, 0, 0);
    }

    public AddDiscoveryMainView(Context context, AttributeSet attrs, int defStyleAttr, AddDiscoveryActivity listener) {
        super(context, attrs, defStyleAttr);
        init(listener, 0, 0);
    }

    public AddDiscoveryMainView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes, AddDiscoveryActivity listener) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(listener, 0, 0);
    }

    private void init(AddDiscoveryActivity listener, int dialogStyleRes, int pageAccentColor) {
        this.activity = listener;
        this.listener = listener;
        this.pageAccentColor = getResources().getColor(pageAccentColor);
        this.dialogStyleRes = dialogStyleRes;

        inflate(getContext(), R.layout.view_add_main_discovery, this);
//        findViewById(R.id.main_view).setBackgroundColor(getResources().getColor(pageAccentColor));
        setupEditTextViews();
        setupTextViews();
        setupButtons();
    }

    private void setupEditTextViews() {
        etName = (EditText) findViewById(R.id.et_add_discovery_name);
        etVideoEvidence = (EditText) findViewById(R.id.et_add_discovery_video);
        etDescription = (EditText) findViewById(R.id.et_add_discovery_description);
        etTags = (EditText) findViewById(R.id.et_add_discovery_tags);

        setEditTextUI(etName);
        setEditTextUI(etVideoEvidence);
        setEditTextUI(etDescription);
        setEditTextUI(etTags);
    }

    private void setEditTextUI(EditText editText) {
        editText.setTextColor(pageAccentColor);
        editText.setHintTextColor(pageAccentColor);
        editText.getBackground().setColorFilter(pageAccentColor, PorterDuff.Mode.SRC_ATOP);
    }

    private void setupTextViews() {
        setTagTotalText(tagTotal);
        setupShowTags();
        setupStardateTextView();
    }

    private void setupStardateTextView() {
        tvStardate = (TextView) findViewById(R.id.tv_stardate);
        tvStardate.setTextColor(pageAccentColor);
    }

    private void setTagTotalText(int tagTotal) {
        tvTagTotal = (TextView) findViewById(R.id.tv_tag_total);
        tvTagTotal.setTextColor(pageAccentColor);
        String text = tagTotal + " " + (tagTotal == 1 ? "Tag" : "Tags") + " Added";
        tvTagTotal.setText(text);
    }

    private void setupShowTags() {
        tvShowTags = (TextView) findViewById(R.id.tv_show_tags);
        tvShowTags.setTextColor(pageAccentColor);
        tvShowTags.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: Launch a new view showing etTags w/remove function
                for (String tag : tags) {
                    Log.d(TAG, "onClick: name:" + tag);
                }
            }
        });
    }

    private void incrementTagTotal() {
        tagTotal++;
        setTagTotalText(tagTotal);
    }

    private void setupButtons() {
        setupAddTagButton();
        setupDateButton();
        setupNextButton();
    }

    private void setupAddTagButton() {
        btnAddTag = (ImageButton) findViewById(R.id.btn_add_tag);
        btnAddTag.setColorFilter(pageAccentColor, PorterDuff.Mode.SRC_ATOP);
        btnAddTag.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                incrementTagTotal();
                tags.add(etTags.getText().toString());
                etTags.setText("");
            }
        });
    }

    private void setupDateButton() {
        btnDate = (Button) findViewById(R.id.btn_calendar);
        btnDate.setBackgroundColor(pageAccentColor);
        btnDate.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog dialog = new DatePickerDialog(activity, dialogStyleRes, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                    }
                }, MiscUtil.getCurrentYear(), MiscUtil.getCurrentMonth(), MiscUtil.getCurrentDay());
                dialog.show();
            }
        });
    }

    private void setupNextButton() {
        btnNext = (Button) findViewById(R.id.btn_next_add_discovery);
        btnNext.setBackgroundColor(pageAccentColor);
        nextButtonListener = new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.nextClicked(etName.getText().toString(), etVideoEvidence.getText().toString(), etDescription.getText().toString(),
                            tags, getDiscoveredAtDate());
                }
            }
        };
        btnNext.setOnClickListener(nextButtonListener);
    }

    public void setNextButtonAsSubmit(final IAddDiscoveryListener shipSubmitListener) {
        btnNext.setText(R.string.submit);
        nextButtonListener = new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (shipSubmitListener != null) {
                    String discoveredAt, name, youtubeUrl, description;
                    discoveredAt = getDiscoveredAtDate();
                    name = checkAndGetName();
                    youtubeUrl = etVideoEvidence.getText().toString();
                    description = etDescription.getText().toString();

                    if (!name.isEmpty()) {
                        shipSubmitListener.submitShipDiscovery(Enums.DiscoveryType.SHIP.getNameString(getContext()), new HashMap<String, Object>(),
                                new ArrayList<String>(), tags, discoveredAt, name, youtubeUrl, description);
                    }
                }
            }
        };
        btnNext.setOnClickListener(nextButtonListener);
    }


    private String getDiscoveredAtDate() {
        try {
            String dateString = btnDate.getText().toString();
            DateFormat fromFormatter = Formatter.dateFormat;
            fromFormatter.setTimeZone(TimeZone.getDefault());
            Date date = fromFormatter.parse(dateString);

            DateFormat toFormatter = Formatter.detailedDateFormat;
            toFormatter.setTimeZone(TimeZone.getTimeZone("GMT"));
            return toFormatter.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "";
    }

    private String checkAndGetName() {
        String name = etName.getText().toString();
        if (name.isEmpty()) {
            listener.showErrorSnackbar();
        }
        return name;
    }

    public interface IAddDiscoveryMainViewListener {
        void nextClicked(String discoveryName, String youtubeUrl, String description, List<String> tags, String discoveredAt);

        void showErrorSnackbar();
    }
}
