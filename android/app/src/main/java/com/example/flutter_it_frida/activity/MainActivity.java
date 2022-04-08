package com.example.flutter_it_frida.activity;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.example.flutter_it_frida.channel.ChannelPm;

import io.flutter.embedding.android.FlutterActivity;

public class MainActivity extends FlutterActivity {

    public static MainActivity instance = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        instance = this;

        ChannelPm.registerWith( getFlutterEngine().getDartExecutor());
        // android.os.Debug.waitForDebugger();
    }

}
