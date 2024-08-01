package com.lautaro.ecommerce.kafka;

import com.lautaro.ecommerce.customer.CustomerResponse;
import com.lautaro.ecommerce.order.PaymentMethod;
import com.lautaro.ecommerce.product.PurchaseResponse;

import java.math.BigDecimal;
import java.util.List;

public record OrderConfirmation (
        String orderReference,
        BigDecimal totalAmount,
        PaymentMethod paymentMethod,
        CustomerResponse customer,
        List<PurchaseResponse> products

) {
}
