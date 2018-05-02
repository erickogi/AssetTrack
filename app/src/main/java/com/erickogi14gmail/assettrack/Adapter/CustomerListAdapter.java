package com.erickogi14gmail.assettrack.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.erickogi14gmail.assettrack.Data.Models.V1.CustomerModel;
import com.erickogi14gmail.assettrack.R;
import com.erickogi14gmail.assettrack.Utills.UtilListeners.OnclickRecyclerListener;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

public class CustomerListAdapter extends RecyclerView.Adapter<CustomerListAdapter.MyViewHolder> {

    private ArrayList<CustomerModel> assetModels;
    private int status;

    private OnclickRecyclerListener onclickRecyclerListener;

    private Context context;

    public CustomerListAdapter(Context context, ArrayList<CustomerModel> assetModels, OnclickRecyclerListener onclickRecyclerListener) {
        this.assetModels = assetModels;
        this.context = context;

        this.onclickRecyclerListener = onclickRecyclerListener;
    }

    @NonNull
    @Override
    public CustomerListAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = null;

        itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.customer_item_layout, parent, false);
        //this.context=parent.getContext();
        return new CustomerListAdapter.MyViewHolder(itemView, onclickRecyclerListener);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomerListAdapter.MyViewHolder holder, int position) {
        // dbOperations = new DbOperations(context);
        holder.actions.setVisibility(View.VISIBLE);
        CustomerModel customerModel = assetModels.get(position);
        holder.txtCustomerName.setText(customerModel.getName());
        holder.txtCustomerLocation.setText(customerModel.getPhysical_address());

    }

    @Override
    public int getItemCount() {
        return assetModels.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        private ImageView imageView, imgMore;
        private CheckBox chckBox;
        private RelativeLayout actions;
        private TextView txtCustomerName, txtCustomerLocation;

        private WeakReference<OnclickRecyclerListener> listenerWeakReference;


        public MyViewHolder(View itemView, OnclickRecyclerListener onclickRecyclerListener) {
            super(itemView);
            actions = itemView.findViewById(R.id.actions);
            listenerWeakReference = new WeakReference<>(onclickRecyclerListener);

            imgMore = itemView.findViewById(R.id.more);
            chckBox = itemView.findViewById(R.id.checkbox);

            imageView = itemView.findViewById(R.id.asset_image);

            txtCustomerName = itemView.findViewById(R.id.txt_customer_name);
            txtCustomerLocation = itemView.findViewById(R.id.txt_customer_location);

            chckBox.setOnClickListener(this);
            imgMore.setOnClickListener(this);

            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);

        }


        @Override
        public void onClick(View v) {

            if (v.getId() == R.id.checkbox) {
                listenerWeakReference.get().onCheckedClickListener(getAdapterPosition());
            }
            if (v.getId() == R.id.more) {

                listenerWeakReference.get().onClickListener(getAdapterPosition(), v);
            } else {

                listenerWeakReference.get().onClickListener(getAdapterPosition());
            }
        }

        @Override
        public boolean onLongClick(View v) {
            // if (v.getId() == R.id.card) {
            listenerWeakReference.get().onLongClickListener(getAdapterPosition());
            // }
            return true;
        }
    }
}
