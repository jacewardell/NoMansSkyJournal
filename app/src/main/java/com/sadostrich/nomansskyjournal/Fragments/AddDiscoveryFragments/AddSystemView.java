package com.sadostrich.nomansskyjournal.Fragments.AddDiscoveryFragments;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.sadostrich.nomansskyjournal.R;

/**
 *
 */
public class AddSystemView extends RelativeLayout {
    private Button previousButton;
    private IAddSystemViewListener listener;

    public AddSystemView(Context context, IAddSystemViewListener listener) {
        super(context);
        init(listener);
    }

    public AddSystemView(Context context, AttributeSet attrs, IAddSystemViewListener listener) {
        super(context, attrs);
        init(listener);
    }

    public AddSystemView(Context context, AttributeSet attrs, int defStyleAttr, IAddSystemViewListener listener) {
        super(context, attrs, defStyleAttr);
        init(listener);
    }

    public AddSystemView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes, IAddSystemViewListener listener) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(listener);
    }

    private void init(IAddSystemViewListener listener) {
        inflate(getContext(), R.layout.view_add_system_discovery, this);
        setupPreviousButton();
        this.listener = listener;
    }

    private void setupPreviousButton() {
        previousButton = (Button) findViewById(R.id.btn_previous_add_discovery);
        previousButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.previousSelected();
                }
            }
        });
    }

    public interface IAddSystemViewListener {
        void previousSelected();
    }
}
