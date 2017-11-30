package com.petaurus.league.ui.players;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.petaurus.league.R;
import com.petaurus.league.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class PlayersAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

  private long selectedId;
  private List<Player> items;
  private OnEventListener listener;

  public interface OnEventListener {
	void onClick(View view, int position, Player item);
  }

  public PlayersAdapter(OnEventListener listener) {
	this.listener = listener;
	setItems(null);
  }

  void setItems(List<Player> items) {
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
	View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_player, parent, false);
	return new SeasonsViewHolder(view);
  }

  @Override
  public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
	final Player item = items.get(position);
	SeasonsViewHolder viewHolder = (SeasonsViewHolder) holder;
	viewHolder.vMain.setOnClickListener(new View.OnClickListener() {
	  @Override
	  public void onClick(View view) {
		listener.onClick(view, position, item);
	  }
	});
	viewHolder.tvName.setText(item.getName());
	viewHolder.tvNumber.setText(String.valueOf(item.getJerseyNumber()));
	viewHolder.tvBirth.setText(item.getDateOfBirth());
	viewHolder.tvMarket.setText(item.getMarketValue());
	viewHolder.tvNational.setText(item.getNationality());
  }

  @Override
  public int getItemCount() {
	return items.size();
  }

  private static class SeasonsViewHolder extends RecyclerView.ViewHolder {

	View vMain;
	TextView tvName;
	TextView tvNumber;
	TextView tvBirth;
	TextView tvMarket;
	TextView tvNational;

	SeasonsViewHolder(View itemView) {
	  super(itemView);
	  vMain = itemView.findViewById(R.id.v_root);
	  tvName = itemView.findViewById(R.id.tv_name);
	  tvNumber = itemView.findViewById(R.id.tv_number);
	  tvBirth = itemView.findViewById(R.id.tv_birth);
	  tvMarket = itemView.findViewById(R.id.tv_market);
	  tvNational = itemView.findViewById(R.id.tv_nationality);
	}
  }
}
