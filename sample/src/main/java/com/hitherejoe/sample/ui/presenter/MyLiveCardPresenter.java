package com.hitherejoe.sample.ui.presenter;

import android.content.Context;

import com.hitherejoe.leanbackcards.LiveCardView;
import com.hitherejoe.leanbackcards.presenter.LiveCardPresenter;
import com.hitherejoe.sample.R;
import com.hitherejoe.sample.ui.activity.MainActivity;

public class MyLiveCardPresenter extends LiveCardPresenter {

  public MyLiveCardPresenter(Context context) {
    super(context);
  }

  @Override
  public int getDefaultBackgroundColor() {
    return R.color.primary;
  }

  @Override
  public int getSelectedBackgroundColor() {
    return R.color.primary_dark;
  }

  @Override
  public int getDefaultCardImage() {
    return R.drawable.ic_play;
  }

  @Override
  public void onLostFocus(Context mContext, LiveCardView liveCardView) {
      if (mContext instanceof MainActivity) {
          if (((MainActivity) mContext).isFragmentActive()) {
              liveCardView.stopVideo();
          }
      } else {
          liveCardView.stopVideo();
      }
  }
}
