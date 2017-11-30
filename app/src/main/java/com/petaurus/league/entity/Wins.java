package com.petaurus.league.entity;

import io.realm.RealmObject;

public class Wins extends RealmObject {
  private int goals;
  private int goalsAgainst;
  private int wins;
  private int draws;
  private int losses;

  public int getGoals() {
	return goals;
  }

  public void setGoals(int goals) {
	this.goals = goals;
  }

  public int getGoalsAgainst() {
	return goalsAgainst;
  }

  public void setGoalsAgainst(int goalsAgainst) {
	this.goalsAgainst = goalsAgainst;
  }

  public int getWins() {
	return wins;
  }

  public void setWins(int wins) {
	this.wins = wins;
  }

  public int getDraws() {
	return draws;
  }

  public void setDraws(int draws) {
	this.draws = draws;
  }

  public int getLosses() {
	return losses;
  }

  public void setLosses(int losses) {
	this.losses = losses;
  }

  @Override
  public String toString() {
	return "{" +
			"goals=" + goals +
			", goalsAgainst=" + goalsAgainst +
			", wins=" + wins +
			", draws=" + draws +
			", losses=" + losses +
			'}';
  }
}
