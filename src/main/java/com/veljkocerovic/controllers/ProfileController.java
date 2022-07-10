package com.veljkocerovic.controllers;

import com.veljkocerovic.database.UserDAO;
import com.veljkocerovic.models.User;
import com.veljkocerovic.models.UserSession;
import com.veljkocerovic.utils.AlertUtils;
import com.veljkocerovic.utils.ValidationUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class ProfileController {

    @FXML
    private HBox profileGroup;
    @FXML
    private Label usernameLbl;
    @FXML
    private Label userHighscoreLbl;

    @FXML
    private Label userTimesPlayedLbl;

    @FXML
    private TextField usernameTextFld;

    private User activeUser;

    public void initialize() {
        //User Session
        activeUser = UserSession.getInstance().getActiveUser();

        if (activeUser != null) {
            usernameLbl.setText(activeUser.getUsername());
            userHighscoreLbl.setText(String.valueOf(activeUser.getHighscore()));
            userTimesPlayedLbl.setText(String.valueOf(activeUser.getTimesPlayed()));
        } else {
            AlertUtils.showAlertMessage("You didn't select any profile, play first.", Alert.AlertType.INFORMATION);
            profileGroup.setVisible(false);
        }

    }

    @FXML
    public void switchToHomePage(ActionEvent event) {
        SceneController sceneController = SceneController.getInstance();
        sceneController.showScene(((Node) event.getSource()).getScene(), "Home");
    }

    @FXML
    public void updateUser(ActionEvent event) {
        if (ValidationUtils.validateTextFld(usernameTextFld.getText())) {
            activeUser.setUsername(usernameTextFld.getText());
            UserDAO.changeUsername(activeUser.getId(), activeUser);
            UserSession.getInstance().setActiveUser(activeUser);
            SceneController sceneController = SceneController.getInstance();
            sceneController.showScene(((Node) event.getSource()).getScene(),"Profile");
        }
    }

    @FXML
    public void deleteUser(ActionEvent event) {
        UserDAO.deleteUser(activeUser.getId());
        UserSession.getInstance().setActiveUser(null);
        SceneController sceneController = SceneController.getInstance();
        sceneController.showScene(((Node) event.getSource()).getScene(),"Home");
    }
}
