package com.petaurus.league.repository.db;

import android.content.Context;
import android.support.annotation.NonNull;
import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;

@Module
public class DatabaseModule {
  @Provides
  @NonNull
  @Singleton
  public DatabaseRealm provideRealm(Context context) {
	return new DatabaseRealm(context);
  }
}
