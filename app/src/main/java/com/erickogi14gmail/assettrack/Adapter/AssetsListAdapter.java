package com.erickogi14gmail.assettrack.Adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.erickogi14gmail.assettrack.Data.Models.Assets;
import com.erickogi14gmail.assettrack.Data.Sqlite.DbOperations;
import com.erickogi14gmail.assettrack.R;
import com.erickogi14gmail.assettrack.Utills.UtilListeners.OnclickRecyclerListener;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

/**
 * Created by Eric on 11/29/2017.
 */

public class AssetsListAdapter extends RecyclerView.Adapter<AssetsListAdapter.MyViewHolder> {
    private Context context;
    private ArrayList<Assets> modelList;
    private DbOperations dbOperations;
    private OnclickRecyclerListener onclickRecyclerListener;
    private int type=0;

    public AssetsListAdapter(Context context,
                             ArrayList<Assets> modelList,
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
        Assets beneficiaryDataModel = modelList.get(position);
        holder.mName.setText(beneficiaryDataModel.getType());

        holder.id.setText( beneficiaryDataModel.getTag());
        holder.model.setText( beneficiaryDataModel.getModel());
        holder.condition.setText( beneficiaryDataModel.getCondition());

        if(beneficiaryDataModel.getImage().equals("R")){
            Glide.with(context).load(R.drawable.dvf).into(holder.imageView);
        }else {
            Glide.with(context).load(beneficiaryDataModel.getImage()).into(holder.imageView);
        }

    }

    @Override
    public int getItemCount() {
        return (null != modelList ? modelList.size() : 0);
    }

    public void updateList(ArrayList<Assets> list) {
        modelList = list;
        notifyDataSetChanged();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ImageView imageView;
        private TextView mName,id,model,condition;
       private CardView layout;
        private WeakReference<OnclickRecyclerListener> listenerWeakReference;


        public MyViewHolder(View itemView, OnclickRecyclerListener onclickRecyclerListener) {
            super(itemView);
            listenerWeakReference = new WeakReference<>(onclickRecyclerListener);

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
