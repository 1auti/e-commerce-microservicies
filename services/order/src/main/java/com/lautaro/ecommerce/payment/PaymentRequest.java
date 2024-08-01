package com.lautaro.ecommerce.payment;

import com.lautaro.ecommerce.customer.CustomerResponse;
import com.lautaro.ecommerce.order.PaymentMethod;

import java.math.BigDecimal;

public record PaymentRequest(
        BigDecimal amount,
        PaymentMethod paymentMethod,
        Integer orderId,
        String orderReference,
        CustomerResponse customer
) {
}
