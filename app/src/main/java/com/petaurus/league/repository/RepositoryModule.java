package com.petaurus.league.repository;

import com.petaurus.league.repository.db.DatabaseRealm;
import com.petaurus.league.rest.Rest;
import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;

@Module
public class RepositoryModule {

    @Provides
    @Singleton
    public ILeagueRepository provideLeagueRepository(Rest rest, DatabaseRealm databaseRealm) {
        return new LeagueRepository(rest, databaseRealm);
    }
}
