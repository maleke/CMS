package com.digipay.cardmanagement;

import com.digipay.cardmanagement.web.rest.CardController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
public class SmokeTest {

    @Autowired
    private CardController cardController;

    @Test
    public void contextLoads() {
        assertThat(cardController).isNotNull();
    }
}
