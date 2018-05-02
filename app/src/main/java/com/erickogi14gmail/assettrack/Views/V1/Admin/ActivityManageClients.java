package com.erickogi14gmail.assettrack.Views.V1.Admin;

import android.app.Dialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.erickogi14gmail.assettrack.Data.Models.V1.CustomerModel;
import com.erickogi14gmail.assettrack.Data.Sqlite.DbConstants;
import com.erickogi14gmail.assettrack.Data.Sqlite.DbOperations;
import com.erickogi14gmail.assettrack.R;
import com.erickogi14gmail.assettrack.Views.V1.Admin.ManageClients.FragmentClientList;

import java.util.Objects;

public class ActivityManageClients extends AppCompatActivity {
    Fragment fragment = null;
    FragmentClientList fragmentClientList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_clients);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startEditDialog();
            }
        });


        fragment = new FragmentClientList();
        Bundle bundle = new Bundle();
        bundle.putInt("type", 0);

        fragment.setArguments(bundle);
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.frame_layout, fragment, "fragmentMain").commit();

    }

    private void startEditDialog() {
        LayoutInflater layoutInflaterAndroid = LayoutInflater.from(this);
        View mView = layoutInflaterAndroid.inflate(R.layout.dialog_client_details_edit, null);
        AlertDialog.Builder alertDialogBuilderUserInput = new AlertDialog.Builder(Objects.requireNonNull(this));
        alertDialogBuilderUserInput.setView(mView);
        alertDialogBuilderUserInput.setTitle("Customer Details");

        EditText name, email, phone, address, idd;
        name = mView.findViewById(R.id.txt_customer_name);
        email = mView.findViewById(R.id.txt_customer_email);
        phone = mView.findViewById(R.id.txt_customer_phone_no);
        address = mView.findViewById(R.id.txt_customer_location);
        idd = mView.findViewById(R.id.txt_customer_id);
        // name=mView.findViewById(R.id.txt_customer_name);


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
        theButton.setOnClickListener(new CustomListener(alertDialogAndroid));

    }

    class CustomListener implements View.OnClickListener {
        private final Dialog dialog;


        public CustomListener(Dialog dialog) {
            this.dialog = dialog;


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
            contentValues.put(DbConstants.cust_id, customerModel.getId());
            contentValues.put(DbConstants.cust_email, customerModel.getEmail());
            contentValues.put(DbConstants.cust_address, customerModel.getAddress());
            contentValues.put(DbConstants.cust_physical_address, customerModel.getPhysical_address());
            contentValues.put(DbConstants.cust_telephone, customerModel.getTelephone());


            if (new DbOperations(ActivityManageClients.this).insert(DbConstants.TABLE_CLIENT, contentValues)) {
                dialog.dismiss();

                if (fragmentClientList == null) {
                    fragmentClientList = (FragmentClientList) getSupportFragmentManager().findFragmentByTag("fragmentMain");
                }
                fragmentClientList.refresh();
            }

        }


    }

}
