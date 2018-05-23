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

import com.erickogi14gmail.assettrack.Data.Models.V1.EngineerModel;
import com.erickogi14gmail.assettrack.Data.Sqlite.DbConstants;
import com.erickogi14gmail.assettrack.Data.Sqlite.DbOperations;
import com.erickogi14gmail.assettrack.R;
import com.erickogi14gmail.assettrack.Views.V1.Admin.ManageEngineers.FragmentEngineerList;

import java.util.Objects;

public class ActivityManageEngineers extends AppCompatActivity {
    Fragment fragment = null;
    FragmentEngineerList fragmentEngineerList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_engineers);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startEditDialog();
            }
        });

        fragment = new FragmentEngineerList();
        Bundle bundle = new Bundle();
        bundle.putInt("type", 0);

        fragment.setArguments(bundle);
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.frame_layout, fragment, "fragmentMain").commit();
    }


    private void startEditDialog() {
        LayoutInflater layoutInflaterAndroid = LayoutInflater.from(this);
        View mView = layoutInflaterAndroid.inflate(R.layout.dialog_engineer_details_edit, null);
        AlertDialog.Builder alertDialogBuilderUserInput = new AlertDialog.Builder(Objects.requireNonNull(this));
        alertDialogBuilderUserInput.setView(mView);
        alertDialogBuilderUserInput.setTitle("Engineers Details");

        EditText name, sname, email, phone, speciality, idd;
        name = mView.findViewById(R.id.txt_eng_first_name);
        sname = mView.findViewById(R.id.txt_eng_last_name);
        email = mView.findViewById(R.id.txt_eng_email);
        phone = mView.findViewById(R.id.txt_eng_phone_no);
        speciality = mView.findViewById(R.id.txt_eng_speciality);
        idd = mView.findViewById(R.id.txt_eng_id);
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


            EditText name, sname, email, phone, speciality, idd;
            name = dialog.findViewById(R.id.txt_eng_first_name);
            sname = dialog.findViewById(R.id.txt_eng_last_name);
            email = dialog.findViewById(R.id.txt_eng_email);
            phone = dialog.findViewById(R.id.txt_eng_phone_no);
            speciality = dialog.findViewById(R.id.txt_eng_speciality);
            idd = dialog.findViewById(R.id.txt_eng_id);


            if (name.getText().toString().isEmpty()) {
                name.setError("Required");
                name.requestFocus();
                return;
            }


            if (sname.getText().toString().isEmpty()) {
                sname.setError("Required");
                sname.requestFocus();
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

            if (speciality.getText().toString().isEmpty()) {
                speciality.setError("Required");
                speciality.requestFocus();
                return;
            }
            if (idd.getText().toString().isEmpty()) {
                idd.setError("Required");
                idd.requestFocus();
                return;
            }


            EngineerModel engineerModel = new EngineerModel();

            engineerModel.setName(name.getText().toString());
            engineerModel.setId(idd.getText().toString());
            engineerModel.setSpeciality(speciality.getText().toString());
            engineerModel.setEmail(email.getText().toString());
            engineerModel.setLast_name(sname.getText().toString());
            engineerModel.setPhone(phone.getText().toString());
            engineerModel.setFirst_name(name.getText().toString());

            ContentValues contentValues = new ContentValues();
            contentValues.put(DbConstants.name, engineerModel.getFirst_name() + " " + engineerModel.getLast_name());

            contentValues.put(DbConstants.id, engineerModel.getId());
            contentValues.put(DbConstants.first_name, engineerModel.getFirst_name());
            contentValues.put(DbConstants.last_name, engineerModel.getLast_name());
            contentValues.put(DbConstants.email, engineerModel.getEmail());
            contentValues.put(DbConstants.speciality, engineerModel.getSpeciality());

            contentValues.put(DbConstants.phone, engineerModel.getPhone());


            if (new DbOperations(ActivityManageEngineers.this).insert(DbConstants.TABLE_ENG_V1, contentValues)) {
                dialog.dismiss();

                if (fragmentEngineerList == null) {
                    fragmentEngineerList = (FragmentEngineerList) getSupportFragmentManager().findFragmentByTag("fragmentMain");
                }
                fragmentEngineerList.refresh();
            }

        }


    }
}
