package com.petaurus.league.ui.players;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.petaurus.league.application.Injector;
import com.petaurus.league.entity.Player;
import com.petaurus.league.repository.LeagueRepository;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import javax.inject.Inject;
import java.util.List;

@InjectViewState
public class PlayersPresenter extends MvpPresenter<PlayersView> {

  private static final String TAG = PlayersPresenter.class.getSimpleName();

  @Inject
  LeagueRepository repository;

  @Override
  protected void onFirstViewAttach() {
	super.onFirstViewAttach();
	Injector.getAppComponent().inject(this);
	getViewState().initExtras();
  }

  private Subscription subSeasons;

  public void getPlayers(long id) {
	if (subSeasons != null && !subSeasons.isUnsubscribed())
	  return;

    getViewState().showProgress(true);
    subSeasons = repository.getPlayers(id)
			.retry(3)
			.subscribeOn(Schedulers.io())
			.observeOn(AndroidSchedulers.mainThread())
			.subscribe(new Subscriber<List<Player>>() {
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
			  public void onNext(List<Player> items) {
				getViewState().setPlayers(items);
			  }
			});
  }
}
