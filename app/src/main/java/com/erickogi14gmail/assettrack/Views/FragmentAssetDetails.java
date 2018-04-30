package com.erickogi14gmail.assettrack.Views;

import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.erickogi14gmail.assettrack.Data.Models.Assets;
import com.erickogi14gmail.assettrack.Data.Models.V1.AssetModel;
import com.erickogi14gmail.assettrack.Data.Sqlite.DbConstants;
import com.erickogi14gmail.assettrack.Data.Sqlite.DbContentValues;
import com.erickogi14gmail.assettrack.Data.Sqlite.DbOperations;
import com.erickogi14gmail.assettrack.R;
import com.erickogi14gmail.assettrack.Utills.Constants;

import java.util.ArrayList;

/**
 * Created by Eric on 2/28/2018.
 */

public class FragmentAssetDetails extends Fragment {

    private DbOperations dbOperations;
    private DbContentValues dbContentValues;

    private TextView txtTag,txtType,txtSite,txtSerial,txtCondition,txtInstallitiondate,txtLastmaintancedate,txtLastmaintainedby,
            txtContract,txtModel,txtInstalledby;


    private TextView edtEngRemarks, edtCustRemarks, edtDateInst;
    private TextView edtScan;
    private ImageView img;

    private ImageView imageView;

    private TextView edtAssetName, edtWarranty, edtWarrantyDuration, edtModel, edtStatus,
            edtSerialNo, edtManufacturer, edtYrOfManufacture, edtServiceContract, edtCustomerName;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        dbOperations=new DbOperations(getContext());
        dbContentValues =new DbContentValues();

        return inflater.inflate(R.layout.fragment_asset_details,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        int key_id=getArguments().getInt("key_id");

        img = view.findViewById(R.id.image);

//        imageView=view.findViewById(R.id.asset_image);
//        txtInstalledby=view.findViewById(R.id.txt_installation_by);
//        txtModel=view.findViewById(R.id.txt_model);
//        txtContract=view.findViewById(R.id.txt_service_contract);
//
//        txtTag=view.findViewById(R.id.txt_tag);
//        txtType=view.findViewById(R.id.txt_type);
//        txtSite=view.findViewById(R.id.txt_site);
//        txtSerial=view.findViewById(R.id.txt_serial);
//        txtCondition=view.findViewById(R.id.txt_condition);
//        txtInstallitiondate=view.findViewById(R.id.txt_installation);
//        txtLastmaintancedate=view.findViewById(R.id.txt_last_maintenance);
//        txtLastmaintainedby=view.findViewById(R.id.txt_last_maintenance_by);


        edtScan = view.findViewById(R.id.edt_scan_code);
        edtStatus = view.findViewById(R.id.edt_status);
        edtAssetName = view.findViewById(R.id.edt_asset_name);
        //tilWarrantyDuration=view.findViewById(R.id.til_Warranty_duration);
        //edtWarranty=view.findViewById(R.id.edt_warranty);
        edtWarrantyDuration = view.findViewById(R.id.edt_warranty_duration);
        edtModel = view.findViewById(R.id.edt_model);
        edtSerialNo = view.findViewById(R.id.edt_serial);
        edtManufacturer = view.findViewById(R.id.edt_manufacturer);
        edtYrOfManufacture = view.findViewById(R.id.edt_manufacture_yr);
        edtServiceContract = view.findViewById(R.id.edt_contact);

        //rgWarranty=view.findViewById(R.id.rgrp_warranty);
        //rbNo=view.findViewById(R.id.rbtn_warranty_no);
        //rbYes=view.findViewById(R.id.rbtn_warranty_yes);

        edtCustomerName = view.findViewById(R.id.edt_customer_name);
        edtDateInst = view.findViewById(R.id.edt_installation_date);

        //rgAssetStatus=view.findViewById(R.id.rgrp_status);


        edtEngRemarks = view.findViewById(R.id.edt_engineer_remarks);
        edtCustRemarks = view.findViewById(R.id.edt_customer_remarks);


        ArrayList<Assets> assets=new ArrayList<>();

        Cursor cursor=dbOperations.select(DbConstants.TABLE_ITEMS,DbConstants.KEY_ID, Constants.id);
        if(cursor!=null&&cursor.getCount()>0){
            assets=dbContentValues.getAssets(cursor);
        }
        Assets assets1=new Assets();
        if(assets.size()>0) {
            assets1 = assets.get(0);
        }

        if(assets1!=null){
            // setView(assets1);
        }

        ArrayList<AssetModel> assetModels = new ArrayList<>();
        // ArrayList<Assets> assets=new ArrayList<>();

        Cursor cursorv1 = dbOperations.select(DbConstants.TABLE_ITEMS_V1, DbConstants.KEY_ID, Constants.id);
        if (cursor != null && cursor.getCount() > 0) {
            assetModels = dbContentValues.getAssetsv1(cursorv1);
        }
        if (assetModels != null && assetModels.size() > 0) {
            //setViewV1(assetModels.get(0));
        }

        setViewV1(Constants.assetModel);


    }

    void setViewV1(AssetModel assetModels) {
        if (assetModels != null) {
            if (assetModels.getCustomer_name() != null) {
                // customerID=assetModel.getCustomer_id();
                edtCustomerName.setText(assetModels.getCustomer_name());

            }

            if (assetModels.getAsset_name() != null) {
                edtAssetName.setText(assetModels.getAsset_name());
            }
            if (assetModels.getWarranty() != null && assetModels.getWarranty().equals("Yes")) {
                //rbYes.setChecked(true);
                edtWarrantyDuration.setVisibility(View.VISIBLE);
                edtWarrantyDuration.setText(assetModels.getWarranty_duration());
                //rbNo.setChecked(false);
            } else if (assetModels.getWarranty() != null && assetModels.getWarranty().equals("No")) {
                //rbNo.setChecked(true);
                edtWarrantyDuration.setVisibility(View.GONE);
                //rbYes.setChecked(false);
            }
            if (assetModels.getModel() != null) {
                edtModel.setText(assetModels.getModel());
            }
            if (assetModels.getSerial() != null) {
                edtSerialNo.setText(assetModels.getSerial());
            }
            if (assetModels.getYr_of_manufacture() != null) {
                edtYrOfManufacture.setText(assetModels.getYr_of_manufacture());
            }
            if (assetModels.getManufacturer() != null) {
                edtManufacturer.setText(assetModels.getManufacturer());
            }
            if (assetModels.getContract() != null) {
                edtServiceContract.setText(assetModels.getContract());
            }
            if (assetModels.getAsset_image() != null) {
                Glide.with(getContext()).load(assetModels.getAsset_image()).into(img);
            }
            if (assetModels.getEngineer_remarks() != null) {
                //customerID=assetModel.getCustomer_id();
                edtEngRemarks.setText(assetModels.getEngineer_remarks());

            }
            if (assetModels.getReceiver_comments() != null) {
                edtCustRemarks.setText(assetModels.getReceiver_comments());
            }
            if (assetModels.getInstallation_date() != null) {
                edtDateInst.setText(assetModels.getInstallation_date());
            }
            if (assetModels.getAsset_code() != null) {
                //customerID=assetModel.getCustomer_id();
                edtScan.setText(assetModels.getAsset_code());

            }
            if (assetModels.getAsset_status() != null) {
                //customerID=assetModel.getCustomer_id();
                edtStatus.setText(assetModels.getAsset_status());

            }
        }
    }

    private void setView(Assets assets1) {

        txtTag.setText(assets1.getTag());
        txtSite.setText(assets1.getSite());
        txtCondition.setText(assets1.getCondition());
        txtType.setText(assets1.getType());
        txtSerial.setText("");
        txtInstallitiondate.setText(assets1.getDate());
        txtLastmaintancedate.setText(assets1.getLast_maintenance());
        txtLastmaintainedby.setText(assets1.getLast_maintenance_by());

        txtContract.setText(assets1.getContract());
        txtInstalledby.setText(assets1.getInstalledby());
        txtModel.setText(assets1.getModel());

        if(assets1.getImage().equals("R")){

        }else {
            Glide.with(getContext()).load(assets1.getImage()).into(imageView);
        }



    }
}
