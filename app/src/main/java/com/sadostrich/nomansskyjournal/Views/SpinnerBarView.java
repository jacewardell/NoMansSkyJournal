package com.sadostrich.nomansskyjournal.Views;

import android.content.Context;
import android.database.DataSetObserver;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import com.sadostrich.nomansskyjournal.Models.CustomSpinnerObject;
import com.sadostrich.nomansskyjournal.R;

import java.util.ArrayList;
import java.util.List;

/**
 * TODO JavaDoc
 * <p/>
 * Created by Jace Wardell on 7/31/16.
 */
public class SpinnerBarView extends LinearLayout implements AdapterView.OnItemSelectedListener {
    private Spinner spinnerFirst;
    private Spinner spinnerSecond;

    public SpinnerBarView(Context context) {
        super(context);
        init();
    }

    public SpinnerBarView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public SpinnerBarView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public SpinnerBarView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        inflate(getContext(), R.layout.view_spinner_bar, this);
        spinnerFirst = (Spinner) findViewById(R.id.spinner_bar_first);
        spinnerSecond = (Spinner) findViewById(R.id.spinner_bar_second);

        setListeners();
        setAdapters();
    }

    private void setListeners() {
        spinnerFirst.setOnItemSelectedListener(this);
        spinnerSecond.setOnItemSelectedListener(this);
    }

    private void setAdapters() {
        setFirstSpinnerAdapter();
        setSecondSpinnerAdapter();
    }

    private void setFirstSpinnerAdapter() {
        List<CustomSpinnerObject> options = new ArrayList<>();
        options.add(new CustomSpinnerObject(R.drawable.ic_system, R.color.system_purple, R.string.title_system));
        options.add(new CustomSpinnerObject(R.drawable.ic_star, R.color.star_yellow, R.string.title_star));
        options.add(new CustomSpinnerObject(R.drawable.ic_station, R.color.station_green, R.string.title_station));
        options.add(new CustomSpinnerObject(R.drawable.ic_planet, R.color.planet_purple, R.string.title_planet));
        options.add(new CustomSpinnerObject(R.drawable.ic_fauna, R.color.fauna_red, R.string.title_fauna));
        options.add(new CustomSpinnerObject(R.drawable.ic_flora, R.color.flora_blue, R.string.title_flora));
        options.add(new CustomSpinnerObject(R.drawable.ic_structure, R.color.structure_green, R.string.title_structure));
        options.add(new CustomSpinnerObject(R.drawable.ic_item, R.color.item_red, R.string.title_item));
        options.add(new CustomSpinnerObject(R.drawable.ic_ship, R.color.ship_gray, R.string.title_ship));

        SpinnerBarViewAdapter adapter = new SpinnerBarViewAdapter(options);
        spinnerFirst.setAdapter(adapter);
    }

    private void setSecondSpinnerAdapter() {
        List<CustomSpinnerObject> options = new ArrayList<>();
        options.add(new CustomSpinnerObject(-1, -1, R.string.today));
        options.add(new CustomSpinnerObject(-1, -1, R.string.week));
        options.add(new CustomSpinnerObject(-1, -1, R.string.month));
        options.add(new CustomSpinnerObject(-1, -1, R.string.all_time));

        SpinnerBarViewAdapter adapter = new SpinnerBarViewAdapter(options);
        spinnerSecond.setAdapter(adapter);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if(view != null) {
                switch (view.getId()) {
                case R.id.spinner_bar_first:
                    break;

                case R.id.spinner_bar_second:
                    break;
            }
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public class SpinnerBarViewAdapter implements SpinnerAdapter {
        // First Integer is the drawable res; second is the string res
        private final List<CustomSpinnerObject> options;

        public SpinnerBarViewAdapter(List<CustomSpinnerObject> options) {
            this.options = options;
        }

        @Override
        public View getDropDownView(int position, View convertView, ViewGroup parent) {
            return getCustomView(position, convertView, parent);
        }

        private View getCustomView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(getContext().LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.spinner_bar_item, parent, false);
            }

            View spinnerImageContainer = convertView.findViewById(R.id.container_img_spinner_bar_item);
            ImageView spinnerImage = (ImageView) convertView.findViewById(R.id.img_spinner_bar_item);
            TextView spinnerText = (TextView) convertView.findViewById(R.id.tv_spinner_bar_item);

            CustomSpinnerObject option = options.get(position);

            if (option.getDrawableRes() != -1) {
                spinnerImageContainer.setVisibility(View.VISIBLE);
                spinnerImage.setImageDrawable(getResources().getDrawable(option.getDrawableRes()));
                spinnerImageContainer.setBackgroundColor(getResources().getColor(option.getDrawableColorRes()));
            } else {
                spinnerImageContainer.setVisibility(View.GONE);
            }

            spinnerText.setText(option.getStringRes());

            return convertView;
        }

        @Override
        public void registerDataSetObserver(DataSetObserver observer) {

        }

        @Override
        public void unregisterDataSetObserver(DataSetObserver observer) {

        }

        @Override
        public int getCount() {
            return options.size();
        }

        @Override
        public Object getItem(int position) {
            return options.get(position);
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public boolean hasStableIds() {
            return false;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            return getCustomView(position, convertView, parent);
        }

        @Override
        public int getItemViewType(int position) {
            return 0;
        }

        @Override
        public int getViewTypeCount() {
            return 1;
        }

        @Override
        public boolean isEmpty() {
            return false;
        }
    }
}
