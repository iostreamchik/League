package com.petaurus.league.entity;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Season extends RealmObject {
  @PrimaryKey
  private long id;
  private String caption;
  private String league;
  private String year;
  private int currentMatchday;
  private int numberOfMatchdays;
  private int numberOfTeams;
  private int numberOfGames;
  private String lastUpdated;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getCaption() {
    return caption;
  }

  public void setCaption(String caption) {
    this.caption = caption;
  }

  public String getLeague() {
    return league;
  }

  public void setLeague(String league) {
    this.league = league;
  }

  public String getYear() {
    return year;
  }

  public void setYear(String year) {
    this.year = year;
  }

  public int getCurrentMatchday() {
    return currentMatchday;
  }

  public void setCurrentMatchday(int currentMatchday) {
    this.currentMatchday = currentMatchday;
  }

  public int getNumberOfMatchdays() {
    return numberOfMatchdays;
  }

  public void setNumberOfMatchdays(int numberOfMatchdays) {
    this.numberOfMatchdays = numberOfMatchdays;
  }

  public int getNumberOfTeams() {
    return numberOfTeams;
  }

  public void setNumberOfTeams(int numberOfTeams) {
    this.numberOfTeams = numberOfTeams;
  }

  public int getNumberOfGames() {
    return numberOfGames;
  }

  public void setNumberOfGames(int numberOfGames) {
    this.numberOfGames = numberOfGames;
  }

  public String getLastUpdated() {
    return lastUpdated;
  }

  public void setLastUpdated(String lastUpdated) {
    this.lastUpdated = lastUpdated;
  }

  @Override
  public String toString() {
    return "{" +
            "id=" + id +
            ", caption='" + caption + '\'' +
            ", league='" + league + '\'' +
            ", year='" + year + '\'' +
            ", currentMatchday=" + currentMatchday +
            ", numberOfMatchdays=" + numberOfMatchdays +
            ", numberOfTeams=" + numberOfTeams +
            ", numberOfGames=" + numberOfGames +
            ", lastUpdated='" + lastUpdated + '\'' +
            '}';
  }
}
