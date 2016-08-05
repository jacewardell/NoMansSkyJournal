package com.sadostrich.nomansskyjournal.Views.AddDiscoveryFragments;

import android.app.DatePickerDialog;
import android.content.Context;
import android.graphics.PorterDuff;
import android.support.annotation.ColorRes;
import android.support.annotation.StyleRes;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.sadostrich.nomansskyjournal.Activities.AddDiscoveryActivity;
import com.sadostrich.nomansskyjournal.R;
import com.sadostrich.nomansskyjournal.Utils.MiscUtil;

/**
 *
 */
public class AddDiscoveryMainView extends RelativeLayout {
    private int dialogStyleRes;
    private EditText name, videoEvidence, description, tags;
    private Button addTagButton;
    private TextView tagTotalTextView, showTagsTextView, stardate;
    private Button dateButton, nextButton;

    private int tagTotal = 0;

    private AddDiscoveryActivity activity;
    private IAddDiscoveryMainViewListener listener;
    private int pageAccentColor;

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
        name = (EditText) findViewById(R.id.et_add_discovery_name);
        videoEvidence = (EditText) findViewById(R.id.et_add_discovery_video);
        description = (EditText) findViewById(R.id.et_add_discovery_description);
        tags = (EditText) findViewById(R.id.et_add_discovery_tags);

        setEditTextUI(name);
        setEditTextUI(videoEvidence);
        setEditTextUI(description);
        setEditTextUI(tags);
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
        stardate = (TextView) findViewById(R.id.tv_stardate);
        stardate.setTextColor(pageAccentColor);
    }

    private void setTagTotalText(int tagTotal) {
        tagTotalTextView = (TextView) findViewById(R.id.tv_tag_total);
        tagTotalTextView.setTextColor(pageAccentColor);
        String text = tagTotal + " " + (tagTotal == 1 ? "Tag" : "Tags") + " Added";
        tagTotalTextView.setText(text);
    }

    private void setupShowTags() {
        showTagsTextView = (TextView) findViewById(R.id.tv_show_tags);
        showTagsTextView.setTextColor(pageAccentColor);
        showTagsTextView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: Launch a new view showing tags w/remove function
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
        addTagButton = (Button) findViewById(R.id.btn_add_tag);
        addTagButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                incrementTagTotal();
            }
        });
    }

    private void setupDateButton() {
        dateButton = (Button) findViewById(R.id.btn_calendar);
        dateButton.setBackgroundColor(pageAccentColor);
        dateButton.setOnClickListener(new OnClickListener() {
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

    public void setupNextButton() {
        nextButton = (Button) findViewById(R.id.btn_next_add_discovery);
        nextButton.setBackgroundColor(pageAccentColor);
        nextButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.nextClicked();
                }
            }
        });
    }

    public interface IAddDiscoveryMainViewListener {
        void nextClicked();
    }
}
