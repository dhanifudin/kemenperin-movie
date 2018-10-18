package com.dhanifudin.movieapp.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.dhanifudin.movieapp.R;
import com.dhanifudin.movieapp.model.Result;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ResultAdapter
	extends RecyclerView.Adapter<ResultAdapter.ViewHolder> {

	private List<Result> results;

	private OnAdapterClickListener listener;

	public interface OnAdapterClickListener {
		void onClick(Result result);
	}

	public ResultAdapter() {
		results = new ArrayList<>();
	}

	public void setResults(List<Result> results) {
		this.results = results;
		this.notifyDataSetChanged();
	}

	public void setListener(OnAdapterClickListener listener) {
		this.listener = listener;
	}

	@NonNull
	@Override
	public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		View view = LayoutInflater.from(parent.getContext())
			.inflate(R.layout.item_movie, parent, false);
		ViewHolder viewHolder = new ViewHolder(view);
		return viewHolder;
	}

	@Override
	public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
		Result result = results.get(position);
		Picasso.get()
			.load(result.getPosterPathUrl())
			.into(holder.imageMovie);
	}

	@Override
	public int getItemCount() {
		return (results != null) ? results.size() : 0;
	}

	public class ViewHolder extends RecyclerView.ViewHolder {
		ImageView imageMovie;

		public ViewHolder(View itemView) {
			super(itemView);
			imageMovie = itemView.findViewById(R.id.image_movie);
			itemView.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					listener.onClick(results.get(getAdapterPosition()));
				}
			});
		}
	}
}
