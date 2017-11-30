package com.petaurus.league.ui.seasons;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import com.petaurus.league.R;

public class SeasonsActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_seasons);
	initFragment();
  }

  private void initFragment() {
	Fragment fragment = getSupportFragmentManager().findFragmentByTag(SeasonsFragment.TAG);
	if (fragment == null) {
	  getSupportFragmentManager()
			  .beginTransaction()
			  .replace(R.id.container, new SeasonsFragment(), SeasonsFragment.TAG)
			  .commit();
	}
  }
}
