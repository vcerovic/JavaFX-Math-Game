package com.veljkocerovic.utils;

import javafx.scene.control.Alert;

public class AlertUtils {

    public static void showAlertMessage(String msg, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setContentText(msg);
        alert.show();
    }
}
