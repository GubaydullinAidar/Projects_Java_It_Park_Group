package ru.itpark.onlineBanking.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import ru.itpark.onlineBanking.Main;
import ru.itpark.onlineBanking.app.OnlineBankingRestTemp;
import ru.itpark.onlineBanking.models.AccountTransaction;
import ru.itpark.onlineBanking.models.User;

import java.util.List;

public class MainWindowController {

    @FXML
    private Label primaryBalance;

    @FXML
    private Label savingsBalance;

    @FXML
    private TableView<AccountTransaction> tableTransactions;

    @FXML
    private TableColumn<AccountTransaction, String> date;

    @FXML
    private TableColumn<AccountTransaction, String> description;

    @FXML
    private TableColumn<AccountTransaction, String> status;

    @FXML
    private TableColumn<AccountTransaction, Double> amount;

    @FXML
    private TableColumn<AccountTransaction, Double> availableBalance;

    private Main main;

    private OnlineBankingRestTemp onlineBankingRestTemp;

    private ObservableList<AccountTransaction> transactionsData = FXCollections.observableArrayList();

    private User user;

    public void setMain(Main main) {
        this.main = main;
    }

    public void setOnlineBankingRestTemp(OnlineBankingRestTemp onlineBankingRestTemp) {
        this.onlineBankingRestTemp = onlineBankingRestTemp;
    }


    public void setUser(User user) {
        this.user = user;
    }

    @FXML
    public void initialize() {
        date.setCellValueFactory(new PropertyValueFactory<>("dateStr"));
        description.setCellValueFactory(new PropertyValueFactory<>("description"));
        status.setCellValueFactory(new PropertyValueFactory<>("status"));
        amount.setCellValueFactory(new PropertyValueFactory<>("amount"));
        availableBalance.setCellValueFactory(new PropertyValueFactory<>("availableBalance"));
        tableTransactions.setItems(transactionsData);

    }

    @FXML
    public void primaryTransaction() {
        List<AccountTransaction> data = onlineBankingRestTemp.primaryTransactionList(user);
        transactionsData.clear();
        transactionsData.addAll(data);
    }

    @FXML
    public void savingsTransaction() {
        List<AccountTransaction> data = onlineBankingRestTemp.savingsTransactionList(user);
        transactionsData.clear();
        transactionsData.addAll(data);
    }

    public void refillDebit() {
        main.showRefillDebitWindow(user);
        getPrimaryBalance();
        getSavingsBalance();
    }

    public void getPrimaryBalance() {
        primaryBalance.setText(onlineBankingRestTemp.getPrimaryBalance(user.getToken()));
    }

    public void getSavingsBalance() {
        savingsBalance.setText(onlineBankingRestTemp.getSavingsBalance(user.getToken()));
    }


}
