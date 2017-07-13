package ru.itpark.service;

import ru.itpark.models.PrimaryAccount;
import ru.itpark.models.SavingsAccount;

import java.security.Principal;

public interface AccountService {

    PrimaryAccount createPrimaryAccount();
    SavingsAccount createSavingsAccount();
    void deposit(String accountType, double amount, String token);
    void withdraw(String accountType, double amount, String token);

}
