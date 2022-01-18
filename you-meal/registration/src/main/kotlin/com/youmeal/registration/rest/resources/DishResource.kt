package com.youmeal.registration.rest.resources

import com.youmeal.registration.jpa.DishEntity
import com.youmeal.registration.jpa.RestaurantEntity
import com.youmeal.registration.rest.dtos.DishDTO
import com.youmeal.registration.rest.mappers.DishMapper
import javax.transaction.Transactional
import javax.ws.rs.DELETE
import javax.ws.rs.GET
import javax.ws.rs.NotFoundException
import javax.ws.rs.POST
import javax.ws.rs.PUT
import javax.ws.rs.Path
import javax.ws.rs.PathParam
import javax.ws.rs.core.Response

@Path("/restaurants/{restaurantId}/dishes")
class DishResource {

    @GET
    fun listAll(@PathParam("restaurantId") restaurantId: Long) =
        RestaurantEntity.findById(restaurantId)
            ?.let { DishEntity.list("restaurant", it) }
            ?: throw NotFoundException()

    @POST
    @Transactional
    fun create(
        @PathParam("restaurantId") restaurantId: Long,
        dto: DishDTO
    ): Response {
        RestaurantEntity.findById(restaurantId)
            ?.let {
                val entity = DishMapper.INSTANCE.mapToEntity(dto)
                entity.restaurant = it
                entity.persist()
            }
            ?: throw NotFoundException()

        return Response
            .status(Response.Status.CREATED)
            .build()
    }

    @Path("/{id}")
    @PUT
    @Transactional
    fun update(
        @PathParam("restaurantId") restaurantId: Long,
        @PathParam("id") id: Long,
        dto: DishDTO
    ): Response {
        DishEntity.findById(id)
            ?.let {
                if (it.restaurant?.id != restaurantId) {
                    throw IllegalArgumentException()
                }

                it.name = dto.name
                it.description = dto.description
                it.price = dto.price
                it.persist()
            }
            ?: throw NotFoundException()

        return Response
            .status(Response.Status.OK)
            .build()
    }

    @Path("/{id}")
    @DELETE
    @Transactional
    fun delete(
        @PathParam("restaurantId") restaurantId: Long,
        @PathParam("id") id: Long
    ): Response {
        DishEntity.findById(id)
            ?.let {
                if (it.restaurant?.id != restaurantId) {
                    throw IllegalArgumentException()
                }
                it.delete()
            }
            ?: throw NotFoundException()

        return Response
            .status(Response.Status.NO_CONTENT)
            .build()
    }
}
