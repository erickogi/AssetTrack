package com.erickogi14gmail.assettrack.Views.V1.Admin.ManageClients;

import android.app.Dialog;
import android.app.SearchManager;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
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
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.erickogi14gmail.assettrack.Adapter.ListAdapter;
import com.erickogi14gmail.assettrack.Data.Models.V1.CustomerModel;
import com.erickogi14gmail.assettrack.Data.Sqlite.DbConstants;
import com.erickogi14gmail.assettrack.Data.Sqlite.DbContentValues;
import com.erickogi14gmail.assettrack.Data.Sqlite.DbOperations;
import com.erickogi14gmail.assettrack.R;
import com.erickogi14gmail.assettrack.Utills.UtilListeners.OnclickRecyclerListener;

import java.util.ArrayList;
import java.util.Objects;

public class FragmentClientList extends Fragment {

    ListAdapter listAdapter;
    String searchtext = "";
    private View view;
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
        Cursor cursor = null;
        cursor = new DbOperations(getContext()).select(DbConstants.TABLE_CLIENT);

        if (cursor != null) {
            ArrayList<CustomerModel> customerModels = new DbContentValues().getClients(cursor);
            if (customerModels != null && customerModels.size() > 0) {
                mStaggeredLayoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
                recyclerView.setLayoutManager(mStaggeredLayoutManager);
                recyclerView.setItemAnimator(new DefaultItemAnimator());


                listAdapter = new ListAdapter(getContext(), customerModels, new OnclickRecyclerListener() {
                    @Override
                    public void onClickListener(int position) {

                    }

                    @Override
                    public void onLongClickListener(int position) {

                    }

                    @Override
                    public void onCheckedClickListener(int position) {
                        int count = 0;
                        if (customerModels.get(position).isChecked()) {
                            customerModels.get(position).setChecked(false);

                            // count--;

                        } else {
                            customerModels.get(position).setChecked(true);

                        }


                        listAdapter.notifyItemChanged(position, customerModels.get(position));


                        for (CustomerModel customerModel : customerModels) {
                            if (customerModel.isChecked()) {
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

                        popupMenu(adapterPosition, view, customerModels.get(adapterPosition));
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

    private void popupMenu(int pos, View view, CustomerModel customerModel) {
        PopupMenu popupMenu = new PopupMenu(Objects.requireNonNull(getContext()), view);
        popupMenu.inflate(R.menu.menu_asset_options);

        popupMenu.getMenu().getItem(3).setVisible(false);
        popupMenu.getMenu().getItem(5).setVisible(false);
        popupMenu.getMenu().getItem(4).setVisible(false);

        popupMenu.setOnMenuItemClickListener(item -> {
            switch (item.getItemId()) {
                case R.id.view:

                    startViewDialog(customerModel);
                    break;
                case R.id.edit:

                    startEditDialog(customerModel);
                    break;
                case R.id.delete:

                    startDeleteDialog(customerModel);
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

    private void startDeleteDialog(CustomerModel customerModel) {
        final DialogInterface.OnClickListener dialogClickListener = (dialog, which) -> {
            switch (which) {
                case DialogInterface.BUTTON_POSITIVE:

                    new DbOperations(getContext()).delete(DbConstants.TABLE_CLIENT, DbConstants.KEY_ID, customerModel.getKEYID());
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


    private void startEditDialog(CustomerModel customerModel) {
        LayoutInflater layoutInflaterAndroid = LayoutInflater.from(getContext());
        View mView = layoutInflaterAndroid.inflate(R.layout.dialog_client_details_edit, null);
        AlertDialog.Builder alertDialogBuilderUserInput = new AlertDialog.Builder(Objects.requireNonNull(getContext()));
        alertDialogBuilderUserInput.setView(mView);
        alertDialogBuilderUserInput.setTitle("Customer Details");

        EditText name, email, phone, address, idd;
        name = mView.findViewById(R.id.txt_customer_name);
        email = mView.findViewById(R.id.txt_customer_email);
        phone = mView.findViewById(R.id.txt_customer_phone_no);
        address = mView.findViewById(R.id.txt_customer_location);
        idd = mView.findViewById(R.id.txt_customer_id);
        // name=mView.findViewById(R.id.txt_customer_name);


        name.setText(customerModel.getName());
        email.setText(customerModel.getEmail());
        address.setText(customerModel.getPhysical_address());
        phone.setText(customerModel.getTelephone());
        idd.setText(customerModel.getId());
        //name.setText(customerModel.getName());


        alertDialogBuilderUserInput
                .setCancelable(false)
                .setPositiveButton("Save", (dialogBox, id) -> {
                    // ToDo get user input here

                    // dialogBox.dismiss();

                })

                .setNegativeButton("Dismiss",
                        (dialogBox, id) -> dialogBox.cancel());

        AlertDialog alertDialogAndroid = alertDialogBuilderUserInput.create();
        alertDialogAndroid.show();

        Button theButton = alertDialogAndroid.getButton(DialogInterface.BUTTON_POSITIVE);
        theButton.setOnClickListener(new CustomListener(alertDialogAndroid, customerModel.getKEYID()));

    }

    private void startViewDialog(CustomerModel customerModel) {
        LayoutInflater layoutInflaterAndroid = LayoutInflater.from(getContext());
        View mView = layoutInflaterAndroid.inflate(R.layout.dialog_client_details, null);
        AlertDialog.Builder alertDialogBuilderUserInput = new AlertDialog.Builder(Objects.requireNonNull(getContext()));
        alertDialogBuilderUserInput.setView(mView);
        alertDialogBuilderUserInput.setTitle("Customer Details");

        TextView name, email, phone, address, idd;
        name = mView.findViewById(R.id.txt_customer_name);
        email = mView.findViewById(R.id.txt_customer_email);
        phone = mView.findViewById(R.id.txt_customer_phone_no);
        address = mView.findViewById(R.id.txt_customer_location);
        idd = mView.findViewById(R.id.txt_customer_id);
        // name=mView.findViewById(R.id.txt_customer_name);


        name.setText(customerModel.getName());
        email.setText(customerModel.getEmail());
        address.setText(customerModel.getPhysical_address());
        phone.setText(customerModel.getTelephone());
        idd.setText(customerModel.getId());
        //name.setText(customerModel.getName());


        alertDialogBuilderUserInput
                .setCancelable(false)
                .setPositiveButton("Dismiss", (dialogBox, id) -> {
                    // ToDo get user input here

                    // dialogBox.dismiss();

                });

//                .setNegativeButton("Dismiss",
//                        (dialogBox, id) -> dialogBox.cancel());

        AlertDialog alertDialogAndroid = alertDialogBuilderUserInput.create();
        alertDialogAndroid.show();


    }

    public void refresh() {
        if (listAdapter != null) {
            listAdapter.notifyDataSetChanged();
            initUI();
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

    class CustomListener implements View.OnClickListener {
        private final Dialog dialog;
        private final int keyid;


        public CustomListener(Dialog dialog, int keyid) {
            this.dialog = dialog;
            this.keyid = keyid;

        }

        @Override
        public void onClick(View v) {
            EditText name, email, phone, address, idd;
            name = dialog.findViewById(R.id.txt_customer_name);
            email = dialog.findViewById(R.id.txt_customer_email);
            phone = dialog.findViewById(R.id.txt_customer_phone_no);
            address = dialog.findViewById(R.id.txt_customer_location);
            idd = dialog.findViewById(R.id.txt_customer_id);
            // name=mView.findViewById(R.id.txt_customer_name);


            if (name.getText().toString().isEmpty()) {
                name.setError("Required");
                name.requestFocus();
                return;
            }

            if (email.getText().toString().isEmpty()) {
                email.setError("Required");
                email.requestFocus();
                return;
            }

            if (phone.getText().toString().isEmpty()) {
                phone.setError("Required");
                phone.requestFocus();
                return;
            }

            if (address.getText().toString().isEmpty()) {
                address.setError("Required");
                address.requestFocus();
                return;
            }
            if (idd.getText().toString().isEmpty()) {
                idd.setError("Required");
                idd.requestFocus();
                return;
            }


            CustomerModel customerModel = new CustomerModel();
            customerModel.setName(name.getText().toString());
            customerModel.setId(idd.getText().toString());
            customerModel.setPhysical_address(address.getText().toString());
            customerModel.setEmail(email.getText().toString());
            customerModel.setAddress(email.getText().toString());
            customerModel.setTelephone(phone.getText().toString());

            ContentValues contentValues = new ContentValues();
            contentValues.put(DbConstants.cust_name, customerModel.getName());
            contentValues.put(DbConstants.cust_id, customerModel.getName());
            contentValues.put(DbConstants.cust_email, customerModel.getEmail());
            contentValues.put(DbConstants.cust_address, customerModel.getEmail());
            contentValues.put(DbConstants.cust_physical_address, customerModel.getPhysical_address());
            contentValues.put(DbConstants.cust_telephone, customerModel.getTelephone());


            if (new DbOperations(getActivity()).update(DbConstants.TABLE_CLIENT, DbConstants.KEY_ID, keyid, contentValues)) {
                dialog.dismiss();
            }

        }


    }
}
