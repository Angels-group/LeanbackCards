package com.hitherejoe.sample.ui.presenter;

import com.hitherejoe.leanbackcards.presenter.TagItemPresenter;
import com.hitherejoe.sample.R;

public class MyTagItemPresenter extends TagItemPresenter {
  @Override
  public int getDefaultBackgroundColor() {
    return R.color.primary;
  }

  @Override
  public int getSelectedBackgroundColor() {
    return R.color.primary_dark;
  }
}
