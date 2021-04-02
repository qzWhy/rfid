package com.blueocean.flutter_rfid_example;

import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;

import io.flutter.embedding.android.FlutterActivity;
import io.flutter.plugin.common.BinaryMessenger;
import io.flutter.plugin.common.MethodChannel;
import com.blueocean.flutter_rfid.FlutterRfidPlugin;


public class MainActivity extends FlutterActivity {

   private static final String CHANNEL = "flutter_rfid";

    MethodChannel methodChannel;
//
    @Override
    protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
//    GeneratedPluginRegistrant.registerWith(this);
//
//    idataRfid = new iDataRFID(MainActivity.this);
//    idataRfid.openRfid();
}

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == 139 || keyCode == 280 || keyCode == 293) {

            if (event.getRepeatCount() == 0) {
                Log.v("qluerp", "tigger clicked");
//                methodChannel.invokeMethod("rfidRead", null);

            }
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if (keyCode == 139 || keyCode == 280) {
            if (event.getRepeatCount() == 0) {
//                barcode2DWithSoft.stopScan();
//        methodChannel.invokeMethod("triggerClickedup", null);
                return true;
            }
        }
        return super.onKeyUp(keyCode, event);
    }

    BinaryMessenger getFlutterView() {
        return  getFlutterEngine().getDartExecutor().getBinaryMessenger();
    }
}
