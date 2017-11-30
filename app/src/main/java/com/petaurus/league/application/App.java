package com.petaurus.league.application;

import android.app.Application;
import com.nostra13.universalimageloader.cache.memory.impl.LruMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.petaurus.league.repository.db.DatabaseRealm;
import com.petaurus.league.ui.svg.SvgDecoder;

import javax.inject.Inject;

public class App extends Application {

  @Inject
  DatabaseRealm databaseRealm;

  @Override
  public void onCreate() {
	super.onCreate();
	initDagger();
	initRealm();
	initImageLoader();
  }

  protected void initDagger() {
	Injector.initializeAppComponent(this);
	Injector.getAppComponent().inject(this);
  }

  protected void initRealm() {
	databaseRealm.setup();
  }

  protected void initImageLoader() {
	DisplayImageOptions options;
	options = new DisplayImageOptions.Builder()
			.cacheInMemory(true)
			.cacheOnDisk(true)
			.build();
	ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(this)
			.defaultDisplayImageOptions(options)
			.denyCacheImageMultipleSizesInMemory()
			.memoryCache(new LruMemoryCache(7 * 1024 * 1024))
			.imageDecoder(new SvgDecoder(true))
			.diskCacheFileCount(300)
			//.writeDebugLogs()
			.build();
	ImageLoader imageLoader = ImageLoader.getInstance();
	imageLoader.init(config);
  }
}
