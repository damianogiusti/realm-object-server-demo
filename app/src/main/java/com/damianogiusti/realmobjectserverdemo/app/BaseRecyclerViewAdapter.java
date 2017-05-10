package com.damianogiusti.realmobjectserverdemo.app;

import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by Damiano Giusti on 04/05/17.
 */
public abstract class BaseRecyclerViewAdapter<T, VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> {

    protected List<T> dataset = new ArrayList<>(0);

    public void setDataset(List<T> dataset) {
        this.dataset = dataset;
    }

    public void add(T item) {
        this.dataset.add(item);
    }

    public void addAll(Collection<T> elements) {
        this.dataset.addAll(elements);
    }

    public void remove(int position) {
        this.dataset.remove(position);
    }

    public void remove(T item) {
        this.dataset.remove(item);
    }

    public void clear() {
        this.dataset.clear();
    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }
}
