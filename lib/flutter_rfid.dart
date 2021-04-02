
import 'dart:async';

import 'package:flutter/services.dart';

class FlutterRfid {
  static const MethodChannel _channel =
      const MethodChannel('flutter_rfid');

  static Future<String> get platformVersion async {
    final String version = await _channel.invokeMethod('getPlatformVersion');
    return version;
  }

  static Future<String> get rfidRead async {
    final String code = await _channel.invokeMethod('rfidRead');
    return code;
  }

}
