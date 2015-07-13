package com.inventure.test.aftest;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.Toast;

import com.mixpanel.android.mpmetrics.MixpanelAPI;

import org.json.JSONException;
import org.json.JSONObject;


public class MainActivity extends Activity {

    MixpanelAPI mMixpanel;
    JSONObject props;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String projectToken = "7065ba64bf3b84db62283dafe92d2b72";
        mMixpanel = MixpanelAPI.getInstance(getBaseContext(), projectToken);
        TelephonyManager telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        final String deviceId = telephonyManager.getDeviceId();


        // Delay event to see if it works or not
        android.os.Handler handler = new android.os.Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                try {
                    mMixpanel.identify(deviceId);
                    props = new JSONObject();
                    props.put("TEST", "WITH PROPS");
                    mMixpanel.track("Install", props);
                    Log.d("TEST", "FIRST");
                    Toast.makeText(getBaseContext(), "CALLED", Toast.LENGTH_LONG).show();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, 5000);

    }
}