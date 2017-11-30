package com.petaurus.league.ui.season_detail;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.petaurus.league.entity.Season;

@StateStrategyType(AddToEndSingleStrategy.class)
public interface SeasonView extends MvpView {
  @StateStrategyType(OneExecutionStateStrategy.class)
  void initSeason();

  void setSeason(Season seasons);

  void showProgress(boolean isShown);

  @StateStrategyType(OneExecutionStateStrategy.class)
  void showInfo(String title, String message);
}