package com.springhow.examples.drools;

import com.springhow.examples.drools.domain.OrderRequest;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DecisionController {


    private final KieContainer kieContainer;

    public DecisionController(KieContainer kieContainer) {
        this.kieContainer = kieContainer;
    }

    @PostMapping("/discount")
    private OrderRequest getDiscountPercent(@RequestBody OrderRequest orderRequest) {
        KieSession kieSession = kieContainer.newKieSession();
        kieSession.insert(orderRequest);
        kieSession.fireAllRules();
        kieSession.dispose();
        return orderRequest;
    }
}
