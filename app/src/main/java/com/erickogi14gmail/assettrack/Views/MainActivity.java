package com.erickogi14gmail.assettrack.Views;

import android.Manifest;
import android.app.Dialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.edwardvanraak.materialbarcodescanner.MaterialBarcodeScanner;
import com.edwardvanraak.materialbarcodescanner.MaterialBarcodeScannerBuilder;
import com.erickogi14gmail.assettrack.Adapter.TimeLine.OrderStatus;
import com.erickogi14gmail.assettrack.Adapter.TimeLine.TimeLineModel;
import com.erickogi14gmail.assettrack.Data.Models.Assets;
import com.erickogi14gmail.assettrack.Data.Models.V1.AssetModel;
import com.erickogi14gmail.assettrack.Data.PrefManager;
import com.erickogi14gmail.assettrack.Data.Sqlite.DbConstants;
import com.erickogi14gmail.assettrack.Data.Sqlite.DbContentValues;
import com.erickogi14gmail.assettrack.Data.Sqlite.DbOperations;
import com.erickogi14gmail.assettrack.R;
import com.erickogi14gmail.assettrack.Utills.Constants;
import com.erickogi14gmail.assettrack.Utills.MyToast;
import com.erickogi14gmail.assettrack.Views.Login.Login;
import com.erickogi14gmail.assettrack.Views.V1.Installation;
import com.google.android.gms.vision.barcode.Barcode;
import com.nightonke.boommenu.BoomButtons.BoomButton;
import com.nightonke.boommenu.BoomButtons.ButtonPlaceEnum;
import com.nightonke.boommenu.BoomButtons.HamButton;
import com.nightonke.boommenu.BoomButtons.OnBMClickListener;
import com.nightonke.boommenu.BoomMenuButton;
import com.nightonke.boommenu.OnBoomListener;
import com.nightonke.boommenu.Piece.PiecePlaceEnum;
import com.special.ResideMenu.ResideMenu;
import com.special.ResideMenu.ResideMenuItem;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ResideMenu resideMenu;
    private BoomMenuButton bmb;
    private ImageView img_fab;
    private DbOperations dbOperations;
    int i;

    void setResideMenu() {
        resideMenu = new ResideMenu(this);
        resideMenu.setBackground(R.drawable.background_bf);
        resideMenu.attachToActivity(this);



        ResideMenuItem resideMenuItem=new ResideMenuItem(this,R.drawable.ic_home_black_24dp,"Home ");
        ResideMenuItem resideMenuItem0=new ResideMenuItem(this,R.drawable.ic_add_bla_24dp,"Add Assets ");
        ResideMenuItem resideMenuItem1=new ResideMenuItem(this,R.drawable.ic_account_circle_black_24dp,"Profile");
        ResideMenuItem resideMenuItem2=new ResideMenuItem(this,R.drawable.ic_history_black_24dp,"History");
        ResideMenuItem resideMenuItem3=new ResideMenuItem(this,R.drawable.ic_exit_to_app_black_24dp,"Log Out");

        resideMenu.addMenuItem(resideMenuItem, ResideMenu.DIRECTION_LEFT);
        resideMenu.addMenuItem(resideMenuItem3, ResideMenu.DIRECTION_LEFT);

        resideMenuItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // MyToast.toast("Home Clicked",MainActivity.this,R.drawable.ic_home_black_24dp, Constants.TOAST_LONG);
                dial();
                resideMenu.closeMenu();
            }
        });
        resideMenuItem0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // MyToast.toast("Home Clicked",MainActivity.this,R.drawable.ic_home_black_24dp, Constants.TOAST_LONG);
                resideMenu.closeMenu();
                startActivity(new Intent(MainActivity.this,CreateAsset.class));
            }
        });
        resideMenuItem1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resideMenu.closeMenu();
                // MyToast.toast("Account Clicked",MainActivity.this,R.drawable.ic_account_circle_black_24dp, Constants.TOAST_LONG);

            }
        });
        resideMenuItem2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resideMenu.closeMenu();
                //  MyToast.toast("History Clicked",MainActivity.this,R.drawable.ic_history_black_24dp, Constants.TOAST_LONG);

            }
        });
        resideMenuItem3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resideMenu.closeMenu();
                PrefManager prefManager = new PrefManager(MainActivity.this);
                prefManager.setIsLoggedIn(false, 2);
                startActivity(new Intent(MainActivity.this, Login.class));
                finish();


                // MyToast.toast("Log Out Clicked",MainActivity.this,R.drawable.ic_exit_to_app_black_24dp, Constants.TOAST_LONG);
            }
        });
        resideMenu.setSwipeDirectionDisable(ResideMenu.DIRECTION_RIGHT);


    }

    void setBmb() {
        // bmb=findViewById(R.id.bmb);
        bmb.setNormalColor(R.color.colorAccent);
        bmb.setRippleEffect(true);

        bmb.setPiecePlaceEnum(PiecePlaceEnum.HAM_4);
        bmb.setButtonPlaceEnum(ButtonPlaceEnum.HAM_4);

        HamButton.Builder buildero = new HamButton.Builder()

                .listener(new OnBMClickListener() {
                    @Override
                    public void onBoomButtonClick(int index) {
                        startActivity(new Intent(MainActivity.this,Installation.class));
//                            Intent intent =new Intent(MainActivity.this,AssetActivity.class);
//                            intent.putExtra("key_id",Constants.id);
//                            startActivity(intent);


                    }
                })
                // .button().setClickable(true)
                .normalImageRes(R.drawable.code_bar)
                .normalText("Add New Asset")
                .containsSubText(true)
                .rippleEffect(true)
                .rotateImage(true)

                .subNormalText("Add a new asset to be able to track and manage")
                ;
        bmb.addBuilder(buildero);




        //for (int i = 0; i < bmb.getPiecePlaceEnum().pieceNumber(); i++) {
        HamButton.Builder builder = new HamButton.Builder()

                .listener(new OnBMClickListener() {
                    @Override
                    public void onBoomButtonClick(int index) {
                        if (checkPerrmission()) {
                            startScan();
                        } else {
                            requestPermissions();
                        }


                    }
                })
                // .button().setClickable(true)
                .normalImageRes(R.drawable.code_bar)
                .normalText("Scan Bar code")
                .containsSubText(true)
                .rippleEffect(true)
                .rotateImage(true)

                .subNormalText("Find asset by scanning a bar code");
        bmb.addBuilder(builder);



        HamButton.Builder builder1 = new HamButton.Builder()
                .normalImageRes(R.drawable.code)
                .containsSubText(true)
                .rippleEffect(true)
                .normalText("Enter Asset Code")
                .rotateImage(true)
                .listener(new OnBMClickListener() {
                    @Override
                    public void onBoomButtonClick(int index) {
                        searchByCode();


                    }
                })
                //.rotateImage(true)


                .subNormalText("Find asset by its unique code");


        bmb.addBuilder(builder1);

        HamButton.Builder builder2 = new HamButton.Builder()
                .normalImageRes(R.drawable.ic_search_black_24dp)
                .normalText("Assets List")
                .containsSubText(true)
                .rippleEffect(true)
                .subNormalText("List all assets registered")
                .listener(new OnBMClickListener() {
                    @Override
                    public void onBoomButtonClick(int index) {
                        startList();
//                            Intent intent =new Intent(MainActivity.this,AssetActivity.class);
//                            intent.putExtra("key_id",Constants.id);
//                            startActivity(intent);


                    }
                })
                ;
        bmb.addBuilder(builder2);
        bmb.setAutoBoomImmediately(false);

        bmb.setOnBoomListener(new OnBoomListener() {
            @Override
            public void onClicked(int index, BoomButton boomButton) {
                // startScan();
            }

            @Override
            public void onBackgroundClick() {

                // img_fab.setRotation(45);
                bmb.setAutoBoomImmediately(true);
            }

            @Override
            public void onBoomWillHide() {
                // img_fab.setRotation(45);
                bmb.setAutoHide(true);
            }

            @Override
            public void onBoomDidHide() {

            }

            @Override
            public void onBoomWillShow() {
                // img_fab.setRotation(45);
            }

            @Override
            public void onBoomDidShow() {

            }
        });
        //}
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dbOperations = new DbOperations(MainActivity.this);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        //img_fab=findViewById(R.id.img_btn);


        setResideMenu();

    }

    private void startList() {

        startActivity(new Intent(MainActivity.this,AssetList.class));

    }

    private void searchByCode() {
        startDialog();
    }

    private boolean enterTestData() {
        Assets assets=new Assets();
        assets.setType("Medical Equipment Name");
        assets.setImage("R");
        assets.setTag("4353254623");
        assets.setSite("Aga-Khan Nairobi Hospital");
        assets.setSerial("4432d2442d");
        assets.setLast_maintenance_by("Eric Kogi Kimani");
        assets.setLast_maintenance("2011-10-5");
        assets.setDate("2011-10-5");
        assets.setCondition("In Perfect Location");
        assets.setModel("X3R4CR4");
        assets.setInstalledby("Eric Kogi Kimani");
        assets.setContract("Yes ");

        ContentValues contentValues=new ContentValues();
        contentValues.put(DbConstants.type,assets.getType());
        contentValues.put(DbConstants.tag,assets.getTag());
        contentValues.put(DbConstants.image,assets.getImage());
        contentValues.put(DbConstants.site,assets.getSite());
        contentValues.put(DbConstants.serial,assets.getSerial());
        contentValues.put(DbConstants.last_maintenance_by,assets.getLast_maintenance_by());
        contentValues.put(DbConstants.last_maintenance,assets.getLast_maintenance());
        contentValues.put(DbConstants.condition,assets.getCondition());
        //contentValues.put(DbConstants.per);

        return dbOperations.insert(DbConstants.TABLE_ITEMS, contentValues);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
//    @Override
//    public boolean dispatchTouchEvent(MotionEvent ev) {
//        return resideMenu.dispatchTouchEvent(ev);
//    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }else if(id==R.id.action_nav){
            resideMenu.openMenu(ResideMenu.DIRECTION_LEFT);
        }

        return super.onOptionsItemSelected(item);
    }


    private void startScan() {
        /**
         * Build a new MaterialBarcodeScanner
         */
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
                .withResultListener(new MaterialBarcodeScanner.OnResultListener() {
                    @Override
                    public void onResult(Barcode barcode) {

                        int id = dbOperations.getID(DbConstants.TABLE_ITEMS_V1, DbConstants.ASSET_CODE, barcode.rawValue);
                        if(id!=9999){
                            Constants.id=String.valueOf(id);

//                            Log.d("constantidd",Constants.id);
//                            Intent intent =new Intent(MainActivity.this,AssetActivity.class);
//                            intent.putExtra("key_id",String.valueOf(id));
//                            startActivity(intent);


                            Cursor c = dbOperations.select(DbConstants.TABLE_ITEMS_V1, DbConstants.KEY_ID, id);
                            ArrayList<AssetModel> assetModels = new ArrayList<>();
                            if (c != null) {

                                assetModels = new DbContentValues().getAssetsv1(c);
                            }
                            if (assetModels != null && assetModels.size() > 0) {
                                Constants.assetModel = assetModels.get(0);
                                Intent intent = new Intent(MainActivity.this, AssetActivity.class);
                                intent.putExtra("key_id", String.valueOf(id));

                                startActivity(intent);
                            }

                        } else {
//                            Log.d("constantidd",Constants.id);
//                            int asset_id = dbOperations.getID(DbConstants.TABLE_ITEMS, DbConstants.image, "R");
//                            Log.d("assetid", String.valueOf(asset_id));
//                            Constants.id = String.valueOf(asset_id);
//
//
//                            // startActivity(new Intent(MainActivity.this,AssetActivity.class));
//
//                            //Constants.id=String.valueOf(id);
//                            Intent intent = new Intent(MainActivity.this, AssetActivity.class);
//                            intent.putExtra("key_id", String.valueOf(id));
///                            startActivity(intent);

                            MyToast.toast("Asset not found ", MainActivity.this, R.drawable.ic_error_outline_black_24dp, Toast.LENGTH_LONG);
                            snack("Asset not found");

                        }

                       // Toast.makeText(MainActivity.this, String.valueOf(barcode.rawValue), Toast.LENGTH_SHORT).show();
                    }
                })
                .build();
        materialBarcodeScanner.startScan();
    }

    @Override
    public void onClick(View v) {

    }

    private void snack(String msg) {
        ScrollView scrollView = findViewById(R.id.scroll);
        Snackbar.make(scrollView, msg, Snackbar.LENGTH_SHORT)
                .setAction("Action", null).show();
    }
    private List<TimeLineModel> getTimeline(){
        List<TimeLineModel> timeLineModels=new LinkedList<>();
        TimeLineModel t=new TimeLineModel();
        t.setDate("2011-02-12");
        t.semMessage("System installation");
        t.setStatus(OrderStatus.COMPLETED);
        t.setIssue(" None ");
        t.setFix(" None");
        t.setComment("System Installed Successfully");
        t.setPerson(" Eric Kogi (0012)");
        t.setStatus(OrderStatus.COMPLETED);


        TimeLineModel t1=new TimeLineModel();
        t1.setDate("2011-06-12");
        t1.semMessage("Routine System Maintenance");
        t1.setStatus(OrderStatus.COMPLETED);
        t1.setIssue(" None ");
        t1.setFix(" None");
        t1.setComment("System was in good condition");
        t1.setPerson(" Eric Kogi (0012)");

        TimeLineModel t2=new TimeLineModel();
        t2.setDate("2011-08-10");
        t2.semMessage("System Servicing");
        t2.setStatus(OrderStatus.COMPLETED);
        t2.setIssue(" None ");
        t2.setFix(" None");
        t2.setComment("System Was in Good condition");
        t2.setPerson(" Eric Kogi (0012)");

        TimeLineModel t3=new TimeLineModel();
        t3.setDate("2012-11-10");
        t3.semMessage("Fix on board Computer");
        t3.setStatus(OrderStatus.COMPLETED);
        t3.setIssue(" On Board Computer was over heating ");
        t3.setFix(" Replaced the heat sink");
        t3.setComment("System Now in good condition");
        t3.setPerson(" Eric Kogi (0012)");

        TimeLineModel t4=new TimeLineModel();
        t4.setDate("2012-09-08");
        t4.semMessage("Routing Maintenance");
        t4.setStatus(OrderStatus.COMPLETED);
        t4.setIssue(" None ");
        t4.setFix(" None");
        t4.setComment("System Was in good condition");
        t4.setPerson(" Eric Kogi (0012)");

        TimeLineModel t5=new TimeLineModel();
        t5.setDate("2013-07-05");
        t5.semMessage("System Servicing");
        t5.setStatus(OrderStatus.COMPLETED);
        t5.setIssue(" None ");
        t5.setFix(" None");
        t5.setComment("System Was in Good condition");
        t5.setPerson(" Eric Kogi (0012)");

        TimeLineModel t6=new TimeLineModel();
        t6.setDate("2014-02-03");
        t6.semMessage("Boot loader replacement");
        t6.setStatus(OrderStatus.COMPLETED);
        t6.setIssue(" The bootloader system had reached its service limit ");
        t6.setFix(" I replaced the bootloader ( ID 334R434) ");
        t6.setComment("System now  in Good condition");
        t6.setPerson(" Eric Kogi (0012)");

        TimeLineModel t7=new TimeLineModel();
        t7.setDate("2015-02-12");
        t7.semMessage("System Servicing");
        t7.setStatus(OrderStatus.COMPLETED);
        t7.setIssue(" None ");
        t7.setFix(" None");
        t7.setComment("System Was in Good condition");
        t7.setPerson(" Eric Kogi (0012)");

        timeLineModels.add(t7);
        timeLineModels.add(t6);
        timeLineModels.add(t5);
        timeLineModels.add(t4);
        timeLineModels.add(t3);
        timeLineModels.add(t2);
        timeLineModels.add(t1);
        timeLineModels.add(t);




        return timeLineModels;
    }


    @Override
    protected void onResume() {
        super.onResume();
        //bmb.setAutoBoomImmediately(true);
    }

    private void startDialog() {

        LayoutInflater layoutInflaterAndroid = LayoutInflater.from(MainActivity.this);
        View mView = layoutInflaterAndroid.inflate(R.layout.dialog_search_asset, null);
        AlertDialog.Builder alertDialogBuilderUserInput = new AlertDialog.Builder(MainActivity.this);
        alertDialogBuilderUserInput.setView(mView);
        alertDialogBuilderUserInput.setTitle("Search By Item Code");
       // alertDialogBuilderUserInput.setIcon(R.drawable.ic_add_black_24dp);



        // final EditText userInputDialogEditText = (EditText) mView.findViewById(R.id.userInputDialog);
        alertDialogBuilderUserInput
                .setCancelable(false)
                .setPositiveButton("Search", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogBox, int id) {
                        // ToDo get user input here

                        // dialogBox.dismiss();

                    }
                })

                .setNegativeButton("Dismiss",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogBox, int id) {
                                dialogBox.cancel();
                            }
                        });

        AlertDialog alertDialogAndroid = alertDialogBuilderUserInput.create();
        alertDialogAndroid.show();
        Button theButton = alertDialogAndroid.getButton(DialogInterface.BUTTON_POSITIVE);
        theButton.setOnClickListener(new CustomListener(alertDialogAndroid));

    }

    public void scan(View view) {
        if (checkPerrmission()) {
            startScan();
        } else {
            requestPermissions();
        }
    }

    public void entercode(View view) {
        searchByCode();
    }

    public void assetList(View view) {
        startList();
    }

    public void addNewAssetr(View view) {
    }

    public void addNewAsset(View view) {
        startActivity(new Intent(MainActivity.this, Installation.class));
//
    }

    public void assignments(View view) {

    }

    class CustomListener implements View.OnClickListener {
        private final Dialog dialog;


        public CustomListener(Dialog dialog) {
            this.dialog = dialog;

        }

        @Override
        public void onClick(View v) {
            EditText edtCode;

            edtCode=dialog.findViewById(R.id.edt_code);

            if(edtCode.getText().toString().isEmpty()){
                edtCode.setError("Required");
                return;
            }



            DbOperations dbOperations=new DbOperations(MainActivity.this);


            int id = dbOperations.getID(DbConstants.TABLE_ITEMS_V1, DbConstants.ASSET_CODE, edtCode.getText().toString());
            if(id!=9999){
                Constants.id=String.valueOf(id);

                Cursor c = dbOperations.select(DbConstants.TABLE_ITEMS_V1, DbConstants.KEY_ID, id);
                ArrayList<AssetModel> assetModels = new ArrayList<>();
                if (c != null) {

                    assetModels = new DbContentValues().getAssetsv1(c);
                }
                if (assetModels != null && assetModels.size() > 0) {
                    Constants.assetModel = assetModels.get(0);
                    Intent intent = new Intent(MainActivity.this, AssetActivity.class);
                    intent.putExtra("key_id", String.valueOf(id));

                    startActivity(intent);
                }

            }else {
                MyToast.toast("Could not find item",MainActivity.this,R.drawable.ic_error_outline_black_24dp,Constants.TOAST_LONG);
//                int asset_id = dbOperations.getID(DbConstants.TABLE_ITEMS, DbConstants.image, "R");
//                Log.d("assetid", String.valueOf(asset_id));
//                Constants.id = String.valueOf(asset_id);
//
//
//                // startActivity(new Intent(MainActivity.this,AssetActivity.class));
//
//                //Constants.id=String.valueOf(id);
//                Intent intent = new Intent(MainActivity.this, AssetActivity.class);
//                intent.putExtra("key_id", String.valueOf(id));
//                startActivity(intent);
            }
            //contentValues.put(DbConstants.);



        }


    }


    private boolean checkPerrmission() {
        return Build.VERSION.SDK_INT < Build.VERSION_CODES.M || checkSelfPermission(Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED;
    }
    private void  requestPermissions(){
        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.CAMERA},100);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode==100&&grantResults.length>0){
           if(grantResults[0]==PackageManager.PERMISSION_GRANTED){
               startScan();
           }else {
               permisionDeneied();
           }




        }
    }
    private void dial(){
        Dialog dialog=new Dialog(MainActivity.this);
        dialog.setContentView(R.layout.dialog_new_issue);
        EditText   edtIssue=dialog.findViewById(R.id.edt_issue_title);
        TextView txtBy=dialog.findViewById(R.id.txt_by);

        txtBy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edtIssue.getText().toString().isEmpty()){
                    MyToast.toast("Fill issue",MainActivity.this,R.drawable.ic_error_outline_black_24dp,Constants.TOAST_LONG);
                }else {
                    dialog.dismiss();
                }
            }
        });
        dialog.show();



    }
    private void permisionDeneied() {
        Dialog dialog=new Dialog(MainActivity.this);
        dialog.setCancelable(false);
        dialog.setTitle("Permission Denied");
        //dialog.setM

    }
}
