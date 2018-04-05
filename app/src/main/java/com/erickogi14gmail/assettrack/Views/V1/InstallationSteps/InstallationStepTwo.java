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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

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
    private Button btnAddTrrainees;
    private ListView recyclerView;

    private ArrayList<String> traineesList = new ArrayList<>();
    private String[] trainees;


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
        btnAddTrrainees = view.findViewById(R.id.btn_add);
        recyclerView = view.findViewById(R.id.recyclerView);


        initActions();
        if (traineesList != null) {
            intData();
        }

    }

    void intData() {


        trainees = new String[traineesList.size()];
        for (int a = 0; a < traineesList.size(); a++) {

            trainees[a] = traineesList.get(a);
        }

        // if(trainees!=null&&trainees.length>0) {
        ArrayAdapter adapter = new ArrayAdapter(Objects.requireNonNull(getContext()), android.R.layout.simple_list_item_1, trainees);
        recyclerView.setAdapter(adapter);
        // }
    }

    void initActions() {
        btnAddTrrainees.setOnClickListener(view -> addTrainee());
        recyclerView.setOnItemLongClickListener((adapterView, view, i, l) -> {


            CustomAlertDialogue.Builder alert = new CustomAlertDialogue.Builder(getActivity())
                    .setStyle(CustomAlertDialogue.Style.DIALOGUE)
                    .setCancelable(false)
                    .setTitle("Delete Items")
                    .setMessage("Delete " + traineesList.get(i) + " From this list ")
                    .setPositiveText("Confirm")
                    .setPositiveColor(R.color.negative)
                    .setPositiveTypeface(Typeface.DEFAULT_BOLD)
                    .setOnPositiveClicked(new CustomAlertDialogue.OnPositiveClicked() {
                        @Override
                        public void OnClick(View view, Dialog dialog) {

                            if (traineesList.size() > 0) {
                                traineesList.remove(i);
                            }
                            intData();
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
//                if(traineesList.size()>0) {
//                    traineesList.remove(i);
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
//                    if(traineesList!=null){
//                        traineesList.add(input.getText().toString());
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
        boxHint.add("Position");

//        ArrayList<String> boxText = new ArrayList<>();
//        boxText.add("BoxText");


        CustomAlertDialogue.Builder alert = new CustomAlertDialogue.Builder(getActivity())
                .setStyle(CustomAlertDialogue.Style.INPUT)
                .setTitle("Trainees")
                .setMessage("Add a trainee & the position ie ( Mr Juma , Head of IT)")
                .setPositiveText("Submit")
                .setPositiveColor(R.color.positive)
                .setPositiveTypeface(Typeface.DEFAULT_BOLD)
                .setOnInputClicked((view, dialog, inputList) -> {
                    if (inputList != null && inputList.size() > 0) {
                        if (!TextUtils.isEmpty(inputList.get(0))) {
//
                            if (traineesList != null) {
                                String name = inputList.get(0);
                                String pos = "";

                                if (inputList.size() > 1) {
                                    pos = inputList.get(1);
                                }
                                traineesList.add(name + "  " + pos);
                                intData();
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
        if (verify()) {
            return null;
        } else {
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

    private boolean verify() {
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

    private boolean isRadioGroupChecked(RadioGroup r) {
        if (r.getCheckedRadioButtonId() == -1) {

            Snackbar.make(r, "Choose whether room meets specifications", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
            return false;
        } else {
            return rgRoomSpecification.getCheckedRadioButtonId() == R.id.rbtn_warranty_yes || rgRoomSpecification.getCheckedRadioButtonId() == R.id.rbtn_warranty_yes;
        }
    }
}
