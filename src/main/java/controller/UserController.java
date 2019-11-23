package controller;

import models.ChatUser;
/**
 * @apiNote Quando o usuário loga, fica como instancia para obter em qualquer lugar...
 * **/
public class UserController {
    public static UserController instance;
    private ChatUser user;

    private UserController(ChatUser user) {
        this.user = user;
    }

    public static UserController getInstance() {
        return instance;
    }

    public static synchronized UserController setInstance(ChatUser user) {
        instance = new UserController(user);
        return instance;
    }

    public ChatUser getUser() {
        return user;
    }

    public void setUser(ChatUser user) {
        this.user = user;
    }
}
