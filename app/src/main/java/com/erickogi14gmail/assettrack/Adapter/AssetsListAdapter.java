package com.erickogi14gmail.assettrack.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.erickogi14gmail.assettrack.Data.Models.V1.AssetModel;
import com.erickogi14gmail.assettrack.Data.Sqlite.DbOperations;
import com.erickogi14gmail.assettrack.R;
import com.erickogi14gmail.assettrack.Utills.UtilListeners.OnclickRecyclerListener;
import com.haozhang.lib.SlantedTextView;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

/**
 * Created by Eric on 11/29/2017.
 */

public class AssetsListAdapter extends RecyclerView.Adapter<AssetsListAdapter.MyViewHolder> {
    private Context context;
    private ArrayList<AssetModel> modelList;
    private DbOperations dbOperations;
    private OnclickRecyclerListener onclickRecyclerListener;
    private int type=0;

    public AssetsListAdapter(Context context,
                             ArrayList<AssetModel> modelList,
                             OnclickRecyclerListener onclickRecyclerListener) {
        this.context = context;
        this.type=type;
        this.modelList = modelList;
        dbOperations = new DbOperations(context);
        this.onclickRecyclerListener = onclickRecyclerListener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = null;

        itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.asset_item, parent, false);
        return new MyViewHolder(itemView, onclickRecyclerListener);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

       // AppUtils appUtils = new AppUtils(context);
        dbOperations = new DbOperations(context);
        AssetModel beneficiaryDataModel = modelList.get(position);
        holder.mName.setText("Item : " + beneficiaryDataModel.getAsset_name());

        holder.id.setText("Tag : " + beneficiaryDataModel.getAsset_code());
        holder.model.setText("Model : " + beneficiaryDataModel.getModel());

        if (beneficiaryDataModel.getAsset_status().equals("Okay")) {
            holder.slantedTextView.setSlantedBackgroundColor(context.getResources().getColor(R.color.green));
        } else {
            holder.slantedTextView.setSlantedBackgroundColor(context.getResources().getColor(R.color.red));
        }
        holder.condition.setText("Status : " + beneficiaryDataModel.getAsset_status());
        holder.slantedTextView.setText(beneficiaryDataModel.getAsset_status());

        holder.slantedTextView.setTextColor(Color.WHITE);
        if (beneficiaryDataModel.getAsset_image().equals("R")) {
            Glide.with(context).load(R.drawable.dvf).into(holder.imageView);
        }else {
            Glide.with(context).load(beneficiaryDataModel.getAsset_image()).into(holder.imageView);
        }
        if (beneficiaryDataModel.getAsset_image() != null) {

            Log.d("imh", beneficiaryDataModel.getAsset_image());
        } else {
            Log.d("imh", "nulll");
        }
    }

    @Override
    public int getItemCount() {
        return (null != modelList ? modelList.size() : 0);
    }

    public void updateList(ArrayList<AssetModel> list) {
        modelList = list;
        notifyDataSetChanged();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ImageView imageView;
        private TextView mName,id,model,condition;
       private CardView layout;
        private SlantedTextView slantedTextView;
        private WeakReference<OnclickRecyclerListener> listenerWeakReference;


        public MyViewHolder(View itemView, OnclickRecyclerListener onclickRecyclerListener) {
            super(itemView);
            listenerWeakReference = new WeakReference<>(onclickRecyclerListener);

            slantedTextView = itemView.findViewById(R.id.slanted_txt);
            id = itemView.findViewById(R.id.txt_id);
            mName = itemView.findViewById(R.id.txt_name);
            model = itemView.findViewById(R.id.txt_model);
            condition = itemView.findViewById(R.id.txt_condition);

            imageView = itemView.findViewById(R.id.image);
            layout = itemView.findViewById(R.id.card);

            layout.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            // modelList.get(0);
            listenerWeakReference.get().onClickListener(getAdapterPosition());
        }
    }
}
