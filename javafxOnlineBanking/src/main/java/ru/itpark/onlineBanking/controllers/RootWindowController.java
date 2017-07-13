package ru.itpark.onlineBanking.controllers;

import javafx.fxml.FXML;
import ru.itpark.onlineBanking.Main;
import ru.itpark.onlineBanking.models.User;

public class RootWindowController {

    private Main main;

    private User user;

    public void setMain(Main main) {
        this.main = main;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @FXML
    private void handleExit() {
        main.saveToken("0");
        System.exit(0);
    }

    @FXML
    public void showProfileWindow() {
        main.showProfileWindow(user);
    }
}
