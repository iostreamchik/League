package com.petaurus.league.application;


import com.petaurus.league.repository.RepositoryModule;
import com.petaurus.league.repository.db.DatabaseModule;
import com.petaurus.league.rest.RestModule;

public class Injector {

  private static AppComponent appComponent;

  private Injector() {
  }

  static void initializeAppComponent(App app) {
	appComponent = DaggerAppComponent.builder()
			.appModule(new AppModule(app))
			.restModule(new RestModule())
			.databaseModule(new DatabaseModule())
			.repositoryModule(new RepositoryModule())
			.build();
  }

  public static AppComponent getAppComponent() {
	return appComponent;
  }

}
