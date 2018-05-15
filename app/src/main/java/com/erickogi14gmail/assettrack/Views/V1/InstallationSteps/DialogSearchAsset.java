package com.erickogi14gmail.assettrack.Views.V1.InstallationSteps;

import android.app.SearchManager;
import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import com.erickogi14gmail.assettrack.Adapter.CustomerSearchAdapter;
import com.erickogi14gmail.assettrack.Data.Models.V1.CustomerModel;
import com.erickogi14gmail.assettrack.R;
import com.erickogi14gmail.assettrack.Utills.RecyclerTouchListener;

import java.util.ArrayList;

/**
 * Created by Eric on 11/30/2017.
 */

public class DialogSearchAsset extends android.support.v4.app.DialogFragment {


    private RecyclerView recyclerView;
    private StaggeredGridLayoutManager mStaggeredLayoutManager;
    private CustomerSearchAdapter customerSearchAdapter;
    private int type = 1;

    private ArrayList<CustomerModel> customerModelsFilterd;
    private ArrayList<CustomerModel> customerModelsFull;

    public DialogSearchAsset() {
        // Empty constructor is required for DialogFragment
        // Make sure not to add arguments to the constructor
        // Use `newInstance` instead as shown below
    }

    public static DialogSearchAsset newInstance(String title, int type, ArrayList<CustomerModel> customerModels) {
        DialogSearchAsset frag = new DialogSearchAsset();
        Bundle args = new Bundle();
        // args.putSerializable("data", customerModels);
        args.putString("title", title);
        args.putInt("type", type);
        args.putSerializable("data", customerModels);
        frag.setArguments(args);
        return frag;
    }

    public void sendBackResult(CustomerModel model) {
        // Notice the use of `getTargetFragment` which will be set when the dialog is displayed
        DialogSearchAsset.DialogSearchListener listener = (DialogSearchAsset.DialogSearchListener) getTargetFragment();
        listener.onSelected(model);
        dismiss();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Rect displayRectangle = new Rect();
        Window window = getActivity().getWindow();
        window.getDecorView().getWindowVisibleDisplayFrame(displayRectangle);
        View view = inflater.inflate(R.layout.dialog_search_item, container);
        view.setMinimumWidth((int) (displayRectangle.width() * 0.8f));
        view.setMinimumHeight((int) (displayRectangle.height() * 0.8f));
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        type = getArguments().getInt("type");

        // Get field from view
        recyclerView = view.findViewById(R.id.recycler_view);
        customerModelsFull = (ArrayList<CustomerModel>) getArguments().getSerializable("data");
        for (CustomerModel customerModel : customerModelsFull) {
            Log.d("dla", "" + customerModel.getName());
        }


        customerModelsFilterd = customerModelsFull;


        customerSearchAdapter = new CustomerSearchAdapter(getContext(), customerModelsFilterd);

        customerSearchAdapter.notifyDataSetChanged();
        mStaggeredLayoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(mStaggeredLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(customerSearchAdapter);

        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getActivity(), recyclerView,
                new RecyclerTouchListener.ClickListener() {
                    @Override
                    public void onClick(View view, int position) {

                        sendBackResult(customerModelsFilterd.get(position));
                    }

                    @Override
                    public void onLongClick(View view, int position) {

                    }
                }));


        // Fetch arguments from bundle and set title
        String title = getArguments().getString("title", "Search");
        getDialog().setTitle(title);
        // if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {

        SearchManager manager = (SearchManager) getActivity().getSystemService(Context.SEARCH_SERVICE);

        final SearchView search = view.findViewById(R.id.search_bar);
        search.setOnClickListener(v -> search.setIconified(false));

        search.setSearchableInfo(manager.getSearchableInfo(getActivity().getComponentName()));

        search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {


            @Override
            public boolean onQueryTextSubmit(String query) {


                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                try {

                    Log.d("hereisdata", newText);

                    customerModelsFilterd.clear();
                    //  Log.d("TRW",customerModelsFull.get(0).getName());

                    for (CustomerModel customerModel : (ArrayList<CustomerModel>) getArguments().getParcelable("data")) {
                        Log.d("hersdata", customerModel.getName());
                        if (customerModel.getName().toLowerCase().contains(newText.toLowerCase()) || customerModel.getPhysical_address().toLowerCase().contains(newText.toLowerCase())) {
                            customerModelsFilterd.add(customerModel);
                            Log.d("listfilterAdded", customerModel.getName());
                        } else {
                            Log.d("unfound", customerModel.getName());
                        }
                    }
                    customerSearchAdapter.updateList(customerModelsFilterd);


                } catch (Exception m) {

                    m.printStackTrace();
                }
                return false;
            }
        });

        //}
    }

    public interface DialogSearchListener {
        void onSelected(CustomerModel model);
    }
}