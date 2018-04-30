package com.erickogi14gmail.assettrack.Views.V1.InstallationSteps;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.edwardvanraak.materialbarcodescanner.MaterialBarcodeScanner;
import com.edwardvanraak.materialbarcodescanner.MaterialBarcodeScannerBuilder;
import com.erickogi14gmail.assettrack.Data.Models.V1.AssetModel;
import com.erickogi14gmail.assettrack.GLConstants;
import com.erickogi14gmail.assettrack.R;
import com.erickogi14gmail.assettrack.Utills.DateTimeUtils;
import com.erickogi14gmail.assettrack.Utills.Imageutils;
import com.google.android.gms.vision.barcode.Barcode;
import com.stepstone.stepper.BlockingStep;
import com.stepstone.stepper.StepperLayout;
import com.stepstone.stepper.VerificationError;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

import stream.customalert.CustomAlertDialogue;

import static com.github.florent37.runtimepermission.RuntimePermission.askPermission;

public class InstallationStepFour extends Fragment implements BlockingStep, Imageutils.ImageAttachmentListener {


    public static final int READ_WRITE_STORAGE = 52;
    Imageutils imageutils;
    private RadioGroup rgAssetStatus;
    private RadioButton rbOkay, rbFaulty;
    private TextInputEditText edtScan;
    private ImageView img;
    private Button btnImage;
    private String path;
    private ProgressDialog mProgressDialog;
    private Button btnAddTrrainees;
    private ListView recyclerView;
    private ArrayList<String> traineesList = new ArrayList<>();
    private String[] trainees;
    private View view;


    public InstallationStepFour() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_installation_four, container, false);
    }

    public void per() {
        askPermission(this)
                .request(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE)

                .onAccepted((result) -> {
                    imagePicker();
                })
                .onDenied((result) -> {
                    //the list of denied permissions
                    for (String permission : result.getDenied()) {
                        // appendText(resultView, permission);
                    }
                    //permission denied, but you can ask again, eg:

         /*
         new AlertDialog.Builder(MainActivity.this)
                 .setMessage("Please accept our permissions")
                 .setPositiveButton("yes", (dialog, which) -> { result.askAgain(); }) // ask again
                 .setNegativeButton("no", (dialog, which) -> { dialog.dismiss(); })
                 .show();
         */
                })
                .onForeverDenied((result) -> {
                    //the list of forever denied permissions, user has check 'never ask again'
                    for (String permission : result.getForeverDenied()) {

                    }
                    // you need to open setting manually if you really need it
                    //result.goToSettings();
                })
                .ask();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.view = view;


        imageutils = new Imageutils(getActivity(), this, true);

        initUI(view);
    }

    void initUI(View view) {

        rgAssetStatus = view.findViewById(R.id.rgrp_status);
        rbFaulty = view.findViewById(R.id.rbtn_faulty);
        rbOkay = view.findViewById(R.id.rbtn_okay);


        btnAddTrrainees = view.findViewById(R.id.btn_add);
        // recyclerView = view.findViewById(R.id.recyclerView);

//
        img = view.findViewById(R.id.asset_image);
//
        btnImage = view.findViewById(R.id.btn_asset_image);
        edtScan = view.findViewById(R.id.edt_scan_code);
//            if (traineesList != null) {
//                intData();
//            }
        initUIData();
        initActions();

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
        btnImage.setOnClickListener(view1 -> per());
        edtScan.setOnClickListener(view1 -> codeScanner());


//        btnAddTrrainees.setOnClickListener(view -> addTrainee());


    }

    private void initUIData() {

        if (GLConstants.assetModel != null) {
            AssetModel assetModel = GLConstants.assetModel;
            if (GLConstants.assetModel.getAsset_code() != null) {
                //customerID=assetModel.getCustomer_id();
                edtScan.setText(assetModel.getAsset_code());

            }

            if (assetModel.getAsset_status() != null && assetModel.getAsset_status().equals("Okay")) {
                rbOkay.setChecked(true);
                // edtWarrantyDuration.setVisibility(View.VISIBLE);
                // edtWarrantyDuration.setText(assetModel.getWarranty_duration());
                rbFaulty.setChecked(false);
            } else if (assetModel.getAsset_status() != null && assetModel.getAsset_status().equals("Faulty")) {
                rbFaulty.setChecked(true);
                //edtWarrantyDuration.setVisibility(View.GONE);
                rbOkay.setChecked(false);
            }
            if (assetModel.getAsset_image() != null) {
                path = assetModel.getAsset_image();
                RequestOptions options = (new RequestOptions())
                        .placeholder(R.drawable.imagepicker_image_placeholder)
                        .error(R.drawable.imagepicker_image_placeholder)
                        .centerCrop().diskCacheStrategy(DiskCacheStrategy.RESOURCE);
                Glide.with(getContext())
                        .load(assetModel.getAsset_image())
                        .apply(options)
                        .into(img);
                //edtDepartment.setText(assetModel.getDepartment());
            }


        } else {

            snack("null");
        }

    }

    private void snack(String msg) {
        Snackbar.make(view, msg, Snackbar.LENGTH_SHORT)
                .setAction("Action", null).show();
    }

    @Override
    public void onNextClicked(StepperLayout.OnNextClickedCallback callback) {

        callback.goToNextStep();
    }

    @Override
    public void onCompleteClicked(StepperLayout.OnCompleteClickedCallback callback) {
        if (GLConstants.assetModel == null) {
            GLConstants.assetModel = new AssetModel();

            GLConstants.assetModel.setAsset_code(edtScan.getText().toString());
            GLConstants.assetModel.setAsset_image(path);
            GLConstants.assetModel.setAsset_status(getRadioChecked(rgAssetStatus));


        } else {


            GLConstants.assetModel.setAsset_code(edtScan.getText().toString());
            GLConstants.assetModel.setAsset_image(path);
            GLConstants.assetModel.setAsset_status(getRadioChecked(rgAssetStatus));


        }
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
        initUI(view);
    }

    @Override
    public void onError(@NonNull VerificationError error) {

    }

    private void codeScanner() {
        final MaterialBarcodeScanner materialBarcodeScanner = new MaterialBarcodeScannerBuilder()
                .withActivity(Objects.requireNonNull(getActivity()))
                .withBackfacingCamera()
                .withEnableAutoFocus(true)
                .withBleepEnabled(true)
                .withCenterTracker()
                .withOnly2DScanning()
                .withBarcodeFormats(Barcode.AZTEC | Barcode.EAN_13 | Barcode.CODE_93)
                // .withBackfacingCamera()
                // .withText("Scanning...")
                .withResultListener(barcode -> {

                    edtScan.setText(barcode.rawValue);


                })
                .build();
        materialBarcodeScanner.startScan();
    }

    private void imagePicker() {
        imageutils.imagepicker(1);
        // ImagePicker.pickImage(this, "Select your image:");
        // ImagePicker.setMinQuality(600, 600);
        // ImagePicker.setMinQuality();

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        Log.d("Fragment", "onRequestPermissionsResult: " + requestCode);
        imageutils.request_permission_result(requestCode, permissions, grantResults);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.d("Fragment", "onActivityResult: ");
        imageutils.onActivityResult(requestCode, resultCode, data);

    }

    public String saveImage(Bitmap bitmap, String name) {
        ContextWrapper cw = new ContextWrapper(getContext());
        File directory = cw.getDir("images", Context.MODE_PRIVATE);
        if (!directory.exists()) {
            directory.mkdir();
        }
        final File myImageFile = new File(directory, name);
        new Thread(new Runnable() {
            @Override
            public void run() {

                // Create image file
                FileOutputStream fos = null;
                try {
                    fos = new FileOutputStream(myImageFile);
                    bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        fos.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                Log.i("image", "image saved to >>>" + myImageFile.getAbsolutePath());

            }
        }).start();

        return myImageFile.getAbsolutePath();
    }

    @Override
    public void image_attachment(int from, String filename, Bitmap file, Uri uri) {
        showLoading("Saving photo");
        Bitmap bitmap = file;
        String file_name = filename;
        img.setImageBitmap(file);
        img.setVisibility(View.VISIBLE);
        btnImage.setVisibility(View.GONE);


        // String path =  Environment.getExternalStorageDirectory() + File.separator + "ImageAttach" + File.separator;
        // imageutils.createImage(file,filename,path,false);
        // this.path=path;
        File files = new File(uri.getPath() + "" + DateTimeUtils.getNowslong());
        try {
            path = saveImage(file, DateTimeUtils.getNow());
//            files.createNewFile();
//
//            Imageutils.saveImage(files.getAbsolutePath(), new Imageutils.OnSaveListener() {
//                @Override
//                public void onSuccess(@NonNull String imagePath) {
            hideLoading();
            showSnackbar("Image Saved Successfully");
//                    //mPhotoEditorView.getSource().setImageURI(Uri.fromFile(new File(imagePath)));
//
//                    path=imagePath;
//                }
//
//                @Override
//                public void onFailure(@NonNull Exception exception) {
//                    hideLoading();
//                    showSnackbar("Failed to save Image");
//                }
//            }, file);

        } catch (Exception nm) {

            //throw new Exception();
            Log.d("savingerrro", nm.toString());
            hideLoading();
            showSnackbar("Failed to save Image" + nm.toString());
        }
    }

    public boolean requestPermission(String permission) {
        boolean isGranted = ContextCompat.checkSelfPermission(Objects.requireNonNull(getContext()), permission) == PackageManager.PERMISSION_GRANTED;
        if (!isGranted) {
            ActivityCompat.requestPermissions(
                    Objects.requireNonNull(getActivity()),
                    new String[]{permission},
                    READ_WRITE_STORAGE);
        }
        return isGranted;
    }

    public void showLoading(@NonNull String message) {
        mProgressDialog = new ProgressDialog(getContext());
        mProgressDialog.setMessage(message);
        mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        mProgressDialog.setCancelable(false);
        mProgressDialog.show();
    }

    public void hideLoading() {
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
    }

    public void showSnackbar(@NonNull String message) {
        //  View view = vfindViewById(android.R.id.content);
        if (view != null) {
            Snackbar.make(view, message, Snackbar.LENGTH_LONG).show();
        } else {
            Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
        }
    }

    private boolean verify() {
        Log.d("Next", "Verify start");
        return isTextInputEditTextFilled(edtScan)
                && isRadioGroupChecked(rgAssetStatus)
                && isStringValid(path);

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

    private String getRadioChecked(RadioGroup r) {
        if (r.getCheckedRadioButtonId() == R.id.rbtn_okay) {
            return "Okay";
        } else {
            return "Faulty";
        }
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
