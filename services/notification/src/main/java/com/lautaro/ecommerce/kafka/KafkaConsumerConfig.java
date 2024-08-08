package com.lautaro.ecommerce.kafka;

import com.lautaro.ecommerce.kafka.order.OrderConfirmation;
import com.lautaro.ecommerce.kafka.payment.PaymentConfirmation;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.ErrorHandlingDeserializer;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableKafka
public class KafkaConsumerConfig {

    @Bean
    public ConsumerFactory<String, OrderConfirmation> orderConfirmationConsumerFactory() {
        JsonDeserializer<OrderConfirmation> jsonDeserializer = new JsonDeserializer<>(OrderConfirmation.class, false);
        jsonDeserializer.addTrustedPackages("*");
        jsonDeserializer.setRemoveTypeHeaders(false);
        jsonDeserializer.setUseTypeMapperForKey(false);

        ErrorHandlingDeserializer<String> errorHandlingStringDeserializer = new ErrorHandlingDeserializer<>(new StringDeserializer());
        ErrorHandlingDeserializer<OrderConfirmation> errorHandlingJsonDeserializer = new ErrorHandlingDeserializer<>(jsonDeserializer);

        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "orderGroup");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, errorHandlingStringDeserializer);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, errorHandlingJsonDeserializer);

        return new DefaultKafkaConsumerFactory<>(props, errorHandlingStringDeserializer, errorHandlingJsonDeserializer);
    }

    @Bean
    public ConsumerFactory<String, PaymentConfirmation> paymentConfirmationConsumerFactory() {
        JsonDeserializer<PaymentConfirmation> jsonDeserializer = new JsonDeserializer<>(PaymentConfirmation.class, false);
        jsonDeserializer.addTrustedPackages("*");
        jsonDeserializer.setRemoveTypeHeaders(false);
        jsonDeserializer.setUseTypeMapperForKey(false);

        ErrorHandlingDeserializer<String> errorHandlingStringDeserializer = new ErrorHandlingDeserializer<>(new StringDeserializer());
        ErrorHandlingDeserializer<PaymentConfirmation> errorHandlingJsonDeserializer = new ErrorHandlingDeserializer<>(jsonDeserializer);

        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "paymentGroup");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, errorHandlingStringDeserializer);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, errorHandlingJsonDeserializer);

        return new DefaultKafkaConsumerFactory<>(props, errorHandlingStringDeserializer, errorHandlingJsonDeserializer);
    }


    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, OrderConfirmation> orderConfirmationKafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, OrderConfirmation> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(orderConfirmationConsumerFactory());
        return factory;
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, PaymentConfirmation> paymentConfirmationKafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, PaymentConfirmation> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(paymentConfirmationConsumerFactory());
        return factory;
    }



}
