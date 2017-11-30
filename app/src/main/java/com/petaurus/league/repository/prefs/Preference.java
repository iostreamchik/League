package com.petaurus.league.repository.prefs;

public interface Preference {

  long getCurrentLeagueId();

  void writeCurrentLeagueId(long id);
}
