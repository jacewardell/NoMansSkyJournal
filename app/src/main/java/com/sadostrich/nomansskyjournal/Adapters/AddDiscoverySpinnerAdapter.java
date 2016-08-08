package com.sadostrich.nomansskyjournal.Adapters;

import android.content.Context;
import android.content.res.Resources;
import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import com.sadostrich.nomansskyjournal.Models.CustomSpinnerObject;
import com.sadostrich.nomansskyjournal.R;

import java.util.List;

/**
 * Created by jacewardell on 8/2/16.
 */
public class AddDiscoverySpinnerAdapter implements SpinnerAdapter {
    // First Integer is the drawable res; second is the string res
    private final List<CustomSpinnerObject> options;
    private final LayoutInflater inflater;
    private final Resources resources;

    public AddDiscoverySpinnerAdapter(Context context, List<CustomSpinnerObject> options) {
        this.options = options;
        inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        resources = context.getResources();
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }

    private View getCustomView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.spinner_bar_item, parent, false);
        }

        View spinnerImageContainer =
                convertView.findViewById(R.id.container_img_spinner_bar_item);
        spinnerImageContainer.setVisibility(View.GONE);
        TextView spinnerText = (TextView) convertView.findViewById(R.id.tv_spinner_bar_item);

        CustomSpinnerObject option = options.get(position);

        if (option.getBackgroundColorRes() != -1) {
            convertView.setBackgroundResource(option.getBackgroundColorRes());
        }

        if(option.getStringRes() == -1) {
            spinnerText.setText(option.getObject().toString());
        } else {
            spinnerText.setText(resources.getString(option.getStringRes()));
        }

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
