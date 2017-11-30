package com.petaurus.league.entity;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class LeagueTable extends RealmObject {

  @PrimaryKey
  private String leagueCaption;
  private int matchday;
  private RealmList<WinInfo> standing;

  public void upgradeWinInfo() {
	if (standing == null)
	  return;
	for (WinInfo winInfo : standing) {
	  winInfo.generateTeamId();
	}
  }

  public String getLeagueCaption() {
    return leagueCaption;
  }

  public void setLeagueCaption(String leagueCaption) {
    this.leagueCaption = leagueCaption;
  }

  public int getMatchday() {
    return matchday;
  }

  public void setMatchday(int matchday) {
    this.matchday = matchday;
  }

  public RealmList<WinInfo> getStanding() {
    return standing;
  }

  public void setStanding(RealmList<WinInfo> standing) {
    this.standing = standing;
  }

  @Override
  public String toString() {
	return "{" +
			"leagueCaption='" + leagueCaption + '\'' +
			", matchday=" + matchday +
			", standing=" + standing +
			'}';
  }
}
