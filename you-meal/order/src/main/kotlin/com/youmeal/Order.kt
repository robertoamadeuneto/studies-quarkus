package com.youmeal

import io.quarkus.mongodb.panache.PanacheMongoEntity
import io.quarkus.mongodb.panache.common.MongoEntity

@MongoEntity(collection = "orders", database = "order")
data class Order(
    val clientId: String,
    val dishes: List<Dish>,
    val restaurant: Restaurant,
    val deliveryman: String,
    val deliverymanLocation: Location
) : PanacheMongoEntity()
