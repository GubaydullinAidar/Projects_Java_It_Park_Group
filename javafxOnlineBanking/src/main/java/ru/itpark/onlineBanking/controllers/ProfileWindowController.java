package ru.itpark.onlineBanking.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import ru.itpark.onlineBanking.app.OnlineBankingRestTemp;
import ru.itpark.onlineBanking.models.User;

public class ProfileWindowController {

    @FXML
    private Label firstname;

    @FXML
    private Label lastname;

    @FXML
    private Label email;

    @FXML
    private Label phone;

    @FXML
    private TextField changeFirstname;

    @FXML
    private TextField changeLastname;

    @FXML
    private TextField changeEmail;

    @FXML
    private TextField changePhone;

    private User user;
    private OnlineBankingRestTemp onlineBankingRestTemp;

    public ProfileWindowController() {
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setOnlineBankingRestTemp(OnlineBankingRestTemp onlineBankingRestTemp) {
        this.onlineBankingRestTemp = onlineBankingRestTemp;
    }

    public void init() {
        firstname.setText(user.getFirstName());
        lastname.setText(user.getLastName());
        phone.setText(user.getPhone());
        email.setText(user.getEmail());
    }

    public void saveChange() {
        User newUser = user;
        if (changeFirstname.getText() != null && !changeFirstname.getText().equals("")) {
            newUser.setFirstName(changeFirstname.getText());
        }
        if (changeLastname.getText() != null && !changeLastname.getText().equals("")) {
            newUser.setLastName(changeLastname.getText());
        }
        if (changeEmail.getText() != null && !changeEmail.getText().equals("")) {
            newUser.setEmail(changeEmail.getText());
        }
        if (changePhone.getText() != null && !changePhone.getText().equals("")) {
            newUser.setPhone(changePhone.getText());
        }

        User changedUser = onlineBankingRestTemp.saveShange(newUser);
        if (changedUser != null) {
            user = changedUser;
            init();
        }
    }
}
