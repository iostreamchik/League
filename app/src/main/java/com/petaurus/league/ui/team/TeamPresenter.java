package com.petaurus.league.ui.team;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.petaurus.league.application.Injector;
import com.petaurus.league.entity.Team;
import com.petaurus.league.repository.LeagueRepository;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import javax.inject.Inject;

@InjectViewState
public class TeamPresenter extends MvpPresenter<TeamView> {

  private static final String TAG = TeamPresenter.class.getSimpleName();

  @Inject
  LeagueRepository repository;

  @Override
  protected void onFirstViewAttach() {
	super.onFirstViewAttach();
	Injector.getAppComponent().inject(this);
	getViewState().initTeam();
  }

  private Subscription subs;

  public void getTeam(final long id) {
	if (subs != null && !subs.isUnsubscribed())
	  return;

    getViewState().showProgress(true);
    subs = repository.getTeam(id)
			.retry(3)
			.subscribeOn(Schedulers.io())
			.observeOn(AndroidSchedulers.mainThread())
			.subscribe(new Subscriber<Team>() {
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
			  public void onNext(Team table) {
				getViewState().setTeam(table);
			  }
			});
  }
}
