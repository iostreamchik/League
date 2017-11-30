package com.petaurus.league.ui.players;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.petaurus.league.entity.Player;

import java.util.List;

@StateStrategyType(AddToEndSingleStrategy.class)
public interface PlayersView extends MvpView {
  @StateStrategyType(OneExecutionStateStrategy.class)
  void initExtras();

  void setPlayers(List<Player> items);

  void showProgress(boolean isShown);

  @StateStrategyType(OneExecutionStateStrategy.class)
  void showInfo(String title, String message);
}