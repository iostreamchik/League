package com.petaurus.league.entity;

import io.realm.RealmObject;

public class Player extends RealmObject {

  private long id;
  private long commandId;
  private String name;
  private String position;
  private int jerseyNumber;
  private String dateOfBirth;
  private String nationality;
  private String contractUntil;
  private String marketValue;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public long getCommandId() {
    return commandId;
  }

  public void setCommandId(long commandId) {
    this.commandId = commandId;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getPosition() {
    return position;
  }

  public void setPosition(String position) {
    this.position = position;
  }

  public int getJerseyNumber() {
    return jerseyNumber;
  }

  public void setJerseyNumber(int jerseyNumber) {
    this.jerseyNumber = jerseyNumber;
  }

  public String getDateOfBirth() {
    return dateOfBirth;
  }

  public void setDateOfBirth(String dateOfBirth) {
    this.dateOfBirth = dateOfBirth;
  }

  public String getNationality() {
    return nationality;
  }

  public void setNationality(String nationality) {
    this.nationality = nationality;
  }

  public String getContractUntil() {
    return contractUntil;
  }

  public void setContractUntil(String contractUntil) {
    this.contractUntil = contractUntil;
  }

  public String getMarketValue() {
    return marketValue;
  }

  public void setMarketValue(String marketValue) {
    this.marketValue = marketValue;
  }
}
