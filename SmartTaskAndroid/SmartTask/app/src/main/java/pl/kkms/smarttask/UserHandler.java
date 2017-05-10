package pl.kkms.smarttask;

import pl.kkms.smarttask.model.User;

/**
 * Created by Damian on 2017-04-21.
 */

public class UserHandler {
    private User user;

    private static UserHandler instance;
    private UserHandler(){}

    public static UserHandler getInstance(){
        if (instance == null) {
            instance = new UserHandler();
        }
        return instance;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
