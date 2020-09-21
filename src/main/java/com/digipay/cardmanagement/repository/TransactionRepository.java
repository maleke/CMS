package com.digipay.cardmanagement.repository;

import com.digipay.cardmanagement.entity.TransactionLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<TransactionLog, Long> {
}
