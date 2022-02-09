package com.youmeal

import org.bson.types.Decimal128

data class Dish(
    val name: String,
    val description: String,
    val price: Decimal128
)
