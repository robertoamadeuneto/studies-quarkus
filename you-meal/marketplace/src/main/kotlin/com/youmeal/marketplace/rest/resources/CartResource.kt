package com.youmeal.marketplace.rest.resources

import com.youmeal.marketplace.entities.CartItem
import com.youmeal.marketplace.rest.dtos.OrderCreatedDTO
import io.smallrye.mutiny.Uni
import io.vertx.mutiny.pgclient.PgPool
import org.eclipse.microprofile.reactive.messaging.Channel
import org.eclipse.microprofile.reactive.messaging.Emitter
import javax.inject.Inject
import javax.ws.rs.GET
import javax.ws.rs.POST
import javax.ws.rs.Path
import javax.ws.rs.PathParam

@Path("/carts")
class CartResource {

    @Inject
    lateinit var pgPool: PgPool

    @Inject
    @Channel("orders")
    lateinit var emitter: Emitter<OrderCreatedDTO>

    // This was hard coded since this code is only from studying purposes.
    private val userId = "temp"

    @GET
    fun find(): Uni<List<CartItem>> = CartItem.find(pgPool, userId)

    @POST
    @Path("/dish/{dishId}")
    fun addDish(@PathParam("dishId") dishId: Long): Uni<Long> {
        return CartItem.save(pgPool, userId, dishId)
    }

    @POST
    @Path("/finalization")
    fun finalize() {
        val orderCreatedDTO = OrderCreatedDTO(
            orderId = 1,
            userId = userId
        )

        emitter.send(orderCreatedDTO)
    }
}
