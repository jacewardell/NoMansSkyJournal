package com.sadostrich.nomansskyjournal.Views;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.sadostrich.nomansskyjournal.Data.NMSOriginsService;
import com.sadostrich.nomansskyjournal.Data.NMSOriginsServiceHelper;
import com.sadostrich.nomansskyjournal.Models.Discovery;
import com.sadostrich.nomansskyjournal.R;
import com.squareup.picasso.Picasso;

/**
 * Created by jacewardell on 4/7/16.
 */
public class DiscoveryViewHolder extends RecyclerView.ViewHolder {

	private View itemView;
	private ImageView discoveryImage;
	private TextView discoveryType, discoveryCommentCount, discoveryName, discoveryUsername;
	private Discovery discovery;

	public DiscoveryViewHolder(View itemView) {
		super(itemView);

		getViewRefs(itemView);

		//		itemView.setOnClickListener(this);
	}

	private void getViewRefs(View itemView) {
		this.itemView = itemView;
		discoveryImage = (ImageView) itemView.findViewById(R.id
																   .img_discovery_grid_image);
		discoveryType =
				(TextView) itemView.findViewById(R.id.tv_discovery_grid_type);
		discoveryCommentCount =
				(TextView) itemView.findViewById(R.id.tv_discovery_grid_comment_count);
		discoveryName =
				(TextView) itemView.findViewById(R.id.tv_discovery_grid_name);
		discoveryUsername =
				(TextView) itemView.findViewById(R.id.tv_discovery_grid_username);
		itemView.findViewById(R.id.tv_discovery_grid_time_ago);
	}

	public void setData(Discovery discovery) {
		this.discovery = discovery;

		Picasso.with(itemView.getContext())
				.load(discovery.getImage().getFileUrl().getCarouselThumb())
				.into(discoveryImage);
		setDiscoveryType();
		discoveryCommentCount.setText("" + discovery.getCommentCount());
		discoveryName.setText(discovery.getName());
		setDiscoveryUsername();
	}

	private void setDiscoveryType() {
		int colorRes = -1;
		int stringRes = -1;

		switch (discovery.getType().toLowerCase()) {
			case NMSOriginsServiceHelper.SOLAR_SYSTEM:
				colorRes = R.color.system_purple;
				stringRes = R.string.system;
				break;

			case NMSOriginsServiceHelper.STAR:
				colorRes = R.color.star_yellow;
				stringRes = R.string.star;
				break;

			case NMSOriginsServiceHelper.PLANET:
				colorRes = R.color.planet_purple;
				stringRes = R.string.planet;
				break;

			case NMSOriginsServiceHelper.FAUNA:
				colorRes = R.color.fauna_red;
				stringRes = R.string.fauna;
				break;

			case NMSOriginsServiceHelper.FLORA:
				colorRes = R.color.flora_blue;
				stringRes = R.string.flora;
				break;

			case NMSOriginsServiceHelper.STRUCTURE:
				colorRes = R.color.structure_green;
				stringRes = R.string.structure;
				break;

			case NMSOriginsServiceHelper.ITEM:
				colorRes = R.color.item_red;
				stringRes = R.string.item;
				break;

			case NMSOriginsServiceHelper.SHIP:
				colorRes = R.color.ship_gray;
				stringRes = R.string.ship;
				break;
		}
		if (colorRes == -1) {
			colorRes = android.R.color.black;
		}
		discoveryType.setBackgroundColor(itemView.getResources().getColor(colorRes));

		if (stringRes == -1) {
			discoveryType.setVisibility(View.GONE);
			return;
		}
		discoveryType.setVisibility(View.VISIBLE);
		discoveryType.setText(stringRes);
	}

	private void setDiscoveryUsername() {
		String byUsername = itemView.getResources().getString(R.string.by_username);

		if (discovery.getUser() != null) {
			byUsername = byUsername.replace(NMSOriginsService.USERNAME, discovery.getUser().getUsername
					());
		} else {
			byUsername = byUsername.replace(NMSOriginsService.USERNAME, "N/A");
		}
		discoveryUsername.setText(byUsername);
	}

	//	@Override
	//	public void onClick(View v) {
	//		discoveryListener.onDiscoverySelected(discovery);
	//	}
}
