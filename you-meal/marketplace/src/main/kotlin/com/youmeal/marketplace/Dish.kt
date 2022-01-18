package com.youmeal.marketplace

import java.math.BigDecimal

data class Dish(
    var id: Long,
    var name: String,
    var description: String,
    var restaurant: Restaurant,
    var price: BigDecimal
)
