package com.erickogi14gmail.assettrack.Views;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.edwardvanraak.materialbarcodescanner.MaterialBarcodeScanner;
import com.edwardvanraak.materialbarcodescanner.MaterialBarcodeScannerBuilder;
import com.erickogi14gmail.assettrack.R;
import com.google.android.gms.vision.barcode.Barcode;

public class ScanBarCode extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scan);
        startScan();
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

                        Toast.makeText(ScanBarCode.this, String.valueOf(barcode.rawValue), Toast.LENGTH_SHORT).show();
                    }
                })
                .build();
        materialBarcodeScanner.startScan();
    }

}
