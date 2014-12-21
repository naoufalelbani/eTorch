package com.newfeel.etorch;

import android.content.Context;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
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
            Toast.makeText(context ,"Error: Your device does not support flash light!", Toast.LENGTH_LONG).show();
        }

        switchLight.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                try{
                    if(light.getStatus() == light.LIGHT_OFF) {
                        light.lightOn();
                    } else {
                        light.lightOff();
                    }
                } catch(Exception e) {
                    Toast.makeText(context ,"Error: " + e.getMessage(), Toast.LENGTH_LONG).show();
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_torch, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
