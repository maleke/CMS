package com.digipay.cardmanagement.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "card")
public class Card implements Serializable {
  @Id
  //@GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id", updatable = false, nullable = false)
    @SequenceGenerator(name = "card_id_seq", sequenceName = "card_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "card_id_seq")
  private Long id;

  @Column(name = "card_number", unique = true)
  private String cardNumber;

  private String cvv2;

  @Column(name = "exp_date")
  private String expDate;

  private String pin;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "user_id", nullable = false)
  private User user;

  public Card() {}

  // region getterAndSetter

  public Long getId() {
    return id;
  }

  public Card setId(Long id) {
    this.id = id;
    return this;
  }

  public String getCardNumber() {
    return cardNumber;
  }

  public Card setCardNumber(String cardNumber) {
    this.cardNumber = cardNumber;
    return this;
  }

  public String getCvv2() {
    return cvv2;
  }

  public Card setCvv2(String cvv2) {
    this.cvv2 = cvv2;
    return this;
  }

  public String getExpDate() {
    return expDate;
  }

  public Card setExpDate(String expDate) {
    this.expDate = expDate;
    return this;
  }

  public String getPin() {
    return pin;
  }

  public Card setPin(String pin) {
    this.pin = pin;
    return this;
  }

  public User getUser() {
    return user;
  }

  public Card setUser(User user) {
    this.user = user;
    return this;
  }

  // endregion

  @Override
  public String toString() {
    return "Card{"
        + "id="
        + id
        + ", cardNumber='"
        + cardNumber
        + '\''
        + ", cvv2='"
        + cvv2
        + '\''
        + ", expDate='"
        + expDate
        + '\''
        + ", pin='"
        + pin
        + '\''
        + '}';
  }
}
