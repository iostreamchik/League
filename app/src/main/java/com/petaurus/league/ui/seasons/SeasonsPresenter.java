package com.petaurus.league.ui.seasons;

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
import java.util.List;

@InjectViewState
public class SeasonsPresenter extends MvpPresenter<SeasonsView> {

  private static final String TAG = SeasonsPresenter.class.getSimpleName();

  @Inject
  LeagueRepository repository;

  @Override
  protected void onFirstViewAttach() {
	super.onFirstViewAttach();
	Injector.getAppComponent().inject(this);
	getSeasons();
  }

  private Subscription subSeasons;

  public void getSeasons() {
	if (subSeasons != null && !subSeasons.isUnsubscribed())
	  return;

    getViewState().showProgress(true);
    subSeasons = repository.getAllSeasons()
			.subscribeOn(Schedulers.io())
			.observeOn(AndroidSchedulers.mainThread())
			.subscribe(new Subscriber<List<Season>>() {
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
			  public void onNext(List<Season> seasons) {
			    if (seasons.size() > 0)
					getViewState().showProgress(false);
				getViewState().setSeasons(seasons);
			  }
			});
  }
}
