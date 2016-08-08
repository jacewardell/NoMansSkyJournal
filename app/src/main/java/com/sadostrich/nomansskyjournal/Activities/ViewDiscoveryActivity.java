package com.sadostrich.nomansskyjournal.Activities;

import android.app.Dialog;
import android.app.DialogFragment;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.EditText;

import com.sadostrich.nomansskyjournal.Adapters.CommentAdapter;
import com.sadostrich.nomansskyjournal.Data.NMSOriginsService;
import com.sadostrich.nomansskyjournal.Data.NMSOriginsServiceHelper;
import com.sadostrich.nomansskyjournal.Interfaces.ICommentVhListener;
import com.sadostrich.nomansskyjournal.Interfaces.IDiscoveryDetailView;
import com.sadostrich.nomansskyjournal.Models.Authentication;
import com.sadostrich.nomansskyjournal.Models.Discovery;
import com.sadostrich.nomansskyjournal.Models.DiscoveryComment;
import com.sadostrich.nomansskyjournal.Models.User;
import com.sadostrich.nomansskyjournal.R;
import com.sadostrich.nomansskyjournal.Views.DiscoveryDetailView;
import com.sadostrich.nomansskyjournal.modals.FullPageImageModal;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Activity that controls fragments related to the viewing of a discovery in
 * full detail.<br>
 * Clicking the picture goes to full screen view of the pic.<br>
 * Maybe a button to view 'related discoveries' in a full page fashion?
 * <p/>
 * <p/>
 * Created by Jacobus LaFazia on 8/2/2016.
 */
public class ViewDiscoveryActivity extends AppCompatActivity implements IDiscoveryDetailView, ICommentVhListener {

    private static final String TAG = "ViewDiscoveryActivity";
    public static final String INTENT_EXTRA_DISCOVERY = "INTENT_EXTRA_DISCOVERY";

    private DiscoveryDetailView mDetailView;
    private CollapsingToolbarLayout mCollapsingToolbarLayout;
    private RecyclerView mRvComments;
    private FloatingActionButton mAddCommentFAB;

    /* Modal */
    private DialogFragment mCurrentlyShowingModal;

    /* Dialog */
    private Dialog mAddCommentDialog;

    private Discovery mDiscovery;
    private CommentAdapter mCommentAdapter;
    private NMSOriginsService nmsOriginsService;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_discovery);

        // Get discovery from Intent
        mDiscovery = (Discovery) getIntent().getSerializableExtra(INTENT_EXTRA_DISCOVERY);
        if (mDiscovery == null) {
            throw new IllegalArgumentException("You must pass a Discovery object to this " + "activity!");
        }

        // Init views
        initViews();
        initRetrofit();
        // TODO create test comments
        createTestComments();
        initCommentsAdapter();
    }

    private void initRetrofit() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(NMSOriginsService.BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        nmsOriginsService = retrofit.create(NMSOriginsService.class);
    }

    private void createTestComments() {
//        List<DiscoveryComment> comments = new ArrayList<>();
//
//        User user = new User("123", "Bob Zudusky", "2016-08-01T13:25.000-07:00", false, false);
//
//        DiscoveryComment dc = new DiscoveryComment();
//        dc.setUser(user);
//        dc.setTimeAgo("4 days ago");
//        dc.setReportsCount(0);
//        dc.setComment("Nice one slick!\nYou find all on your own or did your mommy help you?");
//        comments.add(dc);
//
//        dc = new DiscoveryComment();
//        dc.setUser(user);
//        dc.setTimeAgo("5 days ago");
//        dc.setReportsCount(0);
//        dc.setComment("I dare you to report me!!!\n\nCHUMP");
//        comments.add(dc);
//
//        mDiscovery.setComments(comments);

        nmsOriginsService.getDiscoveryComments(NMSOriginsServiceHelper.createGetCommentsRequestBody(mDiscovery)).enqueue(new Callback<List<DiscoveryComment>>() {
            @Override
            public void onResponse(Call<List<DiscoveryComment>> call, Response<List<DiscoveryComment>> response) {
                Log.d(TAG, "onResponse: ");
                mDiscovery.setComments(response.body());
                mCommentAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<DiscoveryComment>> call, Throwable t) {
                Log.d(TAG, "onFailure: ");
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // TODO create menu options

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // TODO handle action bar menu item clicked

        return super.onOptionsItemSelected(item);
    }

    private void initViews() {
        mDetailView = (DiscoveryDetailView) findViewById(R.id.detail_view);
        if (mDetailView != null) {
            mDetailView.setListener(this);
            mDetailView.setDiscovery(mDiscovery);

        } else {
            Log.e(TAG, "@ initViews(): Discovery Detail View from XML is NULL!");
        }

        mRvComments = (RecyclerView) findViewById(R.id.rv_comments);
        mRvComments.setItemAnimator(new DefaultItemAnimator());

        // RecyclerView layout manager.
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        mRvComments.setLayoutManager(manager);

        // The CollapsingToolbarLayout that will act as the comments header.
        mCollapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        mCollapsingToolbarLayout.setTitle(getResources().getString(R.string.comments).toUpperCase());

        mAddCommentFAB = (FloatingActionButton) findViewById(R.id.fab_add_comment);
        if (mAddCommentFAB != null) {
            // mAddCommentFAB.hide();
            mAddCommentFAB.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d(TAG, "@ Add Comment FAB clicked!");

                    // TODO show add comment dialog
                    showAddCommentDialog();
                }
            });
        }

        // appbar layout collapse listener
        AppBarLayout appBar = ((AppBarLayout) findViewById(R.id.appbar));
        if (appBar != null) {
            appBar.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
                @Override
                public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                    if (mCollapsingToolbarLayout.getHeight() + verticalOffset < 2 * ViewCompat.getMinimumHeight(mCollapsingToolbarLayout)) {
                        // show the FAB
                        mAddCommentFAB.show();

                    } else {
                        // hide the FAB
                        mAddCommentFAB.hide();
                    }
                }
            });
        }
    }

    private void initCommentsAdapter() {
        // TODO init RV comments adapter

        mCommentAdapter = new CommentAdapter(mDiscovery.getComments(), this);
        mRvComments.setAdapter(mCommentAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();

        // TODO fetch comments from server!
        Log.i(TAG, "@ onResume(): Must fetch discovery comments from the server!");

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        // Dismiss the currently showing modal (if any).
        if (mCurrentlyShowingModal != null) {
            mCurrentlyShowingModal.dismiss();
            mCurrentlyShowingModal = null;
        }
    }

    /**
     * Shows the full screen image modal for the given bundle containing the
     * bitmap image.
     *
     * @param bundle Bundle containing the bitmap image to show. The bundle & the
     *               Bitmap in the bundle must not be <strong>null</strong>.
     */
    public void showFullScreenImage(@NonNull Bundle bundle) {
        FullPageImageModal modal = new FullPageImageModal();

        // Add arguments bundle
        modal.setArguments(bundle);

        if (mCurrentlyShowingModal != null) {
            // Log.d(TAG, "@ showModal(): A Modal is already showing, replacing
            // it...");
            mCurrentlyShowingModal.dismiss();
        }
        mCurrentlyShowingModal = modal;
        modal.show(getFragmentManager(), "Current Modal");
    }

    private void showAddCommentDialog() {
        if (mAddCommentDialog == null) {
            mAddCommentDialog = new Dialog(ViewDiscoveryActivity.this);
            mAddCommentDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            mAddCommentDialog.setContentView(R.layout.dialog_add_comment);
            mAddCommentDialog.findViewById(R.id.btn_cancel).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mAddCommentDialog.dismiss();
                }
            });
            mAddCommentDialog.findViewById(R.id.btn_post).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String comment = ((EditText) mAddCommentDialog.findViewById(R.id.et_comment)).getText().toString();

                    Log.i(TAG, "Post new comment: " + comment);

                    // TODO post new comment
                }
            });
        }

        mAddCommentDialog.show();
    }

    //////////////////////////////////////////////////////////////////
    // IDiscoveryDetailView
    //////////////////////////////////////////////////////////////////

    @Override
    public void onUserClicked(User user) {
        Log.d(TAG, "@ onUserClicked(): User Name = " + user.getUsername());

        // TODO go to view user profile in main activity
    }

    @Override
    public void onImageClicked(Bitmap image) {
        Log.d(TAG, "@ onImageClicked(): ");
        // TODO display image in full page image view thingy

        // Product Image Clicked. Show full screen.
        if (image != null) {
            Log.i(TAG, "Bitmap Image Clicked!");

            Bundle bundle = new Bundle();
            bundle.putParcelable(FullPageImageModal.BUNDLE_KEY_IMAGE_BITMAP, image);
            showFullScreenImage(bundle);

        } else {
            Log.w(TAG, "Do not have product image Bitmap. " + "Unable to view full screen :(");
        }
    }

    @Override
    public void onUpVoteClicked(Discovery discovery) {
        Log.d(TAG, "@ onUpVoteClicked(): Discovery = " + discovery.getName());
        // TODO up vote the given discovery

        nmsOriginsService.likeDiscovery(Authentication.getInstance().getCookie(), discovery).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Log.d(TAG, "onResponse: ");
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.d(TAG, "onFailure: ");
            }
        });
    }

    @Override
    public void onReportInappropriate(Discovery discovery) {
        Log.d(TAG, "@ onReportInappropriate(): Discovery = " + discovery.getName());
        // TODO report discovery as inappropriate
    }

    //////////////////////////////////////////////////////////////////
    // ICommentVhListener
    //////////////////////////////////////////////////////////////////

    @Override
    public void onCommentUserClicked(DiscoveryComment comment) {
        Log.d(TAG, "@ onCommentUserClicked(): User = " + comment.getUser().getUsername());

        // TODO Navigate to view user profile
    }

    @Override
    public void onReportCommentClicked(DiscoveryComment comment) {
        Log.d(TAG, "@ onReportCommentClicked(): Comment = " + comment.getComment());

        // TODO Report comment as inappropriate
    }

}
