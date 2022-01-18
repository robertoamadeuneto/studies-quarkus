package com.youmeal.registration.jpa

import io.quarkus.hibernate.orm.panache.kotlin.PanacheCompanion
import io.quarkus.hibernate.orm.panache.kotlin.PanacheEntityBase
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.OffsetDateTime
import javax.persistence.CascadeType
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.OneToOne
import javax.persistence.Table

@Entity
@Table(name = "restaurant")
data class RestaurantEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long?,
    var proprietary: String?,
    @Column(name = "name")
    var name: String?,
    var number: String?,
    @OneToOne(cascade = [CascadeType.ALL])
    var location: LocationEntity?,
    @CreationTimestamp
    @Column(name = "creation_date")
    var creationDate: OffsetDateTime?,
    @UpdateTimestamp
    @Column(name = "update_date")
    var updateDate: OffsetDateTime?
) : PanacheEntityBase {
    companion object : PanacheCompanion<RestaurantEntity>
}
