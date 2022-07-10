package com.veljkocerovic.utils;

import javafx.scene.control.Alert;

public class ValidationUtils {

    public static boolean validateTextFld(String text){
        boolean isValid = true;

        if(text.equals("")){
            isValid = false;
            AlertUtils.showAlertMessage("You must enter username", Alert.AlertType.ERROR);
        } else if (text.length() > 8) {
            isValid = false;
            AlertUtils.showAlertMessage("Username can only be 8 characters", Alert.AlertType.ERROR);
        }

        return isValid;
    }
}
