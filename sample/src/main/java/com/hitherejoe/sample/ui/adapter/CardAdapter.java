package com.hitherejoe.sample.ui.adapter;

import android.content.Context;
import android.support.v17.leanback.widget.ArrayObjectAdapter;
import android.support.v17.leanback.widget.Presenter;
import android.support.v17.leanback.widget.PresenterSelector;

import com.hitherejoe.leanbackcards.IconCardView;
import com.hitherejoe.leanbackcards.LoadingCardView;
import com.hitherejoe.leanbackcards.TagCardView;
import com.hitherejoe.leanbackcards.model.Post;
import com.hitherejoe.leanbackcards.presenter.IconItemPresenter;
import com.hitherejoe.leanbackcards.presenter.LiveCardPresenter;
import com.hitherejoe.leanbackcards.presenter.LoadingPresenter;
import com.hitherejoe.leanbackcards.presenter.TagItemPresenter;
import com.hitherejoe.sample.ui.presenter.MyLiveCardPresenter;
import com.hitherejoe.sample.ui.presenter.MyTagItemPresenter;


public class CardAdapter extends ArrayObjectAdapter {

    private LoadingPresenter mLoadingPresenter;
    private IconItemPresenter mIconItemPresenter;
    private TagItemPresenter mTagItemPresenter;
    private LiveCardPresenter mLiveCardPresenter;

    public CardAdapter(Context context) {
        mLoadingPresenter = new LoadingPresenter();
        mIconItemPresenter = new IconItemPresenter();
        mTagItemPresenter = new MyTagItemPresenter();
        mLiveCardPresenter = new MyLiveCardPresenter(context);
        setPresenterSelector(new PresenterSelector() {
            @Override
            public Presenter getPresenter(Object item) {
                if (item instanceof LoadingCardView) {
                    return mLoadingPresenter;
                } else if (item instanceof IconCardView) {
                    return mIconItemPresenter;
                } else if (item instanceof TagCardView) {
                    return mTagItemPresenter;
                } else if (item instanceof Post) {
                    return mLiveCardPresenter;
                }
                return null;
            }
        });
    }

}