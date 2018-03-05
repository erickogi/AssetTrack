package com.erickogi14gmail.assettrack.Views;

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
import android.widget.ImageView;

import com.erickogi14gmail.assettrack.Adapter.AssetsListAdapter;
import com.erickogi14gmail.assettrack.Data.Models.Assets;
import com.erickogi14gmail.assettrack.Data.Sqlite.DbConstants;
import com.erickogi14gmail.assettrack.Data.Sqlite.DbContentValues;
import com.erickogi14gmail.assettrack.Data.Sqlite.DbOperations;
import com.erickogi14gmail.assettrack.R;
import com.erickogi14gmail.assettrack.Utills.UtilListeners.OnclickRecyclerListener;

import java.util.ArrayList;

public class AssetList extends AppCompatActivity {
    private RecyclerView recyclerView;
    private DbOperations dbOperations ;
    private AssetsListAdapter assetsListAdapter;
    private ArrayList<Assets> assets=new ArrayList<>();
    private StaggeredGridLayoutManager mStaggeredLayoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asset_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
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
        ArrayList<Assets> assets=new ArrayList<>();
        Cursor cursor=dbOperations.select(DbConstants.TABLE_ITEMS);
        if(cursor!=null){
            assets=dbContentValues.getAssets(cursor);

            assetsListAdapter=new AssetsListAdapter(AssetList.this, assets, new OnclickRecyclerListener() {
                @Override
                public void onClickListener(int position) {

                }

                @Override
                public void onLongClickListener(int position) {

                }

                @Override
                public void onClickListener(int adapterPosition, ImageView imageView) {

                }
            });

            assetsListAdapter.notifyDataSetChanged();
            recyclerView.setAdapter(assetsListAdapter);


        }

    }

}
