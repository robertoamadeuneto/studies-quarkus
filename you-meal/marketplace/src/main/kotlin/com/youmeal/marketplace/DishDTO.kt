package com.youmeal.marketplace

import java.math.BigDecimal

data class DishDTO(
    var id: Long,
    var name: String,
    var description: String,
    var price: BigDecimal
)
