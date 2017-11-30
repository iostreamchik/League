package com.petaurus.league.application;

import android.content.Context;
import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;

@Module
public class AppModule {

  private final App application;

  public AppModule(App application) {
	this.application = application;
  }

  @Provides
  @Singleton
  public App application() {
	return application;
  }

  @Provides
  @Singleton
  public Context applicationContext() {
	return application.getApplicationContext();
  }

}
