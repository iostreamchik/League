package com.petaurus.league.ui.season_detail;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.petaurus.league.R;
import com.petaurus.league.entity.Season;
import com.petaurus.league.ui.league_table.LeagueActivity;

public class SeasonFragment extends MvpAppCompatFragment implements SeasonView {

  public static final String TAG = SeasonFragment.class.getSimpleName();

  public static final String EXTRA_ID = "ei";

  @InjectPresenter
  SeasonPresenter presenter;

  Toolbar toolbar;
  ProgressBar prgbMain;

  TextView tvCaption;
  TextView tvYear;
  TextView tvGamesCount;
  TextView tvTeamsCount;
  FloatingActionButton fab;

  public static SeasonFragment getInstance(long id) {
	SeasonFragment fragment = new SeasonFragment();
	Bundle bundle = new Bundle();
	bundle.putLong(SeasonFragment.EXTRA_ID, id);
	fragment.setArguments(bundle);
	return fragment;
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setHasOptionsMenu(true);
  }

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
	return inflater.inflate(R.layout.fragment_season, container, false);
  }

  @Override
  public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
	super.onViewCreated(view, savedInstanceState);
	initView(view);
  }

  private void initView(View view) {
	toolbar = view.findViewById(R.id.toolbar);
	prgbMain = view.findViewById(R.id.prgb_main);

	tvCaption = view.findViewById(R.id.tv_caption);
	tvYear = view.findViewById(R.id.tv_year);
	tvGamesCount = view.findViewById(R.id.tv_games_count);
	tvTeamsCount = view.findViewById(R.id.tv_teams_count);
	fab = view.findViewById(R.id.fab);
	fab.setOnClickListener(new View.OnClickListener() {
	  @Override
	  public void onClick(View view) {
		LeagueActivity.startActivity(getContext(),
				getArguments().getLong(EXTRA_ID, 1),
				toolbar.getTitle() != null ? toolbar.getTitle().toString() : "");
	  }
	});
	setSeason(null);
  }

  @Override
  public void initSeason() {
	if (getArguments() == null || !getArguments().containsKey(EXTRA_ID))
	  throw new IllegalArgumentException("EXTRA_ID required");
	long id = getArguments().getLong(EXTRA_ID, 1);
	presenter.getSeason(id);
  }

  @Override
  public void setSeason(Season season) {
	Log.d(TAG, "setSeason: " + season);
	if (season == null)
	  season = new Season();
	toolbar.setTitle(season.getCaption());
	tvCaption.setText(season.getLeague());
	tvYear.setText(season.getYear());
	tvGamesCount.setText(String.format("Games Count:	%s", season.getNumberOfGames()));
	tvTeamsCount.setText(String.format("Teams Count:	%s", season.getNumberOfTeams()));
  }

  @Override
  public void showProgress(boolean isShown) {

  }

  @Override
  public void showInfo(String title, String message) {
	Toast.makeText(getContext(), String.format("%s\n%s", title, message), Toast.LENGTH_SHORT).show();
  }
}
