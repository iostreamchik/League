package com.petaurus.league.ui.season_detail;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import com.petaurus.league.R;

public class SeasonActivity extends AppCompatActivity {

  private static final String TAG = SeasonActivity.class.getSimpleName();

  private static final String EXTRA_ID = "ei";

  long id;

  public static void startActivity(Context context, long id) {
	Intent intent = new Intent(context, SeasonActivity.class);
	intent.putExtra(EXTRA_ID, id);
	context.startActivity(intent);
  }

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_season);
	initExtras();
	initFragment();
  }

  private void initExtras() {
	id = getIntent().getLongExtra(EXTRA_ID, 1);
  }

  private void initFragment() {
	Fragment fragment = getSupportFragmentManager().findFragmentByTag(SeasonFragment.TAG);
	if (fragment == null) {
	  getSupportFragmentManager()
			  .beginTransaction()
			  .replace(R.id.container, SeasonFragment.getInstance(id), SeasonFragment.TAG)
			  .commit();
	}
  }
}
