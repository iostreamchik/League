package com.petaurus.league.entity;

import com.google.gson.annotations.SerializedName;
import io.realm.RealmObject;
import io.realm.annotations.Ignore;

public class WinInfo extends RealmObject {

  @Ignore
  @SerializedName("_links")
  private Links links;
  private long teamId;
  private int position;
  private String teamName;
  private String crestURI;
  private int playedGames;
  private int points;
  private int goals;
  private int goalsAgainst;
  private int goalDifference;
  private int wins;
  private int draws;
  private int losses;
  private Wins home;
  private Wins away;

  public WinInfo() {
  }

  public WinInfo(String crestURI) {
	this.crestURI = crestURI;
  }

  void generateTeamId() {
    long id = links != null && links.getTeam() != null ? links.getTeam().getTeamId() : 1;
    setTeamId(id);
  }

  public int getPosition() {
	return position;
  }

  public void setPosition(int position) {
	this.position = position;
  }

  public String getTeamName() {
	return teamName;
  }

  public void setTeamName(String teamName) {
	this.teamName = teamName;
  }

  public String getCrestURI() {
	return crestURI;
  }

  public String getCrestURIFormatted() {
	if (crestURI == null)
	  return "";
	String res = crestURI.replaceAll("\\(", "&lpar;");
	res = res.replaceAll("\\)", "&rpar;");
	return res;
  }

  public void setCrestURI(String crestURI) {
	this.crestURI = crestURI;
  }

  public int getPlayedGames() {
	return playedGames;
  }

  public void setPlayedGames(int playedGames) {
	this.playedGames = playedGames;
  }

  public int getPoints() {
	return points;
  }

  public void setPoints(int points) {
	this.points = points;
  }

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

  public int getGoalDifference() {
	return goalDifference;
  }

  public void setGoalDifference(int goalDifference) {
	this.goalDifference = goalDifference;
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

  public Wins getHome() {
	return home;
  }

  public void setHome(Wins home) {
	this.home = home;
  }

  public Wins getAway() {
	return away;
  }

  public void setAway(Wins away) {
	this.away = away;
  }

  public long getTeamId() {
	return teamId;
  }

  public void setTeamId(long teamId) {
	this.teamId = teamId;
  }

  public static class Links {
	private Team team;

	public Team getTeam() {
	  return team;
	}

	public void setTeam(Team team) {
	  this.team = team;
	}

	public static class Team {
	  private String href;

	  public String getHref() {
		return href;
	  }

	  public void setHref(String href) {
		this.href = href;
	  }

	  public long getTeamId() {
		String res = href.substring(href.lastIndexOf("/") + 1);
		long id;
		try {
		  id = Long.valueOf(res);
		} catch (NumberFormatException e) {
		  id = 1;
		}
		return id;
	  }
	}
  }

  @Override
  public String toString() {
	return "{" +
			"position=" + position +
			", teamName='" + teamName + '\'' +
			", crestURI='" + crestURI + '\'' +
			", playedGames=" + playedGames +
			", points=" + points +
			", goals=" + goals +
			", goalsAgainst=" + goalsAgainst +
			", goalDifference=" + goalDifference +
			", wins=" + wins +
			", draws=" + draws +
			", losses=" + losses +
			", home=" + home +
			", away=" + away +
			'}';
  }
}
