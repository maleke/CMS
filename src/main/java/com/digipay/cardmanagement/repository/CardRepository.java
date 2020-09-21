package com.digipay.cardmanagement.repository;

import com.digipay.cardmanagement.entity.Card;
import com.digipay.cardmanagement.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CardRepository extends JpaRepository<Card, Long> {
  List<Card> findByUserId(@Param("userId") Long userId);

  @Query("select c.user from Card c where (c.cardNumber = :cardNumber)")

  Optional<User> findUserByCardNumber(@Param("cardNumber") String cardNumber);

  String findPinByCardNumber(@Param("cardNumber") String cardNumber);
}
