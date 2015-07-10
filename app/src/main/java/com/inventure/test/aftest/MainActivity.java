package com.inventure.test.aftest;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.telephony.TelephonyManager;

import com.mixpanel.android.mpmetrics.MixpanelAPI;


public class MainActivity extends Activity {

    MixpanelAPI mMixpanel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String projectToken = "7065ba64bf3b84db62283dafe92d2b72";
        mMixpanel = MixpanelAPI.getInstance(this, projectToken);
        TelephonyManager telephonyManager = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
        String deviceId = telephonyManager.getDeviceId();
        mMixpanel.identify(deviceId);
        mMixpanel.track("AppInstall");
    }

    @Override
    protected void onDestroy() {
        mMixpanel.flush();
        super.onDestroy();
    }
}