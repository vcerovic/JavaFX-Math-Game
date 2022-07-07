package com.veljkocerovic.controllers;

import com.veljkocerovic.database.UserDAO;
import com.veljkocerovic.models.User;
import javafx.fxml.FXML;

import java.util.Set;

public class LeaderboardController {

    @FXML
    public void initialize(){
        Set<User> users = UserDAO.getAllUsersByHighscore();
        users.forEach(System.out::println);
    }
}
