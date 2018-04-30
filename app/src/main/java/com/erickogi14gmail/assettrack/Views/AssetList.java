package com.erickogi14gmail.assettrack.Views;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.erickogi14gmail.assettrack.Adapter.AssetsListAdapter;
import com.erickogi14gmail.assettrack.Data.Models.Assets;
import com.erickogi14gmail.assettrack.Data.Models.V1.AssetModel;
import com.erickogi14gmail.assettrack.Data.Sqlite.DbConstants;
import com.erickogi14gmail.assettrack.Data.Sqlite.DbContentValues;
import com.erickogi14gmail.assettrack.Data.Sqlite.DbOperations;
import com.erickogi14gmail.assettrack.R;
import com.erickogi14gmail.assettrack.Utills.Constants;
import com.erickogi14gmail.assettrack.Utills.UtilListeners.OnclickRecyclerListener;

import java.util.ArrayList;

public class AssetList extends AppCompatActivity {
    private RecyclerView recyclerView;
    private DbOperations dbOperations ;
    private AssetsListAdapter assetsListAdapter;
    private ArrayList<Assets> assets=new ArrayList<>();
    ArrayList<AssetModel> assetss = new ArrayList<>();
    private StaggeredGridLayoutManager mStaggeredLayoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asset_list);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        fab.setVisibility(View.GONE);

        dbOperations=new DbOperations(AssetList.this);
        recyclerView=findViewById(R.id.recyclerView);
        mStaggeredLayoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);

        recyclerView.setLayoutManager(mStaggeredLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        DbContentValues dbContentValues=new DbContentValues();

        Cursor cursor = dbOperations.select(DbConstants.TABLE_ITEMS_V1);
        if(cursor!=null){
            assetss = dbContentValues.getAssetsv1(cursor);

            assetsListAdapter = new AssetsListAdapter(AssetList.this, assetss, new OnclickRecyclerListener() {
                @Override
                public void onClickListener(int position) {


                    Constants.id = String.valueOf(assetss.get(position).getKEYID());
                    Constants.assetModel = assetss.get(position);
                    startActivity(new Intent(AssetList.this, AssetActivity.class));


                }

                @Override
                public void onLongClickListener(int position) {

                }

                @Override
                public void onClickListener(int adapterPosition, View view) {

                }

                @Override
                public void onCheckedClickListener(int position) {

                }

                @Override
                public void onMoreClickListener(int position) {

                }
            });

            assetsListAdapter.notifyDataSetChanged();
            recyclerView.setAdapter(assetsListAdapter);


        }

    }

}
