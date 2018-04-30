package com.erickogi14gmail.assettrack.Views.V1.InstallationSteps;

import android.app.Dialog;
import android.graphics.Typeface;
import android.os.Bundle;
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
import android.widget.RadioGroup;

import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnTimeSelectChangeListener;
import com.bigkoo.pickerview.view.TimePickerView;
import com.erickogi14gmail.assettrack.Data.Models.V1.AssetModel;
import com.erickogi14gmail.assettrack.GLConstants;
import com.erickogi14gmail.assettrack.R;
import com.stepstone.stepper.BlockingStep;
import com.stepstone.stepper.StepperLayout;
import com.stepstone.stepper.VerificationError;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Objects;

import stream.customalert.CustomAlertDialogue;

public class InstallationStepThree extends Fragment implements BlockingStep {


    private TextInputEditText edtEngRemarks, edtCustRemarks, edtDateInst;
    private RadioGroup rgAssetStatus;


    private Button btnAddTrrainees;
    private ListView recyclerView;

    private ArrayList<String> traineesList = new ArrayList<>();
    private String[] trainees;
    private TimePickerView pvTime, pvCustomTime, pvCustomLunar;

    private String getTime(Date date) {//可根据需要自行截取数据显示
        Log.d("getTime()", "choice date millis: " + date.getTime());
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(date);
    }

    private void initTimePicker() {

        pvTime = new TimePickerBuilder(getActivity(), (date, v) -> {


            edtDateInst.setText(getTime(date));
            Log.i("pvTime", "onTimeSelect");

        })
                .setTimeSelectChangeListener(new OnTimeSelectChangeListener() {
                    @Override
                    public void onTimeSelectChanged(Date date) {
                        Log.i("pvTime", "onTimeSelectChanged");
                    }
                })
                .setType(new boolean[]{true, true, true, true, true, true})
                .build();

    }

    private View view;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_installation_three, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.view = view;
        initTimePicker();

        initUI(view);
    }

    void initUI(View view) {
        edtDateInst = view.findViewById(R.id.edt_installation_date);

        //rgAssetStatus=view.findViewById(R.id.rgrp_status);


        edtEngRemarks = view.findViewById(R.id.edt_engineer_remarks);
        edtCustRemarks = view.findViewById(R.id.edt_customer_remarks);
        btnAddTrrainees = view.findViewById(R.id.btn_add);
        recyclerView = view.findViewById(R.id.recyclerView);

//
        initData();
        if (traineesList != null) {
            intData();
        }
        initActions();

    }

    private void initData() {

        if (GLConstants.assetModel != null) {
            AssetModel assetModel = GLConstants.assetModel;
            if (GLConstants.assetModel.getEngineer_remarks() != null) {
                //customerID=assetModel.getCustomer_id();
                edtEngRemarks.setText(assetModel.getEngineer_remarks());

            }
            if (assetModel.getReceiver_comments() != null) {
                edtCustRemarks.setText(assetModel.getReceiver_comments());
            }
            if (assetModel.getInstallation_date() != null) {
                edtDateInst.setText(assetModel.getInstallation_date());
            }

        } else {

            snack("null");
        }
    }

    private void snack(String msg) {
        Snackbar.make(view, msg, Snackbar.LENGTH_SHORT)
                .setAction("Action", null).show();
    }

    void intData() {


        trainees = new String[traineesList.size()];
        for (int a = 0; a < traineesList.size(); a++) {

            trainees[a] = traineesList.get(a);
        }


        if (trainees != null && trainees.length > 0) {
            ArrayAdapter adapter = new ArrayAdapter(Objects.requireNonNull(getContext()), android.R.layout.simple_list_item_1, trainees);
            recyclerView.setAdapter(adapter);
        }
    }

    void initActions() {
        edtDateInst.setOnClickListener(view1 -> datePicker());

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

//            recyclerView.setOnItemLongClickListener((adapterView, view, i, l) -> {
//
//
//                CustomAlertDialogue.Builder alert = new CustomAlertDialogue.Builder(getActivity())
//                        .setStyle(CustomAlertDialogue.Style.DIALOGUE)
//                        .setCancelable(false)
//                        .setTitle("Delete Items")
//                        .setMessage("Delete " + traineesList.get(i) + " From this list ")
//                        .setPositiveText("Confirm")
//                        .setPositiveColor(R.color.negative)
//                        .setPositiveTypeface(Typeface.DEFAULT_BOLD)
//                        .setOnPositiveClicked(new CustomAlertDialogue.OnPositiveClicked() {
//                            @Override
//                            public void OnClick(View view, Dialog dialog) {
//
//                                if (traineesList.size() > 0) {
//                                    traineesList.remove(i);
//                                }
//                                intData();
//                                dialog.dismiss();
//
//                            }
//                        })
//                        .setNegativeText("Cancel")
//                        .setNegativeColor(R.color.positive)
//                        .setOnNegativeClicked((view1, dialog) -> dialog.dismiss())
//                        .setDecorView(Objects.requireNonNull(getActivity()).getWindow().getDecorView())
//                        .build();
//                alert.show();
//
//
////            AlertDialog.Builder builder = new AlertDialog.Builder(Objects.requireNonNull(getContext()));
////            builder.setTitle("Delete this item");
////            builder.setPositiveButton(android.R.string.yes, (dialog, id) -> {
////                //TODO
////                if(traineesList.size()>0) {
////                    traineesList.remove(i);
////                }
////                intData();
////                dialog.dismiss();
////            });
////            builder.setNegativeButton(android.R.string.cancel, (dialog, id) -> {
////                //TODO
////                dialog.dismiss();
////            });
////            AlertDialog dialog = builder.create();
////            dialog.show();
//
//
//                return false;
//            });

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

    void datePicker() {

        pvTime.show();
    }


    @Override
    public void onNextClicked(StepperLayout.OnNextClickedCallback callback) {
        if (GLConstants.assetModel == null) {
            GLConstants.assetModel = new AssetModel();

            GLConstants.assetModel.setEngineer_remarks(edtEngRemarks.getText().toString());
            GLConstants.assetModel.setReceiver_comments(edtCustRemarks.getText().toString());
            GLConstants.assetModel.setInstallation_date(edtDateInst.getText().toString());
            if (trainees != null) {
                GLConstants.assetModel.setTrainees(Arrays.toString(trainees));
            } else {
                GLConstants.assetModel.setTrainees("");
            }

        } else {

            GLConstants.assetModel.setEngineer_remarks(edtEngRemarks.getText().toString());
            GLConstants.assetModel.setReceiver_comments(edtCustRemarks.getText().toString());
            GLConstants.assetModel.setInstallation_date(edtDateInst.getText().toString());
            if (trainees != null) {
                GLConstants.assetModel.setTrainees(Arrays.toString(trainees));
            } else {
                GLConstants.assetModel.setTrainees("");
            }

        }
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
        if (verify()) {
            return null;
        } else {
            return new VerificationError("Fill all required fields");
        }
    }

    @Override
    public void onSelected() {
        //initUI(view);
    }

    @Override
    public void onError(@NonNull VerificationError error) {

    }


    private boolean verify() {
        Log.d("Next", "Verify start");
        return isTextInputEditTextFilled(edtDateInst)
                && isTextInputEditTextFilled(edtEngRemarks)
                && isTextInputEditTextFilled(edtCustRemarks)
                ;

    }

    private boolean isStringValid(String s) {
        if (s == null) {
            Snackbar.make(view, "Select Asset Image", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
            return false;
        }
        return true;
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

            Snackbar.make(r, "Select asset status", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
            return false;
        } else {
            return rgAssetStatus.getCheckedRadioButtonId() == R.id.rbtn_okay
                    || rgAssetStatus.getCheckedRadioButtonId() == R.id.rbtn_faulty;
        }
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


}
