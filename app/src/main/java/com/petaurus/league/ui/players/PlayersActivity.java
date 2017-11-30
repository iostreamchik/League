package com.petaurus.league.ui.players;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;
import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.petaurus.league.R;
import com.petaurus.league.entity.Player;

import java.util.List;

public class PlayersActivity extends MvpAppCompatActivity implements PlayersView {

  private static final String TAG = PlayersActivity.class.getSimpleName();

  private static final String EXTRA_ID = "ei";

  @InjectPresenter
  PlayersPresenter presenter;

  private long id;
  View vCap;
  ProgressBar prgbMain;
  PlayersAdapter adapter;
  RecyclerView rvMain;

  public static void startActivity(Context context, long id) {
	Intent intent = new Intent(context, PlayersActivity.class);
	intent.putExtra(EXTRA_ID, id);
	context.startActivity(intent);
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_players);
	initView();
  }

  private void initView() {
	vCap = findViewById(R.id.v_cap);
	vCap.setVisibility(View.GONE);
	prgbMain = findViewById(R.id.prgb_main);
	rvMain = findViewById(R.id.rv_players);
	rvMain.setLayoutManager(new LinearLayoutManager(this));
	adapter = new PlayersAdapter(new PlayersAdapter.OnEventListener() {
	  @Override
	  public void onClick(View view, int position, Player item) {

	  }
	});
	rvMain.setAdapter(adapter);
  }

  @Override
  public void initExtras() {
	id = getIntent().getLongExtra(EXTRA_ID, 1);
	presenter.getPlayers(id);
  }

  @Override
  public void setPlayers(List<Player> items) {
	adapter.setItems(items);
  }

  @Override
  public void showProgress(boolean isShown) {
	prgbMain.animate().setDuration(200).alpha(isShown ? 1 : 0).start();
	if (!isShown)
	  vCap.setVisibility(adapter.getItemCount() == 0 ? View.VISIBLE : View.GONE);
  }

  @Override
  public void showInfo(String title, String message) {
	Toast.makeText(this, String.format("%s\n%s", title, message), Toast.LENGTH_SHORT).show();
  }
}
