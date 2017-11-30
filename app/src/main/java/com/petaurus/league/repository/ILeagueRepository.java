package com.petaurus.league.repository;

import com.petaurus.league.entity.*;
import rx.Observable;

import java.util.List;

public interface ILeagueRepository {

    Observable<List<Season>> getAllSeasons();

    Observable<Season> getSeasonById(final long id);

    Observable<LeagueTable> getLeagueTable(final long id, String caption);

    Observable<Team> getTeam(final long id);

    Observable<List<Player>> getPlayers(final long id);
}
