package com.youmeal.marketplace.entities

import com.youmeal.marketplace.rest.dtos.DishDTO
import io.smallrye.mutiny.Multi
import io.smallrye.mutiny.Uni
import io.vertx.mutiny.pgclient.PgPool
import io.vertx.mutiny.sqlclient.Row
import io.vertx.mutiny.sqlclient.RowSet
import io.vertx.mutiny.sqlclient.Tuple
import java.math.BigDecimal
import java.util.stream.StreamSupport

data class Dish(
    var id: Long,
    var name: String,
    var description: String,
    var restaurant: Restaurant,
    var price: BigDecimal
) {

    companion object {
        fun findAll(pgPool: PgPool): Multi<DishDTO> {
            val query = pgPool
                .query(
                    "  SELECT * " +
                        "FROM dish"
                )
                .execute()

            return convertToMulti(query)
        }

        fun findAll(pgPool: PgPool, restaurantId: Long): Multi<DishDTO> {
            val query = pgPool
                .preparedQuery(
                    "   SELECT * " +
                        " FROM dish " +
                        "WHERE dish.restaurant_id = $1"
                )
                .execute(Tuple.of(restaurantId));

            return convertToMulti(query)
        }

        private fun convertToMulti(query: Uni<RowSet<Row>>): Multi<DishDTO> {
            return query
                .onItem()
                .transformToMulti { Multi.createFrom().items { StreamSupport.stream(it.spliterator(), false) } }
                .onItem()
                .transform(DishDTO::from)
        }
    }
}
