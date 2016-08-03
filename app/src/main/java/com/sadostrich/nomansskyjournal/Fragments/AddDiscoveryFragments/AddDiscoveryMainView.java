package com.sadostrich.nomansskyjournal.Fragments.AddDiscoveryFragments;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.sadostrich.nomansskyjournal.R;

/**
 *
 */
public class AddDiscoveryMainView extends RelativeLayout {
    private Button nextButton;

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
        setupNextButton(view);
        this.listener = listener;
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
