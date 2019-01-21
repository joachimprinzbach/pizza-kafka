package com.pizza.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaOperations;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/pizzas")
public class PizzaRestController {

    public static final String MYTOPIC_0 = "myTopic-0";
    private final @NonNull KafkaOperations<Object, Object> kafka;

    @Autowired
    public PizzaRestController(KafkaOperations<Object, Object> kafka) {
        this.kafka = kafka;
    }

    @GetMapping
    public List<PizzaDto> getAll() {
        kafka.send(MYTOPIC_0, "msg");
        return Arrays.asList(new PizzaDto("Funghi"));
    }

    @KafkaListener(topics = MYTOPIC_0)
    public void onMessageSend(Object message) {
        System.out.println("ADDED: " + message);
    }

}
