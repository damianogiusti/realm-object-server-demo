package com.damianogiusti.realmobjectserverdemo.app.main;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.damianogiusti.realmobjectserverdemo.R;
import com.damianogiusti.realmobjectserverdemo.app.BaseRecyclerViewAdapter;
import com.damianogiusti.realmobjectserverdemo.app.mvp.model.RealmViewModel;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Damiano Giusti on 10/05/17.
 */
public class MainItemsAdapter extends BaseRecyclerViewAdapter<RealmViewModel, MainItemsAdapter.MainItemViewHolder> {

    public MainItemsAdapter() {
        setHasStableIds(true);
    }

    @Override
    public MainItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.main_list_item_layout, parent, false);
        MainItemViewHolder mainItemViewHolder = new MainItemViewHolder(view);
        return mainItemViewHolder;
    }

    @Override
    public void onBindViewHolder(MainItemViewHolder holder, int position) {
        RealmViewModel realmViewModel = dataset.get(position);
        holder.titleTextView.setText(realmViewModel.getName());
        holder.subtitleTextView.setText(realmViewModel.getDescription());
    }

    @Override
    public long getItemId(int position) {
        return dataset.get(position).hashCode();
    }

    static class MainItemViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.titleTextView) TextView titleTextView;
        @BindView(R.id.subtitleTextView) TextView subtitleTextView;

        public MainItemViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
