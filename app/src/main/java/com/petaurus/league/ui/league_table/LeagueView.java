package com.petaurus.league.ui.league_table;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.petaurus.league.entity.LeagueTable;

@StateStrategyType(AddToEndSingleStrategy.class)
public interface LeagueView extends MvpView {
  @StateStrategyType(OneExecutionStateStrategy.class)
  void initTable();

  void setTable(LeagueTable table);

  void showProgress(boolean isShown);

  @StateStrategyType(OneExecutionStateStrategy.class)
  void showInfo(String title, String message);
}