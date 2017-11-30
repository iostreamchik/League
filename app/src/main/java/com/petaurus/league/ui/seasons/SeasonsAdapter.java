package com.petaurus.league.ui.seasons;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.petaurus.league.R;
import com.petaurus.league.entity.Season;

import java.util.ArrayList;
import java.util.List;

public class SeasonsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

  private long selectedId;
  private List<Season> items;
  private OnEventListener listener;

  public interface OnEventListener {
	void onClick(View view, int position, Season season);
  }

  public SeasonsAdapter(OnEventListener listener) {
	this.listener = listener;
	setItems(null);
  }

  Season getFirstItem() {
	Season season = items.size() > 0 ? items.get(0) : new Season();
	return season;
  }

  void setItems(List<Season> items) {
	if (items == null)
	  items = new ArrayList<>(1);
	this.items = items;
	notifyDataSetChanged();
  }

  void setSelectedId(long selectedId) {
	this.selectedId = selectedId;
	notifyDataSetChanged();
  }

  @Override
  public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
	View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_season, parent, false);
	return new SeasonsViewHolder(view);
  }

  @Override
  public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
	final Season season = items.get(position);
	SeasonsViewHolder viewHolder = (SeasonsViewHolder) holder;
	viewHolder.vMain.setOnClickListener(new View.OnClickListener() {
	  @Override
	  public void onClick(View view) {
		listener.onClick(view, position, season);
	  }
	});
	viewHolder.tvName.setText(season.getCaption());
	viewHolder.vMain.setBackgroundResource(selectedId != season.getId() ? android.R.color.background_light : R.color.colorAccent);
  }

  @Override
  public int getItemCount() {
	return items.size();
  }

  private static class SeasonsViewHolder extends RecyclerView.ViewHolder {

	View vMain;
	TextView tvName;

	SeasonsViewHolder(View itemView) {
	  super(itemView);
	  vMain = itemView.findViewById(R.id.v_root);
	  tvName = itemView.findViewById(R.id.tv_name);
	}
  }
}
