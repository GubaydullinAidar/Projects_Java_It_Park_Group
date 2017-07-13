package ru.itpark.onlineBanking.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import ru.itpark.onlineBanking.app.OnlineBankingRestTemp;
import ru.itpark.onlineBanking.models.User;

public class RefillDebitWindowController {

    private OnlineBankingRestTemp onlineBankingRestTemp;

    @FXML
    private TextField primaryAmount;

    @FXML
    private TextField savingsAmount;

    private User user;

    public void setUser(User user) {
        this.user = user;
    }

    public void setOnlineBankingRestTemp(OnlineBankingRestTemp onlineBankingRestTemp) {
        this.onlineBankingRestTemp = onlineBankingRestTemp;
    }

    @FXML
    public void handleRefillPrimaryAccount() {
        if (primaryAmount.getText() != null || !primaryAmount.getText().equals("")) {
            String refilPrimaryAccount = onlineBankingRestTemp.refillPrimaryAccount(user.getToken(), primaryAmount.getText());

            if (refilPrimaryAccount != null) {
                okMessage(refilPrimaryAccount);
            } else {
                errorMessage();
            }
        }
    }

    @FXML
    public void handleRefillSavingsAccount() {
        if (savingsAmount.getText() != null || !savingsAmount.getText().equals("")) {
            String refilSavingsAccount = onlineBankingRestTemp.refillSavingsAccount(user.getToken(), savingsAmount.getText());

            if (refilSavingsAccount != null) {
                okMessage(refilSavingsAccount);
            } else {
                errorMessage();
            }
        }
    }

    @FXML
    public void handleDebitPrimaryAccount() {
        if (primaryAmount.getText() != null || !primaryAmount.getText().equals("")) {
            String debitPrimaryAccount = onlineBankingRestTemp.debitPrimaryAccount(user.getToken(), primaryAmount.getText());

            if (debitPrimaryAccount != null) {
                okMessage(debitPrimaryAccount);
            } else {
                errorMessage();
            }
        }
    }

    @FXML
    public void handleDebitSavingsAccount() {
        if (savingsAmount.getText() != null || !savingsAmount.getText().equals("")) {
            String debitSavingsAccount = onlineBankingRestTemp.debitSavingsAccount(user.getToken(), savingsAmount.getText());

            if (debitSavingsAccount != null) {
                okMessage(debitSavingsAccount);
            } else {
                errorMessage();
            }
        }
    }

    private void errorMessage() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(null);
        alert.setHeaderText(null);
        alert.setContentText("Извините, произошла ошибка");
        alert.showAndWait();
    }

    private void okMessage(String balance) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(null);
        alert.setHeaderText(null);
        alert.setContentText("Платеж прошел успешно. Баланс: " + balance);

        alert.showAndWait();
    }

}
