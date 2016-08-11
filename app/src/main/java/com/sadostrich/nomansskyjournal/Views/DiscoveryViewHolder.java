package com.sadostrich.nomansskyjournal.Views;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.sadostrich.nomansskyjournal.Data.NMSOriginsService;
import com.sadostrich.nomansskyjournal.Data.NMSOriginsServiceHelper;
import com.sadostrich.nomansskyjournal.Interfaces.IDiscoveryListener;
import com.sadostrich.nomansskyjournal.Models.Discovery;
import com.sadostrich.nomansskyjournal.R;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import javax.crypto.interfaces.PBEKey;

/**
 * Created by jacewardell on 4/7/16.
 */
public class DiscoveryViewHolder extends RecyclerView.ViewHolder {

	private View itemView;
	private ImageView tvDiscoveryImage;
	private TextView tvDiscoveryCommentCount, tvDiscoveryName, tvDiscoveryUsername;
	private Discovery discovery;
	private ImageView imgDiscoveryType;
	private ProgressBar pbDiscoveryImage;

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
		tvDiscoveryImage = (ImageView) itemView.findViewById(R.id
																   .img_discovery_grid_image);
		imgDiscoveryType =
				(ImageView) itemView.findViewById(R.id.img_discovery_grid_type);
		tvDiscoveryCommentCount =
				(TextView) itemView.findViewById(R.id.tv_discovery_grid_comment_count);
		tvDiscoveryName =
				(TextView) itemView.findViewById(R.id.tv_discovery_grid_name);
		tvDiscoveryUsername =
				(TextView) itemView.findViewById(R.id.tv_discovery_grid_username);
		//		itemView.findViewById(R.id.tv_discovery_grid_time_ago);
		pbDiscoveryImage = (ProgressBar) itemView.findViewById(R.id.pb_discovery_grid_image);
	}

	public void setListener(IDiscoveryListener listener) {
		discoveryListener = listener;
	}

	public void setData(Discovery discovery) {
		this.discovery = discovery;

		pbDiscoveryImage.setVisibility(View.VISIBLE);

		Picasso.with(itemView.getContext())
				.load(discovery.getImage().get(0).getFileUrl().getCarouselThumb()).fit()
				.centerCrop().error(android.R.drawable.stat_notify_error)
				.into(tvDiscoveryImage, new Callback() {
					@Override
					public void onSuccess() {
						pbDiscoveryImage.setVisibility(View.GONE);
					}

					@Override
					public void onError() {
						pbDiscoveryImage.setVisibility(View.GONE);
					}
				});
		setDiscoveryType();
		tvDiscoveryCommentCount.setText("" + discovery.getCommentCount());
		tvDiscoveryName.setText(discovery.getName());
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
		imgDiscoveryType.setBackgroundColor(itemView.getResources().getColor(colorRes));

		if (imageRes == -1) {
			imgDiscoveryType.setVisibility(View.GONE);
			return;
		}
		imgDiscoveryType.setVisibility(View.VISIBLE);
		imgDiscoveryType.setImageDrawable(itemView.getResources().getDrawable(imageRes));
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
		tvDiscoveryUsername.setText(byUsername);
	}

}
