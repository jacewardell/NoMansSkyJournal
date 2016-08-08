package com.sadostrich.nomansskyjournal.Views;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.sadostrich.nomansskyjournal.Interfaces.ICommentVhListener;
import com.sadostrich.nomansskyjournal.Models.DiscoveryComment;
import com.sadostrich.nomansskyjournal.R;
import com.sadostrich.nomansskyjournal.Utils.Formatter;

/**
 * RecyclerView view holder for Discovery comments.
 * <p/>
 * <p/>
 * Created by Jacobus LaFazia on 8/6/2016.
 */
public class CommentViewHolder extends RecyclerView.ViewHolder {

    private static final String TAG = "CommentViewHolder";

    private LoadingImageView mImgUserIcon;
    private TextView mTvUserName, mTvTimeAgo, mTvComment;

    private DiscoveryComment mComment;
    private ICommentVhListener mListener;

    public CommentViewHolder(View itemView) {
        super(itemView);

        mImgUserIcon = (LoadingImageView) itemView.findViewById(R.id.img_user_icon);
        mTvUserName = (TextView) itemView.findViewById(R.id.tv_user_name);
        mTvTimeAgo = (TextView) itemView.findViewById(R.id.tv_time_ago);
        mTvComment = (TextView) itemView.findViewById(R.id.tv_comment);

        mImgUserIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.onCommentUserClicked(mComment);

                } else {
                    Log.w(TAG, "Comment User clicked but no listener set!");
                }
            }
        });

        itemView.findViewById(R.id.tv_btn_report).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.onReportCommentClicked(mComment);

                } else {
                    Log.w(TAG, "Report comment clicked but no listener set!");
                }
            }
        });
    }

    public void setListener(ICommentVhListener listener) {
        mListener = listener;
    }

    public void setData(Context context, DiscoveryComment comment) {
        mComment = comment;

        // TODO load comment user's icon!
        // mImgUserIcon.loadImageFromUrlRounded(comment.getUser().get,
        // R.drawable.ic_x,
        // R.drawable.ic_x);

        mTvUserName.setText(comment.getUser().getUsername());
        mTvTimeAgo.setText(Formatter.calculateTimeAgo(context, comment.getTimeAgo()));
        mTvComment.setText(comment.getComment());
    }

}
