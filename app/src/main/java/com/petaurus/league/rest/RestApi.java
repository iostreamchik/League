package com.petaurus.league.rest;

import com.petaurus.league.entity.LeagueTable;
import com.petaurus.league.entity.Player;
import com.petaurus.league.entity.Season;
import com.petaurus.league.entity.Team;
import rx.Observable;

import java.util.List;

public interface RestApi {

  String PROTOCOL_VERSION = "v1";
  String BASE_URL = String.format("https://api.football-data.org/%s/", PROTOCOL_VERSION);

  String API_KEY = "a69031e6d14e4967bf4a9959d5742bd9";


  Season getSeason(long id) throws Exception;

  Observable<Season> getSeasonRx(long id) throws Exception;

  List<Season> getSeasons() throws Exception;

  Observable<List<Season>> getSeasonsRx();

  LeagueTable getLeagueTable(long id) throws Exception;

  Observable<LeagueTable> getLeagueTableRx(long id);

  Team getTeam(long id) throws Exception;

  Observable<Team> getTeamRx(long id);

  List<Player> getPlayers(long id) throws Exception;

  Observable<List<Player>> getPlayersRx(long id);
}
