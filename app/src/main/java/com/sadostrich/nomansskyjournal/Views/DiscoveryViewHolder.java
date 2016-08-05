package com.sadostrich.nomansskyjournal.Views;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.sadostrich.nomansskyjournal.Data.NMSOriginsService;
import com.sadostrich.nomansskyjournal.Data.NMSOriginsServiceHelper;
import com.sadostrich.nomansskyjournal.Interfaces.IDiscoveryListener;
import com.sadostrich.nomansskyjournal.Models.Discovery;
import com.sadostrich.nomansskyjournal.R;
import com.squareup.picasso.Picasso;

/**
 * Created by jacewardell on 4/7/16.
 */
public class DiscoveryViewHolder extends RecyclerView.ViewHolder {

	private View itemView;
	private ImageView discoveryImage;
	private TextView discoveryCommentCount, discoveryName, discoveryUsername;
	private Discovery discovery;
	private ImageView discoveryType;

	private IDiscoveryListener discoveryListener;

	public DiscoveryViewHolder(View itemView) {
		super(itemView);

		getViewRefs(itemView);

		itemView.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Log.i("DiscoveryVH", "Discovery VH clicked for discovery: " + discovery
						.getName());

				if (discoveryListener != null) {
					discoveryListener.onDiscoverySelected(discovery);
				}
			}
		});
	}

	private void getViewRefs(View itemView) {
		this.itemView = itemView;
		discoveryImage = (ImageView) itemView.findViewById(R.id
																   .img_discovery_grid_image);
		discoveryType =
				(ImageView) itemView.findViewById(R.id.img_discovery_grid_type);
		discoveryCommentCount =
				(TextView) itemView.findViewById(R.id.tv_discovery_grid_comment_count);
		discoveryName =
				(TextView) itemView.findViewById(R.id.tv_discovery_grid_name);
		discoveryUsername =
				(TextView) itemView.findViewById(R.id.tv_discovery_grid_username);
		//		itemView.findViewById(R.id.tv_discovery_grid_time_ago);
	}

	public void setListener(IDiscoveryListener listener) {
		discoveryListener = listener;
	}

	public void setData(Discovery discovery) {
		this.discovery = discovery;

		Picasso.with(itemView.getContext())
				.load(discovery.getImage().get(0).getFileUrl().getCarouselThumb()).fit()
				.centerCrop()
				.into(discoveryImage);
		setDiscoveryType();
		discoveryCommentCount.setText("" + discovery.getCommentCount());
		discoveryName.setText(discovery.getName());
		setDiscoveryUsername();
	}

	private void setDiscoveryType() {
		int colorRes = -1;
		int imageRes = -1;

		switch (discovery.getType().toLowerCase()) {
			case NMSOriginsServiceHelper.SOLAR_SYSTEM:
				colorRes = R.color.system_purple;
				imageRes = R.drawable.ic_system;
				break;

			case NMSOriginsServiceHelper.STAR:
				colorRes = R.color.star_yellow;
				imageRes = R.drawable.ic_star;
				break;

			case NMSOriginsServiceHelper.PLANET:
				colorRes = R.color.planet_purple;
				imageRes = R.drawable.ic_planet;
				break;

			case NMSOriginsServiceHelper.FAUNA:
				colorRes = R.color.fauna_red;
				imageRes = R.drawable.ic_fauna;
				break;

			case NMSOriginsServiceHelper.FLORA:
				colorRes = R.color.flora_blue;
				imageRes = R.drawable.ic_flora;
				break;

			case NMSOriginsServiceHelper.STRUCTURE:
				colorRes = R.color.structure_green;
				imageRes = R.drawable.ic_structure;
				break;

			case NMSOriginsServiceHelper.ITEM:
				colorRes = R.color.item_red;
				imageRes = R.drawable.ic_item;
				break;

			case NMSOriginsServiceHelper.SHIP:
				colorRes = R.color.ship_gray;
				imageRes = R.drawable.ic_ship;
				break;
		}
		if (colorRes == -1) {
			colorRes = android.R.color.black;
		}
		discoveryType.setBackgroundColor(itemView.getResources().getColor(colorRes));

		if (imageRes == -1) {
			discoveryType.setVisibility(View.GONE);
			return;
		}
		discoveryType.setVisibility(View.VISIBLE);
		discoveryType.setImageDrawable(itemView.getResources().getDrawable(imageRes));
	}

	private void setDiscoveryUsername() {
		String byUsername = itemView.getResources().getString(R.string.by_username);

		if (discovery.getUser() != null) {
			byUsername =
					byUsername.replace(NMSOriginsService.USERNAME, discovery.getUser().getUsername
							());
		} else {
			byUsername = byUsername.replace(NMSOriginsService.USERNAME, "N/A");
		}
		discoveryUsername.setText(byUsername);
	}

}
