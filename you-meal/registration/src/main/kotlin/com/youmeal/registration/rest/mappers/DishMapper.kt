package com.youmeal.registration.rest.mappers

import com.youmeal.registration.jpa.DishEntity
import com.youmeal.registration.rest.dtos.DishDTO
import org.mapstruct.Mapper
import org.mapstruct.ReportingPolicy
import org.mapstruct.factory.Mappers

@Mapper(
    unmappedTargetPolicy = ReportingPolicy.IGNORE,
    uses = [RestaurantMapper::class]
)
interface DishMapper {

    companion object {
        val INSTANCE: DishMapper = Mappers.getMapper(DishMapper::class.java)
    }

    fun mapToDTO(entity: DishEntity): DishDTO

    fun mapToEntity(dto: DishDTO): DishEntity
}
