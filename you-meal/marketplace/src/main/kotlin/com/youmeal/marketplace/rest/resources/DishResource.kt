package com.youmeal.marketplace.rest.resources

import com.youmeal.marketplace.entities.Dish
import com.youmeal.marketplace.rest.dtos.DishDTO
import io.smallrye.mutiny.Multi
import io.vertx.mutiny.pgclient.PgPool
import javax.inject.Inject
import javax.ws.rs.GET
import javax.ws.rs.Path

@Path("/dishes")
class DishResource {

    @Inject
    lateinit var pgPool: PgPool

    @GET
    fun findAll(): Multi<DishDTO> = Dish.findAll(pgPool)
}
