package com.petaurus.league.repository;

import android.util.Log;
import com.petaurus.league.entity.*;
import com.petaurus.league.repository.db.DatabaseRealm;
import com.petaurus.league.rest.Rest;
import rx.Observable;
import rx.Subscriber;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;

@Singleton
public class LeagueRepository implements ILeagueRepository {

  private static final String TAG = LeagueRepository.class.getSimpleName();

  private Rest rest;
  private DatabaseRealm databaseRealm;

  @Inject
  public LeagueRepository(Rest rest, DatabaseRealm databaseRealm) {
    this.rest = rest;
    this.databaseRealm = databaseRealm;
  }

  @Override
  public Observable<List<Season>> getAllSeasons() {
	return Observable.unsafeCreate(new Observable.OnSubscribe<List<Season>>() {
	  @Override
	  public void call(Subscriber<? super List<Season>> subscriber) {
		try {
		  List<Season> models = databaseRealm.findAll(Season.class);
		  subscriber.onNext(models);
		  models = rest.getSeasons();
		  databaseRealm.deleteAll(new Class[] {Season.class, WinInfo.class, Wins.class, LeagueTable.class});
		  databaseRealm.addAll(models);
		  subscriber.onNext(models);
		  subscriber.onCompleted();
		} catch (Exception e) {
		  Log.w(TAG, "call: ", e);
		  subscriber.onError(e);
		}
	  }
	});
  }

  @Override
  public Observable<Season> getSeasonById(final long id) {
	return Observable.unsafeCreate(new Observable.OnSubscribe<Season>() {
	  @Override
	  public void call(Subscriber<? super Season> subscriber) {
		try {
		  Season model = databaseRealm.findById(Season.class, id);
		  subscriber.onNext(model);
		  model = rest.getSeason(id);
		  databaseRealm.add(model);
		  subscriber.onNext(model);
		  subscriber.onCompleted();
		} catch (Exception e) {
		  Log.w(TAG, "call: ", e);
		  subscriber.onError(e);
		}
	  }
	});
  }

  @Override
  public Observable<LeagueTable> getLeagueTable(final long id, final String caption) {
	return Observable.unsafeCreate(new Observable.OnSubscribe<LeagueTable>() {
	  @Override
	  public void call(Subscriber<? super LeagueTable> subscriber) {
		try {
		  LeagueTable model = databaseRealm.findByField(LeagueTable.class, "leagueCaption", caption);
		  subscriber.onNext(model);
		  model = rest.getLeagueTable(id);
		  databaseRealm.add(model);
		  subscriber.onNext(model);
		  subscriber.onCompleted();
		} catch (Exception e) {
		  Log.w(TAG, "call: ", e);
		  subscriber.onError(e);
		}
	  }
	});
  }

  @Override
  public Observable<Team> getTeam(final long id) {
	return Observable.unsafeCreate(new Observable.OnSubscribe<Team>() {
	  @Override
	  public void call(Subscriber<? super Team> subscriber) {
		try {
		  Team model = databaseRealm.findById(Team.class, id);
		  subscriber.onNext(model);
		  model = rest.getTeam(id);
		  databaseRealm.add(model);
		  subscriber.onNext(model);
		  subscriber.onCompleted();
		} catch (Exception e) {
		  Log.w(TAG, "call: ", e);
		  subscriber.onError(e);
		}
	  }
	});
  }

  @Override
  public Observable<List<Player>> getPlayers(final long id) {
	return Observable.unsafeCreate(new Observable.OnSubscribe<List<Player>>() {
	  @Override
	  public void call(Subscriber<? super List<Player>> subscriber) {
		try {
		  List<Player> model = databaseRealm.findAllByField(Player.class, "commandId", id);
		  subscriber.onNext(model);
		  model = rest.getPlayers(id);
		  databaseRealm.addAll(model);
		  subscriber.onNext(model);
		  subscriber.onCompleted();
		} catch (Exception e) {
		  Log.w(TAG, "call: ", e);
		  subscriber.onError(e);
		}
	  }
	});

  }
}
