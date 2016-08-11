package com.sadostrich.nomansskyjournal.Views;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.sadostrich.nomansskyjournal.R;


/**
 * TODO JavaDoc
 * <p/>
 * Created by Jace Wardell on 7/5/16.
 */
public class DiscoveryTypeChoiceView extends RelativeLayout implements View.OnClickListener {
	private TextView addSolarSystem, addPlanet, addAnimal, addPlant, addStructure, addTool,
			addShip;

	public DiscoveryTypeChoiceView(Context context) {
		super(context);
		init();
	}

	public DiscoveryTypeChoiceView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public DiscoveryTypeChoiceView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		init();
	}

	public DiscoveryTypeChoiceView(Context context, AttributeSet attrs, int defStyleAttr,
			int defStyleRes) {
		super(context, attrs, defStyleAttr, defStyleRes);
		init();
	}

	private void init() {
		inflate(getContext(), R.layout.view_add_discovery_card, this);
		getViewRefs();
		setupTVDrawables();
		setupListeners();
	}

	private void getViewRefs() {
		addSolarSystem = (TextView) findViewById(R.id.tv_add_discovery_solar_system);
		addPlanet = (TextView) findViewById(R.id.tv_add_discovery_planet);
		addAnimal = (TextView) findViewById(R.id.tv_add_discovery_animal);
		addPlant = (TextView) findViewById(R.id.tv_add_discovery_plant);
		addStructure = (TextView) findViewById(R.id.tv_add_discovery_structure);
		addTool = (TextView) findViewById(R.id.tv_add_discovery_tool);
		addShip = (TextView) findViewById(R.id.tv_add_discovery_ship);
	}

	private void setupTVDrawables() {
		setTvDrawable(addSolarSystem, R.drawable.plus);
		setTvDrawable(addPlanet, R.drawable.plus);
		setTvDrawable(addAnimal, R.drawable.plus);
		setTvDrawable(addPlant, R.drawable.plus);
		setTvDrawable(addTool, R.drawable.plus);
		setTvDrawable(addStructure, R.drawable.plus);
		setTvDrawable(addShip, R.drawable.plus);
	}

	private void setTvDrawable(TextView textView, @DrawableRes int drawableRes) {
		Drawable drawable = getResources().getDrawable(drawableRes);
		textView.setCompoundDrawables(drawable, null, null, null);
	}

	private void setupListeners() {
		addSolarSystem.setOnClickListener(this);
		addPlanet.setOnClickListener(this);
		addAnimal.setOnClickListener(this);
		addPlant.setOnClickListener(this);
		addStructure.setOnClickListener(this);
		addTool.setOnClickListener(this);
		addShip.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {

	}
}
