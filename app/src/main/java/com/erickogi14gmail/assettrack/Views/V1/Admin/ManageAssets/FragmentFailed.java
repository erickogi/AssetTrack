package com.erickogi14gmail.assettrack.Views.V1.Admin.ManageAssets;

import android.app.SearchManager;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.erickogi14gmail.assettrack.Adapter.AdminAssetListAdapter;
import com.erickogi14gmail.assettrack.Data.Models.V1.AssetModel;
import com.erickogi14gmail.assettrack.Data.Sqlite.DbConstants;
import com.erickogi14gmail.assettrack.Data.Sqlite.DbContentValues;
import com.erickogi14gmail.assettrack.Data.Sqlite.DbOperations;
import com.erickogi14gmail.assettrack.GLConstants;
import com.erickogi14gmail.assettrack.R;
import com.erickogi14gmail.assettrack.Utills.UtilListeners.OnclickRecyclerListener;

import java.util.ArrayList;
import java.util.Objects;

public class FragmentFailed extends Fragment {
    AdminAssetListAdapter listAdapter;
    String searchtext = "";
    private View view;
    private EditText edtSearch;
    private SearchView search;
    private RecyclerView recyclerView;
    private StaggeredGridLayoutManager mStaggeredLayoutManager;
    private ActionMode mActionMode;
    private ActionMode.Callback callback = new ActionMode.Callback() {
        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            MenuInflater menuInflater = mode.getMenuInflater();
            menuInflater.inflate(R.menu.menu_asset_options_multi, menu);
            return true;
        }

        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            return false;
        }

        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
            switch (item.getItemId()) {
                case R.id.delete:

                    mode.finish();
                    break;
                case R.id.update_status:

                    mode.finish();

                    break;
                case R.id.share:


                    mode.finish();

                    break;
                default:
                    return false;
            }
            return false;
        }

        @Override
        public void onDestroyActionMode(ActionMode mode) {

            mActionMode = null;
        }
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_admin_assets, container, false);
        //  Log.d("Loohj","in onCreateview");
        //  return super.onCreateView(inflater, container, savedInstanceState);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.view = view;

        search = view.findViewById(R.id.search_bar);
        edtSearch = view.findViewById(R.id.edt_search);

        initUI();
        initSearchView();


    }

    private void initSearchView() {

        try {
            SearchManager manager = (SearchManager) Objects.requireNonNull(getActivity()).getSystemService(Context.SEARCH_SERVICE);


            search.setOnClickListener(v -> search.setIconified(false));
            search.setQueryHint("Search asset list");

            if (manager != null) {
                search.setSearchableInfo(manager.getSearchableInfo(getActivity().getComponentName()));
            }

            search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {


                @Override
                public boolean onQueryTextSubmit(String query) {


                    return false;
                }

                @Override
                public boolean onQueryTextChange(String newText) {


                    if (!newText.isEmpty()) {

                        try {
                            new Thread(() -> {

                            }).start();


                        } catch (Exception nm) {

                        }
                        searchtext = newText;

                    } else {

                        searchtext = "";

                    }


                    return false;
                }
            });

        } catch (Exception nm) {

            edtSearch.setVisibility(View.VISIBLE);
            edtSearch.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    String newText = edtSearch.getText().toString();
                    if (!newText.isEmpty()) {


                        searchtext = newText;

                    } else {

                        searchtext = "";

                    }

                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });

        }
    }

    private void initUI() {


        recyclerView = view.findViewById(R.id.recyclerView);
        Cursor cursor = new DbOperations(getContext()).select(DbConstants.TABLE_ITEMS_V1, DbConstants.ASSET_STATUS_ID, GLConstants.FAULTY);
        if (cursor != null) {
            Log.d("Loohj", "cursor is  not null");
            ArrayList<AssetModel> assetModels = new DbContentValues().getAssetsv1
                    (cursor);
            if (assetModels != null && assetModels.size() > 0) {
                Log.d("Loohj", "assetmodels is  nit null" + assetModels.size());
                mStaggeredLayoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
                recyclerView.setLayoutManager(mStaggeredLayoutManager);
                recyclerView.setItemAnimator(new DefaultItemAnimator());


                listAdapter = new AdminAssetListAdapter(getContext(), assetModels, 1, new OnclickRecyclerListener() {
                    @Override
                    public void onClickListener(int position) {

                    }

                    @Override
                    public void onLongClickListener(int position) {

                    }

                    @Override
                    public void onCheckedClickListener(int position) {
                        int count = 0;
                        if (assetModels.get(position).isChecked()) {
                            assetModels.get(position).setChecked(false);

                            // count--;

                        } else {
                            assetModels.get(position).setChecked(true);

                        }


                        listAdapter.notifyItemChanged(position, assetModels.get(position));


                        for (AssetModel assetModel : assetModels) {
                            if (assetModel.isChecked()) {
                                count++;

                            }
                        }

                        if (count > 0) {
                            mActionMode = Objects.requireNonNull(getActivity()).startActionMode(callback);
                        } else {
                            if (mActionMode != null) {
                                mActionMode.finish();
                            }
                        }

                    }

                    @Override
                    public void onMoreClickListener(int position) {

                    }

                    @Override
                    public void onClickListener(int adapterPosition, View view) {

                        popupMenu(adapterPosition, view);
                    }
                });
                // listAdapter.notifyDataSetChanged();

                recyclerView.setAdapter(listAdapter);
                listAdapter.notifyDataSetChanged();

            } else {
                Log.d("Loohj", "assetmodels is null");

            }
        } else {
            Log.d("Loohj", "cursor is null");
        }
    }

    private void popupMenu(int pos, View view) {
        PopupMenu popupMenu = new PopupMenu(Objects.requireNonNull(getContext()), view);
        popupMenu.inflate(R.menu.menu_asset_options);
        popupMenu.setOnMenuItemClickListener(item -> {
            switch (item.getItemId()) {
                case R.id.view:

                    break;
                case R.id.edit:

                    break;
                case R.id.delete:

                    break;

                case R.id.assign:

                    break;
                case R.id.share:

                    break;
                case R.id.update_status:

                    break;
            }
            return false;
        });
        popupMenu.show();
    }

}
