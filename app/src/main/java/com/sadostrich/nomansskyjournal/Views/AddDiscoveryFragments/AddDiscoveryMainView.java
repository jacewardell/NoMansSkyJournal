package com.sadostrich.nomansskyjournal.Views.AddDiscoveryFragments;

import android.app.DatePickerDialog;
import android.content.Context;
import android.support.annotation.ColorRes;
import android.support.annotation.StyleRes;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
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
    private Button addTagButton;
    private TextView tagTotalTextView, showTagsTextView;
    private Button dateButton, nextButton;

    private int tagTotal = 0;

    private AddDiscoveryActivity activity;
    private IAddDiscoveryMainViewListener listener;

    public AddDiscoveryMainView(Context context, AddDiscoveryActivity listener, @StyleRes int dialogStyleRes, @ColorRes int backgroundColorRes, @ColorRes int backgroundColorResDark) {
        super(context);
        init(listener, dialogStyleRes, backgroundColorRes, backgroundColorResDark);
    }

    public AddDiscoveryMainView(Context context, AttributeSet attrs, AddDiscoveryActivity listener) {
        super(context, attrs);
        init(listener, 0, 0, 0);
    }

    public AddDiscoveryMainView(Context context, AttributeSet attrs, int defStyleAttr, AddDiscoveryActivity listener) {
        super(context, attrs, defStyleAttr);
        init(listener, 0, 0, 0);
    }

    public AddDiscoveryMainView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes, AddDiscoveryActivity listener) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(listener, 0, 0, 0);
    }

    private void init(AddDiscoveryActivity listener, int dialogStyleRes, int backgroundColorRes, int backgroundColorResDark) {
        this.activity = listener;
        this.listener = listener;
        this.dialogStyleRes = dialogStyleRes;

        inflate(getContext(), R.layout.view_add_main_discovery, this);
        findViewById(R.id.main_view).setBackgroundColor(getResources().getColor(backgroundColorRes));
        setupTextViews();
        setupButtons(backgroundColorResDark);
    }

    private void setupTextViews() {
        setTagTotalText(tagTotal);
        setupShowTags();
    }

    private void setTagTotalText(int tagTotal) {
        tagTotalTextView = (TextView) findViewById(R.id.tv_tag_total);
        String text = tagTotal + " " + (tagTotal == 1 ? "Tag" : "Tags") + " Added";
        tagTotalTextView.setText(text);
    }

    private void setupShowTags() {
        showTagsTextView = (TextView) findViewById(R.id.tv_show_tags);
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

    private void setupButtons(int backgroundColorResDark) {
        setupAddTagButton();
        setupDateButton(backgroundColorResDark);
        setupNextButton(backgroundColorResDark);
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

    private void setupDateButton(int backgroundColorResDark) {
        dateButton = (Button) findViewById(R.id.btn_calendar);
        dateButton.setBackgroundColor(getResources().getColor(backgroundColorResDark));
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

    public void setupNextButton(int backgroundColorResDark) {
        nextButton = (Button) findViewById(R.id.btn_next_add_discovery);
        nextButton.setBackgroundColor(getResources().getColor(backgroundColorResDark));
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
