package com.digipay.cardmanagement.repository;

import com.digipay.cardmanagement.entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CardRepository extends JpaRepository<Card, Long> {
  List<Card> findByUserId(@Param("userId") Long userId);

  Long findBycardNumber(@Param("cardNumber") String cardNumber);
}
