package com.petaurus.league.entity;

import java.util.List;

public class PlayerWrapper {
  int count;
  List<Player> players;

  public int getCount() {
    return count;
  }

  public void setCount(int count) {
    this.count = count;
  }

  public void setCommandId(long id) {
    if (players == null)
      return;
    for (Player player : players) {
      player.setCommandId(id);
    }
  }

  public List<Player> getPlayers() {
    return players;
  }

  public void setPlayers(List<Player> players) {
    this.players = players;
  }
}
