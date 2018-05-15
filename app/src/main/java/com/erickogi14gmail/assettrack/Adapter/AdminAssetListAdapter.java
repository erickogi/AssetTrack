package com.erickogi14gmail.assettrack.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.erickogi14gmail.assettrack.Data.Models.V1.AssetModel;
import com.erickogi14gmail.assettrack.GLConstants;
import com.erickogi14gmail.assettrack.R;
import com.erickogi14gmail.assettrack.Utills.UtilListeners.OnclickRecyclerListener;
import com.haozhang.lib.SlantedTextView;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

public class AdminAssetListAdapter extends RecyclerView.Adapter<AdminAssetListAdapter.MyViewHolder> {

    private ArrayList<AssetModel> assetModels;
    private int status;

    private OnclickRecyclerListener onclickRecyclerListener;

    private Context context;

    public AdminAssetListAdapter(Context context, ArrayList<AssetModel> assetModels, int status, OnclickRecyclerListener onclickRecyclerListener) {
        this.assetModels = assetModels;
        this.context = context;
        this.status = status;
        this.onclickRecyclerListener = onclickRecyclerListener;
    }

    @NonNull
    @Override
    public AdminAssetListAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = null;

        itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_admin_asset, parent, false);
        //this.context=parent.getContext();
        return new AdminAssetListAdapter.MyViewHolder(itemView, onclickRecyclerListener);
    }

    @Override
    public void onBindViewHolder(@NonNull AdminAssetListAdapter.MyViewHolder holder, int position) {
        // dbOperations = new DbOperations(context);
        AssetModel assetModel = assetModels.get(position);
        holder.mName.setText("Asset ".concat(assetModel.getAsset_name()));


        if (status == 0) {
            if (assetModel.getAsset_status_id().equals("" + GLConstants.WORKING)) {
                holder.slantedTextView.setTextColor(context.getResources().getColor(R.color.green));
            } else if (assetModel.getAsset_status_id().equals("" + GLConstants.FAULTY)) {
                holder.slantedTextView.setTextColor(context.getResources().getColor(R.color.red));
            } else if (assetModel.getAsset_status_id().equals("" + GLConstants.WRITTEN_OFF)) {
                holder.slantedTextView.setTextColor(context.getResources().getColor(R.color.colorPrimary));
            }
            holder.slantedTextView.setText(assetModel.getAsset_status());
        } else {

            holder.slantedTextView.setVisibility(View.GONE);
        }


        holder.customer.setText("Customer  ".concat(assetModel.getCustomer_name()));

        if (assetModel.getAsset_image().equals("R")) {
            Glide.with(context).load(R.drawable.dvf).into(holder.imageView);
        } else {
            Glide.with(context).load(assetModel.getAsset_image()).into(holder.imageView);
        }

    }

    @Override
    public int getItemCount() {
        return assetModels.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        private ImageView imageView, imgMore;
        private CheckBox chckBox;
        private TextView mName, customer;
        private CardView layout;
        private SlantedTextView slantedTextView;
        private WeakReference<OnclickRecyclerListener> listenerWeakReference;


        public MyViewHolder(View itemView, OnclickRecyclerListener onclickRecyclerListener) {
            super(itemView);
            listenerWeakReference = new WeakReference<>(onclickRecyclerListener);

            imgMore = itemView.findViewById(R.id.more);
            chckBox = itemView.findViewById(R.id.checkbox);
            slantedTextView = itemView.findViewById(R.id.slanted_txt);
            customer = itemView.findViewById(R.id.txt_customer);
            mName = itemView.findViewById(R.id.txt_asset_name);

            imageView = itemView.findViewById(R.id.asset_image);
            // imgMore=imageView
            layout = itemView.findViewById(R.id.card);

            layout.setOnClickListener(this);
            chckBox.setOnClickListener(this);
            imgMore.setOnClickListener(this);
            layout.setOnLongClickListener(this);
        }


        @Override
        public void onClick(View v) {
            if (v.getId() == R.id.card) {
                listenerWeakReference.get().onClickListener(getAdapterPosition());
            }
            if (v.getId() == R.id.checkbox) {
                listenerWeakReference.get().onCheckedClickListener(getAdapterPosition());
            }
            if (v.getId() == R.id.more) {

                listenerWeakReference.get().onClickListener(getAdapterPosition(), v);
            }
            if (v.getId() == R.id.image) {

            }
        }

        @Override
        public boolean onLongClick(View v) {
            if (v.getId() == R.id.card) {
                listenerWeakReference.get().onLongClickListener(getAdapterPosition());
            }
            return true;
        }
    }
}
