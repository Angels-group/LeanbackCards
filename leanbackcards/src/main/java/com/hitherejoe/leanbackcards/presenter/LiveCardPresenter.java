package com.hitherejoe.leanbackcards.presenter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v17.leanback.widget.Presenter;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.ViewGroup;

import com.hitherejoe.leanbackcards.LiveCardView;
import com.hitherejoe.leanbackcards.R;
import com.hitherejoe.leanbackcards.model.Post;

public abstract class LiveCardPresenter extends Presenter {

    private static final int CARD_WIDTH = 300;
    private static final int CARD_HEIGHT = 300;
    private int sSelectedBackgroundColor;
    private int sDefaultBackgroundColor;
    private Drawable mDefaultCardImage;
    private Context mContext;

    public LiveCardPresenter(Context context) {
        mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent) {
        final Context context = parent.getContext();
        sDefaultBackgroundColor = ContextCompat.getColor(parent.getContext(),
            getDefaultBackgroundColor());
        sSelectedBackgroundColor = ContextCompat.getColor(parent.getContext(),
            getSelectedBackgroundColor());
        mDefaultCardImage = ContextCompat.getDrawable(context, getDefaultCardImage());

        final LiveCardView cardView = new LiveCardView(parent.getContext()) {
            @Override
            public void setSelected(boolean selected) {
                updateCardBackgroundColor(this, selected);
                super.setSelected(selected);
            }
        };

        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cardView.stopVideo();
            }
        });

        cardView.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    cardView.startVideo();
                } else {
                    onLostFocus(mContext, cardView);
                }
            }
        });

        cardView.setFocusable(true);
        cardView.setFocusableInTouchMode(true);
        updateCardBackgroundColor(cardView, false);
        return new ViewHolder(cardView);
    }

    private void updateCardBackgroundColor(LiveCardView view, boolean selected) {
        int color = selected ? sSelectedBackgroundColor : sDefaultBackgroundColor;
        // Both background colors should be set because the view's background is temporarily visible
        // during animations.
        view.setBackgroundColor(color);
        view.findViewById(R.id.info_field).setBackgroundColor(color);
    }

    @Override
    public void onBindViewHolder(Presenter.ViewHolder viewHolder, Object item) {
        if (item instanceof Post) {
            Post post = (Post) item;

            final LiveCardView cardView = (LiveCardView) viewHolder.view;
            cardView.setTitleText(post.description);
            cardView.setContentText(post.username);
            cardView.setMainContainerDimensions(getCardWidth(), getCardHeight());
            int size = (int) (getCardWidth() * 1.25);
            cardView.setVideoViewSize(size, size);
            cardView.setVideoUrl(post.videoUrl);
            cardView.getMainImageView().setImageDrawable(mContext.getDrawable(post.thumbnail));
        }
    }

    @Override
    public void onUnbindViewHolder(Presenter.ViewHolder viewHolder) { }

    public int getCardWidth() {
        return CARD_WIDTH;
    }

    public int getCardHeight() {
        return CARD_HEIGHT;
    }

    public abstract int getDefaultBackgroundColor();
    public abstract int getSelectedBackgroundColor();
    public abstract int getDefaultCardImage();
    public abstract void onLostFocus(Context mContext, LiveCardView liveCardView);
}