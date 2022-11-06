package be.kdg.sa.velo.services;

import be.kdg.sa.velo.VeloApplicationTests;
import be.kdg.sa.velo.domain.subscriptions.Subscription;
import be.kdg.sa.velo.domain.subscriptions.SubscriptionType;
import be.kdg.sa.velo.exceptions.SubscriptionNotFoundException;
import be.kdg.sa.velo.repositories.SubscriptionRepository;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;

class SubscriptionUnitServiceTest extends VeloApplicationTests {

    @Autowired
    private SubscriptionService subscriptionService;

    @MockBean
    private SubscriptionRepository subscriptionRepository;

    @BeforeAll
    void setup() {
        //subscription mocken
        var subscription1 = new Subscription();
        subscription1.setStartDate(LocalDate.now().minusMonths(5));
        subscription1.setId(1);
        subscription1.setSubscriptionType(new SubscriptionType("JAAR"));
        var subscription2 = new Subscription();
        subscription2.setStartDate(LocalDate.now().minusDays(10));
        subscription2.setId(2);
        subscription2.setSubscriptionType(new SubscriptionType("MAAND"));
        var subscription3 = new Subscription();
        subscription3.setStartDate(LocalDate.now().minusDays(10));
        subscription3.setId(3);
        subscription3.setSubscriptionType(new SubscriptionType("DAG"));

        given(subscriptionRepository.getActiveSubscriptionsByUserId(1)).willReturn(List.of(subscription1, subscription2));
        given(subscriptionRepository.getActiveSubscriptionsByUserId(3)).willReturn(List.of(subscription3));
    }

    @Test
    void testSubscriptions() {
        var subscription = subscriptionService.getCurrentUserSubscription(1);
        assertEquals(2, subscription.getId());

        assertThrows(SubscriptionNotFoundException.class,()->subscriptionService.getCurrentUserSubscription(3));

    }
}