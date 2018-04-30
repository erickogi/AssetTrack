package com.erickogi14gmail.assettrack.Adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.erickogi14gmail.assettrack.Data.Models.V1.StatusModel;

import java.util.ArrayList;

public class StatusAdapter extends RecyclerView.Adapter<AssetsListAdapter.MyViewHolder> {
    private ArrayList<StatusModel> statusModels;

    public StatusAdapter(ArrayList<StatusModel> statusModels) {
        this.statusModels = statusModels;
    }

    @NonNull
    @Override
    public AssetsListAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull AssetsListAdapter.MyViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return statusModels.size();
    }
}
