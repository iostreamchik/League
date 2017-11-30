package com.petaurus.league.rest;

import com.petaurus.league.entity.LeagueTable;
import com.petaurus.league.entity.PlayerWrapper;
import com.petaurus.league.entity.Season;
import com.petaurus.league.entity.Team;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

import java.util.List;

public interface RestMethods {

  @GET("soccerseasons")
  Call<List<Season>> getSoccerSeasons();

  @GET("competitions/{id}")
  Call<Season> getSoccerSeason(
  		@Path("id") long id
  );

  @GET("competitions/{id}/leagueTable")
  Call<LeagueTable> getLeagueTable(
          @Path("id") long id
  );

  @GET("teams/{id}")
  Call<Team> getTeam(
          @Path("id") long id
  );

  @GET("teams/{id}/players")
  Call<PlayerWrapper> getPlayers(
          @Path("id") long id
  );

}
