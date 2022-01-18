package com.youmeal.registration.rest.mappers

import com.youmeal.registration.jpa.RestaurantEntity
import com.youmeal.registration.rest.dtos.RestaurantDTO
import org.mapstruct.Mapper
import org.mapstruct.ReportingPolicy
import org.mapstruct.factory.Mappers

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
interface RestaurantMapper {

    companion object {
        val INSTANCE: RestaurantMapper = Mappers.getMapper(RestaurantMapper::class.java)
    }

    fun mapToQuery(entity: RestaurantEntity): RestaurantDTO

    fun mapToEntity(dto: RestaurantDTO): RestaurantEntity
}
