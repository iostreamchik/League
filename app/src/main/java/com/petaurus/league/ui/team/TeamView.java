package com.petaurus.league.ui.team;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.petaurus.league.entity.Team;

@StateStrategyType(AddToEndSingleStrategy.class)
public interface TeamView extends MvpView {
  @StateStrategyType(OneExecutionStateStrategy.class)
  void initTeam();

  void setTeam(Team table);

  void showProgress(boolean isShown);

  @StateStrategyType(OneExecutionStateStrategy.class)
  void showInfo(String title, String message);
}