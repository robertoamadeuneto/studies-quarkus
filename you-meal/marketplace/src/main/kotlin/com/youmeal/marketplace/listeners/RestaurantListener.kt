package com.youmeal.marketplace.listeners

import com.youmeal.marketplace.entities.Restaurant
import io.vertx.mutiny.pgclient.PgPool
import org.eclipse.microprofile.reactive.messaging.Incoming
import javax.enterprise.context.ApplicationScoped
import javax.inject.Inject
import javax.json.bind.JsonbBuilder

@ApplicationScoped
class RestaurantListener {

    @Inject
    lateinit var pgPool: PgPool

    @Incoming("restaurants")
    fun create(json: String) {
        val restaurant = JsonbBuilder.create().fromJson(json, Restaurant::class.java)
        restaurant.persist(pgPool)
    }
}
