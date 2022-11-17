package com.example.rabbitmq.rabbitmqconsumer.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CustomMessage {
    @JsonProperty("id")
    private int id;
    @JsonProperty("message")
    private String message;
    @JsonProperty("is_secret")
    private boolean isSecret;
}
