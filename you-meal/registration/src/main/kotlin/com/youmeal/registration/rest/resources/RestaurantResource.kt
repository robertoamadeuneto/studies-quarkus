package com.youmeal.registration.rest.resources

import com.youmeal.registration.jpa.RestaurantEntity
import com.youmeal.registration.rest.dtos.RestaurantDTO
import com.youmeal.registration.rest.mappers.RestaurantMapper
import org.eclipse.microprofile.metrics.annotation.Counted
import org.eclipse.microprofile.metrics.annotation.SimplyTimed
import org.eclipse.microprofile.metrics.annotation.Timed
import org.eclipse.microprofile.openapi.annotations.enums.SecuritySchemeType
import org.eclipse.microprofile.openapi.annotations.security.OAuthFlow
import org.eclipse.microprofile.openapi.annotations.security.OAuthFlows
import org.eclipse.microprofile.openapi.annotations.security.SecurityScheme
import org.eclipse.microprofile.reactive.messaging.Channel
import org.eclipse.microprofile.reactive.messaging.Emitter
import javax.annotation.security.RolesAllowed
import javax.inject.Inject
import javax.json.bind.JsonbBuilder
import javax.transaction.Transactional
import javax.validation.Valid
import javax.ws.rs.DELETE
import javax.ws.rs.GET
import javax.ws.rs.NotFoundException
import javax.ws.rs.POST
import javax.ws.rs.PUT
import javax.ws.rs.Path
import javax.ws.rs.PathParam
import javax.ws.rs.core.Response

@Path("/restaurants")
@RolesAllowed("proprietary")
@SecurityScheme(
    securitySchemeName = "you-meal_oauth2",
    type = SecuritySchemeType.OAUTH2,
    flows = OAuthFlows(
        password = OAuthFlow(
            tokenUrl = "http://localhost:8180/auth/realms/you-meal/protocol/openid-connect/token"
        )
    )
)
class RestaurantResource {

    @Inject
    @Channel("restaurants")
    lateinit var emitter: Emitter<String>

    @GET
    @Counted(name = "RestaurantResource.listAll() quantity")
    @SimplyTimed(name = "RestaurantResource.listAll() simple search time")
    @Timed(name = "RestaurantResource.listAll() complete search time")
    fun listAll() = RestaurantEntity.listAll()

    @POST
    @Transactional
    fun create(@Valid dto: RestaurantDTO): Response {
        val entity = RestaurantMapper.INSTANCE.mapToEntity(dto)
        entity.persist()

        emitter.send(JsonbBuilder.create().toJson(entity))

        return Response
            .status(Response.Status.CREATED)
            .build()
    }

    @Path("/{id}")
    @PUT
    @Transactional
    fun update(
        @PathParam("id") id: Long,
        @Valid dto: RestaurantDTO
    ): Response {
        RestaurantEntity.findById(id)
            ?.let {
                it.name = dto.name
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
    fun delete(@PathParam("id") id: Long): Response {
        RestaurantEntity.findById(id)
            ?.delete()
            ?: throw NotFoundException()

        return Response
            .status(Response.Status.NO_CONTENT)
            .build()
    }
}
