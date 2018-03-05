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
    private ImageView imageView;

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


        imageView=view.findViewById(R.id.asset_image);
        txtInstalledby=view.findViewById(R.id.txt_installation_by);
        txtModel=view.findViewById(R.id.txt_model);
        txtContract=view.findViewById(R.id.txt_service_contract);

        txtTag=view.findViewById(R.id.txt_tag);
        txtType=view.findViewById(R.id.txt_type);
        txtSite=view.findViewById(R.id.txt_site);
        txtSerial=view.findViewById(R.id.txt_serial);
        txtCondition=view.findViewById(R.id.txt_condition);
        txtInstallitiondate=view.findViewById(R.id.txt_installation);
        txtLastmaintancedate=view.findViewById(R.id.txt_last_maintenance);
        txtLastmaintainedby=view.findViewById(R.id.txt_last_maintenance_by);






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
            setView(assets1);
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
