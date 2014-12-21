package com.newfeel.etorch;

import android.content.Context;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.newfeel.etorch.camera_flash.Light;


public class MainTorchActivity extends ActionBarActivity {

    ToggleButton switchLight;
    TextView message;

    Light light;

    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_torch);

        context = MainTorchActivity.this;

        switchLight = (ToggleButton) findViewById(R.id.switch_light);
        message = (TextView) findViewById(R.id.message);

        light = new Light(context);

        // check if flash is supported
        if (!light.supportFLash()) {
            Toast.makeText(context, "Error: Your device does not support flash light!", Toast.LENGTH_LONG).show();
        }

        switchLight.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                try {
                    if (light.getStatus() == light.LIGHT_OFF) {
                        light.lightOn();
                    } else {
                        light.lightOff();
                    }
                } catch (Exception e) {
                    Toast.makeText(context, "Error: " + e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    @Override
    protected void onPause() {
        light.release();

        super.onPause();
    }

    @Override
    protected void onResume() {
        light = new Light(context);

        super.onResume();
    }
}