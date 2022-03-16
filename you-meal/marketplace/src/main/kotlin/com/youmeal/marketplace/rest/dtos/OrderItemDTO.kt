package com.youmeal.marketplace.rest.dtos

import java.math.BigDecimal

data class OrderItemDTO(
    var name: String,
    var description: String,
    var price: BigDecimal
)
