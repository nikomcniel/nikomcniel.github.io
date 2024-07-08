package edu.csueb.android.zoodirectoryapp;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;

public class UninstallUtil {
    // Creates and calls intent to uninstall app
    public static void uninstall(Activity currentActivity){
        Intent delete = new Intent(Intent.ACTION_DELETE, Uri.parse("package:edu.csueb.android.zoodirectoryapp"));
        currentActivity.startActivity(delete);
    }
}
