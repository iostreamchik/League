package com.petaurus.league.ui.seasons;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;
import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.petaurus.league.R;
import com.petaurus.league.entity.Season;
import com.petaurus.league.ui.season_detail.SeasonActivity;
import com.petaurus.league.ui.season_detail.SeasonFragment;

import java.util.List;

public class SeasonsFragment extends MvpAppCompatFragment implements SeasonsView {

  public static final String TAG = SeasonsFragment.class.getSimpleName();

  @InjectPresenter
  SeasonsPresenter presenter;

  Toolbar toolbar;
  ProgressBar prgbMain;
  SeasonsAdapter adapter;
  RecyclerView rvSeasons;
  SwipeRefreshLayout srlSeasons;

  private long currentId;

  @Override
  public void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setHasOptionsMenu(true);
  }

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
	return inflater.inflate(R.layout.fragment_seasons, container, false);
  }

  @Override
  public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
	super.onViewCreated(view, savedInstanceState);
	initView(view);
  }

  private void initView(View view) {
	toolbar = view.findViewById(R.id.toolbar);
	prgbMain = view.findViewById(R.id.prgb_main);
	srlSeasons = view.findViewById(R.id.srl_seasons);
	srlSeasons.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
	  @Override
	  public void onRefresh() {
		presenter.getSeasons();
	  }
	});
	rvSeasons = view.findViewById(R.id.rv_seasons);
	rvSeasons.setLayoutManager(new LinearLayoutManager(getContext()));
	adapter = new SeasonsAdapter(new SeasonsAdapter.OnEventListener() {
	  @Override
	  public void onClick(View view, int position, Season season) {
		presenter.getViewState().setId(season.getId());
		presenter.getViewState().openSeasonScreen(season.getId());
	  }
	});
	rvSeasons.setAdapter(adapter);
  }

  @Override
  public void openSeasonScreen(long id) {
    if (currentId == id && getActivity().getResources().getConfiguration().orientation != Configuration.ORIENTATION_PORTRAIT)
      return;
    currentId = id;
	if (getActivity().getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
	  SeasonActivity.startActivity(getContext(), id);
	} else {
	  SeasonFragment fragment = SeasonFragment.getInstance(id);
	  FragmentTransaction transaction = getActivity().getSupportFragmentManager()
			  .beginTransaction();
	  transaction.replace(R.id.container_two, fragment, SeasonFragment.TAG);
	  transaction.commitAllowingStateLoss();
	}
  }

  @Override
  public void setId(long id) {
	if (getActivity().getResources().getConfiguration().orientation
			== Configuration.ORIENTATION_PORTRAIT)
	  return;
	adapter.setSelectedId(id);
	openSeasonScreen(id);
  }

  @Override
  public void setSeasons(List<Season> seasons) {
	toolbar.setTitle("Leagues");
	adapter.setItems(seasons);
	setSelection();
  }

  private void setSelection() {
	if (getActivity().getResources().getConfiguration().orientation
			== Configuration.ORIENTATION_PORTRAIT)
	  return;
	if (adapter.getItemCount() == 0)
	  return;
	long id = adapter.getFirstItem().getId();
	adapter.setSelectedId(id);
	openSeasonScreen(id);
  }

  @Override
  public void showProgress(boolean isShown) {
	prgbMain.animate().setDuration(200).alpha(isShown ? 1 : 0).start();
	if (!isShown && srlSeasons.isRefreshing())
	  srlSeasons.setRefreshing(false);
  }

  @Override
  public void showInfo(String title, String message) {
	Toast.makeText(getContext(), String.format("%s\n%s", title, message), Toast.LENGTH_SHORT).show();
  }
}
