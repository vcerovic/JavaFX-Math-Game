package com.veljkocerovic.controllers;

import com.veljkocerovic.database.UserDAO;
import com.veljkocerovic.models.User;
import com.veljkocerovic.models.UserTableModel;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;

import java.util.ArrayList;
import java.util.List;

public class LeaderboardController {

    @FXML
    private TableView<UserTableModel> leaderBoardTable;

    @FXML
    public void initialize(){
        List<User> users = UserDAO.getAllUsersByHighscore();
        ArrayList<UserTableModel> userTableModels = new ArrayList<>();


        for (int i = 0; i < users.size(); i++) {
            userTableModels.add(new UserTableModel(i+1, users.get(i)));
        }

        leaderBoardTable.getItems().addAll(userTableModels);
    }
}
