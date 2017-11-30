package com.petaurus.league.ui.seasons;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.petaurus.league.entity.Season;

import java.util.List;

@StateStrategyType(AddToEndSingleStrategy.class)
public interface SeasonsView extends MvpView {
  @StateStrategyType(OneExecutionStateStrategy.class)
  void openSeasonScreen(long id);

  void setId(long id);

  void setSeasons(List<Season> seasons);

  void showProgress(boolean isShown);

  @StateStrategyType(OneExecutionStateStrategy.class)
  void showInfo(String title, String message);
}