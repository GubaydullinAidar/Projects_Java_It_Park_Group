package ru.itpark.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import ru.itpark.models.PrimaryAccount;
import ru.itpark.models.PrimaryTransaction;
import ru.itpark.models.SavingsAccount;
import ru.itpark.models.SavingsTransaction;
import ru.itpark.service.AccountService;
import ru.itpark.service.TransactionService;
import ru.itpark.service.UserService;

import java.util.List;

@RestController
public class AccountController {

    @Autowired
    private UserService userService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private TransactionService transactionService;

    @PostMapping("/user/{user-id}/primaryAccountTransaction")
    public ResponseEntity<List<PrimaryTransaction>> primaryTransaction(@PathVariable("user-id") Long userId) {
        List<PrimaryTransaction> primaryTransactionList = transactionService.findPrimaryTransactionList(userId);
        return new ResponseEntity<>(primaryTransactionList, HttpStatus.OK);
    }

    @PostMapping("/user/{user-id}/savingsAccountTransaction")
    public ResponseEntity<List<SavingsTransaction>> savingsTransaction(@PathVariable("user-id") Long userId) {
        List<SavingsTransaction> savingsTransactionList = transactionService.findSavingsTransactionList(userId);
        return new ResponseEntity<>(savingsTransactionList, HttpStatus.OK);
    }

    @PostMapping("/refillPrimaryAccount")
    public ResponseEntity<String> refillPrimaryAccount(@RequestHeader("Auth-token") String token,
                                                       @RequestHeader("amount") String amount) {
        PrimaryAccount primaryAccount = userService.findByToken(token).getPrimaryAccount();

        accountService.deposit("Основной", Double.parseDouble(amount), token);
        String balance = String.valueOf(primaryAccount.getAccountBalance());
        return new ResponseEntity<>(balance, HttpStatus.OK);
    }

    @PostMapping("/refillSavingsAccount")
    public ResponseEntity<String> refillSavingsAccount(@RequestHeader("Auth-token") String token,
                                                       @RequestHeader("amount") String amount) {
        SavingsAccount savingsAccount = userService.findByToken(token).getSavingsAccount();

        accountService.deposit("Сберегательный", Double.parseDouble(amount), token);
        String balance = String.valueOf(savingsAccount.getAccountBalance());
        return new ResponseEntity<>(balance, HttpStatus.OK);
    }

    @PostMapping("/debitPrimaryAccount")
    public ResponseEntity<String> debitPrimaryAccount(@RequestHeader("Auth-token") String token,
                                                      @RequestHeader("amount") String amount) {
        PrimaryAccount primaryAccount = userService.findByToken(token).getPrimaryAccount();

        accountService.withdraw("Основной", Double.parseDouble(amount), token);
        String balance = String.valueOf(primaryAccount.getAccountBalance());
        return new ResponseEntity<>(balance, HttpStatus.OK);
    }

    @PostMapping("/debitSavingsAccount")
    public ResponseEntity<String> debitSavingsAccount(@RequestHeader("Auth-token") String token,
                                                      @RequestHeader("amount") String amount) {
        SavingsAccount savingsAccount = userService.findByToken(token).getSavingsAccount();

        accountService.withdraw("Сберегательный", Double.parseDouble(amount), token);
        String balance = String.valueOf(savingsAccount.getAccountBalance());
        return new ResponseEntity<>(balance, HttpStatus.OK);
    }

    @PostMapping("/getBalance")
    public ResponseEntity<String> getBalance(@RequestHeader("Auth-token") String token,
                                             @RequestHeader("TypeAccount") String type) {
        String balance;
        if (type.equals("primary")) {
            PrimaryAccount primaryAccount = userService.findByToken(token).getPrimaryAccount();
            balance = String.valueOf(primaryAccount.getAccountBalance());
        } else {
            SavingsAccount savingsAccount = userService.findByToken(token).getSavingsAccount();
            balance = String.valueOf(savingsAccount.getAccountBalance());
        }
        return new ResponseEntity<>(balance, HttpStatus.OK);
    }
}
