package com.youmeal.registration.jpa

import io.quarkus.hibernate.orm.panache.kotlin.PanacheCompanion
import io.quarkus.hibernate.orm.panache.kotlin.PanacheEntityBase
import java.math.BigDecimal
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.ManyToOne
import javax.persistence.Table

@Entity
@Table(name = "dish")
data class DishEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long?,
    var name: String?,
    var description: String?,
    @ManyToOne
    var restaurant: RestaurantEntity?,
    var price: BigDecimal?
) : PanacheEntityBase {
    companion object : PanacheCompanion<DishEntity>
}
