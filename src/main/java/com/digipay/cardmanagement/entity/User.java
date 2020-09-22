package com.digipay.cardmanagement.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "digi_user")
public class User implements Serializable {
    @Id
//  @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = false)
    @SequenceGenerator(name = "digi_user_id_seq", sequenceName = "digi_user_id_seq",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "digi_user_id_seq")
    private Long id;

    @Column(unique = true)
    private String name;

    @NotNull(message = "null.phoneNumber")
    @Column(name = "phone_number", unique = true)
    private String phoneNumber;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @org.springframework.data.annotation.Transient
    private Set<Card> cards = new HashSet<>();

    public User() {
    }
    // region getterAndSetter

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

    public Set<Card> getCards() {
        return cards;
    }

    public User setCards(Set<Card> cards) {
        this.cards = cards;
        return this;
    }

    // endregion

    @Override
    public String toString() {
        return "User{"
                + "id="
                + id
                + ", name='"
                + name
                + '\''
                + ", phoneNumber='"
                + phoneNumber
                + '\''
                + '}';
    }
}
