package com.hitherejoe.leanbackcards.presenter;

import android.support.v17.leanback.widget.Presenter;
import android.support.v4.content.ContextCompat;
import android.view.ViewGroup;

import com.hitherejoe.leanbackcards.TagCardView;

public abstract class TagItemPresenter extends Presenter {

    private static int sSelectedBackgroundColor;
    private static int sDefaultBackgroundColor;

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent) {
        sDefaultBackgroundColor = ContextCompat.getColor(parent.getContext(),
            getDefaultBackgroundColor());
        sSelectedBackgroundColor = ContextCompat.getColor(parent.getContext(),
            getSelectedBackgroundColor());

        TagCardView cardView = new TagCardView(parent.getContext()) {
            @Override
            public void setSelected(boolean selected) {
                updateCardBackgroundColor(this, selected);
                super.setSelected(selected);
            }
        };

        cardView.setFocusable(true);
        cardView.setFocusableInTouchMode(true);
        updateCardBackgroundColor(cardView, false);
        return new ViewHolder(cardView);
    }

    private static void updateCardBackgroundColor(TagCardView view, boolean selected) {
        view.setBackgroundColor(selected ? sSelectedBackgroundColor : sDefaultBackgroundColor);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, Object item) {
        if (item instanceof TagCardView) {
            TagCardView cardView = (TagCardView) viewHolder.view;
            cardView.setCardText(((TagCardView) item).getCardText());
        }
    }

    @Override
    public void onUnbindViewHolder(ViewHolder viewHolder) {

    }

    public abstract int getDefaultBackgroundColor();
    public abstract int getSelectedBackgroundColor();
}