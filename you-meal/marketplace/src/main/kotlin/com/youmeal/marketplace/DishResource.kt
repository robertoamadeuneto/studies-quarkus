package com.youmeal.marketplace

import io.smallrye.mutiny.Multi
import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType

@Path("/dishes")
class DishResource {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    fun hello(): Multi<DishDTO>? = null
}