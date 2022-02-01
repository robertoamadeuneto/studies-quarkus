package com.youmeal.marketplace.rest.resources

import com.youmeal.marketplace.entities.Dish
import com.youmeal.marketplace.rest.dtos.DishDTO
import io.smallrye.mutiny.Multi
import io.vertx.mutiny.pgclient.PgPool
import javax.inject.Inject
import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.PathParam

@Path("/restaurants")
class RestaurantResource {

    @Inject
    lateinit var pgPool: PgPool

    @GET
    @Path("/{restaurantId}/dishes")
    fun findDishes(@PathParam("restaurantId") restaurantId: Long): Multi<DishDTO> = Dish.findAll(pgPool, restaurantId)
}
