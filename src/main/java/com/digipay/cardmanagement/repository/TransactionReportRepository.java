package com.digipay.cardmanagement.repository;

import com.digipay.cardmanagement.dto.TransactionLogDto;
import com.digipay.cardmanagement.entity.TransactionLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionReportRepository extends JpaRepository<TransactionLog, Long> {
  @Query(
      "SELECT new com.digipay.cardmanagement.dto.TransactionLogDto("
          + "(t.source),"
          + "(t.transactionStatus),"
          + "count (t.source)"
          + ")"
          + "from TransactionLog t "
          + "where t.transactionDate between ?1 and ?2"
          + " group by t.source,t.transactionStatus")
  // Page<TransactionLogDto> findAllByTransactionDate(String startDateTime, String endDateTime,
  // PageRequest pageRequest);
  List<TransactionLogDto> findAllByTransactionDate(String startDateTime, String endDateTime);
}
