package com.youmeal.marketplace.rest.dtos

data class OrderCreatedDTO(
    val orderId: Long,
    val userId: String
)