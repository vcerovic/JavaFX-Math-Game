package com.veljkocerovic.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserSession {

    private static UserSession session;

    private User activeUser;

    private UserSession(){

    }

    public static UserSession getInstance() {
        if (session == null)
            session = new UserSession();

        return session;
    }

}
