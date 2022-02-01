package com.youmeal.marketplace.entities

import io.vertx.mutiny.pgclient.PgPool
import io.vertx.mutiny.sqlclient.Tuple

data class Restaurant(
    var id: Long? = null,
    var name: String? = null,
    var location: Location? = null
) {
    fun persist(pgPool: PgPool) {
        if (location != null) {
            pgPool
                .preparedQuery("INSERT INTO location (id, latitude, longitude) VALUES ($1, $2, $3)")
                .execute(Tuple.of(location?.id, location?.latitude, location?.longitude))
                .await()
                .indefinitely()
        }

        pgPool
            .preparedQuery("INSERT INTO restaurant (id, name, location_id) VALUES ($1, $2, $3)")
            .execute(Tuple.of(id, name, location?.id))
            .await()
            .indefinitely()
    }
}
