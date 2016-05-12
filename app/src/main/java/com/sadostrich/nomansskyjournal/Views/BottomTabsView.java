package com.sadostrich.nomansskyjournal.Views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

import com.sadostrich.nomansskyjournal.R;

/**
 * Created by jacewardell on 5/9/16.
 */
public class BottomTabsView extends FrameLayout implements View.OnClickListener {
    private static final String TAG = "BottomTabsView";
    private View communityTab, friendsTab, profileTab;
    private ITabSelectedListener tabSelectedListener;

    public BottomTabsView(Context context) {
        super(context);
        init();
    }

    public BottomTabsView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public BottomTabsView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public BottomTabsView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        inflate(getContext(), R.layout.view_tabs_bottom, this);
        communityTab = findViewById(R.id.community_tab);
        friendsTab = findViewById(R.id.friends_tab);
        profileTab = findViewById(R.id.profile_tab);
        setupListeners();
    }

    private void setupListeners() {
        communityTab.setOnClickListener(this);
        friendsTab.setOnClickListener(this);
        profileTab.setOnClickListener(this);
    }

    public void setTabSelectedListener(ITabSelectedListener tabSelectedListener) {
        this.tabSelectedListener = tabSelectedListener;
    }

    @Override
    public void onClick(View v) {
        if (tabSelectedListener != null) {
            switch (v.getId()) {
                case R.id.community_tab:
                    tabSelectedListener.onTabSelected(0);
                    break;

                case R.id.friends_tab:
                    tabSelectedListener.onTabSelected(1);
                    break;

                case R.id.profile_tab:
                    tabSelectedListener.onTabSelected(2);
                    break;
            }
        }
    }

    public interface ITabSelectedListener {
        void onTabSelected(int index);
    }
}
