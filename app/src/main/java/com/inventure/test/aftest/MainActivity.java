package com.inventure.test.aftest;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

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
        mMixpanel = MixpanelAPI.getInstance(this, projectToken);
        try {
            props = new JSONObject();
            props.put("USER", "Name Goes HERE");
            props.put("ID", "ID Goes HERE");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMixpanel.track("My Event", props);
            }
        });
    }

    @Override
    protected void onDestroy() {
        mMixpanel.flush();
        super.onDestroy();
    }
}