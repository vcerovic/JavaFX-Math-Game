package com.veljkocerovic.controllers;

import com.veljkocerovic.database.UserDAO;
import com.veljkocerovic.models.Difficulty;
import com.veljkocerovic.models.GameManager;
import com.veljkocerovic.models.User;
import com.veljkocerovic.models.UserSession;
import com.veljkocerovic.utils.AlertUtils;
import com.veljkocerovic.utils.ValidationUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;

import java.util.List;
import java.util.Optional;

public class OptionsController {

    @FXML
    private ChoiceBox<String> userChoiceBox;
    @FXML
    private TextField usernameTextFld;
    @FXML
    private ToggleGroup difficultyGroup;

    @FXML
    public void initialize(){
        List<User> users = UserDAO.getAllUsersByHighscore();
        users.forEach(user -> userChoiceBox.getItems().add(user.getUsername()));
    }
    @FXML
    private void playGame(ActionEvent event) {
        RadioButton radioButton = (RadioButton) difficultyGroup.getSelectedToggle();
        boolean success = false;

        if (userChoiceBox.getValue() == null){
                if(ValidationUtils.validateTextFld(usernameTextFld.getText())) {
                    //Create user and play as him
                    UserSession userSession = UserSession.getInstance();
                    User newUser = new User(usernameTextFld.getText().trim().toLowerCase());

                    //Save user
                    boolean isSaved = UserDAO.saveUser(newUser);

                    System.out.println(isSaved);

                    if (isSaved) {
                        userSession.setActiveUser(newUser);
                        success = true;
                    }
                }
        } else {
            //Play as selected user
            Optional<User> optionalUser = UserDAO.getUserByUsername(userChoiceBox.getValue().trim().toLowerCase());
            User user = optionalUser.orElseThrow(() -> new RuntimeException("No user found with that username"));
            success = true;

            //Set selected user as active
            UserSession userSession = UserSession.getInstance();
            userSession.setActiveUser(user);

        }



        switch (radioButton.getText()) {
            case "Easy" -> GameManager.getInstance().setDifficulty(Difficulty.EASY);
            case "Medium" -> GameManager.getInstance().setDifficulty(Difficulty.MEDIUM);
            case "Hard" -> GameManager.getInstance().setDifficulty(Difficulty.HARD);
        }


        if(success){
            SceneController sceneController = SceneController.getInstance();
            sceneController.showScene(((Node) event.getSource()).getScene(),"Gameplay");
        }
    }

    public void switchToHomePage(ActionEvent event) {
        SceneController sceneController = SceneController.getInstance();
        sceneController.showScene(((Node) event.getSource()).getScene(),"Home");
    }
}
