package com.petaurus.league.ui.team;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageSize;
import com.petaurus.league.R;
import com.petaurus.league.entity.Team;
import com.petaurus.league.ui.players.PlayersActivity;

public class TeamActivity extends MvpAppCompatActivity
		implements TeamView, AppBarLayout.OnOffsetChangedListener {

  private static final String TAG = TeamActivity.class.getSimpleName();

  private static final String EXTRA_ID = "ei";

  long id;

  @InjectPresenter
  TeamPresenter presenter;

  private static final float PERCENTAGE_TO_SHOW_TITLE_AT_TOOLBAR = 0.9f;
  private static final float PERCENTAGE_TO_HIDE_TITLE_DETAILS = 0.3f;
  private static final int ALPHA_ANIMATIONS_DURATION = 200;

  private boolean mIsTheTitleVisible = false;
  private boolean mIsTheTitleContainerVisible = true;

  private LinearLayout mTitleContainer;
  private View vPlayers;
  private TextView tvTitle;
  private TextView tvName;
  private TextView tvInfo;
  private ImageView ivLogo;
  private AppBarLayout mAppBarLayout;
  private Toolbar toolbar;

  public static void startActivity(Context context, long id) {
	Intent intent = new Intent(context, TeamActivity.class);
	intent.putExtra(EXTRA_ID, id);
	context.startActivity(intent);
  }

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_team);
	initView();
  }

  private void initView() {
	toolbar = findViewById(R.id.main_toolbar);
	tvTitle = findViewById(R.id.tv_title);
	mTitleContainer = findViewById(R.id.main_linearlayout_title);
	mAppBarLayout = findViewById(R.id.main_appbar);
	mAppBarLayout.addOnOffsetChangedListener(this);
	startAlphaAnimation(tvTitle, 0, View.INVISIBLE);

	tvName = findViewById(R.id.tv_name);
	tvInfo = findViewById(R.id.tv_info);
	ivLogo = findViewById(R.id.iv_logo);
	vPlayers = findViewById(R.id.v_players);
	vPlayers.setOnClickListener(new View.OnClickListener() {
	  @Override
	  public void onClick(View view) {
		PlayersActivity.startActivity(TeamActivity.this, id);
	  }
	});
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
  public void initTeam() {
	if (getIntent() == null || !getIntent().hasExtra(EXTRA_ID)) {
	  throw new IllegalArgumentException(new Throwable("EXTRA_ID & EXTRA_CAPTION required!"));
	}
	id = getIntent().getLongExtra(EXTRA_ID, 1);
	presenter.getTeam(id);
  }

  @Override
  public void setTeam(Team table) {
	if (table == null)
	  table = new Team();
	Log.d(TAG, "setTeam: " + table);
	tvTitle.setText(table.getName());
	tvName.setText(table.getName());
	tvInfo.setText(table.getShortName());
	ImageLoader.getInstance().displayImage(table.getCrestUrl(), ivLogo, new ImageSize(300, 300));
  }

  @Override
  public void showProgress(boolean isShown) {

  }

  @Override
  public void showInfo(String title, String message) {

  }
}
