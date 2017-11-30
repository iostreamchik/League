package com.petaurus.league.entity;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Team extends RealmObject {
  @PrimaryKey
  private long id;
  private String name;
  private String shortName;
  private String squadMarketValue;
  private String crestUrl;

  public long getId() {
	return id;
  }

  public void setId(long id) {
	this.id = id;
  }

  public String getName() {
	return name;
  }

  public void setName(String name) {
	this.name = name;
  }

  public String getShortName() {
	return shortName;
  }

  public void setShortName(String shortName) {
	this.shortName = shortName;
  }

  public String getSquadMarketValue() {
	return squadMarketValue;
  }

  public void setSquadMarketValue(String squadMarketValue) {
	this.squadMarketValue = squadMarketValue;
  }

  public String getCrestUrl() {
	return crestUrl;
  }

  public void setCrestUrl(String crestUrl) {
	this.crestUrl = crestUrl;
  }
}
