package com.sadostrich.nomansskyjournal.Adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sadostrich.nomansskyjournal.Interfaces.ICommentVhListener;
import com.sadostrich.nomansskyjournal.Models.DiscoveryComment;
import com.sadostrich.nomansskyjournal.R;
import com.sadostrich.nomansskyjournal.Views.CommentViewHolder;

import java.util.List;

/**
 * TODO JavaDoc
 * <p/>
 * Created by Jacobus LaFazia on 8/5/2016.
 */
public class CommentAdapter extends RecyclerView.Adapter<CommentViewHolder> {

	private final List<DiscoveryComment> mItems;
	private ICommentVhListener mListener;

	public CommentAdapter(@NonNull List<DiscoveryComment> items,
			ICommentVhListener listener) {
		super();

		mItems = items;
		mListener = listener;
	}

	@Override
	public CommentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View view = LayoutInflater.from(parent.getContext())
				.inflate(R.layout.view_holder_comment, parent, false);
		CommentViewHolder cvh = new CommentViewHolder(view);
		cvh.setListener(mListener);
		return cvh;
	}

	@Override
	public void onBindViewHolder(CommentViewHolder holder, int position) {
		holder.setData(mItems.get(position));
	}

	@Override
	public int getItemCount() {
		return mItems.size();
	}

}
