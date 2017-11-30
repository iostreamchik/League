package com.petaurus.league.rest;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import com.google.gson.Gson;
import com.petaurus.league.entity.*;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Subscriber;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Singleton
public class Rest implements RestApi {

  private static final String TAG = Rest.class.getSimpleName();

  private static final int CONNECT_TIMEOUT = 15;
  private static final int WRITE_TIMEOUT = 15;
  private static final int READ_TIMEOUT = 15;

  private Context context;

  @Inject
  public Rest(Context context) {
	this.context = context;
  }

  @NonNull
  private <T> T getBasicService(Class<T> clazz) {
	return getRetrofit().create(clazz);
  }

  @NonNull
  private Retrofit getRetrofit() {
	OkHttpClient client = new OkHttpClient.Builder()
			.addInterceptor(getHeaderInterceptor())
			.addInterceptor(getLoggerInterceptor())
			.connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
			.writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
			.readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
			.build();

	return new Retrofit.Builder()
			.baseUrl(RestApi.BASE_URL)
			.addConverterFactory(GsonConverterFactory.create(new Gson()))
			.addCallAdapterFactory(RxJavaCallAdapterFactory.create())
			.client(client)
			.build();
  }

  private Interceptor getHeaderInterceptor() {
	return new Interceptor() {
	  @Override
	  public okhttp3.Response intercept(@NonNull Chain chain) throws IOException {
		Request original = chain.request();
		Request.Builder requestBuilder = original.newBuilder()
				.method(original.method(), original.body());
		requestBuilder.addHeader("X-Auth-Token", API_KEY);
		Request request = requestBuilder.build();
		return chain.proceed(request);
	  }
	};

  }

  private Interceptor getLoggerInterceptor() {
	HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor(RestLogger.getInstance());
	interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
	return interceptor;
  }

  @Override
  public Season getSeason(long id) throws Exception {
	RestMethods restMethods = getBasicService(RestMethods.class);
	Call<Season> response = restMethods.getSoccerSeason(id);
	Response<Season> res = response.execute();
	return res.body();
  }

  @Override
  public Observable<Season> getSeasonRx(final long id) throws Exception {
	return Observable.unsafeCreate(new Observable.OnSubscribe<Season>() {
	  @Override
	  public void call(Subscriber<? super Season> subscriber) {
		try {
		  Season item = getSeason(id);
		  subscriber.onNext(item);
		  subscriber.onCompleted();
		} catch (Exception e) {
		  Log.w(TAG, "call: ", e);
		  subscriber.onError(e);
		}
	  }
	});

  }

  @Override
  public List<Season> getSeasons() throws Exception {
	RestMethods restMethods = getBasicService(RestMethods.class);
	Call<List<Season>> response = restMethods.getSoccerSeasons();
	Response<List<Season>> res = response.execute();
	return res.body();
  }

  @Override
  public Observable<List<Season>> getSeasonsRx() {
	return Observable.unsafeCreate(new Observable.OnSubscribe<List<Season>>() {
	  @Override
	  public void call(Subscriber<? super List<Season>> subscriber) {
		try {
		  List<Season> bundle = getSeasons();
		  subscriber.onNext(bundle);
		  subscriber.onCompleted();
		} catch (Exception e) {
		  Log.w(TAG, "call: ", e);
		  subscriber.onError(e);
		}
	  }
	});
  }

  @Override
  public LeagueTable getLeagueTable(final long id) throws Exception {
	RestMethods restMethods = getBasicService(RestMethods.class);
	Call<LeagueTable> response = restMethods.getLeagueTable(id);
	Response<LeagueTable> res = response.execute();
	LeagueTable table = res.body();
	if (table != null) {
	  table.upgradeWinInfo();
	}
	return table;
  }

  @Override
  public Observable<LeagueTable> getLeagueTableRx(final long id) {
	return Observable.unsafeCreate(new Observable.OnSubscribe<LeagueTable>() {
	  @Override
	  public void call(Subscriber<? super LeagueTable> subscriber) {
		try {
		  LeagueTable bundle = getLeagueTable(id);
		  subscriber.onNext(bundle);
		  subscriber.onCompleted();
		} catch (Exception e) {
		  Log.w(TAG, "call: ", e);
		  subscriber.onError(e);
		}
	  }
	});
  }

  @Override
  public Team getTeam(final long id) throws Exception {
	RestMethods restMethods = getBasicService(RestMethods.class);
	Call<Team> response = restMethods.getTeam(id);
	Response<Team> res = response.execute();
	Team team = res.body();
	if (team != null) {
	  team.setId(id);
	}
	return team;
  }

  @Override
  public Observable<Team> getTeamRx(final long id) {
	return Observable.unsafeCreate(new Observable.OnSubscribe<Team>() {
	  @Override
	  public void call(Subscriber<? super Team> subscriber) {
		try {
		  Team bundle = getTeam(id);
		  subscriber.onNext(bundle);
		  subscriber.onCompleted();
		} catch (Exception e) {
		  Log.w(TAG, "call: ", e);
		  subscriber.onError(e);
		}
	  }
	});
  }

  @Override
  public List<Player> getPlayers(long id) throws Exception {
	RestMethods restMethods = getBasicService(RestMethods.class);
	Call<PlayerWrapper> response = restMethods.getPlayers(id);
	Response<PlayerWrapper> res = response.execute();
	PlayerWrapper pw = res.body();
	List<Player> players;
	if (pw != null) {
	  pw.setCommandId(id);
		players = pw.getPlayers() == null ? new ArrayList<Player>(1) : pw.getPlayers();
	} else {
	  players = new ArrayList<>(1);
	}
	return players;
  }

  @Override
  public Observable<List<Player>> getPlayersRx(long id) {
	return null;
  }
}
