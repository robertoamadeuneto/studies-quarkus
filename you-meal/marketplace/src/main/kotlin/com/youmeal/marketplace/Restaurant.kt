package com.youmeal.marketplace

data class Restaurant(
    var id: Long,
    var name: String,
    var location: com.youmeal.marketplace.Location
)
