package com.digipay.cardmanagement.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "digi_user")
public class User implements Serializable {
    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id",updatable = false, nullable = false)
    @SequenceGenerator(name = "digi_user_id_seq", sequenceName = "digi_user_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "digi_user_id_seq")
    private Long id;

    @Column(unique = true)
    private String name;

    @Column(name = "phone_number")
    private String phoneNumber;


    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @org.springframework.data.annotation.Transient
    private List<Card> cards = new ArrayList<>();

    public User() {
    }
    //region getterAndSetter

    public Long getId() {
        return id;
    }

    public User setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public User setName(String name) {
        this.name = name;
        return this;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public User setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public List<Card> getCards() {
        return cards;
    }

    public User setCards(List<Card> cards) {
        this.cards = cards;
        return this;
    }
    //endregion

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}
