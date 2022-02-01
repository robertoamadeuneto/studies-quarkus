package com.youmeal.marketplace.rest.dtos

import io.vertx.mutiny.sqlclient.Row
import java.math.BigDecimal

data class DishDTO(
    var id: Long,
    var name: String,
    var description: String,
    var price: BigDecimal
) {

    companion object {
        fun from(row: Row) =
            DishDTO(
                id = row.getLong("id"),
                name = row.getString("name"),
                description = row.getString("description"),
                price = row.getBigDecimal("price")
            )
    }
}
