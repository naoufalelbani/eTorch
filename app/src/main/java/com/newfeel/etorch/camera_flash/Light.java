package com.newfeel.etorch.camera_flash;

import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.util.Log;

import java.util.List;

/**
 * Created by EL BANI Naoufal on 20/12/2014.
 */
public class Light {
    public final boolean LIGHT_ON  = true;
    public final boolean LIGHT_OFF = false;

    private boolean status;

    private Camera cam;
    private Camera.Parameters camParamsOn;
    private Camera.Parameters camParamsOff;

    private Context context;

    public Light(Context context) {
        status = LIGHT_OFF;

        this.context = context;

        this.cam = Camera.open();
        this.camParamsOn = cam.getParameters();
        this.camParamsOff = cam.getParameters();

        List<String> flashModes = camParamsOn.getSupportedFlashModes();
        if(flashModes.contains(Camera.Parameters.FLASH_MODE_TORCH))
            this.camParamsOn.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
        flashModes = camParamsOff.getSupportedFlashModes();
        if(flashModes.contains(Camera.Parameters.FLASH_MODE_OFF))
            this.camParamsOff.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);


    }

    /**
     * <p>Turn light on.</p>
     */
    public void lightOn() {
        if(this.status == LIGHT_OFF) {
            this.cam.setParameters(camParamsOn);

            this.setStatus(LIGHT_ON);
        }
//            this.cam.startPreview();
    }

    /**
     * <p>Turn light off.</p>
     */
    public void lightOff() {
        if(this.status == LIGHT_ON) {
            this.cam.setParameters(camParamsOff);

            this.setStatus(LIGHT_OFF);
//            this.cam.stopPreview();
//            this.cam.release();
        }
    }

    /**
     * <p></p>Release Camera.</p>
     */
    public void release() {
        cam.release();
    }

    /**
     * Support flash.
     * @return boolean <p>Flash is/isn't supported by device.</p>
     */
    public boolean supportFLash() {
        return context.getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH);
    }

    public boolean getStatus() {
        return this.status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
