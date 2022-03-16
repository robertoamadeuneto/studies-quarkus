package com.youmeal.marketplace.entities

import io.smallrye.mutiny.Uni
import io.vertx.mutiny.pgclient.PgPool
import io.vertx.mutiny.sqlclient.Row
import io.vertx.mutiny.sqlclient.Tuple

data class CartItem(
    var userId: String,
    var dishId: Long
) {
    companion object {
        fun find(pgPool: PgPool, userId: String): Uni<List<CartItem>> {
            return pgPool
                .preparedQuery(
                    "   SELECT * " +
                        " FROM cart_item" +
                        "WHERE user_id = $1"
                )
                .execute(Tuple.of(userId))
                .map { rowSet -> rowSet.map { mapToCartItem(it) } }
        }

        fun save(pgPool: PgPool, userId: String, dishId: Long): Uni<Long> {
            return pgPool
                .preparedQuery("INSERT INTO cart_item (user_id, dish_id) VALUES ($1, $2) RETURNING (user_id)")
                .execute(Tuple.of(userId, dishId))
                .map { it.iterator().next().getLong("user_id") }
        }

        private fun mapToCartItem(row: Row) =
            CartItem(
                userId = row.getString("user_id"),
                dishId = row.getLong("dish_id")
            )
    }
}
