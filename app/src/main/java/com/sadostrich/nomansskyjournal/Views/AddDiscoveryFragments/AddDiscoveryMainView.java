package com.sadostrich.nomansskyjournal.Views.AddDiscoveryFragments;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.sadostrich.nomansskyjournal.R;

/**
 *
 */
public class AddDiscoveryMainView extends RelativeLayout {
    private Button addTagButton;
    private TextView tagTotalTextView, showTagsTextView;
    private Button nextButton;

    private int tagTotal = 0;

    private IAddDiscoveryMainViewListener listener;

    public AddDiscoveryMainView(Context context, IAddDiscoveryMainViewListener listener) {
        super(context);
        init(listener);
    }

    public AddDiscoveryMainView(Context context, AttributeSet attrs, IAddDiscoveryMainViewListener listener) {
        super(context, attrs);
        init(listener);
    }

    public AddDiscoveryMainView(Context context, AttributeSet attrs, int defStyleAttr, IAddDiscoveryMainViewListener listener) {
        super(context, attrs, defStyleAttr);
        init(listener);
    }

    public AddDiscoveryMainView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes, IAddDiscoveryMainViewListener listener) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(listener);
    }

    private void init(IAddDiscoveryMainViewListener listener) {
        View view = inflate(getContext(), R.layout.view_add_main_discovery, this);
        addTagButton = (Button) findViewById(R.id.btn_add_tag);
        tagTotalTextView = (TextView) findViewById(R.id.tv_tag_total);
        showTagsTextView = (TextView) findViewById(R.id.tv_show_tags);
        setupTextViews();
        setupAddTagButton();
        setupNextButton(view);
        this.listener = listener;
    }

    private void setupAddTagButton() {
        addTagButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                incrementTagTotal();
            }
        });
    }

    private void setupTextViews() {
        setTagTotalText(tagTotal);
        setupShowTags();
    }

    private void setTagTotalText(int tagTotal) {
        String text = tagTotal + " " + (tagTotal == 1 ? "Tag" : "Tags") + " Added";
        tagTotalTextView.setText(text);
    }

    private void setupShowTags() {
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

    public void setupNextButton(View view) {
        nextButton = (Button) view.findViewById(R.id.btn_next_add_discovery);
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
