package com.erickogi14gmail.assettrack.Views;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.edwardvanraak.materialbarcodescanner.MaterialBarcodeScanner;
import com.edwardvanraak.materialbarcodescanner.MaterialBarcodeScannerBuilder;
import com.erickogi14gmail.assettrack.Data.Models.Assets;
import com.erickogi14gmail.assettrack.Data.Sqlite.DbConstants;
import com.erickogi14gmail.assettrack.Data.Sqlite.DbOperations;
import com.erickogi14gmail.assettrack.R;
import com.erickogi14gmail.assettrack.Utills.Constants;
import com.erickogi14gmail.assettrack.Utills.DateTimeUtils;
import com.erickogi14gmail.assettrack.Utills.MyToast;
import com.google.android.gms.vision.barcode.Barcode;
import com.nguyenhoanglam.imagepicker.model.Config;
import com.nguyenhoanglam.imagepicker.model.Image;
import com.nguyenhoanglam.imagepicker.ui.imagepicker.ImagePicker;

import java.util.ArrayList;

public class CreateAsset extends AppCompatActivity {

    private EditText edtName,edtSite,edtSerial,edtCondition,edtInstalationdate,edtModel,edtService;
    private ImageView imageView,imgbar;
    private TextView txtDate,txtBy,txtBar;
    private String tag_s=null;
    private String imagepath=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_asset);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        imgbar=findViewById(R.id.bar);
        txtBar=findViewById(R.id.txt_bar_code);
        imageView=findViewById(R.id.asset_image);
        txtBy=findViewById(R.id.txt_by);
        txtDate=findViewById(R.id.txt_date);
        edtName=findViewById(R.id.edt_asset_name);
        edtSite=findViewById(R.id.edt_site);
        edtSerial=findViewById(R.id.edt_serial);
        edtInstalationdate=findViewById(R.id.edt_installation_date);
        edtCondition=findViewById(R.id.edt_conditions);
        edtModel=findViewById(R.id.edt_model);
        edtService=findViewById(R.id.edt_service_contract);



        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(view -> {
            if(imagepath!=null&&tag_s!=null) {
                if (edtCondition.getText().toString().isEmpty()) {
                    edtCondition.setError("Required");
                    return;
                }
                if (edtName.getText().toString().isEmpty()) {
                    edtName.setError("Required");
                    return;
                }
                if (edtSite.getText().toString().isEmpty()) {
                    edtSite.setError("Required");
                    return;
                }
                if (edtSerial.getText().toString().isEmpty()) {
                    edtSerial.setError("Required");
                    return;
                }
                if (edtInstalationdate.getText().toString().isEmpty()) {
                    edtInstalationdate.setError("Required");
                    return;
                }
                if (edtCondition.getText().toString().isEmpty()) {
                    edtCondition.setError("Required");
                    return;
                }

                if (edtModel.getText().toString().isEmpty()) {
                    edtModel.setError("Required");
                    return;
                }
                if (edtService.getText().toString().isEmpty()) {
                    edtService.setError("Required");
                    return;
                }


                Assets assets = new Assets();
                assets.setLast_maintenance(edtInstalationdate.getText().toString());
                assets.setCondition(edtCondition.getText().toString());
                assets.setDate(DateTimeUtils.getToday());
                assets.setLast_maintenance_by("Eric Kogi");
                assets.setSerial(edtSerial.getText().toString());
                assets.setTag(tag_s);
                assets.setType(edtName.getText().toString());
                assets.setImage(imagepath);
                assets.setModel(edtModel.getText().toString());
                assets.setContract(edtService.getText().toString());
                assets.setInstalledby("Eric Kogi");

                ContentValues cv=new ContentValues();
                cv.put(DbConstants.type,assets.getType());
                cv.put(DbConstants.tag,assets.getTag());
                cv.put(DbConstants.installatiodate,assets.getDate());
                cv.put(DbConstants.last_maintenance,assets.getLast_maintenance());
                cv.put(DbConstants.last_maintenance_by,assets.getLast_maintenance_by());
                cv.put(DbConstants.serial,assets.getSerial());
                cv.put(DbConstants.site,assets.getSite());
                cv.put(DbConstants.image,assets.getImage());
                cv.put(DbConstants.condition,assets.getCondition());
                cv.put(DbConstants.installedby,assets.getInstalledby());
                cv.put(DbConstants.model,assets.getModel());
                cv.put(DbConstants.contract,assets.getContract());



                DbOperations dbOperations=new DbOperations(CreateAsset.this);
                if(!dbOperations.insert(DbConstants.TABLE_ITEMS,cv)){
                    MyToast.toast("Error Inserting",CreateAsset.this,R.drawable.ic_error_outline_black_24dp, Constants.TOAST_LONG);

                }else {
                    finish();
                }
            }
            else {
                MyToast.toast("Make Sure to have an image and barcode for the asset",CreateAsset.this,R.drawable.ic_error_outline_black_24dp, Constants.TOAST_LONG);
            }
        });
    }

    public void takeBar(View view) {
        final MaterialBarcodeScanner materialBarcodeScanner = new MaterialBarcodeScannerBuilder()
                .withActivity(this)
                .withBackfacingCamera()
                .withEnableAutoFocus(true)
                .withBleepEnabled(true)
                .withCenterTracker()
                .withOnly2DScanning()
                .withBarcodeFormats(Barcode.AZTEC | Barcode.EAN_13 | Barcode.CODE_93)
                // .withBackfacingCamera()
                // .withText("Scanning...")
                .withResultListener(barcode -> {

                    tag_s=barcode.rawValue;
                    imgbar.setVisibility(View.GONE);
                    txtBar.setVisibility(View.VISIBLE);
                    txtBar.setText(barcode.rawValue);




                    // Toast.makeText(MainActivity.this, String.valueOf(barcode.rawValue), Toast.LENGTH_SHORT).show();
                })
                .build();
        materialBarcodeScanner.startScan();
    }

    private void pick() {
        ImagePicker.with(this)                         //  Initialize ImagePicker with activity or fragment context

                .setToolbarColor("#00BCD4")         //  Toolbar color
                .setStatusBarColor("#0097A7")       //  StatusBar color (works with SDK >= 21  )
                //.setImageTitle()
                .setToolbarTextColor("#FFFFFF")     //  Toolbar text color (Title and Done button)
                .setToolbarIconColor("#FFFFFF")     //  Toolbar icon color (Back and Camera button)
                .setProgressBarColor("#4CAF50")     //  ProgressBar color
                .setBackgroundColor("#FFFFFF")      //  Background color
                .setCameraOnly(false)               //  Camera mode
                .setMultipleMode(true)              //  Select multiple images or single image
                .setFolderMode(true)                //  Folder mode
                .setShowCamera(true)                //  Show camera button
                .setFolderTitle("Albums")           //  Folder title (works with FolderMode = true)
                .setImageTitle("Galleries")         //  MyImage title (works with FolderMode = false)
                .setDoneTitle("Done")               //  Done button title
                .setLimitMessage("You have reached selection limit")    // Selection limit message
                // .setMaxSize(20)                     //  Max images can be selected
                .setSavePath("ImagePicker")         //  MyImage capture folder name
                //.setSelectedImages(images)          //  Selected images
                .setKeepScreenOn(true)              //  Keep screen on when selecting images
                .start();                           //  Start ImagePicker
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == Config.RC_PICK_IMAGES && resultCode == Activity.RESULT_OK && data != null) {
            // images = data.getParcelableArrayListExtra(Config.EXTRA_IMAGES);
            //adapter.setData(images);

            //adapter.setData(images);
            ArrayList<Image> images = data.getParcelableArrayListExtra(Config.EXTRA_IMAGES);
            if (images != null && images.size() > 0) {
//                for (int a = 0; a < images.size(); a++) {
//
//                    images.get(a).setImageFrom(Constants.GALLERY);
//                }
                update(images.get(0));
            }
            // update(data.getParcelableArrayListExtra(Config.EXTRA_IMAGES));
        }
    }

    private void update(Image image) {

        Glide.with(CreateAsset.this).load(image.getPath()).into(imageView);
        this.imagepath=image.getPath();
    }

    public void takeImage(View view) {
        pick();
    }
}

