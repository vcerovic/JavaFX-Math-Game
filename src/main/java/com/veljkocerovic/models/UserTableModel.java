package com.veljkocerovic.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserTableModel {
    private int rank;
    private String username;
    private int highscore;
    private int timesPlayed;

    public UserTableModel(int rank, User user){
        this.rank = rank;
        this.username = user.getUsername();
        this.highscore = user.getHighscore();
        this.timesPlayed = user.getTimesPlayed();
    }
}
