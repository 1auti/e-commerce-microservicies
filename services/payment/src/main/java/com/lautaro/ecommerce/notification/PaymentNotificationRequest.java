package com.lautaro.ecommerce.notification;

import com.lautaro.ecommerce.payment.PaymentMethod;
import java.math.BigDecimal;

public record PaymentNotificationRequest(
        String orderReference,
        BigDecimal amount,
        PaymentMethod paymentMethod,
        String customerFirstname,
        String customerLastname,
        String customerEmail
) {
}
