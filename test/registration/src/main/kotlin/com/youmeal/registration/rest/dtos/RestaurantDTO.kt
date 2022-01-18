package com.youmeal.registration.rest.dtos

import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

class RestaurantDTO(
    @NotEmpty
    @NotNull
    var proprietary: String? = null,
    @Size(min = 3, max = 30)
    var name: String? = null,
    var number: String? = null,
    var location: LocationDTO? = null
)
