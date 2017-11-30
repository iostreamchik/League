package com.petaurus.league.rest;

import android.content.Context;
import android.support.annotation.NonNull;
import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;

@Module
public class RestModule {

	@Provides
	@NonNull
	@Singleton
	public Rest provideRest(Context context) {
		return new Rest(context);
	}
}
