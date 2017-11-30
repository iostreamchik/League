package com.petaurus.league.ui.season_detail;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.petaurus.league.application.Injector;
import com.petaurus.league.entity.Season;
import com.petaurus.league.repository.LeagueRepository;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import javax.inject.Inject;

@InjectViewState
public class SeasonPresenter extends MvpPresenter<SeasonView> {

  private static final String TAG = SeasonPresenter.class.getSimpleName();

  @Inject
  LeagueRepository repository;

  @Override
  protected void onFirstViewAttach() {
	super.onFirstViewAttach();
	Injector.getAppComponent().inject(this);
	getViewState().initSeason();
  }

  private Subscription subSeasons;

  public void getSeason(final long id) {
	if (subSeasons != null && !subSeasons.isUnsubscribed())
	  return;

    getViewState().showProgress(true);
    subSeasons = repository.getSeasonById(id)
			.retry(3)
			.subscribeOn(Schedulers.io())
			.observeOn(AndroidSchedulers.mainThread())
			.subscribe(new Subscriber<Season>() {
			  @Override
			  public void onCompleted() {
				getViewState().showProgress(false);
			  }

			  @Override
			  public void onError(Throwable e) {
				getViewState().showProgress(false);
				getViewState().showInfo("Error", "Something Wrong! Try Later.");
			  }

			  @Override
			  public void onNext(Season seasons) {
				getViewState().setSeason(seasons);
			  }
			});
  }
}
