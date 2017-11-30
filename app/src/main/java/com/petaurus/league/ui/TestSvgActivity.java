package com.petaurus.league.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import com.petaurus.league.R;
import com.petaurus.league.entity.WinInfo;
import com.petaurus.league.ui.league_table.LeagueAdapter;

import java.util.ArrayList;

public class TestSvgActivity extends AppCompatActivity {

  private static final String TAG = TestSvgActivity.class.getSimpleName();

  private LeagueAdapter adapter;
  private RecyclerView rvTables;

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_image);
	initView();
	testImageDownload();
  }

  private void initView() {
	rvTables = findViewById(R.id.rv_tables);
	rvTables.setLayoutManager(new LinearLayoutManager(this));
	adapter = new LeagueAdapter(new LeagueAdapter.OnEventListener() {
	  @Override
	  public void onClick(View view, int position, WinInfo season) {
		Log.d(TAG, "onClick: " + season.getCrestURI());
	  }
	});
	rvTables.setAdapter(adapter);
	DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this,
			DividerItemDecoration.VERTICAL);
	rvTables.addItemDecoration(dividerItemDecoration);
  }

  private void testImageDownload() {
	ArrayList<WinInfo> list = new ArrayList<>();

	list.add(new WinInfo("http://upload.wikimedia.org/wikipedia/de/0/05/PSV_Eindhoven.svg"));
	list.add(new WinInfo("http://upload.wikimedia.org/wikipedia/de/7/79/Ajax_Amsterdam.svg"));
	list.add(new WinInfo("https://upload.wikimedia.org/wikipedia/commons/e/e0/AZ_Alkmaar.svg"));
	list.add(new WinInfo("http://upload.wikimedia.org/wikipedia/de/2/28/PEC_Zwolle_2012.svg"));
	list.add(new WinInfo("http://upload.wikimedia.org/wikipedia/de/4/48/FC_Utrecht.svg"));
	list.add(new WinInfo("http://upload.wikimedia.org/wikipedia/de/4/41/Vitesse_Arnheim.svg"));
	list.add(new WinInfo("http://upload.wikimedia.org/wikipedia/de/2/24/Logo_Feyenoord_Rotterdam.svg"));
	list.add(new WinInfo("http://upload.wikimedia.org/wikipedia/de/d/d8/Heracles_Almelo.svg"));
	list.add(new WinInfo("http://upload.wikimedia.org/wikipedia/de/e/e7/SC_Heerenveen.svg"));
	list.add(new WinInfo("https://upload.wikimedia.org/wikipedia/en/f/f5/SBV_Excelsior_logo.png"));
	list.add(new WinInfo("http://upload.wikimedia.org/wikipedia/de/4/45/ADO_Den_Haag.svg"));
	list.add(new WinInfo("https://upload.wikimedia.org/wikipedia/en/6/60/VVV-Venlo_logo.svg"));
	list.add(new WinInfo("http://upload.wikimedia.org/wikipedia/de/e/ef/FC_Groningen.svg"));
	list.add(new WinInfo("https://upload.wikimedia.org/wikipedia/en/9/95/Sparta_Rotterdam_logo.jpeg"));
	list.add(new WinInfo("https://upload.wikimedia.org/wikipedia/de/7/7c/Willem_II_Tilburg.svg"));
	list.add(new WinInfo("http://upload.wikimedia.org/wikipedia/de/6/6a/FC_Twente_Enschede_(ab_2006).svg"));
	list.add(new WinInfo("https://upload.wikimedia.org/wikipedia/commons/c/c9/Logo_NAC_Breda.png"));
	list.add(new WinInfo("http://upload.wikimedia.org/wikipedia/de/3/36/Roda_JC_Kerkrade_(2011).svg"));

	adapter.setItems(list);
  }
}
