package ru.itpark.dao;

import org.springframework.data.repository.CrudRepository;
import ru.itpark.models.PrimaryAccount;

public interface PrimaryAccountDao extends CrudRepository<PrimaryAccount, Long> {

    PrimaryAccount findByAccountNumber(int accountNumber);
}
