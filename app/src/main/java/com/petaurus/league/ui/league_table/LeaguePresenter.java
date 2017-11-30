package com.petaurus.league.ui.league_table;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.petaurus.league.application.Injector;
import com.petaurus.league.entity.LeagueTable;
import com.petaurus.league.repository.LeagueRepository;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import javax.inject.Inject;

@InjectViewState
public class LeaguePresenter extends MvpPresenter<LeagueView> {

  private static final String TAG = LeaguePresenter.class.getSimpleName();

  @Inject
  LeagueRepository repository;

  @Override
  protected void onFirstViewAttach() {
	super.onFirstViewAttach();
	Injector.getAppComponent().inject(this);
	getViewState().initTable();
  }

  private Subscription subs;

  public void getTable(final long id, String caption) {
	if (subs != null && !subs.isUnsubscribed())
	  return;

    getViewState().showProgress(true);
    subs = repository.getLeagueTable(id, caption)
			.retry(3)
			.subscribeOn(Schedulers.io())
			.observeOn(AndroidSchedulers.mainThread())
			.subscribe(new Subscriber<LeagueTable>() {
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
			  public void onNext(LeagueTable table) {
				getViewState().setTable(table);
			  }
			});
  }
}
