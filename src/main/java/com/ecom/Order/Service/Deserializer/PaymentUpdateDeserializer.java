package com.ecom.Order.Service.Deserializer;


import com.ecom.Order.Service.Entity.PaymentUpdate;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.serialization.Deserializer;

import java.io.IOException;


public class PaymentUpdateDeserializer implements Deserializer<PaymentUpdate> {
    private final ObjectMapper objectMapper = new ObjectMapper();
    @Override
    public PaymentUpdate deserialize(String s, byte[] bytes) {
        try{
            return objectMapper.readValue(bytes, PaymentUpdate.class);
        }
        catch (IOException e){
            throw new RuntimeException("Failed to deserialize Order", e);
        }
    }
}
