package com.example.rabbitmq.rabbitmqconsumer.utils;

import com.example.rabbitmq.rabbitmqconsumer.models.CustomMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import static com.example.rabbitmq.rabbitmqconsumer.configurations.RabbitConfig.QUEUE_A;
import static com.example.rabbitmq.rabbitmqconsumer.configurations.RabbitConfig.QUEUE_B;

@Component
@Slf4j
public class Consumer {

    @RabbitListener(queues = QUEUE_A)
    public void receiveFromQueueA(CustomMessage message){
      log.info("Message Received from Queue A {}",message);
    }

    @RabbitListener(queues = QUEUE_B)
    public void receiveFromQueueB(CustomMessage message){
        log.info("Message Received from Queue B {}",message);
    }
}
