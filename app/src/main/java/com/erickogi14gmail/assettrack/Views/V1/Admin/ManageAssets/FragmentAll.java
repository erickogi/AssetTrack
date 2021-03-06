package com.erickogi14gmail.assettrack.Views.V1.Admin.ManageAssets;

import android.app.SearchManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
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
import android.widget.LinearLayout;
import android.widget.TextView;

import com.erickogi14gmail.assettrack.Adapter.AdminAssetListAdapter;
import com.erickogi14gmail.assettrack.Data.Models.V1.AssetModel;
import com.erickogi14gmail.assettrack.Data.Sqlite.DbConstants;
import com.erickogi14gmail.assettrack.Data.Sqlite.DbContentValues;
import com.erickogi14gmail.assettrack.Data.Sqlite.DbOperations;
import com.erickogi14gmail.assettrack.GLConstants;
import com.erickogi14gmail.assettrack.R;
import com.erickogi14gmail.assettrack.Utills.Constants;
import com.erickogi14gmail.assettrack.Utills.UtilListeners.OnclickRecyclerListener;
import com.erickogi14gmail.assettrack.Views.AssetActivity;
import com.erickogi14gmail.assettrack.Views.V1.Installation;

import java.util.ArrayList;
import java.util.Objects;

public class FragmentAll extends Fragment {
    AdminAssetListAdapter listAdapter;
    private View view;
    String searchtext = "";
    private EditText edtSearch;
    private LinearLayout linearLayoutEmpty;
    private SearchView search;
    private RecyclerView recyclerView;
    private StaggeredGridLayoutManager mStaggeredLayoutManager;
    private ActionMode mActionMode;
    private int STATUS_ID;
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
        int STATUS_ID = GLConstants.WORKING;
        if (getArguments() != null) {
            STATUS_ID = getArguments().getInt("STATUS_ID");
        }
        initUI(STATUS_ID);
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

    private void initUI(int status) {


        recyclerView = view.findViewById(R.id.recyclerView);
        Cursor cursor = null;
        if (status == 0) {
            cursor = new DbOperations(getContext()).select(DbConstants.TABLE_ITEMS_V1);
        } else {
            cursor = new DbOperations(getContext()).select(DbConstants.TABLE_ITEMS_V1, DbConstants.ASSET_STATUS_ID, status);

        }
        if (cursor != null) {
            ArrayList<AssetModel> assetModels = new DbContentValues().getAssetsv1
                    (cursor);
            if (assetModels != null && assetModels.size() > 0) {
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

                        popupMenu(adapterPosition, assetModels.get(adapterPosition), view);
                    }
                });
                // listAdapter.notifyDataSetChanged();

                recyclerView.setAdapter(listAdapter);
                listAdapter.notifyDataSetChanged();

                setEmptyState(listAdapter.getItemCount() < 1);


            } else {
                Log.d("Loohj", "assetmodels is null");

            }
        } else {
            Log.d("Loohj", "cursor is null");
        }
    }

    private void popupMenu(int pos, AssetModel assetModel, View view) {
        PopupMenu popupMenu = new PopupMenu(Objects.requireNonNull(getContext()), view);
        popupMenu.inflate(R.menu.menu_asset_options);
        if (STATUS_ID == GLConstants.WORKING) {
            popupMenu.getMenu().getItem(3).setVisible(false);
        }
        if (STATUS_ID == GLConstants.FAULTY) {
            // popupMenu.getMenu().getItem(3).setVisible(false);
        }
        if (STATUS_ID == GLConstants.WRITTEN_OFF) {
            popupMenu.getMenu().getItem(1).setVisible(false);
            popupMenu.getMenu().getItem(3).setVisible(false);
            popupMenu.getMenu().getItem(5).setVisible(false);
        }
        popupMenu.setOnMenuItemClickListener(item -> {
            switch (item.getItemId()) {
                case R.id.view:
                    Constants.id = String.valueOf(assetModel.getKEYID());
                    Constants.assetModel = assetModel;
                    startActivity(new Intent(getActivity(), AssetActivity.class));


                    break;
                case R.id.edit:
                    GLConstants.assetModel = assetModel;

                    startActivity(new Intent(getActivity(), Installation.class));



                    break;
                case R.id.delete:

                    startDeleteDialog(assetModel);



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

    private void startDeleteDialog(AssetModel assetModel) {
        final DialogInterface.OnClickListener dialogClickListener = (dialog, which) -> {
            switch (which) {
                case DialogInterface.BUTTON_POSITIVE:

                    new DbOperations(getContext()).delete(DbConstants.TABLE_CLIENT, DbConstants.KEY_ID, assetModel.getKEYID());
                    listAdapter.notifyDataSetChanged();
                    break;
                case DialogInterface.BUTTON_NEGATIVE:
                    dialog.dismiss();

                    break;
            }
        };


        if (getContext() != null) {

            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

            builder.setMessage("You are about to delete this item ").setPositiveButton("Delete", dialogClickListener)
                    .setNegativeButton("Dismiss", dialogClickListener)
                    .show();
        }

    }

    private void setEmptyState(Boolean b) {
        LinearLayout linearLayoutEmpty2 = view.findViewById(R.id.empty_layout);
        TextView txt_empty = view.findViewById(R.id.empty_view);
        if (b) {
            linearLayoutEmpty2.setVisibility(View.VISIBLE);
            //         linearLayoutEmpty.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
            txt_empty.setText("No assets found");
            // swipe_refresh_layout.setRefreshing(true);


            //   txt_empty.setText("Couldn't load beneficiaries");

            Log.d("revisi", "recyclerinvisible");
        } else {
            txt_empty.setText("No assets found");

//            linearLayoutEmpty.setVisibility(View.GONE);
            // swipe_refresh_layout.setRefreshing(false);
            linearLayoutEmpty2.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);

            Log.d("revisi", "recyclervisible");
        }
    }
}
