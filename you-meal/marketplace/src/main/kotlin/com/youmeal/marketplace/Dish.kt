package com.youmeal.marketplace

import java.math.BigDecimal

data class Dish(
    var id: Long,
    var name: String,
    var description: String,
    var restaurant: com.youmeal.marketplace.Restaurant,
    var price: BigDecimal
)
