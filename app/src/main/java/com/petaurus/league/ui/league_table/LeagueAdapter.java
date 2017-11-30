package com.petaurus.league.ui.league_table;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.petaurus.league.R;
import com.petaurus.league.entity.WinInfo;

import java.util.ArrayList;
import java.util.List;

public class LeagueAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

  private static final String TAG = LeagueAdapter.class.getSimpleName();

  private List<WinInfo> items;
  private OnEventListener listener;

  public interface OnEventListener {
	void onClick(View view, int position, WinInfo item);
  }

  public LeagueAdapter(OnEventListener listener) {
	this.listener = listener;
	setItems(null);
  }

  public void setItems(List<WinInfo> items) {
	if (items == null)
	  items = new ArrayList<>(1);
	this.items = items;
	notifyDataSetChanged();
  }

  @Override
  public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
	View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_table, parent, false);
	return new TableViewHolder(view);
  }

  @Override
  public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
	final WinInfo item = items.get(position);
	TableViewHolder viewHolder = (TableViewHolder) holder;
	viewHolder.vMain.setOnClickListener(new View.OnClickListener() {
	  @Override
	  public void onClick(View view) {
		listener.onClick(view, position, item);
	  }
	});
	viewHolder.tvName.setText(item.getTeamName());
	viewHolder.tvWins.setText(String.format("Wins: %s", item.getWins()));
	viewHolder.tvLosses.setText(String.format("Losses: %s", item.getLosses()));
	ImageLoader.getInstance().displayImage(item.getCrestURI(), viewHolder.ivImg);
  }

  @Override
  public int getItemCount() {
	return items.size();
  }

  private static class TableViewHolder extends RecyclerView.ViewHolder {

	View vMain;
	TextView tvName;
	TextView tvWins;
	TextView tvLosses;
	ImageView ivImg;

	TableViewHolder(View itemView) {
	  super(itemView);
	  vMain = itemView.findViewById(R.id.v_root);
	  tvName = itemView.findViewById(R.id.tv_name);
	  tvWins = itemView.findViewById(R.id.tv_wins);
	  tvLosses = itemView.findViewById(R.id.tv_losses);
	  ivImg = itemView.findViewById(R.id.iv_img);
	}
  }
}
