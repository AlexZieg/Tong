package de.uni_stuttgart.sopra_ws1617_team8.tong;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;

/**
 * Created by Alex on 24.01.2017.
 */

public class Permissions {

    public void checkStoragePermission(Context context, Activity activity) {
        int permissionRW = ActivityCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE);

        // If we don't have permissions, ask user for permissions
        if (permissionRW != PackageManager.PERMISSION_GRANTED) {
            String[] PERMISSIONS_STORAGE = {
                    android.Manifest.permission.READ_EXTERNAL_STORAGE,
                    android.Manifest.permission.WRITE_EXTERNAL_STORAGE
            };
            int REQUEST_EXTERNAL_STORAGE = 1;

            ActivityCompat.requestPermissions(
                    activity,
                    PERMISSIONS_STORAGE,
                    REQUEST_EXTERNAL_STORAGE
            );
        }
    }

    void checkMultiPermission(Context context, Activity activity) {
        int permissionMIC = ActivityCompat.checkSelfPermission(context, Manifest.permission.RECORD_AUDIO);
        int permissionWrite = ActivityCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        int permissionRead = ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_EXTERNAL_STORAGE);

        int granted = PackageManager.PERMISSION_GRANTED;

        if (permissionMIC != granted || permissionWrite != granted || permissionRead != granted) {
            ActivityCompat.requestPermissions(
                    activity,
                    new String[]{Manifest.permission.RECORD_AUDIO,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE,
                            Manifest.permission.READ_EXTERNAL_STORAGE},
                    1);
        }

    }

    /**
     * get the permission to use the microphone
     *
     * @param context
     * @param activity
     */
    public void checkMicrophonePermission(Context context, Activity activity) {
        int permissionMIC = ActivityCompat.checkSelfPermission(context, Manifest.permission.RECORD_AUDIO);

        if (permissionMIC != PackageManager.PERMISSION_GRANTED) {
            int REQUEST_RECORD_AUDIO = 1;
            ActivityCompat.requestPermissions(
                    activity,
                    new String[]{Manifest.permission.RECORD_AUDIO},
                    REQUEST_RECORD_AUDIO);
        }
    }
}
