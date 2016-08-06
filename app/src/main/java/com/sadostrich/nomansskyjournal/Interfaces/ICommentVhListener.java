package com.sadostrich.nomansskyjournal.Interfaces;

import com.sadostrich.nomansskyjournal.Models.DiscoveryComment;
import com.sadostrich.nomansskyjournal.Views.CommentViewHolder;

/**
 * Listener interface for the {@linkplain CommentViewHolder}.
 * 
 * <p/>
 * Created by Jacobus LaFazia on 8/6/2016.
 */
public interface ICommentVhListener {
	void onCommentUserClicked(DiscoveryComment comment);

	void onReportCommentClicked(DiscoveryComment comment);
}
