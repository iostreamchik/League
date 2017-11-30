package com.petaurus.league.ui.league_table;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.petaurus.league.R;
import com.petaurus.league.entity.LeagueTable;
import com.petaurus.league.entity.WinInfo;
import com.petaurus.league.ui.team.TeamActivity;

public class LeagueActivity extends MvpAppCompatActivity
		implements LeagueView, AppBarLayout.OnOffsetChangedListener  {

  private static final String TAG = LeagueActivity.class.getSimpleName();

  private static final String EXTRA_ID = "ei";
  private static final String EXTRA_CAPTION = "ec";

  long id;
  String caption;

  @InjectPresenter
  LeaguePresenter presenter;

  private static final float PERCENTAGE_TO_SHOW_TITLE_AT_TOOLBAR = 0.9f;
  private static final float PERCENTAGE_TO_HIDE_TITLE_DETAILS = 0.3f;
  private static final int ALPHA_ANIMATIONS_DURATION = 200;

  private boolean mIsTheTitleVisible = false;
  private boolean mIsTheTitleContainerVisible = true;

  private View vCap;
  private LinearLayout mTitleContainer;
  private TextView tvTitle;
  private TextView tvName;
  private AppBarLayout mAppBarLayout;
  private Toolbar toolbar;

  private LeagueAdapter adapter;
  private RecyclerView rvTables;

  public static void startActivity(Context context, long id, String caption) {
	Intent intent = new Intent(context, LeagueActivity.class);
	intent.putExtra(EXTRA_ID, id);
	intent.putExtra(EXTRA_CAPTION, caption);
	context.startActivity(intent);
  }

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_league);
	initView();
  }

  private void initView() {
    vCap = findViewById(R.id.v_cap);
	toolbar = findViewById(R.id.main_toolbar);
	tvTitle = findViewById(R.id.tv_title);
	tvName = findViewById(R.id.tv_name);
	mTitleContainer = findViewById(R.id.main_linearlayout_title);
	mAppBarLayout = findViewById(R.id.main_appbar);
	mAppBarLayout.addOnOffsetChangedListener(this);
	startAlphaAnimation(tvTitle, 0, View.INVISIBLE);

	rvTables = findViewById(R.id.rv_tables);
	rvTables.setLayoutManager(new LinearLayoutManager(this));
	adapter = new LeagueAdapter(new LeagueAdapter.OnEventListener() {
	  @Override
	  public void onClick(View view, int position, WinInfo item) {
		Log.d(TAG, "onClick: " + item.getTeamId());
		TeamActivity.startActivity(LeagueActivity.this, item.getTeamId());
	  }
	});
	rvTables.setAdapter(adapter);
	DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this,
			DividerItemDecoration.VERTICAL);
	rvTables.addItemDecoration(dividerItemDecoration);
  }

  @Override
  public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
	int maxScroll = appBarLayout.getTotalScrollRange();
	float percentage = (float) Math.abs(verticalOffset) / (float) maxScroll;

	handleAlphaOnTitle(percentage);
	handleToolbarTitleVisibility(percentage);
  }

  private void handleAlphaOnTitle(float percentage) {
	if (percentage >= PERCENTAGE_TO_HIDE_TITLE_DETAILS) {
	  if (mIsTheTitleContainerVisible) {
		startAlphaAnimation(mTitleContainer, ALPHA_ANIMATIONS_DURATION, View.INVISIBLE);
		mIsTheTitleContainerVisible = false;
	  }

	} else {

	  if (!mIsTheTitleContainerVisible) {
		startAlphaAnimation(mTitleContainer, ALPHA_ANIMATIONS_DURATION, View.VISIBLE);
		mIsTheTitleContainerVisible = true;
	  }
	}
  }

  private void handleToolbarTitleVisibility(float percentage) {
	if (percentage >= PERCENTAGE_TO_SHOW_TITLE_AT_TOOLBAR) {

	  if (!mIsTheTitleVisible) {
		startAlphaAnimation(tvTitle, ALPHA_ANIMATIONS_DURATION, View.VISIBLE);
		mIsTheTitleVisible = true;
	  }

	} else {

	  if (mIsTheTitleVisible) {
		startAlphaAnimation(tvTitle, ALPHA_ANIMATIONS_DURATION, View.INVISIBLE);
		mIsTheTitleVisible = false;
	  }
	}
  }

  public static void startAlphaAnimation(View v, long duration, int visibility) {
	AlphaAnimation alphaAnimation = (visibility == View.VISIBLE)
			? new AlphaAnimation(0f, 1f)
			: new AlphaAnimation(1f, 0f);

	alphaAnimation.setDuration(duration);
	alphaAnimation.setFillAfter(true);
	v.startAnimation(alphaAnimation);
  }

  @Override
  public void initTable() {
    if (getIntent() == null || !getIntent().hasExtra(EXTRA_ID) || !getIntent().hasExtra(EXTRA_CAPTION)) {
      throw new IllegalArgumentException(new Throwable("EXTRA_ID & EXTRA_CAPTION required!"));
	}
	id = getIntent().getLongExtra(EXTRA_ID, 1);
	caption = getIntent().getStringExtra(EXTRA_CAPTION);
	presenter.getTable(id, caption);
  }

  @Override
  public void setTable(LeagueTable table) {
	if (table == null)
	  table = new LeagueTable();
	Log.d(TAG, "setTable: " + table);
	tvTitle.setText(table.getLeagueCaption());
	tvName.setText(table.getLeagueCaption());
	adapter.setItems(table.getStanding());
  }

  @Override
  public void showProgress(boolean isShown) {
    if (!isShown)
		vCap.setVisibility(adapter.getItemCount() == 0 ? View.VISIBLE : View.GONE);
  }

  @Override
  public void showInfo(String title, String message) {

  }
}
