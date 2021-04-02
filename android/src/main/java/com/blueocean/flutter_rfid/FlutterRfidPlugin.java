package com.blueocean.flutter_rfid;

import android.os.AsyncTask;
import android.util.Log;
import android.view.KeyEvent;

import androidx.annotation.NonNull;

import io.flutter.embedding.engine.plugins.FlutterPlugin;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.MethodChannel.MethodCallHandler;
import io.flutter.plugin.common.MethodChannel.Result;
import io.flutter.plugin.common.PluginRegistry.Registrar;

import com.rscja.deviceapi.RFIDWithUHFUART;
import com.rscja.deviceapi.entity.UHFTAGInfo;
import com.rscja.utility.StringUtility;

/** FlutterRfidPlugin */
public class FlutterRfidPlugin implements FlutterPlugin, MethodCallHandler {
  /// The MethodChannel that will the communication between Flutter and native Android
  ///
  /// This local reference serves to register the plugin with the Flutter Engine and unregister it
  /// when the Flutter Engine is detached from the Activity
  public static MethodChannel channel;
//  private MethodChannel channel;
  public RFIDWithUHFUART mReader;
//  public static MethodChannel publicChannel;

  @Override
  public void onAttachedToEngine(@NonNull FlutterPluginBinding flutterPluginBinding) {
    channel = new MethodChannel(flutterPluginBinding.getBinaryMessenger(), "flutter_rfid");
    channel.setMethodCallHandler(this);
  }

  @Override
  public void onMethodCall(@NonNull MethodCall call, @NonNull Result result) {

    if (getDeviceModel().equals("handheld")) {
      initUHF();
    }

    if (call.method.equals("getPlatformVersion")) {
      result.success("Android " + android.os.Build.VERSION.RELEASE);
    }  else  if (call.method.equals("rfidRead")) {
      String rfid = "";
      if (getDeviceModel().equals("handheld")) {
        rfid = readTag();
        // rfid = "我是一条测试信息";
        if (rfid != null && !rfid.isEmpty()) {
//          playSound(1);
          result.success(rfid);
        } else {
//          playSound(2);
          result.error("1000", "未获取到RFID", null);
        }
      } else {
        result.error("1001", "该设备不是手持机", null);
      }
    } else {
      result.notImplemented();
    }
  }

  @Override
  public void onDetachedFromEngine(@NonNull FlutterPluginBinding binding) {
    channel.setMethodCallHandler(null);
  }

  public String getDeviceModel() {
    try {
      return android.os.Build.MODEL;
    } catch (Exception e) {
      return "";
    }
  }

  public String readTag() {
    UHFTAGInfo strUII = mReader.inventorySingleTag();
    if (strUII != null) {
      return strUII.getEPC();
    } else {
      return null;
    }
  }

  public void initUHF() {
    try {
      mReader = RFIDWithUHFUART.getInstance();
    } catch (Exception ex) {
      ex.printStackTrace();
      return;
    }

    if (mReader != null) {
      new InitTask().execute();
    }
  }
  public class InitTask extends AsyncTask<String, Integer, Boolean> {
    @Override
    protected Boolean doInBackground(String... params) {
      // TODO Auto-generated method stub
      boolean result = false;
      return mReader.init();
    }

    @Override
    protected void onPostExecute(Boolean result) {
      super.onPostExecute(result);
      if (result) {
      }
    }

    @Override
    protected void onPreExecute() {
      // TODO Auto-generated method stub
      super.onPreExecute();

    }
  }


}
