package com.example.rabbitmq.rabbitmqconsumer.configurations;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    public static final String ROUTING_A = "routing.A";

    public static final String ROUTING_B = "routing.B";
    public static final String QUEUE_A = "queue.A";
    public static final String QUEUE_B = "queue.B";
    public static final String EXCHANGE_DIRECT = "exchange.direct";
    public static final String EXCHANGE_FANOUT = "exchange.fanout";


    @Bean
    DirectExchange exchange(){
        return new DirectExchange(EXCHANGE_DIRECT);
    }

    @Bean
    FanoutExchange fanoutExchange(){return new FanoutExchange(EXCHANGE_FANOUT);}

    @Bean
    Queue queueA(){
         return new Queue(QUEUE_A,false);
    }

    @Bean
    Binding bindingForA(@Qualifier("queueA") Queue queue, DirectExchange exchange){
        return BindingBuilder.bind(queue)
                .to(exchange)
                .with(ROUTING_A);

    }

    @Bean
    Binding bindingForAFanOut(@Qualifier("queueA") Queue queue, FanoutExchange exchange){
        return BindingBuilder.bind(queue)
                .to(exchange);
    }

    @Bean
    Queue queueB(){
        return new Queue(QUEUE_B,false);
    }

    @Bean
    Binding bindingForB(@Qualifier("queueB") Queue queue, DirectExchange exchange){
        return BindingBuilder.bind(queue)
                .to(exchange)
                .with(ROUTING_B);

    }

    @Bean
    Binding bindingForBFanout(@Qualifier("queueB") Queue queue, FanoutExchange exchange){
        return BindingBuilder.bind(queue)
                .to(exchange);
    }

    @Bean
    MessageConverter converter(){
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    RabbitTemplate rabbitTemplate(ConnectionFactory factory){
       RabbitTemplate rabbitTemplate = new RabbitTemplate(factory);
       rabbitTemplate.setMessageConverter(converter());
       return rabbitTemplate;
    }

}
