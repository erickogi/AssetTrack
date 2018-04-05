package com.erickogi14gmail.assettrack.Views.V1.InstallationSteps;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.erickogi14gmail.assettrack.Data.Models.V1.CustomerModel;
import com.erickogi14gmail.assettrack.R;
import com.stepstone.stepper.BlockingStep;
import com.stepstone.stepper.StepperLayout;
import com.stepstone.stepper.VerificationError;

import java.util.Calendar;
import java.util.LinkedList;
import java.util.Objects;

public class InstallationStepOne extends Fragment implements BlockingStep,DialogSearchCustomer.DialogSearchListener  {

    private View view;
    private Button btnSelectCustomer;
    private TextInputEditText edtAssetName,edtWarranty,edtWarrantyDuration,edtModel,edtSerialNo,edtManufacturer,edtYrOfManufacture,edtServiceContract;

    private TextInputLayout tilWarrantyDuration;
    private RadioGroup rgWarranty;
    private RadioButton rbYes,rbNo;

    private String customerID=null;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_installation_one,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.view=view;
        initUI(view);
    }
    void  initUI(View view){
        btnSelectCustomer=view.findViewById(R.id.btn_customer);
        edtAssetName=view.findViewById(R.id.edt_asset_name);
        tilWarrantyDuration=view.findViewById(R.id.til_Warranty_duration);
        //edtWarranty=view.findViewById(R.id.edt_warranty);
        edtWarrantyDuration=view.findViewById(R.id.edt_warranty_duration);
        edtModel=view.findViewById(R.id.edt_model);
        edtSerialNo=view.findViewById(R.id.edt_serial);
        edtManufacturer=view.findViewById(R.id.edt_manufacturer);
        edtYrOfManufacture=view.findViewById(R.id.edt_manufacture_yr);
        edtServiceContract=view.findViewById(R.id.edt_contact);

        rgWarranty=view.findViewById(R.id.rgrp_warranty);
        rbNo=view.findViewById(R.id.rbtn_warranty_no);
        rbYes=view.findViewById(R.id.rbtn_warranty_yes);



        UiActions();
    }
    LinkedList<CustomerModel> getCustomers(){
        LinkedList<CustomerModel> customerModels=new LinkedList<>();

        for(int a=0;a<10;a++){
            CustomerModel c=new CustomerModel();
            c.setId(String.valueOf(a));
            c.setName("Customer "+String.valueOf(a));
            c.setPhysical_address("Address "+String.valueOf(a));

            customerModels.add(c);

        }
        return customerModels;
    }
    void UiActions(){
        btnSelectCustomer.setOnClickListener(view ->

                dialogSelectCustomer(getCustomers()));

        edtYrOfManufacture.setOnClickListener(this::onClick);


        rgWarranty.setOnCheckedChangeListener((radioGroup, i) -> {
            if(i==R.id.rbtn_warranty_yes){
                tilWarrantyDuration.setVisibility(View.VISIBLE);
            }else if(i==R.id.rbtn_warranty_no){
                tilWarrantyDuration.setVisibility(View.GONE);
            }
        });
    }

    private void dialogSelectCustomer(LinkedList<CustomerModel> customerModels) {
        FragmentManager fm = getFragmentManager();
        DialogSearchCustomer dialogSearch = DialogSearchCustomer.newInstance("Clients", 1, customerModels);
        // dialogSearch.show(fm,"dialog");

        dialogSearch.setTargetFragment(InstallationStepOne.this, 300);
        dialogSearch.show(fm, "fragment_search");


    }
    public void show()
    {

        //final Dialog d = new Dialog(Objects.requireNonNull(getContext()),R.style.cust_dialog);
        final Dialog d = new Dialog(Objects.requireNonNull(getContext()));
        d.setTitle("Year Of Manufacture");

        d.setContentView(R.layout.dialog);
        Button b1 = d.findViewById(R.id.button1);
        Button b2 = d.findViewById(R.id.button2);
        final NumberPicker np = d.findViewById(R.id.numberPicker1);


        np.setMaxValue( Calendar.getInstance().get(Calendar.YEAR));
        np.setMinValue(1900);
        np.setValue(Calendar.getInstance().get(Calendar.YEAR));
        np.setWrapSelectorWheel(false);
        np.setOnValueChangedListener((numberPicker, i, i1) -> {

        });
        b1.setOnClickListener(v -> {
            edtYrOfManufacture.setText(String.valueOf(np.getValue()));
            //tv.setText(String.valueOf(np.getValue()));
            d.dismiss();
        });
        b2.setOnClickListener(v -> d.dismiss());
        d.show();


    }

    @Override
    public void onNextClicked(StepperLayout.OnNextClickedCallback callback) {

        callback.goToNextStep();
    }

    @Override
    public void onCompleteClicked(StepperLayout.OnCompleteClickedCallback callback) {

        callback.complete();
    }

    @Override
    public void onBackClicked(StepperLayout.OnBackClickedCallback callback) {

        callback.goToPrevStep();
    }

    @Nullable
    @Override
    public VerificationError verifyStep() {
        if(verify()) {
            return null;
        }else {
            return new VerificationError("Fill all fields");
        }
    }

    @Override
    public void onSelected() {

        initUI(view);
    }

    @Override
    public void onError(@NonNull VerificationError error) {

    }

    @Override
    public void onSelected(CustomerModel model) {
        customerID=model.getId();
        btnSelectCustomer.setText(model.getName());
    }

    private void onClick(View view) {
        show();
    }
    private boolean verify(){
        return isTextInputEditTextFilled(edtAssetName)
                && isRadioGroupChecked(rgWarranty)
                && isTextInputEditTextFilled(edtModel)
                && isTextInputEditTextFilled(edtSerialNo)
                && isTextInputEditTextFilled(edtManufacturer)
                && isTextInputEditTextFilled(edtYrOfManufacture)
                && isTextInputEditTextFilled(edtServiceContract)
                && isStringValid(customerID);
    }
    private boolean isStringValid(String s){
        if(s==null){
            Snackbar.make(view, "Select Customer", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
            return false;
        }
        return true;
    }

    private boolean isTextInputEditTextFilled(TextInputEditText t){
        if(TextUtils.isEmpty(t.getText())){
            t.requestFocus();
            t.setError("Required");
            return false;
        }
        return true;
    }
    private boolean isRadioGroupChecked(RadioGroup r){
        if(r.getCheckedRadioButtonId()==-1){

            Snackbar.make(r, "Select an option for warranty", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
            return false;
        }
        else {
            return rgWarranty.getCheckedRadioButtonId() == R.id.rbtn_warranty_yes && isTextInputEditTextFilled(edtWarrantyDuration);
        }
    }
}