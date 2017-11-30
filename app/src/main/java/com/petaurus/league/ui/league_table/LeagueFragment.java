package com.petaurus.league.ui.league_table;

import android.os.Bundle;
import android.support.annotation.Nullable;
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
import com.petaurus.league.entity.LeagueTable;

public class LeagueFragment extends MvpAppCompatFragment implements LeagueView {

  public static final String TAG = LeagueFragment.class.getSimpleName();

  public static final String EXTRA_ID = "ei";
  public static final String EXTRA_CAPTION = "ec";

  @InjectPresenter
  LeaguePresenter presenter;

  Toolbar toolbar;
  ProgressBar prgbMain;

  TextView tvCaption;
  TextView tvYear;
  TextView tvGamesCount;
  TextView tvTeamsCount;

  public static LeagueFragment getInstance(long id, String caption) {
	LeagueFragment fragment = new LeagueFragment();
	Bundle bundle = new Bundle();
	bundle.putLong(LeagueFragment.EXTRA_ID, id);
	bundle.putString(LeagueFragment.EXTRA_CAPTION, caption);
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
	setTable(null);
  }

  @Override
  public void initTable() {
	if (getArguments() == null
			|| !getArguments().containsKey(EXTRA_ID)
			|| !getArguments().containsKey(EXTRA_CAPTION))
	  throw new IllegalArgumentException("EXTRA_ID & EXTRA_CAPTION required");
	long id = getArguments().getLong(EXTRA_ID, 1);
	String caption = getArguments().getString(EXTRA_CAPTION);
	presenter.getTable(id, caption);
  }

  @Override
  public void setTable(LeagueTable table) {
    if (table == null)
      table = new LeagueTable();
	Log.d(TAG, "setTable: " + table);

  }

  @Override
  public void showProgress(boolean isShown) {

  }

  @Override
  public void showInfo(String title, String message) {
	Toast.makeText(getContext(), String.format("%s\n%s", title, message), Toast.LENGTH_SHORT).show();
  }
}
