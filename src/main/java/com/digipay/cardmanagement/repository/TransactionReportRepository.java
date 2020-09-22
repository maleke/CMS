package com.digipay.cardmanagement.repository;

import com.digipay.cardmanagement.dto.TransactionLogDto;
import com.digipay.cardmanagement.entity.TransactionLog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionReportRepository extends JpaRepository<TransactionLog, Long>, JpaSpecificationExecutor {
  @Query(
      "SELECT new com.digipay.cardmanagement.dto.TransactionLogDto("
          + "(t.source),"
          + "(sum(t.successStatus)),"
          + "(sum(t.failStatus))"
          + ")"
          + "from TransactionLog t "
          + "where t.transactionDate between ?1 and ?2"
          + " group by t.source")
  List<TransactionLogDto> getTransactionReport(String startDateTime, String endDateTime, PageRequest pageRequest);

}
