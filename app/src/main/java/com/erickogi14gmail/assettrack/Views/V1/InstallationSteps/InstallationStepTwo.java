package com.erickogi14gmail.assettrack.Views.V1.InstallationSteps;

import android.app.Dialog;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.erickogi14gmail.assettrack.Data.Models.V1.AccessoriesModel;
import com.erickogi14gmail.assettrack.Data.Models.V1.AssetModel;
import com.erickogi14gmail.assettrack.GLConstants;
import com.erickogi14gmail.assettrack.R;
import com.stepstone.stepper.BlockingStep;
import com.stepstone.stepper.StepperLayout;
import com.stepstone.stepper.VerificationError;

import java.util.ArrayList;
import java.util.Objects;

import stream.customalert.CustomAlertDialogue;


public class InstallationStepTwo extends Fragment implements BlockingStep {

    private View view;
    private TextInputEditText edtContactPerson, edtContactPersonPosition, edtDepartment, edtRoomSizeMet;
    private RadioGroup rgRoomSpecification;
    private RadioButton rbYes, rbNo;
    private Button btnAdAccessories;
    private ListView recyclerView;

    private ArrayList<String> accessoriesList = new ArrayList<>();
    private String[] accessories;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_installation_two,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.view=view;
        initUI(view);
    }

    void initUI(View view) {
        edtContactPerson = view.findViewById(R.id.edt_contact_person);
        edtContactPersonPosition = view.findViewById(R.id.edt_position);
        edtDepartment = view.findViewById(R.id.edt_department);
        edtRoomSizeMet = view.findViewById(R.id.edt_room_size);
        rgRoomSpecification = view.findViewById(R.id.rgrp_specifications);
        rbYes = view.findViewById(R.id.rbtn_yes);
        rbNo = view.findViewById(R.id.rbtn_no);
        btnAdAccessories = view.findViewById(R.id.btn_add);
        recyclerView = view.findViewById(R.id.recyclerView);


        initActions();
        initUIData();
        if (GLConstants.assetModel != null && GLConstants.assetModel.getAccessoriesModels() != null) {
            intData(GLConstants.assetModel.getAccessoriesModels());
        }

    }

    private void initUIData() {

        if (GLConstants.assetModel != null) {
            AssetModel assetModel = GLConstants.assetModel;
            if (GLConstants.assetModel.getContanct_person() != null) {
                //customerID=assetModel.getCustomer_id();
                edtContactPerson.setText(assetModel.getContanct_person());

            }
            if (assetModel.getContact_person_position() != null) {
                edtContactPersonPosition.setText(assetModel.getContact_person_position());
            }
            if (assetModel.getRoom_meets_specification() != null && assetModel.getRoom_meets_specification().equals("Yes")) {
                rbYes.setChecked(true);
                // edtWarrantyDuration.setVisibility(View.VISIBLE);
                // edtWarrantyDuration.setText(assetModel.getWarranty_duration());
                rbNo.setChecked(false);
            } else if (assetModel.getWarranty() != null && assetModel.getWarranty().equals("No")) {
                rbNo.setChecked(true);
                //edtWarrantyDuration.setVisibility(View.GONE);
                rbYes.setChecked(false);
            }
            if (assetModel.getDepartment() != null) {
                edtDepartment.setText(assetModel.getDepartment());
            }
            if (assetModel.getRoomsizestatus() != null) {
                edtRoomSizeMet.setText(assetModel.getRoomsizestatus());
            }
            if (assetModel.getAccessoriesModels() != null && assetModel.getAccessoriesModels().size() > 0) {
                // edtModel.setText(assetModel.getModel());
                intData(assetModel.getAccessoriesModels());
            }

        } else {

            snack("null");
        }

    }

    private void snack(String msg) {
        Snackbar.make(view, msg, Snackbar.LENGTH_SHORT)
                .setAction("Action", null).show();
    }

    void intData(ArrayList<AccessoriesModel> accessoriesModel) {


        accessories = new String[accessoriesModel.size()];
        for (int a = 0; a < accessoriesModel.size(); a++) {

            accessories[a] = accessoriesModel.get(a).getName();
        }

        if (accessories != null && accessories.length > 0) {
            ArrayAdapter adapter = new ArrayAdapter(Objects.requireNonNull(getContext()), android.R.layout.simple_list_item_1, accessories);
        recyclerView.setAdapter(adapter);
        }
    }

    void initActions() {
        btnAdAccessories.setOnClickListener(view -> addTrainee());
        recyclerView.setOnItemLongClickListener((adapterView, view, i, l) -> {


            CustomAlertDialogue.Builder alert = new CustomAlertDialogue.Builder(getActivity())
                    .setStyle(CustomAlertDialogue.Style.DIALOGUE)
                    .setCancelable(false)
                    .setTitle("Delete Items")
                    .setMessage("Delete " + accessoriesList.get(i) + " From this list ")
                    .setPositiveText("Confirm")
                    .setPositiveColor(R.color.negative)
                    .setPositiveTypeface(Typeface.DEFAULT_BOLD)
                    .setOnPositiveClicked(new CustomAlertDialogue.OnPositiveClicked() {
                        @Override
                        public void OnClick(View view, Dialog dialog) {

                            if (accessoriesList.size() > 0) {
                                accessoriesList.remove(i);
                            }
                            ArrayList<AccessoriesModel> accessoriesModels = new ArrayList<>();

                            try {
                                for (String a : accessoriesList) {
                                    AccessoriesModel accessoriesModel = new AccessoriesModel();
                                    accessoriesModel.setName(a);
                                    accessoriesModel.setId(a);
                                }
                                GLConstants.assetModel.setAccessoriesModels(accessoriesModels);

                            } catch (Exception nm) {
                                nm.printStackTrace();
                            }
                            intData(accessoriesModels);
                            dialog.dismiss();

                        }
                    })
                    .setNegativeText("Cancel")
                    .setNegativeColor(R.color.positive)
                    .setOnNegativeClicked((view1, dialog) -> dialog.dismiss())
                    .setDecorView(Objects.requireNonNull(getActivity()).getWindow().getDecorView())
                    .build();
            alert.show();


//            AlertDialog.Builder builder = new AlertDialog.Builder(Objects.requireNonNull(getContext()));
//            builder.setTitle("Delete this item");
//            builder.setPositiveButton(android.R.string.yes, (dialog, id) -> {
//                //TODO
//                if(accessoriesList.size()>0) {
//                    accessoriesList.remove(i);
//                }
//                intData();
//                dialog.dismiss();
//            });
//            builder.setNegativeButton(android.R.string.cancel, (dialog, id) -> {
//                //TODO
//                dialog.dismiss();
//            });
//            AlertDialog dialog = builder.create();
//            dialog.show();


            return false;
        });


    }


    private void addTrainee() {
//        AlertDialog.Builder builder = new AlertDialog.Builder(Objects.requireNonNull(getContext()));
//        builder.setTitle("Title");
//
//  final EditText input = new EditText(getContext());
//        input.setHint("Trainee Name & Position");
//   input.setInputType(InputType.TYPE_CLASS_TEXT );
//        builder.setView(input);
//
//  builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                if(!TextUtils.isEmpty(input.getText())){
//
//                    if(accessoriesList!=null){
//                        accessoriesList.add(input.getText().toString());
//                        intData();
//                    }
//                }
//            }
//        });
//        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                dialog.cancel();
//            }
//        });
//
//        builder.show();


        ArrayList<String> lineHint = new ArrayList<>();
        lineHint.add("Name");


//        ArrayList<String> lineText = new ArrayList<>();
//        lineText.add("Name & Position");


        ArrayList<String> boxHint = new ArrayList<>();
        boxHint.add("Qty");

//        ArrayList<String> boxText = new ArrayList<>();
//        boxText.add("BoxText");


        CustomAlertDialogue.Builder alert = new CustomAlertDialogue.Builder(getActivity())
                .setStyle(CustomAlertDialogue.Style.INPUT)
                .setTitle("Accessories")
                .setMessage("Accessories( Include any spare parts and startup kits that came with equipment) ")
                .setPositiveText("Submit")
                .setPositiveColor(R.color.positive)
                .setPositiveTypeface(Typeface.DEFAULT_BOLD)
                .setOnInputClicked((view, dialog, inputList) -> {
                    if (inputList != null && inputList.size() > 0) {
                        if (!TextUtils.isEmpty(inputList.get(0))) {
//
                            if (accessoriesList != null) {
                                String name = inputList.get(0);
                                String pos = "";

                                if (inputList.size() > 1) {
                                    pos = inputList.get(1);
                                }
                                ArrayList<AccessoriesModel> accessoriesModels = new ArrayList<>();

                                try {
                                    for (String a : accessoriesList) {
                                        AccessoriesModel accessoriesModel = new AccessoriesModel();
                                        accessoriesModel.setName(a);
                                        accessoriesModel.setId(a);
                                    }
                                    GLConstants.assetModel.setAccessoriesModels(accessoriesModels);

                                } catch (Exception nm) {
                                    nm.printStackTrace();
                                }
                                accessoriesList.add(name + "  " + pos);
                                intData(accessoriesModels);
                            }
                        }
                    }
                    dialog.dismiss();
                })
                .setNegativeText("Cancel")
                .setNegativeColor(R.color.negative)
                .setOnNegativeClicked((view, dialog) -> dialog.dismiss())
                .setLineInputHint(lineHint)
                // .setLineInputText(lineText)
                .setBoxInputHint(boxHint)
                // .setBoxInputText(boxText)
                .setDecorView(Objects.requireNonNull(getActivity()).getWindow().getDecorView())
                .build();
        alert.show();
    }

    @Override
    public void onNextClicked(StepperLayout.OnNextClickedCallback callback) {
        if (GLConstants.assetModel == null) {
            GLConstants.assetModel = new AssetModel();

            GLConstants.assetModel.setContanct_person(edtContactPerson.getText().toString());
            GLConstants.assetModel.setCustomer_name(edtContactPersonPosition.getText().toString());
            GLConstants.assetModel.setRoom_meets_specification(getRadioChecked(rgRoomSpecification));
            GLConstants.assetModel.setDepartment(edtDepartment.getText().toString());
            GLConstants.assetModel.setRoomsizestatus(edtRoomSizeMet.getText().toString());


        } else {
            GLConstants.assetModel.setContanct_person(edtContactPerson.getText().toString());
            GLConstants.assetModel.setCustomer_name(edtContactPersonPosition.getText().toString());
            GLConstants.assetModel.setRoom_meets_specification(getRadioChecked(rgRoomSpecification));
            GLConstants.assetModel.setDepartment(edtDepartment.getText().toString());
            GLConstants.assetModel.setRoomsizestatus(edtRoomSizeMet.getText().toString());

        }
        callback.goToNextStep();

    }

    @Override
    public void onCompleteClicked(StepperLayout.OnCompleteClickedCallback callback) {

        callback.complete();
    }

    @Override
    public void onBackClicked(StepperLayout.OnBackClickedCallback callback) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {


                //you can do anythings you want
                callback.goToPrevStep();
            }
        }, 0L);// delay open another fragment,
    }

    @Nullable
    @Override
    public VerificationError verifyStep() {
        Log.d("Next", "Verify started");
        if (verify()) {
            Log.d("Next", "Verify Passed");
            return null;
        } else {
            Log.d("Next", "Verify Failed");
            return new VerificationError("Fill all fields");
        }
    }

    @Override
    public void onSelected() {

        initUI(view);
    }

    @Override
    public void onError(@NonNull VerificationError error) {
        Log.d("Next", "On Error");
    }

    private boolean verify() {
        Log.d("Next", "Verify start");
        return isTextInputEditTextFilled(edtContactPerson)
                && isTextInputEditTextFilled(edtContactPersonPosition)
                && isTextInputEditTextFilled(edtDepartment)
                && isTextInputEditTextFilled(edtRoomSizeMet)
                && isRadioGroupChecked(rgRoomSpecification);
    }


    private boolean isTextInputEditTextFilled(TextInputEditText t) {
        if (TextUtils.isEmpty(t.getText())) {
            t.requestFocus();
            t.setError("Required");
            return false;
        }
        return true;
    }

    private String getRadioChecked(RadioGroup r) {
        if (r.getCheckedRadioButtonId() == R.id.rbtn_yes) {
            return "Yes";
        } else {
            return "No";
        }
    }

    private boolean isRadioGroupChecked(RadioGroup r) {
        if (r.getCheckedRadioButtonId() == -1) {

            Snackbar.make(r, "Choose whether room meets specifications", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
            return false;
        } else {
            return rgRoomSpecification.getCheckedRadioButtonId() == R.id.rbtn_yes
                    || rgRoomSpecification.getCheckedRadioButtonId() == R.id.rbtn_no;
        }
    }
}
