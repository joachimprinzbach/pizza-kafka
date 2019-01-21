package com.pizza.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

    @Value("${cloudkarafka.topic}")
    private String topic;

    private final @NonNull KafkaOperations<Object, Object> kafka;

    @Autowired
    public PizzaRestController(KafkaOperations<Object, Object> kafka) {
        this.kafka = kafka;
    }

    @GetMapping
    public List<PizzaDto> getAll() {
        kafka.send(topic, "msg");
        return Arrays.asList(new PizzaDto("Funghi"));
    }

    @KafkaListener(topics = "${cloudkarafka.topic}")
    public void onMessageSend(Object message) {
        System.out.println("ADDED: " + message);
    }

}
