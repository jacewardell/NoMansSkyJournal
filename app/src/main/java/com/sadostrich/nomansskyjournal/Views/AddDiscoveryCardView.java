package com.sadostrich.nomansskyjournal.Views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.sadostrich.nomansskyjournal.Interfaces.IDiscoveryListener;
import com.sadostrich.nomansskyjournal.R;

/**
 * Created by jacewardell on 5/16/16.
 */
public class AddDiscoveryCardView extends FrameLayout implements View.OnClickListener {
    private static final String TAG = "AddDiscoveryCardView";
    private ImageView addPlanetButton;
    private IDiscoveryListener listener;

    public AddDiscoveryCardView(Context context) {
        super(context);
        init();
    }

    public AddDiscoveryCardView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public AddDiscoveryCardView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public AddDiscoveryCardView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        inflate(getContext(), R.layout.view_add_discovery_card, this);
        addPlanetButton = (ImageView) findViewById(R.id.add_planet_button);
        setupListeners();
    }

    private void setupListeners() {
        addPlanetButton.setOnClickListener(this);
    }

    public void setListener(IDiscoveryListener listener) {
        this.listener = listener;
    }

    @Override
    public void onClick(View v) {
        if(listener != null) {
            switch (v.getId()) {
                case R.id.add_planet_button:
                    listener.onAddPlanet();
                    break;
            }
        }
    }
}
