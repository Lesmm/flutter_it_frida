package com.example.flutter_it_frida.channel;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.util.Log;

import com.example.flutter_it_frida.activity.MainActivity;

import io.flutter.plugin.common.BinaryMessenger;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;

public class ChannelPm implements MethodChannel.MethodCallHandler {

    public static void registerWith(BinaryMessenger messenger) {
        final MethodChannel channel = new MethodChannel(messenger, "pm");
        channel.setMethodCallHandler(new ChannelPm());
    }

    @Override
    public void onMethodCall(MethodCall call, MethodChannel.Result result) {
        if (call.method.equals("getPlatformVersion")) {
            result.success("Android " + Build.VERSION.RELEASE);
        } else if (call.method.equals("calculate")) {
            int a = call.argument("a");
            int b = call.argument("b");
            int r = a + b;
            result.success(r);
        } else if (call.method.equals("isPackageInstalled")) {
            Object arguments = call.arguments();
            String s = arguments.toString();
            Log.d("======>>>", "arguments: " + s);
            PackageManager packageManager = MainActivity.instance.getPackageManager();
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                try {
                    PackageInfo packageInfo = packageManager.getPackageInfo(s, 0);
                    Log.d("======>>>", "packageInfo == null ? " + (packageInfo == null));
                    result.success(packageInfo != null);
                } catch (PackageManager.NameNotFoundException e) {
                    e.printStackTrace();
                }
            }
            result.success(false);

        } else {
            result.notImplemented();
        }
    }

}

