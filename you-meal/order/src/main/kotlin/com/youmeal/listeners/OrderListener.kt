package com.youmeal.listeners

import org.eclipse.microprofile.reactive.messaging.Incoming
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class RestaurantListener {

    @Incoming("orders")
    fun create(json: String) {
        println(json)
    }
}
