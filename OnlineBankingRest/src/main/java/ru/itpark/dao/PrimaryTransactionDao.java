package ru.itpark.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itpark.models.PrimaryTransaction;

public interface PrimaryTransactionDao extends JpaRepository<PrimaryTransaction, Long> {
}
