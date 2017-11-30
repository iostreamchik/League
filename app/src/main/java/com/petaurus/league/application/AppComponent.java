package com.petaurus.league.application;

import com.petaurus.league.repository.RepositoryModule;
import com.petaurus.league.repository.db.DatabaseModule;
import com.petaurus.league.rest.RestModule;
import com.petaurus.league.ui.league_table.LeaguePresenter;
import com.petaurus.league.ui.players.PlayersPresenter;
import com.petaurus.league.ui.season_detail.SeasonPresenter;
import com.petaurus.league.ui.seasons.SeasonsPresenter;
import com.petaurus.league.ui.team.TeamPresenter;
import dagger.Component;

import javax.inject.Singleton;

@Singleton
@Component(modules = {AppModule.class, RestModule.class, DatabaseModule.class, RepositoryModule.class})
public interface AppComponent {

    void inject(App application);
    void inject(SeasonsPresenter presenter);
    void inject(SeasonPresenter presenter);
    void inject(LeaguePresenter presenter);
    void inject(TeamPresenter presenter);
    void inject(PlayersPresenter presenter);
}
