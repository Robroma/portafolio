package com.example.androidfirebase.controller;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.widget.Toast;

import com.google.firebase.Timestamp;

import java.io.Serializable;

public interface ControllerInterface {
    void createActivityButtons();

    default String ifNullString(Object o) {
        if (o == null) return "";
        if (o instanceof Timestamp) return ((Timestamp) o).toDate().toString();
        return (String) o;
    }

    default int ifNullInt(Object o) {
        if (o == null) return 0;
        return (int) ((long) o);
    }

    default void showAlert(Activity activity) {
        showAlert(activity, "Se ha porducido un error");
    }

    default void showAlert(Activity activity, String message) {
        showAlert(activity, message, "ERROR");
    }

    default void showAlert(Activity activity, String message, String title) {
        AlertDialog dialog = new AlertDialog.Builder(activity)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton("Aceptar", null)
                .create();
        dialog.show();
    }

    default void alertToast(Activity activity, String message) {
        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show();
    }

    default void switchActivity(Activity from, Activity to) {
        switchActivity(from, to, "", null);
    }

    default void switchActivity(Activity from, Activity to, String extraKey, Object extra) {
        Intent intent = new Intent(from, to.getClass());
        if (extra != null) {
            intent.putExtra(extraKey, (Serializable) extra);
        }
        from.startActivity(intent);
    }
}

