import 'package:flutter/services.dart';

class ChannelPm {

  static const MethodChannel _channel = MethodChannel('pm');

  static Future<String> getPlatformVersion() async {
    final String result = await _channel.invokeMethod("getPlatformVersion");
    print("dart getPlatformVersion =====>>> $result");
    return result;
  }

  static Future<bool> isPackageInstalled(String packageName) async {
    final bool result = await _channel.invokeMethod("isPackageInstalled", packageName);
    print("dart isPackageInstalled =====>>> $result");
    return result;
  }

  static Future<int> calculate(int a, int b) async {
    final int result = await _channel.invokeMethod("calculate", {"a":a, "b":b});
    print("dart calculate =====>>> $result");
    return result;
  }

}
